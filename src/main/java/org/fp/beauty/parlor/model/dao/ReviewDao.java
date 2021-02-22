package org.fp.beauty.parlor.model.dao;

import org.fp.beauty.parlor.model.entity.Review;

import java.util.List;

public interface ReviewDao extends Dao<Review> {
    List<Review> findByPageAndNumberOfRecords(int currentPage,int numberOfRecords);
    int findNumberOfId();
}
