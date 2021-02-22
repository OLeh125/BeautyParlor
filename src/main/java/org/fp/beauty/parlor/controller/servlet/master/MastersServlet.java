package org.fp.beauty.parlor.controller.servlet.master;

import org.fp.beauty.parlor.model.dao.MasterDao;
import org.fp.beauty.parlor.model.dao.MasterDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/masters")
public class MastersServlet extends HttpServlet {
    private MasterDao masterDao;
    @Override
    public void init() throws ServletException {
        masterDao = new MasterDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("masters",masterDao.findAll());
        req.getServletContext().getRequestDispatcher("/view/masters.jsp").forward(req,resp);
    }
}
