package model.BEAN;

public class Content {
	String type;
	String text;
	String file_path;
	int session_id;
	int id;
	
	public String getType() {
		return type;
	}
	
	public String getText() {
		return text;
	}
	
	public String getFilePath() {
		return file_path;
	}
	
	public int getSessionID() {
		return session_id;
	}
	
	public int getID() {
		return id;
	}
	
	public Content(int id, int sessionID, String type, String text, String file_path) {
		this.id = id;
		this.session_id = sessionID;
		this.type = type;
		this.text = text;
		this.file_path = file_path;
	}
}
