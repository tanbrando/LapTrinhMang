package model.BO;

import java.util.ArrayList;
import java.util.List;

import model.BEAN.Lesson;
import model.BEAN.Section;
import model.DAO.lessonDAO;
import model.DAO.sectionDAO;

public class lessonBO {
	
	public static List<Lesson> getLessonList() {
		return lessonDAO.getLessonList();
	}
	
	public static List<Lesson> getUserLessonList(int userID) {
        return lessonDAO.getUserLessonList(userID);
    }
	
	public static Lesson getLesson(int id) {
		return lessonDAO.getLesson(id);
	}
	
	public static Lesson addLesson(int authorID,String title, String description, String banner_file_path) {
        int newLessonID =  lessonDAO.addLesson(authorID, title, description, banner_file_path);
        return lessonDAO.getLesson(newLessonID);
    }
	
	public static void editLesson(int id, String title, String description, String banner_file_path) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid lesson ID");
        }
        lessonDAO.editLesson(id, title, description, banner_file_path);
    }

    public static void deleteLesson(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid lesson ID");
        }
        lessonDAO.deleteLesson(id);
    }
}
