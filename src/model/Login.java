package model;

public class Login {
	private String User;
	private String Id;
	public String getUser() {
		return User;
	}
	public String getId() {
		return Id;
	}
	public Login(String user, String id) {
		super();
		User = user;
		Id = id;
	}
}
