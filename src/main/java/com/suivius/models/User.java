package com.suivius.models;

import org.json.JSONObject;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users")
public class User {

	private static final String ROLE_ADMIN = "ADMIN";
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;
	private String username;
	private String password;

	private boolean enabled;
	public User() {
	}
	public User(Long id, String username,String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}

	public  long getId() {
		return id;
	}
	public  String getName() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public Date getCreationDate() {
		return null;
	}
	public String getRole() {
		return ROLE_ADMIN;
	}
	  @Override
		public String toString() {
			JSONObject obj = new JSONObject();
			obj.put("id", id)
			.put("name", username)
			.put("password", password);
			return obj.toString(1);
		}
	public String getEmail() {
		return null;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
