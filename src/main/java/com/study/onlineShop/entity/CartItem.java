package com.study.onlineShop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cartitem")
public class CartItem implements Serializable {
  private static final long serialVersionUID = 202L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  private int quantity;

  private double price; // 其实这里不该维护price，因为加入购物车后价格可能还会变化。

  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JsonIgnore
  private Cart cart;

  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private Product product;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public Cart getCart() {
    return cart;
  }

  public void setCart(Cart cart) {
    this.cart = cart;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }
}
