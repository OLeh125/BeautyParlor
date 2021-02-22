package org.fp.beauty.parlor.controller.servlet.registering;

import org.fp.beauty.parlor.model.dao.db.ConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet("/logout")
public class LogOutServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(LogOutServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            Enumeration<String> attributeNames = session.getAttributeNames();
            while (attributeNames.hasMoreElements()){
                String element = attributeNames.nextElement();
                if (!"locale".equals(element))
                session.removeAttribute(element);
            }
        }
        logger.info("User signed out");
        req.getServletContext().getRequestDispatcher("/signInUp").forward(req,resp);
    }
}
