package com.telcomdms.scheduler;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.telcomdms.model.Orders;
import com.telcomdms.repository.OrderRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderRequestScheduler {

    public Logger logger= LoggerFactory.getLogger(OrderRequestScheduler.class);

    @Autowired
    private OrderRepo orderRepo;

    @Scheduled(cron = "0 */1 * * * *")
    public void updateOrderStatus(){

        logger.info("Scheduler method processing");
        List<Orders> orders=this.orderRepo.findAll();
        for(Orders orders1:orders)
        {
            if (orders1.getOrderStatus().equals("PLACED"))
            {
                orders1.setOrderStatus("UNDER_PROCESS");
                this.orderRepo.save(orders1);
            }
        }
        logger.info("Scheduled method processed");
    }
  }

