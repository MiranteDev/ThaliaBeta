package atec.thalia.thaliabeta.Model;

public class Comment {
	
	User user;
	String content,
	date;
	
	
	public Comment(User user, String content, String date) {
		super();
		this.user = user;
		this.content = content;
		this.date = date;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}
	
	
	

}
