package org.fp.beauty.parlor.controller.servlet.client;

import org.fp.beauty.parlor.model.dao.ClientDao;
import org.fp.beauty.parlor.model.dao.ClientDaoImpl;
import org.fp.beauty.parlor.model.dao.ReviewDao;
import org.fp.beauty.parlor.model.dao.ReviewDaoImpl;
import org.fp.beauty.parlor.model.entity.Client;
import org.fp.beauty.parlor.model.entity.Review;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@WebServlet("/client_order/reviews")
public class ClientReviewsServlet extends HttpServlet {
    private ReviewDao reviewDao;
    private ClientDao clientDao;
    @Override
    public void init() throws ServletException {
        reviewDao = new ReviewDaoImpl();
        clientDao = new ClientDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("reviews",reviewDao.findAll());
        req.getServletContext().getRequestDispatcher("/view/client_reviews.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        Client client = (Client) session.getAttribute("client");
        client.setId(clientDao.findClientIdByNameSurnameAndPassword(client.getNameEn(),client.getSurnameEn(),client.getPassword()));
        reviewDao.insert(new Review.Builder()
                .withContent(req.getParameter("feedback"))
                .withDateTime(LocalDateTime.now())
                .withClient(client).build());
        resp.sendRedirect(req.getContextPath() + "/client_order/reviews");
    }
}
