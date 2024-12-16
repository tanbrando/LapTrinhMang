package model.DAO;

import java.util.List;

import model.BEAN.Content;

public class contentDAO {
	private static final GenericDAO<Content> genericDAO = new GenericDAO<>(rs -> 
	    new Content(rs.getInt("id"), rs.getInt("section_id"), rs.getString("type"), rs.getString("text"), rs.getString("file_path"))
	);
	
	public static List<Content> getSectionContentList(int sectionID) {
        String sql = "SELECT * FROM contents WHERE section_id = ?";
        return genericDAO.list(sql, sectionID);
    }
	
	public static Content getContent(int id) {
		String sql = "SELECT * FROM contents WHERE id = ?";
	    return genericDAO.find(sql, id);
	}
	
	public static Integer addContent(int sectionID, String type, String text, String file_path) {
        String sql = "INSERT INTO contents (section_id,type,text,file_path ) VALUES (?, ?, ?, ?)";
        int content_id = genericDAO.add(sql, sectionID, type, text, file_path);
        return content_id;
    }
	
	public static void editContent(int id, String type, String text, String file_path) {
		String sql = "UPDATE contents SET type = ?, text = ?, file_path = ? WHERE id = ?";
		genericDAO.updateOrDelete(sql, type, text, file_path, id);
	}
	
	public static void deleteContent(int id) {
		String sql = "DELETE FROM contents WHERE id = ?";
		genericDAO.updateOrDelete(sql, id);
	}
	
	public static void deleteSectionContent(int section_id) {
		String sql = "DELETE FROM contents WHERE section_id = ?";
		genericDAO.updateOrDelete(sql, section_id);
	}
}
