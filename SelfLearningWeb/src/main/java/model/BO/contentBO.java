package model.BO;

import java.util.List;

import model.BEAN.Content;
import model.DAO.contentDAO;



public class contentBO {
	public List<Content> getSessionContentList(int sectionID) {
        return contentDAO.getSectionContentList(sectionID);
    }
	
	public static Content addContent(int sectionID, String type, String text, String file_path) {
        int newContentID =  contentDAO.addContent(sectionID, type, text, file_path);
        return contentDAO.getContent(newContentID);
    }
	
	public static void editContent(int id, String type, String text, String file_path) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid lesson ID");
        }
        contentDAO.editContent(id, type, text, file_path);
    }

    public static void deleteContent(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid lesson ID");
        }
        contentDAO.deleteContent(id);
    }
    
}
