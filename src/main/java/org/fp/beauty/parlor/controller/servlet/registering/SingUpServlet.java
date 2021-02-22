package org.fp.beauty.parlor.controller.servlet.registering;


import org.fp.beauty.parlor.model.dao.ClientDao;
import org.fp.beauty.parlor.model.dao.ClientDaoImpl;
import org.fp.beauty.parlor.model.entity.Client;
import org.fp.beauty.parlor.validator.SignUpValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signUp")
public class SingUpServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(SignInUpServlet.class);
    private ClientDao clientDao;


    @Override
    public void init() throws ServletException {
        clientDao= new ClientDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath()+"/signInUp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String enName = req.getParameter("en_name");
        String uaName = req.getParameter("ua_name");
        String email = req.getParameter("email");
        String enSurname = req.getParameter("en_surname");
        String uaSurname = req.getParameter("ua_surname");
        String password = req.getParameter("r_password");
        String confirmPassword = req.getParameter("r_c_password");

        SignUpValidator validator = new SignUpValidator(enName,enSurname,uaName,uaSurname,confirmPassword,password);

        if (validator.isValid()) {
            logger.info("Client {} {} is Valid" ,enName,enSurname);
            clientDao.insert(new Client.Builder()
                    .withNameUa(uaName)
                    .withNameEn(enName)
                    .withSurnameUa(uaSurname)
                    .withSurnameEn(enSurname)
                    .withEmail(email)
                    .withPassword(password)
                    .build());
        }else {
            logger.error("Client {} {} is invalid", enName, enSurname);
        }
        resp.sendRedirect(req.getContextPath() + "/signInUp");
    }
}
