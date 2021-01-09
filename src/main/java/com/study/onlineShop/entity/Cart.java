package com.study.onlineShop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "cart")
public class Cart implements Serializable {
  private static final long serialVersionUID = 106L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  private double totalPrice;

  @OneToOne(mappedBy = "cart")
  @JsonIgnore
  private Customer customer;

  @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  // 我所参考的东西，需要维护cascade。我自己就可以修改的东西，不需要维护cascade
  // 比如我和Customer的关系，cascade应该写在Customer一侧，因为关联的是id
  private List<CartItem> cartItem;

  //  @OneToMany(mappedBy = "cart")
  //  private SalesOrder salesOrder;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public double getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(double totalPrice) {
    this.totalPrice = totalPrice;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public List<CartItem> getCartItem() {
    return cartItem;
  }

  public void setCartItem(List<CartItem> cartItem) {
    this.cartItem = cartItem;
  }
}
