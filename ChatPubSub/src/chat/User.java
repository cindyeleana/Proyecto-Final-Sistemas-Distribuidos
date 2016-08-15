package chat;

public class User {
	private String name;
	private String username;
	private String psw;
	private String status;
	private String state;
	
	public User(){}
	
	public User(String name, String username, String psw){
		this.name = name;
		this.username = username;
		this.psw = psw;
	}
	
	public User(String name, String username, String psw, String status, String state ){
		this.name = name;
		this.username = username;
		this.psw = psw;
		this.status = status;
		this.state = state;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPsw() {
		return psw;
	}
	public void setPsw(String psw) {
		this.psw = psw;
	}
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	
}
