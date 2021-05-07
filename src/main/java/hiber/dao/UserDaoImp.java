package hiber.dao;

import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
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
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("FROM User");
      return query.getResultList();
   }

   @Override
   public List <User> getUser(String model, int series) {
      Session session = sessionFactory.getCurrentSession();
      String GET_ALL = "SELECT u FROM User u INNER JOIN u.car c WHERE c.model = :model and c.series = :series";
      Query queryObject = session.createQuery(GET_ALL);
      queryObject.setParameter("model", model);
      queryObject.setParameter("series", series);
      List<User> list = queryObject.list();
      return list;
   }

}
