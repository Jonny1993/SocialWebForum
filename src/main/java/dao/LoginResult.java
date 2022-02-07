package dao;

import java.util.List;

public class LoginResult {
	private User loggedInUser;
	private List<User> usersList;
	
	public LoginResult(User loggedInUser, List<User> usersList) {
		this.loggedInUser = loggedInUser;
		this.usersList = usersList;
	}

	public User getLoggedInUser() {
		return loggedInUser;
	}

	public void setLoggedInUser(User loggedInUser) {
		this.loggedInUser = loggedInUser;
	}

	public List<User> getUsersList() {
		return usersList;
	}

	public void setUsersList(List<User> usersList) {
		this.usersList = usersList;
	}
	
	
}
