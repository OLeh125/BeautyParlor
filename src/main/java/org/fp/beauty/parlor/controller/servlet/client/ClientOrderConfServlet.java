package org.fp.beauty.parlor.controller.servlet.client;

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

@WebServlet("/client_order/conf")
public class ClientOrderConfServlet extends HttpServlet {
    private MasterDao masterDao;
    private OrderDao orderDao;

    @Override
    public void init() throws ServletException {
        masterDao = new MasterDaoImpl();
        orderDao = new OrderDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("master") == null ||
                session.getAttribute("treatment") == null ||
                session.getAttribute("orderDate") == null ||
                session.getAttribute("orderTime") == null ){
            resp.sendRedirect(req.getContextPath()+"/client_order");
        }else{
            String tempMaster = session.getAttribute("master").toString();
            req.setAttribute("masterName",tempMaster.substring(0,tempMaster.lastIndexOf(' ')));
            req.setAttribute("masterSurname",tempMaster.substring(tempMaster.lastIndexOf(' ')+1));
            String tempTreatment = session.getAttribute("treatment").toString();
            req.setAttribute("treatmentName",tempTreatment.substring(0,tempTreatment.lastIndexOf(' ')));
            req.setAttribute("treatmentPrice",tempTreatment.substring(tempTreatment.lastIndexOf(' ')+1));
            req.setAttribute("date",session.getAttribute("orderDate"));
            req.setAttribute(   "time",session.getAttribute("orderTime"));
            //TODO check null

            session.removeAttribute("master");
            session.removeAttribute("treatment");
            session.removeAttribute("orderDate");
            session.removeAttribute("orderTime");
            req.getServletContext().getRequestDispatcher("/view/client_order_conf.jsp").forward(req,resp);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/client_order");
    }
}
