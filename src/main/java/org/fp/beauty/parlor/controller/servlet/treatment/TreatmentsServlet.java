package org.fp.beauty.parlor.controller.servlet.treatment;

import org.fp.beauty.parlor.model.dao.TreatmentDao;
import org.fp.beauty.parlor.model.dao.TreatmentDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/treatments")
public class TreatmentsServlet extends HttpServlet {
    private TreatmentDao treatmentDao;
    @Override
    public void init() throws ServletException {
        treatmentDao = new TreatmentDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("treatments",treatmentDao.findAll());
        req.getServletContext().getRequestDispatcher("/view/treatments.jsp").forward(req,resp);
    }
}
