package com.study.onlineShop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.onlineShop.dao.SalesOrderDao;
import com.study.onlineShop.entity.SalesOrder;

@Service
public class SalesOrderService {

  @Autowired
  private SalesOrderDao salesOrderDao;

  public void addSalesOrder(SalesOrder salesOrder) {
    salesOrderDao.addSalesOrder(salesOrder);
  }
}

