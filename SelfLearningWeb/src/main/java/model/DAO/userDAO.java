package model.DAO;

import model.BEAN.User;

public class userDAO {
	private static final GenericDAO<User> genericDAO = new GenericDAO<>(rs -> 
	    new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("fullname"))
	);
	
	public static Integer addUser(String username, String password, String fullname) {
		String sql = "INSERT INTO users (`username`,`password`,`fullname`) VALUES (?, ?, ?)";
	    int user_id = genericDAO.add(sql, username, password, fullname);
	    return user_id;
	}
	
	public static User login(String username, String password) {
		String sql = "SELECT * FROM users WHERE `username` = ? AND `password` = ? ";
		return genericDAO.find(sql, username,password);
	}
	
	public static User getUser(int id) {
		String sql = "SELECT * FROM users WHERE `id` = ?";
		return genericDAO.find(sql, id);
	}
	
	public static boolean checkPassword(int id, String password) {
		String sql = "SELECT `password` FROM users WHERE `id` = ?";
		return genericDAO.find(sql, id).equals(password);
	}
	
	public static void changeName(int id, String fullname) {
		String sql = "UPDATE users SET `fullname` = ? WHERE `id` = ?";
		genericDAO.updateOrDelete(sql, fullname, id);
	}
	
	public static void changePassword(int id, String password) {
		String sql = "UPDATE users SET `password` = ? WHERE `id` = ?";
		genericDAO.updateOrDelete(sql, password, id);
	}
	
	public static void deleteUser(int id) {
		String sql = "DELETE FROM users WHERE `id` = ?";
		genericDAO.updateOrDelete(sql, id);
	}
}
