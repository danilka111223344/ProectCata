package hiber.service;

import hiber.dao.UserDao;
import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

   @Autowired
   private UserDao userDao;

   @Autowired
   private SessionFactory sessionFactory;

   @Transactional
   @Override
   public void add(User user) {
      userDao.add(user);
   }

   @Transactional
   @Override
   public  void add (Car car) { userDao.add(car); }

    @Transactional
    public User getUserCar(String model, int series) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM User user WHERE user.car.model = :model AND user.car.series = :series";
        User vladelec = session.createQuery(hql, User.class)
                .setParameter("model", model)
                .setParameter("series", series)
                .uniqueResult();
        return vladelec;
    }



   @Transactional(readOnly = true)
   @Override
   public List<User> listUsers() {
      return userDao.listUsers();
   }

}
