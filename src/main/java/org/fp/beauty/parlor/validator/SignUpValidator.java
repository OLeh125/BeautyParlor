package org.fp.beauty.parlor.validator;

import org.fp.beauty.parlor.model.dao.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SignUpValidator {

    private MasterDao masterDao;
    private ClientDao clientDao;
    private AdminDao adminDao;
    private String nameEn;
    private String surnameEn;
    private String nameUa;
    private String surnameUa;
    private String confPassword;
    private String password;

    public SignUpValidator(String nameEn, String surnameEn, String nameUa, String surnameUa, String confPassword, String password) {
        this.nameEn = nameEn;
        this.surnameEn = surnameEn;
        this.nameUa = nameUa;
        this.surnameUa = surnameUa;
        this.confPassword = confPassword;
        this.password = password;
        masterDao = new MasterDaoImpl();
        clientDao = new ClientDaoImpl();
        adminDao = new AdminDaoImpl();
    }
    public boolean isValid(){
        return !checkExists() && checkPassword();
    }
    public boolean checkExists(){
        return checkAdminExists() || checkMasterExists() || checkClientExists();
    }

    public boolean checkAdminExists(){
        return adminDao.exists(nameEn,surnameEn,password) || adminDao.exists(nameUa,surnameUa,password);
    }
    public boolean checkMasterExists(){
        return masterDao.exists(nameEn,surnameEn,password) || masterDao.exists(nameUa,surnameUa,password) ;
    }
    public boolean checkClientExists(){
        return clientDao.exists(nameEn,surnameEn,password) || clientDao.exists(nameUa,surnameUa,password);
    }

    public boolean checkPassword(){
        return password.equals(confPassword);
    }

}
