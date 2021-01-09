package com.study.onlineShop;

import com.study.onlineShop.entity.BillingAddress;
import com.study.onlineShop.log.PaymentAction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

public class Application {
  public static void main(String[] args) {
    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);
    PaymentAction paymentAction = (PaymentAction) applicationContext.getBean(PaymentAction.class);
    paymentAction.pay(new BigDecimal(2));
  }
}
