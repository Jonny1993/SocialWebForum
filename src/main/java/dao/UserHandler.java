package dao;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;

import db.HibernateUtil;

@Transactional
public class UserHandler {
	
	public static boolean registerUser(User newUser) {
		Session sess = HibernateUtil.beginTransaction();
		Query<?> query = sess.createQuery("SELECT COUNT(*) FROM User user WHERE user.email = :email");
		query.setParameter("email", newUser.getEmail());
		
		if((Long) query.uniqueResult() != 0) {
			HibernateUtil.commitTransaction(sess);
			return false;
		}
		sess.save(newUser);
		HibernateUtil.commitTransaction(sess);
		System.out.println("The new user id: " + newUser.getId());
		return true;
	}
	
	public static List<User> getAllUsers(){
		Session sess = HibernateUtil.beginTransaction();
		CriteriaBuilder cb = sess.getCriteriaBuilder();
		CriteriaQuery<User> cquery = cb.createQuery(User.class);
		Root<User> root = cquery.from(User.class);
		cquery.select(root);
		
		Query<User> query = sess.createQuery(cquery);
		List<User> users = query.getResultList();
		HibernateUtil.commitTransaction(sess);
		
		return users;
	}
	
	public static List<User> getAllUsersExcept(int userId){
		Session sess = HibernateUtil.beginTransaction();
		CriteriaBuilder cb = sess.getCriteriaBuilder();
		CriteriaQuery<User> cr = cb.createQuery(User.class);
		Root<User> root = cr.from(User.class);
		cr.select(root).where(cb.notEqual(root.get("id"), userId));
		
		Query<User> query = sess.createQuery(cr);
		List<User> users = query.getResultList();
		HibernateUtil.commitTransaction(sess);
		
		return users;
	}
	
	public static LoginResult checkLogin(User u) {
		Session sess = HibernateUtil.beginTransaction();
		CriteriaBuilder cb = sess.getCriteriaBuilder();
		CriteriaQuery<User> cquery = cb.createQuery(User.class);
		Root<User> root = cquery.from(User.class);
		Predicate[] predicates = new Predicate[2];
		predicates[0] = cb.equal(root.get("email"), u.getEmail());
		predicates[1] = cb.equal(root.get("password"), u.getPassword());
		root.getFetches();
		cquery.select(root).where(predicates);
		User theUser = new User();
		try {
			theUser = sess.createQuery(cquery).getSingleResult();
		}catch (Exception e) {
			return null;
		}
		finally {
			HibernateUtil.commitTransaction(sess);
		}
		return new LoginResult(theUser, getAllUsersExcept(theUser.getId()));
	}
	
	public static void closeHibernateSession() {
		HibernateUtil.closeHibernateSession();
	}
}
