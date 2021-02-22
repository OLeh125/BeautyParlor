package org.fp.beauty.parlor.model.dao;


import org.fp.beauty.parlor.model.dao.db.ConnectionPool;
import org.fp.beauty.parlor.model.dao.db.DBConstants;
import org.fp.beauty.parlor.model.entity.Master;
import org.fp.beauty.parlor.service.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class MasterDaoImpl implements MasterDao{
    private static final Logger logger = LoggerFactory.getLogger(MasterDaoImpl.class);

    @Override
    public void insert(Master master) {
        Connection con = null;
        PreparedStatement pst = null;
        PreparedStatement pst2 = null;
        PreparedStatement pst3 = null;
        ResultSet rs = null;
        try{
            con = ConnectionPool.getInstance().getConnection();
            pst = con.prepareStatement(DBConstants.INSERT_USER);
            con.setAutoCommit(false);
            pst.setString(1,master.getNameUa());
            pst.setString(2,master.getSurnameUa());
            pst.setString(3,master.getNameEn());
            pst.setString(4,master.getSurnameEn());
            pst.setString(5,master.getEmail());
            pst.setString(6,master.getPassword());
            pst.setObject(7, String.valueOf(Role.MASTER).toLowerCase(), Types.OTHER);
            pst.execute();
            pst2 = con.prepareStatement(DBConstants.FIND_ID_BY_USER_EN_NAME_SURNAME_PASSWORD_AND_ROLE);
            pst2.setString(1,master.getNameEn());
            pst2.setString(2,master.getSurnameEn());
            pst2.setString(3,master.getPassword());
            pst2.setObject(4,String.valueOf(Role.MASTER).toLowerCase(),Types.OTHER);
            rs = pst2.executeQuery();
            int masterId = 0;
            if (rs.next()){
                masterId = rs.getInt("id");
                master.setId(masterId);
            }
            if (masterId != 0){
                pst3 = con.prepareStatement(DBConstants.INITIALISE_MASTER_RATING);
                pst3.setInt(1,masterId);
                pst3.execute();
            }
            con.commit();
            logger.info("Master {} {} was successfully registered ",master.getNameEn(),master.getSurnameEn());
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
            logger.error("Master wasn't registered");
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
                    if (pst3!= null)
                        pst3.close();
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
    public void update(Master master) {

    }

    @Override
    public void delete(Master master) {

    }



    public List<Master> findAll() {
        List<Master> masters = new ArrayList<>();
        try(Connection con = ConnectionPool.getInstance().getConnection()){
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(DBConstants.FIND_ALL_MASTERS_WITH_RATING);

            while (rs.next()) {
                masters.add(new Master.Builder().withId(rs.getInt("master_id"))
                        .withNameUa(rs.getString("ua_name"))
                        .withSurnameUa(rs.getString("ua_surname"))
                        .withNameEn(rs.getString("en_name"))
                        .withSurnameEn(rs.getString("en_surname"))
                        .withPassword(rs.getString("password"))
                        .withEmail("email")
                        .withRating(rs.getDouble("rating"))
                        .build());
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
        }
        return masters;
    }


    @Override
    public int findMastersIdByNameAndSurname(String name, String surname) {
        int id  = -1;
        try(Connection con = ConnectionPool.getInstance().getConnection()){
            PreparedStatement pst = con.prepareStatement(DBConstants.FIND_MASTER_ID_BY_NAME_AND_SURNAME);
            pst.setString(1,name);
            pst.setString(2,surname);
            pst.setString(3,name);
            pst.setString(4,surname);
            ResultSet rs = pst.executeQuery();
            if (rs.next()){
                id = rs.getInt("id");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
        }
        return id;
    }

    public boolean exists(String name, String surname, String password){
        for (Master m : findAll()) {
            if (m.getNameUa().equals(name) && m.getSurnameUa().equals(surname) && m.getPassword().equals(password)){
                return true;
            }else if (m.getNameEn().equals(name) && m.getSurnameEn().equals(surname) && m.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }



    @Override
    public List<Master> findAllMastersByTreatmentNameAndPrice(String name, int price) {
        List<Master> masters = new ArrayList<>();
        try(Connection con = ConnectionPool.getInstance().getConnection()){
            PreparedStatement pst = con.prepareStatement(DBConstants.FIND_MASTERS_BY_TREATMENT_NAME_AND_PRICE);
            pst.setString(1,name);
            pst.setInt(2,price);
            pst.setString(3,name);
            pst.setInt(4,price);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                masters.add(new Master.Builder()
                        .withNameEn(rs.getString("en_master_name"))
                        .withSurnameEn(rs.getString("en_master_surname"))
                        .withNameUa(rs.getString("ua_master_name"))
                        .withSurnameUa(rs.getString("ua_master_surname"))
                        .build());
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
        }
        return masters;
    }
}
