package beans;

public class User {
	/**
	 * 用户ID
	 */
	public int u_id;

	/**
	 * 用户账号
	 */
	public String u_account;
	/**
	 * 密码
	 */
	public String u_pwd;
	/**
	 * 真实姓名
	 */
	public String u_name;
	/**
	 * 账户余额
	 */
	public double u_balance;
	/**
	 * 权限
	 */
	public Integer u_authority;
	/**
	 * 邮箱
	 */
	public String u_email;
	/**
	 * 手机号
	 */
	public String u_telephone;
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	
	
	@Override
	public String toString() {
		return "User [u_id=" + u_id + ", u_account=" + u_account + ", u_pwd=" + u_pwd + ", u_name=" + u_name
				+ ", u_balance=" + u_balance + ", u_authority=" + u_authority + ", u_email=" + u_email
				+ ", u_telephone=" + u_telephone + "]";
	}

	public int getU_id() {
		return u_id;
	}

	public void setU_id(int u_id) {
		this.u_id = u_id;
	}
	
	public String getU_account() {
		return u_account;
	}

	public void setU_account(String u_account) {
		this.u_account = u_account;
	}

	public String getU_pwd() {
		return u_pwd;
	}

	public void setU_pwd(String u_pwd) {
		this.u_pwd = u_pwd;
	}

	public String getU_name() {
		return u_name;
	}

	public void setU_name(String u_name) {
		this.u_name = u_name;
	}

	public double getU_balance() {
		return u_balance;
	}

	public void setU_balance(double u_balance) {
		this.u_balance = u_balance;
	}

	public Integer getU_authority() {
		return u_authority;
	}

	public void setU_authority(Integer u_authority) {
		this.u_authority = u_authority;
	}

	public String getU_email() {
		return u_email;
	}

	public void setU_email(String u_email) {
		this.u_email = u_email;
	}

	public String getU_telephone() {
		return u_telephone;
	}

	public void setU_telephone(String u_telephone) {
		this.u_telephone = u_telephone;
	}
	
}
