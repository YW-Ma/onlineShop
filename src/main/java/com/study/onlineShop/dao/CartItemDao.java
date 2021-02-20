package com.study.onlineShop.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.study.onlineShop.entity.Cart;
import com.study.onlineShop.entity.CartItem;

@Repository
public class CartItemDao {
  @Autowired
  private SessionFactory sessionFactory;

  public void addCartItem(CartItem cartItem) {
    Session session = null;
    try {
      session = sessionFactory.openSession();
      session.beginTransaction();
      session.saveOrUpdate(cartItem);
      session.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
      session.getTransaction().rollback();
    } finally {
      if (session != null) {
        session.close();
      }
    }
  }

  public void removeCartItem(int cartItemId) {
    Session session = null;
    try {
      session = sessionFactory.openSession();
      CartItem cartItem = session.get(CartItem.class, cartItemId);
      Cart cart = cartItem.getCart();
      List<CartItem> cartItems = cart.getCartItem();
      cartItems.remove(cartItem); // 先修改本地obj里面的cartItem
      session.beginTransaction();
      session.delete(cartItem); // 再写session指令。（否则hibernate自动加回去）
      session.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
      session.getTransaction().rollback();
    } finally {
      if (session != null) {
        session.close();
      }
    }
  }

  public void removeAllCartItems(Cart cart) {
    List<CartItem> cartItems = cart.getCartItem();
    for (CartItem cartItem : cartItems) {
      removeCartItem(cartItem.getId());
    }
  }
}
