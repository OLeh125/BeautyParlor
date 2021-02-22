package org.fp.beauty.parlor.controller.servlet.client;


import org.fp.beauty.parlor.model.dao.*;
import org.fp.beauty.parlor.model.entity.Client;
import org.fp.beauty.parlor.model.entity.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

@WebServlet("/client_order")
public class ClientOrderServlet extends HttpServlet {
    private MasterDao masterDao;
    private TreatmentDao treatmentDao;
    private OrderDao orderDao;
    private ClientDao clientDao;

    @Override
    public void init() throws ServletException {
        masterDao = new MasterDaoImpl();
        treatmentDao = new TreatmentDaoImpl();
        orderDao = new OrderDaoImpl();
        clientDao = new ClientDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        String date = LocalDate.now().toString();
        if (session.getAttribute("orderDate") != null && !"".equals(session.getAttribute("orderDate"))) {
            date = LocalDate.parse(session.getAttribute("orderDate").toString()).toString();
            req.setAttribute("orderDay", date);
        }
        if (session.getAttribute("treatmentChanged") != null) {
            if (session.getAttribute("treatmentChanged").toString().equals("true")) {
                String tempTreatment = session.getAttribute("treatment").toString();
                req.setAttribute("chosenT", tempTreatment);
                if ("en".equals(session.getAttribute("locale")))
                    req.setAttribute("chosenM", "Masters");
                else if ("ua".equals(session.getAttribute("locale")))
                    req.setAttribute("chosenM", "Майстри");
                req.setAttribute("masters",
                        masterDao.findAllMastersByTreatmentNameAndPrice(
                                tempTreatment.substring(0, tempTreatment.lastIndexOf(' ')),
                                Integer.parseInt(tempTreatment.substring(tempTreatment.lastIndexOf(' ') + 1))));

            }
        }

        if (session.getAttribute("master") != null
                && session.getAttribute("treatment") != null && req.getParameter("chosenTime") == null) {
            String master = session.getAttribute("master").toString();
            String treatment = session.getAttribute("treatment").toString();
            if (session.getAttribute("treatmentChanged") != null) {
                if (!session.getAttribute("treatmentChanged").toString().equals("true")) {
                    req.setAttribute("chosenM", master);
                }
            }
            req.setAttribute("chosenT", treatment);
            req.setAttribute("masters",
                    masterDao.findAllMastersByTreatmentNameAndPrice(
                            treatment.substring(0, treatment.lastIndexOf(' ')),
                            Integer.parseInt(treatment.substring(treatment.lastIndexOf(' ') + 1))));
            req.setAttribute("orderDay", date);
            session.setAttribute("orderDate", date);
            req.setAttribute("freeTime", orderDao.findFreeTimeByDateMasterAndTreatment(
                    masterDao.findMastersIdByNameAndSurname(master.substring(0, master.lastIndexOf(' ')),
                            master.substring(master.lastIndexOf(' ') + 1)), LocalDate.parse(date)));
            req.setAttribute("defaultTime", "Free Time");
            req.setAttribute("send", true);
        } else {
            req.setAttribute("orderDay", date);
            req.setAttribute("send", false);
            req.setAttribute("defaultTime", "Free Time");
            if ("en".equals(session.getAttribute("locale"))) {
                req.setAttribute("chosenM", "Masters");
            }else if ("ua".equals(session.getAttribute("locale"))){
                req.setAttribute("chosenM", "Майстри");
            }

            if (session.getAttribute("treatmentChanged") != null) {
                if (!session.getAttribute("treatmentChanged").toString().equals("true")) {
                    if ("en".equals(session.getAttribute("locale"))) {
                    req.setAttribute("chosenT", "Treatments");
                    }else if ("ua".equals(session.getAttribute("locale"))){
                        req.setAttribute("chosenT", "Процедури");
                    }
                }
            }else {
                if ("en".equals(session.getAttribute("locale"))) {
                    req.setAttribute("chosenT", "Treatments");
                }else if ("ua".equals(session.getAttribute("locale"))){
                    req.setAttribute("chosenT", "Процедури");
                }
            }

        }
        session.setAttribute("treatmentChanged", false);
        req.setAttribute("treatments", treatmentDao.findAll());
        req.getServletContext().getRequestDispatcher("/view/client_order.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String chosenTime = req.getParameter("chosenTime");
        HttpSession s = req.getSession();
        if (req.getParameter("chosenMaster") == null ){
            s.setAttribute("master",s.getAttribute("master"));
        }else {
            s.setAttribute("master",req.getParameter("chosenMaster"));
            chosenTime = null;
        }

        if (req.getParameter("chosenTreatment") == null ){
            s.setAttribute("treatment",s.getAttribute("treatment"));

        }else {
            s.setAttribute("treatment",req.getParameter("chosenTreatment"));
            s.setAttribute("treatmentChanged", true);
            chosenTime = null;
        }

        if (req.getParameter("orderDate") == null || "".equals(req.getParameter("orderDate"))){
            s.setAttribute("orderDate",s.getAttribute("orderDate"));
        }else {
            s.setAttribute("orderDate",req.getParameter("orderDate"));
            chosenTime = null;
        }

        if (chosenTime != null){
            s.setAttribute("orderTime",req.getParameter("chosenTime"));

            String tempTreatment = s.getAttribute("treatment").toString();
            int treatmentId = treatmentDao.findIdByNameAndPrice(tempTreatment.substring(0,tempTreatment.lastIndexOf(' ')),
                    Integer.parseInt(tempTreatment.substring(tempTreatment.lastIndexOf(' ')+1)));

            String tempMaster = s.getAttribute("master").toString();
            int masterId = masterDao.findMastersIdByNameAndSurname(tempMaster.substring(0,tempMaster.lastIndexOf(' ')),
                    tempMaster.substring(tempMaster.lastIndexOf(' ')+1));

            Client client = (Client)s.getAttribute("client");
            int clientId = clientDao.findClientIdByNameSurnameAndPassword(client.getNameEn(),client.getSurnameEn(),client.getPassword());

            LocalDate executionDate = LocalDate.parse(s.getAttribute("orderDate").toString()) ;
            LocalTime executionTime = LocalTime.parse(s.getAttribute("orderTime").toString());


            orderDao.insert(new Order.Builder().withMasterId(masterId).withClientId(clientId).
                    withTreatmentId(treatmentId).withTreatmentExecutionDate(executionDate).withTreatmentExecutionTime(executionTime).build());

            resp.sendRedirect(req.getContextPath()+"/client_order/conf");
        }else {
            resp.sendRedirect(req.getContextPath()+"/client_order");
        }
    }
}