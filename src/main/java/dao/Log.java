package dao;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity(name = "Log")
@Table(name = "log")
public class Log {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "authorId")
	private User author;
	@NotEmpty(message = "Title cannot be empty")
	private String title;
	private Date datePosted;
	private String content;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getDatePosted() {
		return datePosted;
	}
	public void setDatePosted(Date datePosted) {
		this.datePosted = datePosted;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Log(int id, User author, String title, Date datePosted, String content) {
		super();
		this.id = id;
		this.author = author;
		this.title = title;
		this.datePosted = datePosted;
		this.content = content;
	}
	public Log(User author, String title, Date datePosted, String content) {
		super();
		this.author = author;
		this.title = title;
		this.datePosted = datePosted;
		this.content = content;
	}
	
	public Log() {};
	
}
