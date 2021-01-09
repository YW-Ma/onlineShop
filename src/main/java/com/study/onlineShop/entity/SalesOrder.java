package com.study.onlineShop.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

  @Entity
  @Table(name = "salesorder")
  public class SalesOrder implements Serializable {
    // Let's assume one user can create many salesOrder
    // currently, there are now history salesOrder
    // history model will be implemented with a history table later.

    // currently, differents order from the same people
    // may be similar in terms of the content of this table.

    private static final long serialVersionUID = 301L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    private Cart cart;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private ShippingAddress shippingAddress;

    @ManyToOne
    private BillingAddress billingAddress;

    public int getId() {
      return id;
    }

    public void setId(int id) {
      this.id = id;
    }

    public Cart getCart() {
      return cart;
    }

    public void setCart(Cart cart) {
      this.cart = cart;
    }

    public Customer getCustomer() {
      return customer;
    }

    public void setCustomer(Customer customer) {
      this.customer = customer;
    }

    public ShippingAddress getShippingAddress() {
      return shippingAddress;
    }

    public void setShippingAddress(ShippingAddress shippingAddress) {
      this.shippingAddress = shippingAddress;
    }

    public BillingAddress getBillingAddress() {
      return billingAddress;
    }

    public void setBillingAddress(BillingAddress billingAddress) {
      this.billingAddress = billingAddress;
    }
  }
