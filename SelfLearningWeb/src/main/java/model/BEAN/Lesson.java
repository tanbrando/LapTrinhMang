package model.BEAN;

import java.util.ArrayList;
import java.util.List;

public class Lesson {
	int id;
	int author_id;
	String title;
	String description;
	String banner_file_path;
	List<Section> sessions;
	String authorName;
	
	public int getID() {
		return id;
	}
	
	public int getAuthorID() {
		return author_id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getBannerFilePath() {
		return banner_file_path;
	}
	public String getAuthorName() {
		return authorName;
	}
	
	public List<Section> getSessions() {
		return sessions;
	}
	
	
	public Lesson(int id, int authorID,String title, String description, String banner_file_path) {
		this.id = id;
		this.author_id = authorID;
		this.title = title;
		this.description = description;
		this.banner_file_path = banner_file_path;
		this.sessions = new ArrayList<>();
	}
	public void addSession(Section newSession) {
		this.sessions.add(newSession);
	}
}
