package org.fp.beauty.parlor.model.dao;

import org.fp.beauty.parlor.model.dao.db.ConnectionPool;
import org.fp.beauty.parlor.model.dao.db.DBConstants;
import org.fp.beauty.parlor.model.entity.Treatment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TreatmentDaoImpl implements TreatmentDao {
    private static final Logger logger = LoggerFactory.getLogger(MasterDaoImpl.class);

    @Override
    public List<Treatment> findAll() {
        List<Treatment> treatments = new ArrayList<>();
        try(Connection con = ConnectionPool.getInstance().getConnection()) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(DBConstants.FIND_ALL_TREATMENTS);
            while (rs.next()){
                treatments.add(new Treatment.Builder()
                        .withId(rs.getInt("id"))
                        .withNameEn(rs.getString("en_name"))
                        .withNameUa(rs.getString("ua_name"))
                        .withPrice(rs.getInt("price"))
                        .build());
            }

        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
        }
        return treatments;
    }

    @Override
    public int findIdByNameAndPrice(String name, int price) {
        int id = -1;
        try(Connection con = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement pst = con.prepareStatement(DBConstants.FIND_TREATMENT_ID_BY_NAME_AND_PRICE);
            pst.setString(1,name);
            pst.setInt(2,price);
            pst.setString(3,name);
            pst.setInt(4,price);
            ResultSet rs = pst.executeQuery();
            if (rs.next()){
                id = rs.getInt("id");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
        }
        return id;
    }

    @Deprecated
    @Override
    public List<Treatment> findAllTreatmentsByMasterNameAndSurname(String name, String surname) {
        List<Treatment> treatments = new ArrayList<>();
        try(Connection con = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement pst = con.prepareStatement(DBConstants.FIND_TREATMENTS_BY_MASTER_NAME_AND_SURNAME);
            pst.setString(1,name);
            pst.setString(2,surname);
            pst.setString(3,name);
            pst.setString(4,surname);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                treatments.add(new Treatment.Builder()
                        .withNameEn(rs.getString("en_treatment_name"))
                        .withNameUa(rs.getString("ua_treatment_name"))
                        .withPrice(rs.getInt("treatment_price"))
                        .build());
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
        }

        return treatments;
    }
}
