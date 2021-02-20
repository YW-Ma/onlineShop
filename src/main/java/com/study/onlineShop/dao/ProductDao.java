package com.study.onlineShop.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.study.onlineShop.entity.Product;

@Repository
public class ProductDao {

  @Autowired
  private SessionFactory sessionFactory;

  public void addProduct(Product product) {
    Session session = null;
    try { // Transaction保证原子操作
      session = sessionFactory.openSession();
      session.beginTransaction();
      session.save(product);
      session.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
      session.getTransaction().rollback();
    } finally {
      if (session != null) { // 添加完后记得关掉，回收资源。
        session.close();
      }
    }
  }

  public void deleteProduct(int productId) {
    Session session = null;
    try {
      session = sessionFactory.openSession();
      session.beginTransaction();
      Product product = session.get(Product.class, productId);
      session.delete(product);
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

  public void updateProduct(Product product) {
    Session session = null;
    try {
      session = sessionFactory.openSession();
      session.beginTransaction();
      session.saveOrUpdate(product);
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

  public Product getProductById(int productId) {
    try (Session session = sessionFactory.openSession()) {
      return session.get(Product.class, productId);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public List<Product> getAllProducts() {
    List<Product> products = new ArrayList<Product>();
    try (Session session = sessionFactory.openSession()) {
      products = session.createCriteria(Product.class).list(); // criteria是在搜索，返回Criteria变量，返回值做.list()获得一个List。
//      实际上是：（和get不同，get返回值就是结果）
//      Criteria crit = session.createCriteria(Product.class);
//      List<Product> results = crit.list();
//
//      带格式的话：
//      Criteria crit = session.createCriteria(Product.class);
//      crit.add(Restrictions.like("name","Mou%",MatchMode.ANYWHERE));
//      List<Product> results = crit.list();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return products;
  }
}
