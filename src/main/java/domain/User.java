package domain;

public class User {
	
	//フィールド
	private Integer id;
	private String loginId;
	private String loginPass;
	private String name;
	
	//コンストラクタ
	public User() {
		
	}
	
	public User(Integer id, String loginId, String loginPass, String name) {
		this.id = id;
		this.loginId = loginId;
		this.loginPass = loginPass;
		this.name = name;
	}
	
	//アクセッサ
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getLoginPass() {
		return loginPass;
	}
	public void setLoginPass(String loginPass) {
		this.loginPass = loginPass;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
