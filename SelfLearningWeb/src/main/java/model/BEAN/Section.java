package model.BEAN;

import java.util.ArrayList;
import java.util.List;

public class Section {
	String heading;
	int lesson_id;
	int id;
	List<Content> contents;
	
	public String getHeading() {
		return heading;
	}
	
	public int getLessonID() {
		return lesson_id;
	}
	
	public int getID() {
		return id;
	}
	
	public List<Content> getContents() {
		return contents;
	}
	public Section(int id, int lessonID, String heading) {
		this.id = id;
		this.lesson_id = lessonID;
		this.heading = heading;
		this.contents = new ArrayList<Content>();
	}
	
	public void addContent(Content newContent) {
		this.contents.add(newContent);
	}
}
