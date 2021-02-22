package org.fp.beauty.parlor.model.dao;

import org.fp.beauty.parlor.model.entity.Order;
import org.fp.beauty.parlor.service.OrderStatus;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

public interface OrderDao extends Dao<Order> {
    List<LocalTime> WORK_HOURS = Arrays.asList(LocalTime.parse("09:00"),
            LocalTime.parse("10:00"),LocalTime.parse("11:00"),LocalTime.parse("12:00"),
            LocalTime.parse("13:00"),LocalTime.parse("14:00"),LocalTime.parse("15:00"),
            LocalTime.parse("16:00"),LocalTime.parse("17:00"),LocalTime.parse("18:00"),
            LocalTime.parse("19:00"),LocalTime.parse("20:00"));

    List<LocalTime> findFreeTimeByDateMasterAndTreatment(int masterId, LocalDate date);
    List<Order> findAllOrdersWithMastersAndTreatmentsByClientId(int clientId);
    List<Order> findAllByDate(LocalDate date);
    List<Order> findAllByDateAndMasterIdForMaster(LocalDate date, int masterId);
    void updateStatusById(OrderStatus status,int orderId);
    int findIdByMasterIdDateAndTime(int masterId,LocalDate date,LocalTime time);
    List<Integer> findAllIdsWhereDateLowerThanCurrentAndStatusIsNotDone();
    void setRequestFeedbackTrueByDate(LocalDate date);
    void setTimeById(LocalTime time,int id);
}
