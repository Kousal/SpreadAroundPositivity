package ca.mini.kousel.SpreadAroundPositivity.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "spreadaroundpositivity")
public class spreadaroundpositivity {
	@Id
	private String id;
	private String username;
	private float messageid;
	private String message;
	List<spreadaroundpositivity> messages = new ArrayList<spreadaroundpositivity>();
	public float getMessageid() {
		return messageid;
	}
	public void setMessageid(float messageid) {
		this.messageid = messageid;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<spreadaroundpositivity> getMessages() {
		return messages;
	}
	public void setMessages(List<spreadaroundpositivity> messages) {
		this.messages = messages;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
}
