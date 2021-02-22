package org.fp.beauty.parlor.model.dao;

import org.fp.beauty.parlor.model.dao.db.ConnectionPool;
import org.fp.beauty.parlor.model.dao.db.DBConstants;
import org.fp.beauty.parlor.model.entity.Client;
import org.fp.beauty.parlor.model.entity.Master;
import org.fp.beauty.parlor.model.entity.Order;
import org.fp.beauty.parlor.model.entity.Treatment;
import org.fp.beauty.parlor.service.OrderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    private static final Logger logger = LoggerFactory.getLogger(OrderDaoImpl.class);

    @Override
    public List<LocalTime> findFreeTimeByDateMasterAndTreatment(int masterId, LocalDate date) {
        List<LocalTime> freeTime = new LinkedList<>(WORK_HOURS);
        try(Connection con = ConnectionPool.getInstance().getConnection()){
            PreparedStatement pst = con.prepareStatement(DBConstants.FIND_TAKEN_TIME_BY_MASTER_ID_AND_DATE);
            pst.setInt(1,masterId);
            pst.setDate(2,Date.valueOf(date));
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                freeTime.remove(LocalTime.parse(rs.getTime("treatment_execution_time").toString()));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
        }
        return freeTime;
    }



    @Override
    public List<Order> findAllOrdersWithMastersAndTreatmentsByClientId(int clientId) {
        List<Order> orders = new ArrayList<>();
        try(Connection con = ConnectionPool.getInstance().getConnection()){
            PreparedStatement pst = con.prepareStatement(DBConstants.FIND_ORDERS_WITH_MASTER_AND_TREATMENT_BY_ORDER_ID);
            pst.setInt(1,clientId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                orders.add(new Order.Builder().withId(rs.getInt("order_id"))
                        .withMaster(new Master.Builder().withId(rs.getInt("master_id"))
                                .withNameUa(rs.getString("ua_master_name"))
                                .withNameEn(rs.getString("en_master_name"))
                                .withSurnameEn(rs.getString("en_master_surname"))
                                .withSurnameUa(rs.getString("ua_master_surname"))
                                .build())
                        .withTreatment(new Treatment.Builder()
                                .withNameUa(rs.getString("ua_treatment_name"))
                                .withNameEn(rs.getString("en_treatment_name"))
                                .withPrice(rs.getInt("treatment_price"))
                                .build())
                        .withTreatmentExecutionDate(LocalDate.parse(rs.getDate("treatment_execution_date").toString()))
                        .withTreatmentExecutionTime(LocalTime.parse(rs.getTime("treatment_execution_time").toString()))
                        .withStatusEn(OrderStatus.valueOf(rs.getString("order_status").toUpperCase()))
                        .build());
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
        }
        return orders;
    }

    @Override
    public List<Order> findAllByDate(LocalDate date) {
        List<Order> orders = new ArrayList<>();
        try (Connection con =ConnectionPool.getInstance().getConnection()){
            PreparedStatement pst = con.prepareStatement(DBConstants.FIND_ALL_ORDERS_WITH_MASTER_TREATMENT_AND_CLIENT_BY_DATE);
            pst.setDate(1,Date.valueOf(date));
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                orders.add(new Order.Builder()
                        .withId(rs.getInt("order_id"))
                        .withTreatmentExecutionDate(LocalDate.parse(rs.getDate("order_date").toString()))
                        .withTreatmentExecutionTime(LocalTime.parse(rs.getTime("order_time").toString()))
                        .withStatusEn(OrderStatus.valueOf(rs.getString("status").toUpperCase()))
                        .withTreatment(new Treatment.Builder()
                                .withNameEn(rs.getString("en_treatment_name"))
                                .withNameUa(rs.getString("ua_treatment_name"))
                                .withPrice(rs.getInt("treatment_price"))
                                .build())
                        .withMaster(new Master.Builder()
                                .withNameUa(rs.getString("ua_master_name"))
                                .withNameEn(rs.getString("en_master_name"))
                                .withSurnameEn(rs.getString("en_master_surname"))
                                .withSurnameUa(rs.getString("ua_master_surname"))
                                .build())
                        .withClient(new Client.Builder()
                                .withNameEn(rs.getString("en_client_name"))
                                .withNameUa(rs.getString("ua_client_name"))
                                .withSurnameEn(rs.getString("en_client_surname"))
                                .withSurnameUa(rs.getString("ua_client_surname"))
                                .build())
                        .build());
            }

        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
        }
        return orders;
    }

//    @Override
//    public List<Order> findAllByDateAndMasterId(LocalDate date, int masterId) {
//        List<Order> orders = new ArrayList<>();
//        try (Connection con =ConnectionPool.getInstance().getConnection()){
//            PreparedStatement pst = con.prepareStatement(DBConstants.FIND_ALL_ORDERS_WITH_MASTER_TREATMENT_AND_CLIENT_BY_DATE_AND_MASTER_ID);
//            pst.setDate(1,Date.valueOf(date));
//            pst.setInt(2,masterId);
//
//            ResultSet rs = pst.executeQuery();
//            while (rs.next()){
//                orders.add(new Order.Builder()
//                        .withId(rs.getInt("order_id"))
//                        .withTreatmentExecutionDate(LocalDate.parse(rs.getDate("order_date").toString()))
//                        .withTreatmentExecutionTime(LocalTime.parse(rs.getTime("order_time").toString()))
//                        .withStatus(OrderStatus.valueOf(rs.getString("status").toUpperCase()))
//                        .withTreatment(new Treatment.Builder()
//                                .withName(rs.getString("treatment_name"))
//                                .withPrice(rs.getInt("treatment_price"))
//                                .build())
//                        .withMaster(new Master.Builder()
//                                .withName(rs.getString("master_name"))
//                                .withSurname(rs.getString("master_surname"))
//                                .build())
//                        .withClient(new Client.Builder()
//                                .withName(rs.getString("client_name"))
//                                .withSurname(rs.getString("client_surname"))
//                                .build())
//                        .build());
//            }
//
//        } catch (SQLException e) {
//            //TODO handle ex
//            logger.error(e.getMessage(),e);
//        }
//        return orders;
//    }

    @Override
    public List<Order> findAllByDateAndMasterIdForMaster(LocalDate date, int masterId) {
        List<Order> orders = new ArrayList<>();
        try (Connection con =ConnectionPool.getInstance().getConnection()){
            PreparedStatement pst = con.prepareStatement(DBConstants.FIND_ALL_ORDERS_WITH_MASTER_TREATMENT_AND_CLIENT_BY_DATE_AND_MASTER_ID_FOR_MASTER);
            pst.setDate(1,Date.valueOf(date));
            pst.setInt(2,masterId);

            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                orders.add(new Order.Builder()
                        .withId(rs.getInt("order_id"))
                        .withTreatmentExecutionDate(LocalDate.parse(rs.getDate("order_date").toString()))
                        .withTreatmentExecutionTime(LocalTime.parse(rs.getTime("order_time").toString()))
                        .withStatusEn(OrderStatus.valueOf(rs.getString("status").toUpperCase()))
                        .withTreatment(new Treatment.Builder()
                                .withNameEn(rs.getString("en_treatment_name"))
                                .withNameUa(rs.getString("ua_treatment_name"))
                                .withPrice(rs.getInt("treatment_price"))
                                .build())
                        .withMaster(new Master.Builder()
                                .withNameEn(rs.getString("en_master_name"))
                                .withNameUa(rs.getString("ua_master_name"))
                                .withSurnameEn(rs.getString("en_master_surname"))
                                .withSurnameUa(rs.getString("ua_master_surname"))
                                .build())
                        .withClient(new Client.Builder()
                                .withNameEn(rs.getString("en_client_name"))
                                .withNameUa(rs.getString("ua_client_name"))
                                .withSurnameEn(rs.getString("en_client_surname"))
                                .withSurnameUa(rs.getString("ua_client_surname"))
                                .build())
                        .build());
            }

        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
        }

        return orders;
    }

    @Override
    public void updateStatusById(OrderStatus status,int orderId) {
        try(Connection con = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement pst = con.prepareStatement(DBConstants.SET_ORDER_STATUS_BY_ORDER_ID);
            pst.setObject(1,String.valueOf(status).toLowerCase(),Types.OTHER);
            pst.setInt(2,orderId);
            pst.execute();
            logger.info("Order's status which id is: {} was changed to {}",orderId,status);
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
        }
    }

    @Override
    public int findIdByMasterIdDateAndTime(int masterId, LocalDate date, LocalTime time) {
        int id = -1;
        try(Connection con = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement pst = con.prepareStatement(DBConstants.FIND_ORDER_ID_BY_MASTER_ID_DATE_AND_TIME);
            pst.setInt(1, masterId);
            pst.setDate(2,Date.valueOf(date));
            pst.setTime(3,Time.valueOf(time));
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                id = rs.getInt("id");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
        }
        return id;
    }

    @Override
    public List<Integer> findAllIdsWhereDateLowerThanCurrentAndStatusIsNotDone() {
        List<Integer> ids = new ArrayList<>();
        try(Connection con = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement pst = con.prepareStatement(DBConstants.FIND_ALL_IDS_WHERE_DATE_LOWER_THAN_SOME_DATE_AND_STATUS_IS_NOT_SOME_STATUS);
            pst.setDate(1,Date.valueOf(LocalDate.now()));
            pst.setObject(2,String.valueOf(OrderStatus.DONE).toLowerCase(),Types.OTHER);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                ids.add(rs.getInt("id"));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
        }
        return ids;
    }

    @Override
    public void setRequestFeedbackTrueByDate(LocalDate date) {
        try(Connection con = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement pst = con.prepareStatement(DBConstants.SET_REQUEST_FEEDBACK_TRUE_BY_DATE);
            pst.setDate(1,Date.valueOf(date));
            pst.execute();
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
        }
    }

    @Override
    public void setTimeById( LocalTime time,int id) {
        try(Connection con = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement pst = con.prepareStatement(DBConstants.SET_ORDER_TIME_BY_ORDER_ID);
            pst.setTime(1,Time.valueOf(time));
            pst.setInt(2,id);
            pst.execute();
            logger.info("Order which id is {} was rescheduled to {}",id,time);
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
        }
    }


    @Override
    public void insert(Order order) {
        Connection con = null;
        PreparedStatement pst = null;
        PreparedStatement pst2 = null;

        try {
            con = ConnectionPool.getInstance().getConnection();
            pst = con.prepareStatement(DBConstants.INSERT_ORDER);
            con.setAutoCommit(false);
            pst.setDate(1,Date.valueOf(order.getTreatmentExecutionDate()));
            pst.setTime(2,Time.valueOf(order.getTreatmentExecutionTime()));
            pst.setObject(3,String.valueOf(OrderStatus.SUBMITTED).toLowerCase(),Types.OTHER);
            pst.setInt(4,order.getTreatmentId());
            pst.setInt(5,order.getClientId());
            pst.setInt(6,order.getMasterId());
            pst.execute();
            con.commit();
            pst2  = con.prepareStatement(DBConstants.FIND_ORDER_ID_BY_DATE_TIME_MASTER_ID_AND_CLIENT_ID);
            pst2.setDate(1,Date.valueOf(order.getTreatmentExecutionDate()));
            pst2.setTime(2,Time.valueOf(order.getTreatmentExecutionTime()));
            pst2.setInt(3,order.getClientId());
            pst2.setInt(4,order.getMasterId());
            pst2.execute();
            //TODO set orders id
            logger.info("Order was made by Client whose id:{} .Master's id : {} .Date {} .Time {}",order.getClientId(),order.getMasterId(),order.getTreatmentExecutionDate(),order.getTreatmentExecutionTime());
        } catch (SQLException e) {
            try {
                if (con!=null)
                    con.rollback();
            } catch (SQLException ex) {
                logger.error(e.getMessage(),e);
            }
            logger.error(e.getMessage(),e);
        }finally {
            if (con!= null){
                try {
                    con.close();
                } catch (SQLException ex) {
                    logger.error(ex.getMessage(),ex);
                }
                try {
                    if (pst != null)
                        pst.close();
                } catch (SQLException ex) {
                    logger.error(ex.getMessage(),ex);
                }
                try {
                    if (pst2 != null)
                        pst2.close();
                } catch (SQLException ex) {
                    logger.error(ex.getMessage(),ex);
                }
            }
        }

    }

    @Override
    public void update(Order order) {

    }

    @Override
    public void delete(Order order) {

    }



    @Override
    public List<Order> findAll() {
        List<Order> orders = new ArrayList<>();
        try (Connection con =ConnectionPool.getInstance().getConnection()){
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(DBConstants.FIND_ALL_ORDERS_WITH_MASTER_TREATMENT_AND_CLIENT);
            while (rs.next()){
                orders.add(new Order.Builder()
                        .withId(rs.getInt("order_id"))
                        .withTreatmentExecutionDate(LocalDate.parse(rs.getDate("order_date").toString()))
                        .withTreatmentExecutionTime(LocalTime.parse(rs.getTime("order_time").toString()))
                        .withStatusEn(OrderStatus.valueOf(rs.getString("status").toUpperCase()))
                        .withTreatment(new Treatment.Builder()
                                .withNameEn(rs.getString("en_treatment_name"))
                                .withNameUa(rs.getString("ua_treatment_name"))
                                .withPrice(rs.getInt("treatment_price"))
                                .build())
                        .withMaster(new Master.Builder()
                                .withNameEn(rs.getString("en_master_name"))
                                .withNameUa(rs.getString("ua_master_name"))
                                .withSurnameEn(rs.getString("en_master_surname"))
                                .withSurnameUa(rs.getString("ua_master_surname"))
                                .build())
                        .withClient(new Client.Builder()
                                .withNameUa(rs.getString("ua_client_name"))
                                .withNameEn(rs.getString("en_client_name"))
                                .withSurnameEn(rs.getString("en_client_surname"))
                                .withSurnameUa(rs.getString("ua_client_surname"))
                                .build())
                        .build());
            }

        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
        }
        return orders;
    }




}
