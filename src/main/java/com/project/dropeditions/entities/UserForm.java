package com.project.dropeditions.entities;

//class used to process principles info by spring sec
public class UserForm {

	private String username;
	private String email;
	private String password;
	private String repassword;
	
	public UserForm() {
		super();
	}

	public UserForm(String email, String password, String repassword) {
		super();
		this.email = email;
		this.password = password;
		this.repassword = repassword;
	}
	

	public UserForm(String username, String email, String password, String repassword) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.repassword = repassword;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

	@Override
	public String toString() {
		return "UserForm [username=" + username + ", email=" + email + ", password=" + password + ", repassword="
				+ repassword + "]";
	}


	
}
