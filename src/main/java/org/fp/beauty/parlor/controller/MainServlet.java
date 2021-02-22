package org.fp.beauty.parlor.controller;

import org.fp.beauty.parlor.service.MainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/index")
public class MainServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(MainServlet.class);
    private Thread mainService;
    @Override
    public void init() throws ServletException {
        mainService = new MainService();
        mainService.start();
        logger.info("Main Service was started");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("locale") ==null){
            session.setAttribute("locale","en");
        }
        req.getServletContext().getRequestDispatcher("/view/index.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Object locale = session.getAttribute("locale");
        if (locale != null){
            if ("ua".equals(locale)) {
                session.setAttribute("locale", "en");
            }else if ("en".equals(locale)){
                session.setAttribute("locale", "ua");
            }else session.setAttribute("locale","en");

        }else {
            session.setAttribute("locale","en");
        }
        resp.sendRedirect(req.getContextPath() + "/index");
    }

    @Override
    public void destroy() {
        mainService.interrupt();
        try {
            mainService.join();
        } catch (InterruptedException e) {
            logger.error(e.getMessage(),e);
            Thread.currentThread().interrupt();
        }
    }
}
