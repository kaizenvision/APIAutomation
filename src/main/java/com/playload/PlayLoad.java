package com.playload;

import java.util.List;

public class PlayLoad {
	
	private List<User> user = null;
	
	public PlayLoad() {}
	
	

	public PlayLoad(List<User> user) {
		super();
		this.user = user;
	}



	public List<User> getUser() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}
	
	

}
