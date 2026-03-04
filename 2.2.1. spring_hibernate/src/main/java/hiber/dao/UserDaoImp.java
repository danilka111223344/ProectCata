package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Queue;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   public void add(Car car) { sessionFactory.getCurrentSession().save(car); }

   @Override
   public User getUserCar(String model, int series) {
       Session session = sessionFactory.getCurrentSession();
       String hql = "FROM User user WHERE user.car.model = :model AND user.car.series = :series";
       User vladelec = session.createQuery(hql, User.class)
               .setParameter("model", model)
               .setParameter("series", series)
               .uniqueResult();
       return vladelec;
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

}
