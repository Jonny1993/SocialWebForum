package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.transaction.Transactional;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity(name = "User")
@Table(name = "user")
@Transactional
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotEmpty(message = "Name cannot be empty")
	private String name;
	@NotEmpty(message = "Email cannot be empty")
	@Email
	private String email;
	@Size(min=4, message = "Password needs to be at least 4 characters")
	private String password;
	
	@OneToMany(
		targetEntity = Message.class,
		mappedBy = "receiver",
		cascade = CascadeType.ALL,
		orphanRemoval = true
	)
	private List<Message> messagesReceived = new ArrayList<>();
	
	public List<Message> getMessagesReceived() {
		return messagesReceived;
	}

	public void setMessagesReceived(List<Message> messagesReceived) {
		this.messagesReceived = messagesReceived;
	}

	public void addMessage(Message m) {
		messagesReceived.add(m);
		m.setReceiver(this);
	}
	
	public void removeMessage(Message m) {
		messagesReceived.remove(m);
		m.setReceiver(null);
	}
	
	@OneToMany(
		targetEntity = Log.class,
		mappedBy = "author",
		cascade = CascadeType.ALL,
		orphanRemoval = true
	)
	private List<Log> logs = new ArrayList<>();
	
	public void addLog(Log l) {
		logs.add(l);
		l.setAuthor(this);
	}
	
	public void removeLog(Log l) {
		logs.remove(l);
		l.setAuthor(null);
	}
	
	public List<Log> getLogs() {
		return logs;
	}

	public void setLogs(List<Log> logs) {
		this.logs = logs;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public User() {}

	public User(int id, String name, String email, String password, List<Message> messagesReceived, List<Log> logs) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.messagesReceived = messagesReceived;
		this.logs = logs;
	}

	public User(String name, String email, String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(!(obj instanceof User)) return false;
		return id == ((User) obj).id;
	};
	
	
}
