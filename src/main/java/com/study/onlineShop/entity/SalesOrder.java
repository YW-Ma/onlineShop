package com.study.onlineShop.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "salesOrder")
public class SalesOrder implements Serializable {
  // Let's assume one user can create many salesOrder
  // currently, there are now history salesOrder
  // history model will be implemented with a history table later.

  // currently, differents order from the same people
  // may be similar in terms of the content of this table.

  private static final long servialVersionUID = 203L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @ManyToOne
  private Customer customer;

  @ManyToOne
  private Cart cart;

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

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public Cart getCart() {
    return cart;
  }

  public void setCart(Cart cart) {
    this.cart = cart;
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
