package model.BEAN;

public class User {
	private int id;
	private String username;
	private String password;
	private String fullname;
	
	public int getID() {
		return id;
	}
	
	public String getFullName() {
		return fullname;
	}
	
	public User(int id, String username, String password, String fullname) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.fullname = fullname;
	}
}
