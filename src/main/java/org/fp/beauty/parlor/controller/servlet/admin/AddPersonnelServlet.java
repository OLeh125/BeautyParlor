package org.fp.beauty.parlor.controller.servlet.admin;

import org.fp.beauty.parlor.controller.servlet.registering.SignInUpServlet;
import org.fp.beauty.parlor.model.dao.AdminDao;
import org.fp.beauty.parlor.model.dao.AdminDaoImpl;
import org.fp.beauty.parlor.model.dao.MasterDao;
import org.fp.beauty.parlor.model.dao.MasterDaoImpl;
import org.fp.beauty.parlor.model.entity.Admin;
import org.fp.beauty.parlor.model.entity.Master;
import org.fp.beauty.parlor.validator.SignUpValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/personnel")
public class AddPersonnelServlet  extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(AddPersonnelServlet.class);
    private MasterDao masterDao;
    private AdminDao adminDao;

    @Override
    public void init() throws ServletException {
        masterDao = new MasterDaoImpl();
        adminDao = new AdminDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/view/add_personnel.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String role = req.getParameter("role");
        String enName = req.getParameter("en_name");
        String uaName = req.getParameter("ua_name");
        String email = req.getParameter("email");
        String enSurname = req.getParameter("en_surname");
        String uaSurname = req.getParameter("ua_surname");
        String password = req.getParameter("r_password");
        String confirmPassword = req.getParameter("r_c_password");

        SignUpValidator validator = new SignUpValidator(enName,enSurname,uaName,uaSurname,password,confirmPassword);
        if (validator.isValid()) {
            logger.info("Master Or Admin {} {} is Valid" ,enName,enSurname);
            if ("master".equals(role)) {
                masterDao.insert(new Master.Builder()
                        .withNameEn(enName)
                        .withNameUa(uaName)
                        .withSurnameEn(enSurname)
                        .withSurnameUa(uaSurname)
                        .withEmail(email)
                        .withPassword(password)
                        .build());
            } else if ("admin".equals(role)) {
                adminDao.insert(new Admin.Builder()
                        .withNameEn(enName)
                        .withNameUa(uaName)
                        .withSurnameEn(enSurname)
                        .withSurnameUa(uaSurname)
                        .withEmail(email)
                        .withPassword(password)
                        .build());
            }
        }else {
            logger.error("Master or Admin {} {} is invalid", enName, enSurname);
        }
        resp.sendRedirect(req.getContextPath()+"/admin/personnel");
    }
}
