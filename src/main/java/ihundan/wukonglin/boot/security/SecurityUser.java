package ihundan.wukonglin.boot.security;


public class SecurityUser {
    private Long objid;
    private String name;
    private String password;
    private String role;
    
	public Long getObjid() {
		return objid;
	}
	public void setObjid(Long objid) {
		this.objid = objid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	public SecurityUser(Long objid, String name, String password, String role) {
		this.objid = objid;
		this.name = name;
		this.password = password;
		this.role = role;
	}
	public SecurityUser() {
	}
	
	@Override
	public String toString() {
		return "SecurityUser [objid=" + objid + ", name=" + name
				+ ", password=" + password + ", role=" + role + "]";
	}
}
