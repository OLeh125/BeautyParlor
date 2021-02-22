package org.fp.beauty.parlor.controller.servlet.registering;



import org.fp.beauty.parlor.model.dao.*;
import org.fp.beauty.parlor.model.entity.Admin;
import org.fp.beauty.parlor.model.entity.Client;
import org.fp.beauty.parlor.model.entity.Master;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/signIn")
public class SingInServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(SingInServlet.class);
    private MasterDao masterDao;
    private ClientDao clientDao;
    private AdminDao adminDao;

    @Override
    public void init() throws ServletException {
        masterDao = new MasterDaoImpl();
        clientDao = new ClientDaoImpl();
        adminDao = new AdminDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher(req.getContextPath()+"/view/signin_up.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (masterDao.exists(req.getParameter("name"),req.getParameter("surname"),req.getParameter("password"))){
            logger.info("Master {} {} signed in",req.getParameter("name"),req.getParameter("surname"));
            HttpSession session = req.getSession();
            session.setAttribute("master",new Master.Builder()
                    .withNameEn(req.getParameter("name"))
                    .withNameUa(req.getParameter("name"))
                    .withSurnameEn(req.getParameter("surname"))
                    .withSurnameUa(req.getParameter("surname"))
                    .withPassword(req.getParameter("password"))
                    .build());
            resp.sendRedirect(req.getContextPath() + "/master_home");
        }else if (clientDao.exists(req.getParameter("name"),req.getParameter("surname"),req.getParameter("password"))){
            logger.info("Client {} {} signed in",req.getParameter("name"),req.getParameter("surname"));
            HttpSession session = req.getSession();
            session.setAttribute("client",new Client.Builder()
                    .withNameEn(req.getParameter("name"))
                    .withNameUa(req.getParameter("name"))
                    .withSurnameEn(req.getParameter("surname"))
                    .withSurnameUa(req.getParameter("surname"))
                    .withPassword(req.getParameter("password")).build());
            resp.sendRedirect(req.getContextPath() + "/client_order");
        }else if (adminDao.exists(req.getParameter("name"),req.getParameter("surname"),req.getParameter("password"))){
            logger.info("Admin {} {} signed in",req.getParameter("name"),req.getParameter("surname"));
            HttpSession session = req.getSession();
            session.setAttribute("admin",new Admin.Builder()
                    .withNameEn(req.getParameter("name"))
                    .withNameUa(req.getParameter("name"))
                    .withSurnameEn(req.getParameter("surname"))
                    .withSurnameUa(req.getParameter("surname"))
                    .withPassword(req.getParameter("password")).build());
            resp.sendRedirect(req.getContextPath() + "/admin");
        }
        else {
            logger.info("User {} {} tried to sign in but he is out of database or he input wrong password",req.getParameter("name"),req.getParameter("surname"));
            resp.sendRedirect("/signIn");
        }
    }
}
