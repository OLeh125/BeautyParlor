package org.fp.beauty.parlor.controller.servlet.client;

import org.fp.beauty.parlor.model.dao.*;
import org.fp.beauty.parlor.model.entity.Client;
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

@WebServlet("/client_order/orders")
public class ClientOrdersServlet extends HttpServlet {
    private OrderDao orderDao;
    private ClientDao clientDao;
    private MasterDao masterDao;

    @Override
    public void init() throws ServletException {
        orderDao = new OrderDaoImpl();
        clientDao = new ClientDaoImpl();
        masterDao = new MasterDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        Client client = (Client)session.getAttribute("client");
        int clientId = clientDao.findClientIdByNameSurnameAndPassword(client.getNameEn(),client.getSurnameEn(),client.getPassword());
        req.setAttribute("orders",orderDao.findAllOrdersWithMastersAndTreatmentsByClientId(clientId));
        req.getServletContext().getRequestDispatcher("/view/client_orders.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("isPaid") != null){
            if ("pay".equalsIgnoreCase(req.getParameter("isPaid")) || "Заплатити".equalsIgnoreCase(req.getParameter("isPaid")) ){
                String masterTemp = req.getParameter("masterNameAndSurname");
                int masterId = masterDao.findMastersIdByNameAndSurname(masterTemp
                        .substring(0,masterTemp.lastIndexOf(' ')), masterTemp.substring(masterTemp.lastIndexOf(' ')+1));
                LocalDate date = LocalDate.parse(req.getParameter("date"));
                LocalTime time = LocalTime.parse(req.getParameter("time"));
                orderDao.updateStatusById(OrderStatus.PAID, orderDao.findIdByMasterIdDateAndTime(masterId,date,time));
            }
        }

        resp.sendRedirect(req.getContextPath() + "/client_order/orders");
    }
}
