package org.fp.beauty.parlor.model.dao;


import org.fp.beauty.parlor.model.dao.db.ConnectionPool;
import org.fp.beauty.parlor.model.dao.db.DBConstants;
import org.fp.beauty.parlor.model.entity.Client;
import org.fp.beauty.parlor.service.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;




public class ClientDaoImpl implements ClientDao{
    private static final Logger logger = LoggerFactory.getLogger(ClientDaoImpl.class);

    @Override
    public void insert(Client client) {
        Connection con = null;
        PreparedStatement pst = null;
        PreparedStatement pst2 = null;
        ResultSet rs = null;
        try{
            con = ConnectionPool.getInstance().getConnection();
            pst = con.prepareStatement(DBConstants.INSERT_USER);
            con.setAutoCommit(false);
            pst.setString(1,client.getNameUa());
            pst.setString(2,client.getSurnameUa());
            pst.setString(3,client.getNameEn());
            pst.setString(4,client.getSurnameEn());
            pst.setString(5,client.getEmail());
            pst.setString(6,client.getPassword());
            pst.setObject(7, String.valueOf(Role.CLIENT).toLowerCase(),Types.OTHER);
            pst.execute();
            pst2 = con.prepareStatement(DBConstants.FIND_ID_BY_USER_EN_NAME_SURNAME_PASSWORD_AND_ROLE);
            pst2.setString(1,client.getNameEn());
            pst2.setString(2,client.getSurnameEn());
            pst2.setString(3,client.getPassword());
            pst2.setObject(4,String.valueOf(Role.CLIENT).toLowerCase(),Types.OTHER);
            rs = pst2.executeQuery();
            if (rs.next()){
                client.setId(rs.getInt("id"));
            }
            con.commit();
            logger.info("Client {} {} was successfully registered ",client.getNameEn(),client.getSurnameEn());
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
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
    public void update(Client client) {

    }

    @Override
    public void delete(Client client) {

    }

    public List<Client> findAll() {
        List<Client> clients = new ArrayList<>();
        try(Connection con = ConnectionPool.getInstance().getConnection()){
            PreparedStatement stmt = con.prepareStatement(DBConstants.FIND_ALL_USERS_BY_ROLE);
            stmt.setObject(1,String.valueOf(Role.CLIENT).toLowerCase(),Types.OTHER);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                clients.add(new Client.Builder()
                        .withId(rs.getInt("id"))
                        .withNameEn(rs.getString("en_name"))
                        .withSurnameEn(rs.getString("en_surname"))
                        .withNameUa(rs.getString("ua_name"))
                        .withSurnameUa(rs.getString("ua_surname"))
                        .withPassword(rs.getString("password"))
                        .withEmail(rs.getString("email"))
                        .build());
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
        }
        return clients;
    }

    public boolean exists(String name, String surname,String password){
        for (Client c : findAll()) {
            if (c.getNameUa().equals(name) && c.getSurnameUa().equals(surname) && c.getPassword().equals(password)){
                return true;
            }else if (c.getNameEn().equals(name) && c.getSurnameEn().equals(surname) && c.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    @Override
    public int findClientIdByNameSurnameAndPassword(String name, String surname, String password) {
        int id  = -1;
        try(Connection con = ConnectionPool.getInstance().getConnection()){
            PreparedStatement pst = con.prepareStatement(DBConstants.FIND_USERS_ID_BY_NAME_SURNAME_AND_PASSWORD);
            pst.setString(1,name);
            pst.setString(2,surname);
            pst.setString(3,password);
            pst.setString(4,name);
            pst.setString(5,surname);
            pst.setString(6,password);
            ResultSet rs = pst.executeQuery();
            if (rs.next()){
               id =  rs.getInt("id");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
        }
        return id;
    }
}
