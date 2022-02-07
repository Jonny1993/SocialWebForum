package dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;

import db.HibernateUtil;

public class MessageHandler {
	
	public static void sendMessage(Message theMessage) {
		Session sess = HibernateUtil.beginTransaction();
		sess.save(theMessage);
		HibernateUtil.commitTransaction(sess);
	}

	public static List<Message> getAllReceived(int receiverId){
		Session sess = HibernateUtil.beginTransaction();
		CriteriaBuilder cb = sess.getCriteriaBuilder();
		CriteriaQuery<Message> cr = cb.createQuery(Message.class);
		Root<Message> root = cr.from(Message.class);
		cr.select(root).where(cb.equal(root.get("receiver"), receiverId));

		Query<Message> query = sess.createQuery(cr);
		List<Message> users = query.getResultList();
		HibernateUtil.commitTransaction(sess);

		return users;
	}

	public static Message getMessageById(int messageId) {
		Session sess = HibernateUtil.beginTransaction();
		Query<?> query = sess.createQuery("FROM Message WHERE id = :id");
		query.setParameter("id", messageId);

		Message theMessage = (Message) query.getSingleResult();
		HibernateUtil.commitTransaction(sess);

		return theMessage;
	}

	public static Message getReceived(int messageId, int receiverId) {
		Session sess = HibernateUtil.beginTransaction();
		Query<?> query = sess.createQuery("FROM Message WHERE id = :id");
		query.setParameter("id", messageId);

		Message theMessage = (Message) query.getSingleResult();
		HibernateUtil.commitTransaction(sess);

		return theMessage;
	}

	public static List<Message> getAllSent(int userId){
		return null;
	}
}
