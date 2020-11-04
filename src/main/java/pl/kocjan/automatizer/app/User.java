package pl.kocjan.automatizer.app;

class User {
	private final String username;
	private final String password;
	
	User(String username, String password) {
		this.username = username;
		this.password = password;
		
	}
	
	User(String username) {
		this.username = username;
		this.password = null;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
	
}
