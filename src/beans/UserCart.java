package beans;

public class UserCart {
	private int u_id;
	private String books;
	
	public UserCart() {
		// TODO Auto-generated constructor stub
	}
	
	public UserCart(int u_id, String books) {
		super();
		this.u_id = u_id;
		this.books = books;
	}

	public int getU_id() {
		return u_id;
	}

	public void setU_id(int u_id) {
		this.u_id = u_id;
	}

	public String getBooks() {
		return books;
	}

	public void setBooks(String books) {
		this.books = books;
	}
	
	
	
}
