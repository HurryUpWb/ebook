package beans;


public class Order {
	/**
	 * 订单编号
	 */
	public Integer o_id;
	/**
	 * 用户ID
	 */
	public Integer u_id;
	/**
	 * 书籍数量
	 */
	public Integer o_booknum;
	/*
	 * 所有书的名称
	 */
	public String o_booksname;
	/*
	 * 下单日期
	 */
	public String  o_orderdate;
	/*
	 * 总金额
	 */
	public double o_totalprice;
	/*
	 * 是否发货
	 */
	public Integer o_isdeliver;
	
	/**
	 * 是否付款
	 */
	public Integer p_state;
	
	public Order() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "Order [o_id=" + o_id + ", u_id=" + u_id + ", o_booknum=" + o_booknum + ", o_booksname=" + o_booksname
				+ ", o_orderdate=" + o_orderdate + ", o_totalprice=" + o_totalprice + ", o_isdeliver=" + o_isdeliver
				+ "]";
	}

	public Integer getO_id() {
		return o_id;
	}

	public void setO_id(Integer o_id) {
		this.o_id = o_id;
	}

	public Integer getU_id() {
		return u_id;
	}

	public void setU_id(Integer u_id) {
		this.u_id = u_id;
	}

	public Integer getO_booknum() {
		return o_booknum;
	}

	public void setO_booknum(Integer o_booknum) {
		this.o_booknum = o_booknum;
	}

	public String getO_booksname() {
		return o_booksname;
	}

	public void setO_booksname(String o_booksname) {
		this.o_booksname =o_booksname;
	}

	
	public String getO_orderdate() {
		return o_orderdate;
	}

	public void setO_orderdate(String o_orderdate) {
		this.o_orderdate = o_orderdate;
	}

	public double getO_totalprice() {
		return o_totalprice;
	}

	public void setO_totalprice(double o_totalprice) {
		this.o_totalprice = o_totalprice;
	}

	public Integer getO_isdeliver() {
		return o_isdeliver;
	}

	public void setO_isdeliver(Integer o_isdeliver) {
		this.o_isdeliver = o_isdeliver;
	}

	public Integer getP_state() {
		return p_state;
	}

	public void setP_state(Integer p_state) {
		this.p_state = p_state;
	}
	
	
}
