package model.DAO;

import java.util.ArrayList;
import java.util.List;

import model.BEAN.Content;
import model.BEAN.Section;

public class sectionDAO {
	private static final GenericDAO<Section> genericDAO = new GenericDAO<>(rs -> 
	    new Section(rs.getInt("id"), rs.getInt("lesson_id"), rs.getString("heading"))
	);
	
	public static List<Section> getLessonSectionList(int lessonID) {
	    String sql = "SELECT * FROM sections WHERE lesson_id = ?";
	    List<Section> sectionList = genericDAO.list(sql, lessonID);
	    for(int i = 0 ; i< sectionList.size(); i++) {
	    	Section section = sectionList.get(i);
	    	List<Content> contentList = contentDAO.getSectionContentList(section.getID());
	    	for(int j = 0; j<contentList.size(); j++) {
	    		section.addContent(contentList.get(j));
	    	}
	    }
	    return sectionList;
	}
	
	public static Section getSection(int id) {
		String sql = "SELECT * FROM sections WHERE id = ?";
	    return genericDAO.find(sql, id);
	}
	
	public static Integer addSection(int lessonID, String heading) {
	    String sql = "INSERT INTO sections (lesson_id,heading) VALUES (?, ?)";
	    int session_id = genericDAO.add(sql, lessonID, heading);
	    return session_id;
	}
	
	public static void editSection(int id, String heading) {
		String sql = "UPDATE sections SET heading = ? WHERE id = ?";
		genericDAO.updateOrDelete(sql, heading, id);
	}
	
	public static void deleteSection(int id) {
		contentDAO.deleteSectionContent(id);
		String sql = "DELETE FROM sections WHERE id = ?";
		genericDAO.updateOrDelete(sql, id);
	}
	
	public static void deleteLessonSection(int lesson_id) {
		List<Section> section_list = getLessonSectionList(lesson_id);
		for(int i = 0; i< section_list.size();i++) {
			contentDAO.deleteSectionContent(section_list.get(i).getID());
			deleteSection(section_list.get(i).getID());
		}
		
	}
	
}
