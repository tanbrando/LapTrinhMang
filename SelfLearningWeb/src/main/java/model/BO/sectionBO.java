package model.BO;

import java.util.List;

import model.BEAN.Section;
import model.DAO.sectionDAO;

public class sectionBO {

	public static List<Section> getLessonSectionList(int lessonID) {
		return sectionDAO.getLessonSectionList(lessonID);
	}
	
	public static Section addSection(int lessonID, String heading) {
		int newSessionID = sectionDAO.addSection(lessonID, heading);
		return sectionDAO.getSection(newSessionID);
	}
	
	public static void editSection(int id, String heading) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid lesson ID");
        }
        sectionDAO.editSection(id, heading);
    }

    public static void deleteSection(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid lesson ID");
        }
        sectionDAO.deleteSection(id);
    }
}
