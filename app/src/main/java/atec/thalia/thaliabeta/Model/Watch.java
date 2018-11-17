package atec.thalia.thaliabeta.Model;

public class Watch {
	
	User user;
	String data;
	public Watch(User user, String data) {
		super();
		this.user = user;
		this.data = data;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	

}
