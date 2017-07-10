package beans;

public class Me {
	private int u_id;
	private String u_account;
	private String u_pwd;
	private String u_name;
	private double u_balance;
	private int u_authority;
	private String u_email;
	private String u_telephone;
	private Cart cart=new Cart();
	
	public Me() {
		// TODO Auto-generated constructor stub
	}

	public Me(int u_id, String u_account, String u_pwd, String u_name, double u_balance, int u_authority,
			String u_email, String u_telephone) {
		super();
		this.u_id = u_id;
		this.u_account = u_account;
		this.u_pwd = u_pwd;
		this.u_name = u_name;
		this.u_balance = u_balance;
		this.u_authority = u_authority;
		this.u_email = u_email;
		this.u_telephone = u_telephone;
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

	public int getU_authority() {
		return u_authority;
	}

	public void setU_authority(int u_authority) {
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

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	@Override
	public String toString() {
		return "Me [u_id=" + u_id + ", u_account=" + u_account + ", u_pwd=" + u_pwd + ", u_name=" + u_name
				+ ", u_balance=" + u_balance + ", u_authority=" + u_authority + ", u_email=" + u_email
				+ ", u_telephone=" + u_telephone + ", cart=" + cart + "]";
	}
	
}
