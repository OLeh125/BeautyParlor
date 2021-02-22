package org.fp.beauty.parlor.controller.servlet.admin;

import org.fp.beauty.parlor.model.dao.MasterDao;
import org.fp.beauty.parlor.model.dao.MasterDaoImpl;
import org.fp.beauty.parlor.model.dao.OrderDao;
import org.fp.beauty.parlor.model.dao.OrderDaoImpl;
import org.fp.beauty.parlor.service.OrderStatus;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    //TODO it
    private OrderDao orderDao;
    private MasterDao masterDao;
    @Override
    public void init() throws ServletException {
        orderDao = new OrderDaoImpl();
        masterDao = new MasterDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        String date = LocalDate.now().toString();
        if (session.getAttribute("orderDate") != null && !"".equals(session.getAttribute("orderDate"))) {
            date = LocalDate.parse(session.getAttribute("orderDate").toString()).toString();
            req.setAttribute("orderDay", date);
        }
        req.setAttribute("orders",orderDao.findAllByDate(LocalDate.parse(date)));
        req.setAttribute("send", true);
        req.setAttribute("orderDay", date);
        session.setAttribute("orderDate", date);
        if (session.getAttribute("freeTime") != null && session.getAttribute("isEdit") != null) {
            req.setAttribute("freeTime", session.getAttribute("freeTime"));
            req.setAttribute("isEdit", session.getAttribute("isEdit"));
            req.setAttribute("orderId" ,session.getAttribute("orderId"));
        }
        req.getServletContext().getRequestDispatcher("/view/admin_home.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession s = req.getSession();
        if (req.getParameter("orderDate") == null || "".equals(req.getParameter("orderDate"))){
            s.setAttribute("orderDate",s.getAttribute("orderDate"));
        }else {
            s.setAttribute("orderDate",req.getParameter("orderDate"));
        }


        if (req.getParameter("isConfirmed") != null){
            if ("confirm".equalsIgnoreCase(req.getParameter("isConfirmed")) || "підтвердити".equalsIgnoreCase(req.getParameter("isConfirmed"))){
                String masterTemp = req.getParameter("masterNameAndSurname");
                int masterId = masterDao.findMastersIdByNameAndSurname(masterTemp
                        .substring(0,masterTemp.lastIndexOf(' ')), masterTemp.substring(masterTemp.lastIndexOf(' ')+1));
                LocalDate date = LocalDate.parse(req.getParameter("date"));
                LocalTime time = LocalTime.parse(req.getParameter("time"));
                orderDao.updateStatusById(OrderStatus.CONFIRMED, orderDao.findIdByMasterIdDateAndTime(masterId,date,time));
            }
        }

        if (req.getParameter("isCanceled") != null){
            if ("cancel".equalsIgnoreCase(req.getParameter("isCanceled")) || "відмінити".equalsIgnoreCase(req.getParameter("isCanceled"))){
                String masterTemp = req.getParameter("masterNameAndSurname");
                int masterId = masterDao.findMastersIdByNameAndSurname(masterTemp
                        .substring(0,masterTemp.lastIndexOf(' ')), masterTemp.substring(masterTemp.lastIndexOf(' ')+1));
                LocalDate date = LocalDate.parse(req.getParameter("date"));
                LocalTime time = LocalTime.parse(req.getParameter("time"));
                orderDao.updateStatusById(OrderStatus.CANCELED, orderDao.findIdByMasterIdDateAndTime(masterId,date,time));
            }
        }


        if (req.getParameter("chosenTime") != null){
            orderDao.setTimeById(LocalTime.parse(req.getParameter("chosenTime"))
                    ,Integer.parseInt(req.getParameter("orderId")));
            s.setAttribute("orderId",-1);
        }
        if (req.getParameter("isEdit") != null){
            if ("edit".equalsIgnoreCase(req.getParameter("isEdit")) || "редагувати".equalsIgnoreCase(req.getParameter("isEdit"))){
                String masterTemp = req.getParameter("masterNameAndSurname");
                int masterId = masterDao.findMastersIdByNameAndSurname(masterTemp
                        .substring(0,masterTemp.lastIndexOf(' ')), masterTemp.substring(masterTemp.lastIndexOf(' ')+1));
                LocalDate date = LocalDate.parse(req.getParameter("date"));

                s.setAttribute("isEdit",true);

                s.setAttribute("orderId",req.getParameter("orderId"));
                s.setAttribute("freeTime", orderDao.findFreeTimeByDateMasterAndTreatment(masterId,date));
                s.setAttribute("isEdit",false);
            }
        }

        resp.sendRedirect(req.getContextPath() + "/admin");
    }
}
