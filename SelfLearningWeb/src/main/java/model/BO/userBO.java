package model.BO;

import model.BEAN.User;
import model.DAO.userDAO;

public class userBO {
	public static User login(String username, String password) {
		return userDAO.login(username, password);
	}
	
	public static User addUser(String username,String password, String fullname) {
		int newUserID = userDAO.addUser(username, password, fullname);
		return userDAO.getUser(newUserID);
	}
	
	public static void changePassword(int id, String password, String new_password, String confirm_password) {
		if (id <= 0) {
            throw new IllegalArgumentException("Invalid lesson ID");
        }
		
		if (!userDAO.checkPassword(id, confirm_password)) {
			throw new IllegalArgumentException("Mật khẩu không chính xác");
		}
		
		if (!new_password.equals(confirm_password)) {
			throw new IllegalArgumentException("Xác nhận mật khẩu không chính xác");
		}
		
		userDAO.changePassword(id, confirm_password);
	}
	
	public static void changeName(int id, String fullname) {
		if (id <= 0) {
            throw new IllegalArgumentException("Invalid lesson ID");
        }
		
		if (fullname.isBlank()) {
			throw new IllegalArgumentException("Không được để trống");
		}
		
		userDAO.changeName(id, fullname);
	}
}
