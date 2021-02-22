package org.fp.beauty.parlor.controller.servlet.review;

import org.fp.beauty.parlor.model.dao.ReviewDao;
import org.fp.beauty.parlor.model.dao.ReviewDaoImpl;
import org.fp.beauty.parlor.model.entity.Review;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/reviews")
public class ReviewsServlet extends HttpServlet {
    private ReviewDao reviewDao;
    @Override
    public void init() throws ServletException {
        reviewDao = new ReviewDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int currentPage = 1;
        int recordsPerPage = 5;
        if (req.getParameter("currentPage") != null) {
            try{
             currentPage = Integer.parseInt(req.getParameter("currentPage"));
            }catch (NumberFormatException ex){
                currentPage =1;
            }
        }
        int rows = reviewDao.findNumberOfId();
        int nOfPages = rows / recordsPerPage;
        if (nOfPages % recordsPerPage > 0) {
            nOfPages++;
        }

        if (currentPage>nOfPages){
            currentPage=nOfPages;
        }else if (currentPage<0){
            currentPage = 1;
        }
        List<Review> reviews = reviewDao.findByPageAndNumberOfRecords(currentPage,recordsPerPage);

        req.setAttribute("reviews",reviews);
        req.setAttribute("noOfPages", nOfPages);
        req.setAttribute("currentPage", currentPage);
        req.setAttribute("recordsPerPage", recordsPerPage);
        req.getServletContext().getRequestDispatcher("/view/reviews.jsp").forward(req,resp);
    }

}
