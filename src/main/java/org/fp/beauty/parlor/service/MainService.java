package org.fp.beauty.parlor.service;

import org.fp.beauty.parlor.model.dao.OrderDao;
import org.fp.beauty.parlor.model.dao.OrderDaoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;

public class MainService extends Thread {
    private static final Logger logger = LoggerFactory.getLogger(MainService.class);
    private final OrderDao orderDao;

    public MainService() {
        orderDao = new OrderDaoImpl();
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                sleep(1000 * 60*60);
            } catch (InterruptedException e) {
                logger.error(e.getMessage(),e);
                Thread.currentThread().interrupt();
            }
            setAllYesterdaysOrdersStatusesAsDone();
            setRequestAboutFeedbackTrue();
            logger.info("Main Service worked");
        }
    }


    void setAllYesterdaysOrdersStatusesAsDone(){
        List<Integer> ids = orderDao.findAllIdsWhereDateLowerThanCurrentAndStatusIsNotDone();
        for (Integer id :ids) {
            orderDao.updateStatusById(OrderStatus.DONE,id);
        }
        logger.info("All Yesterdays Orders Statuses were set as DONE");
    }

    void setRequestAboutFeedbackTrue(){
        orderDao.setRequestFeedbackTrueByDate(LocalDate.now().minusDays(1));
        logger.info("All requests about feedback were set as TRUE");
    }
}
