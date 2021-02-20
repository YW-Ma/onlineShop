package com.study.onlineShop.dao;

import com.study.onlineShop.entity.Authorities;
import com.study.onlineShop.entity.Customer;
import com.study.onlineShop.entity.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

// rewrite this part to be more concise.
@Repository
public class CustomerDao {
  @Autowired
  private SessionFactory sessionFactory;

  public void addCustomer(Customer customer) {
    // create an authorities entry
    Authorities authorities = new Authorities();
    authorities.setEmailId(customer.getUser().getEmailId());
    authorities.setAuthorities("ROLE_USER");

    // commit to database
    Session session = null;
    try {
      session = sessionFactory.openSession();
      session.beginTransaction();
      session.save(authorities);
      session.save(customer); // user已经在customer里面了
      session.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
      session.getTransaction().rollback(); // roll back
    } finally {
      if (session != null) {
        session.close();
      }
    }
  }

  public Customer getCustomerByUserName(String userName) {
    User user = null;
    try (Session session = sessionFactory.openSession()) {

      Criteria criteria = session.createCriteria(User.class);
      user = (User) criteria.add(Restrictions.eq("emailId", userName)).uniqueResult();
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (user != null)
      return user.getCustomer();
    return null;
  }
}
