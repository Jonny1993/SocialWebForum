package dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;

import db.HibernateUtil;

public class LogHandler {
	
	public static void createLog(Log newLog) {
		Session sess = HibernateUtil.beginTransaction();
		sess.save(newLog);
		HibernateUtil.commitTransaction(sess);
	}
	
	public static Log getLogFromDB(int logId) {
		Session sess = HibernateUtil.beginTransaction();
		Query<?> query = sess.createQuery("FROM Log WHERE id = :id");
		query.setParameter("id", logId);
		
		Log log = (Log) query.getSingleResult();
		HibernateUtil.commitTransaction(sess);
		
		return log;
	}
	
	public static List<Log> getUserLogs(int userId){
		Session sess = HibernateUtil.beginTransaction();
		Query<?> query = sess.createQuery("FROM Log WHERE authorId = :id");
		query.setParameter("id", userId);
		
		List<?> logs = query.getResultList();
		HibernateUtil.commitTransaction(sess);
		
		return (List<Log>) logs;
	}
	
	public static User getUserWithLogs(int userId) {
		Session sess = HibernateUtil.beginTransaction();
		CriteriaBuilder cb = sess.getCriteriaBuilder();
		CriteriaQuery<User> cquery = cb.createQuery(User.class);
		Root<User> root = cquery.from(User.class);
		cquery.select(root);
		root.fetch("logs");
		cquery.where(cb.equal(root.get("id"), userId));
		
		Query<User> query = sess.createQuery(cquery);
		User theUser = query.getSingleResult();
		HibernateUtil.commitTransaction(sess);
		
		return theUser;
	}
}
