package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   public void addWithCar(User user, Car car) {
      Session session = sessionFactory.getCurrentSession();
      user.setCar(car);
      car.setUser(user);
      session.save(user);
      session.save(car);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   public User userFromCar (String model, int series) {
      String hql = "from User as u where u.car.model = :model and u.car.series = :series";
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery(hql);
      query.setParameter("model", model);
      query.setParameter("series", series);
      return query.getSingleResult();
   }

   public void removeUserWithCar(User user) {
      Session session = sessionFactory.getCurrentSession();
      session.delete(user);
      session.delete(user.getCar());
   }

}
