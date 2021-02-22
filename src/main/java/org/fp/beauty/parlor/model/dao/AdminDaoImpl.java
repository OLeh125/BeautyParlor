package org.fp.beauty.parlor.model.dao;

import org.fp.beauty.parlor.model.dao.db.ConnectionPool;
import org.fp.beauty.parlor.model.dao.db.DBConstants;
import org.fp.beauty.parlor.model.entity.Admin;
import org.fp.beauty.parlor.service.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDaoImpl implements AdminDao{
    private static final Logger logger = LoggerFactory.getLogger(AdminDaoImpl.class);


    @Override
    public void insert(Admin admin) {
        Connection con = null;
        PreparedStatement pst = null;
        PreparedStatement pst2 = null;
        ResultSet rs = null;
        try{
            con = ConnectionPool.getInstance().getConnection();
            pst = con.prepareStatement(DBConstants.INSERT_USER);
            con.setAutoCommit(false);
            pst.setString(1,admin.getNameUa());
            pst.setString(2,admin.getSurnameUa());
            pst.setString(3,admin.getNameEn());
            pst.setString(4,admin.getSurnameEn());
            pst.setString(5,admin.getEmail());
            pst.setString(6,admin.getPassword());
            pst.setObject(7, String.valueOf(Role.ADMIN).toLowerCase(), Types.OTHER);
            pst.execute();
            pst2 = con.prepareStatement(DBConstants.FIND_ID_BY_USER_EN_NAME_SURNAME_PASSWORD_AND_ROLE);
            pst2.setString(1,admin.getNameEn());
            pst2.setString(2,admin.getSurnameEn());
            pst2.setString(3,admin.getPassword());
            pst2.setObject(4,String.valueOf(Role.ADMIN).toLowerCase(),Types.OTHER);
            rs = pst2.executeQuery();
            if (rs.next()){
                admin.setId(rs.getInt("id"));
            }
            con.commit();
            logger.info("Admin {} {} was successfully registered ",admin.getNameEn(),admin.getSurnameEn());
        } catch (SQLException e) {
            logger.error("Admin wasn't registered");
            try {
                if (con!=null)
                    con.rollback();
            } catch (SQLException exception) {
                logger.error(e.getMessage(),e);
            }
            logger.error(e.getMessage(),e);
        } finally {
            if (con!= null){
                try {
                    con.close();
                } catch (SQLException e) {
                    logger.error(e.getMessage(),e);
                }
                try {
                    if (pst != null)
                        pst.close();
                } catch (SQLException e) {
                    logger.error(e.getMessage(),e);
                }

                try {
                    if (pst2!= null)
                        pst2.close();
                } catch (SQLException e) {
                    logger.error(e.getMessage(),e);
                }
                try {
                    if (rs != null)
                        rs.close();
                } catch (SQLException e) {
                    logger.error(e.getMessage(),e);
                }
            }
        }
    }

    @Override
    public void update(Admin admin) {

    }

    @Override
    public void delete(Admin admin) {

    }

    @Override
    public List<Admin> findAll() {
        List<Admin> admins = new ArrayList<>();
        try(Connection con = ConnectionPool.getInstance().getConnection()){
            PreparedStatement stmt = con.prepareStatement(DBConstants.FIND_ALL_USERS_BY_ROLE);
            stmt.setObject(1,String.valueOf(Role.ADMIN).toLowerCase(),Types.OTHER);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                admins.add(new Admin.Builder()
                        .withId(rs.getInt("id"))
                        .withNameUa(rs.getString("ua_name"))
                        .withSurnameUa(rs.getString("ua_surname"))
                        .withNameEn(rs.getString("en_name"))
                        .withSurnameEn(rs.getString("en_surname"))
                        .withPassword(rs.getString("password"))
                        .withEmail(rs.getString("email"))
                        .build());
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
        }
        return admins;
    }




    @Override
    public boolean exists(String name, String surname, String password) {
        for (Admin a : findAll()) {
            if (a.getNameUa().equals(name) && a.getSurnameUa().equals(surname) && a.getPassword().equals(password)){
                return true;
            }else if (a.getNameEn().equals(name) && a.getSurnameEn().equals(surname) && a.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }
}
