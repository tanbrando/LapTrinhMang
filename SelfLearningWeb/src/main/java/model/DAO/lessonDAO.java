package model.DAO;

import java.util.ArrayList;
import java.util.List;

import model.BEAN.Lesson;
import model.BEAN.Section;

public class lessonDAO {
	private static final GenericDAO<Lesson> genericDAO = new GenericDAO<>(rs -> 
	    new Lesson(rs.getInt("id"), rs.getInt("author_id"), rs.getString("title"), rs.getString("description"), rs.getString("banner_file_path"))
	);
	
	public static List<Lesson> getLessonList() {
	    String sql = "SELECT * FROM lessons";
	    return genericDAO.list(sql);
	}
	public static List<Lesson> getUserLessonList(int userID) {
	    String sql = "SELECT * FROM lessons WHERE author_id = ?";
	    return genericDAO.list(sql, userID);
	}
	
	public static Lesson getLesson(int id) {
		String sql = "SELECT * FROM lessons WHERE id = ?";
	    Lesson lesson = genericDAO.find(sql, id);
		List<Section> sectionList = sectionDAO.getLessonSectionList(id);
		for (int i = 0; i < sectionList.size(); i++) {
			lesson.addSession(sectionList.get(i));
		}
		return lesson;
	}
	
	public static Integer addLesson(int authorID, String title, String description, String banner_file_path) {
	    String sql = "INSERT INTO lessons (author_id,title,description,banner_file_path ) VALUES (?, ?, ?, ?)";
	    int lesson_id = genericDAO.add(sql, authorID, title, description, banner_file_path);
	    return lesson_id;
	}
	
	public static void editLesson(int id, String title, String description, String banner_file_path) {
		String sql = "UPDATE lessons SET title = ?, description = ?, banner_file_path = ? WHERE id = ?";
		genericDAO.updateOrDelete(sql, title, description, banner_file_path, id);
	}
	
	public static void deleteLesson(int id) {
		sectionDAO.deleteLessonSection(id);
		String sql = "DELETE FROM lessons WHERE id = ?";
		genericDAO.updateOrDelete(sql, id);
	}
}
