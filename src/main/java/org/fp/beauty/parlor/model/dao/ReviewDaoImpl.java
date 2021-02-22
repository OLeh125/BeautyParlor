package org.fp.beauty.parlor.model.dao;


import org.fp.beauty.parlor.model.dao.db.ConnectionPool;
import org.fp.beauty.parlor.model.dao.db.DBConstants;
import org.fp.beauty.parlor.model.entity.Client;
import org.fp.beauty.parlor.model.entity.Review;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReviewDaoImpl implements ReviewDao {
    private static final Logger logger = LoggerFactory.getLogger(ReviewDao.class);

    @Override
    public void insert(Review review) {
        Connection con = null;
        PreparedStatement pst = null;
        PreparedStatement pst2 = null;
        ResultSet rs = null;
        try{
            con = ConnectionPool.getInstance().getConnection();
            pst = con.prepareStatement(DBConstants.INSERT_REVIEW);
            con.setAutoCommit(false);
            pst.setString(1,review.getContent());
            pst.setTimestamp(2,Timestamp.valueOf(review.getDateTime()));
            pst.setInt(3,review.getClient().getId());
            pst.execute();
            pst2 = con.prepareStatement(DBConstants.FIND_REVIEW_ID_BY_CONTENT_AND_CLIENT_ID);
            pst2.setString(1,review.getContent());
            pst2.setInt(2,review.getClient().getId());
            rs = pst2.executeQuery();
            if (rs.next()){
                review.setId(rs.getInt("id"));
            }
            con.commit();
            logger.info("review was successfully added");
        } catch (SQLException e) {
            logger.error("review wasn't added");
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
    public void update(Review review) {

    }

    @Override
    public void delete(Review review) {

    }

    @Override
    public List<Review> findAll() {
        List<Review> reviews = new ArrayList<>();
        try(Connection con = ConnectionPool.getInstance().getConnection()){
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(DBConstants.FIND_FIVE_LATEST_REVIEWS);
            while (rs.next()){
                reviews.add(new Review.Builder()
                        .withId(rs.getInt("review_id"))
                        .withContent(rs.getString("review_content"))
                        .withDateTime(rs.getTimestamp("date_time").toLocalDateTime())
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
        return reviews;
    }

    @Override
    public List<Review> findByPageAndNumberOfRecords(int currentPage, int numberOfRecords) {
        int start = currentPage * numberOfRecords - numberOfRecords;
        List<Review> reviews = new ArrayList<>();
        try(Connection con = ConnectionPool.getInstance().getConnection()){
            PreparedStatement pst = con.prepareStatement(DBConstants.FIND_ALL_BY_PAGE_AND_NUMBER_OF_RECORDS);
            pst.setInt(1,numberOfRecords);
            pst.setInt(2,start);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                reviews.add(new Review.Builder()
                        .withId(rs.getInt("review_id"))
                        .withContent(rs.getString("review_content"))
                        .withDateTime(rs.getTimestamp("date_time").toLocalDateTime())
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
        return reviews;
    }

    @Override
    public int findNumberOfId() {
        int id =-1;
        try(Connection con = ConnectionPool.getInstance().getConnection()){
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(DBConstants.FIND_NUMBER_OF_REVIEWS_ID);
            if (rs.next()){
                id=rs.getInt("count");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
        }
        return id;
    }
}
