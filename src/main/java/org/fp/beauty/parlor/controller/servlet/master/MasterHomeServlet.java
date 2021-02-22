package org.fp.beauty.parlor.controller.servlet.master;

import org.fp.beauty.parlor.model.dao.MasterDao;
import org.fp.beauty.parlor.model.dao.MasterDaoImpl;
import org.fp.beauty.parlor.model.dao.OrderDao;
import org.fp.beauty.parlor.model.dao.OrderDaoImpl;
import org.fp.beauty.parlor.model.entity.Master;
import org.fp.beauty.parlor.model.entity.Order;
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
import java.util.*;

@WebServlet("/master_home")
public class MasterHomeServlet extends HttpServlet {
    private OrderDao orderDao;
    private MasterDao masterDao;
    @Override
    public void init() throws ServletException {
        masterDao = new MasterDaoImpl();
        orderDao = new OrderDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        String date = LocalDate.now().toString();
        if (session.getAttribute("orderDate") != null && !"".equals(session.getAttribute("orderDate"))) {
            date = LocalDate.parse(session.getAttribute("orderDate").toString()).toString();
            req.setAttribute("orderDay", date);
        }
        Master master = (Master) session.getAttribute("master");
        int masterId = masterDao.findMastersIdByNameAndSurname(master.getNameEn(), master.getSurnameEn());


        Map<LocalTime,Order> schedule  = new TreeMap<>();
        List<LocalTime> workHours = orderDao.WORK_HOURS;
        for (Order order:orderDao.findAllByDateAndMasterIdForMaster(LocalDate.parse(date),masterId)) {
            if (workHours.contains(order.getTreatmentExecutionTime())) {
                schedule.put(workHours.get(workHours.indexOf(order.getTreatmentExecutionTime())),order);
            }
        }
        for (LocalTime time : workHours) {
            if (!schedule.containsKey(time))
                schedule.put(time, null);
        }
        req.setAttribute("schedule",schedule);
        req.setAttribute("send", true);
        req.setAttribute("currentDate",LocalDate.now().toString());
        req.setAttribute("orderDay", date);
        session.setAttribute("orderDate", date);
        req.getServletContext().getRequestDispatcher("/view/master_home.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession s = req.getSession();
        if (req.getParameter("orderDate") == null || "".equals(req.getParameter("orderDate"))){
            s.setAttribute("orderDate",s.getAttribute("orderDate"));
        }else {
            s.setAttribute("orderDate",req.getParameter("orderDate"));
        }



        if (req.getParameter("isDone") != null){
            if ("done".equalsIgnoreCase(req.getParameter("isDone")) || "зроблено".equalsIgnoreCase(req.getParameter("isDone"))){
                Master master = (Master) s.getAttribute("master");
                int masterId = masterDao.findMastersIdByNameAndSurname(master.getNameEn(), master.getSurnameEn());
                LocalDate date = LocalDate.parse(req.getParameter("date"));
                LocalTime time = LocalTime.parse(req.getParameter("time"));
                int id  = orderDao.findIdByMasterIdDateAndTime(masterId,date,time);

                orderDao.updateStatusById(OrderStatus.DONE, id);
            }
        }

        resp.sendRedirect(req.getContextPath() + "/master_home");
    }
}
