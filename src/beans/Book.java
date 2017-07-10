package beans;

public class Book {
	/**
	 *  ID
	 */
	public Integer b_id;
	/*
	 * 类型
	 */
	public String b_booktype;
	/*
	 * 出版社
	 */
	public String b_pubs;
	/*
	 * 名称
	 */
	public String b_bookname;
	/*
	 * 作者
	 */
	public String b_author;
	/*
	 * 价格
	 */
	public double b_price;
	/*
	 * 描述
	 */
	public String b_description;
	
	/**
	 * 缩略图片
	 */
	public String b_pic;
	
	/**
	 * 大图
	 */
	public String b_pic_large;


	/**
	 * 是否显示
	 */
	public Integer b_show;
	
	/**
	 * 库存
	 */
	
	
	public Book() {
		// TODO Auto-generated constructor stub
	}


	public Integer getB_id() {
		return b_id;
	}

	public void setB_id(Integer b_id) {
		this.b_id = b_id;
	}

	public String getB_booktype() {
		return b_booktype;
	}

	public void setB_booktype(String b_booktype) {
		this.b_booktype = b_booktype;
	}

	public String getB_pubs() {
		return b_pubs;
	}

	public void setB_pubs(String b_pubs) {
		this.b_pubs = b_pubs;
	}

	public String getB_bookname() {
		return b_bookname;
	}

	public void setB_bookname(String b_bookname) {
		this.b_bookname = b_bookname;
	}

	public String getB_author() {
		return b_author;
	}

	public void setB_author(String b_author) {
		this.b_author = b_author;
	}

	public double getB_price() {
		return b_price;
	}

	public void setB_price(double b_price) {
		this.b_price = b_price;
	}

	public String getB_description() {
		return b_description;
	}

	public void setB_description(String b_description) {
		this.b_description = b_description;
	}
	
	public String getB_pic() {
		return b_pic;
	}

	public void setB_pic(String b_pic) {
		this.b_pic = b_pic;
	}
	
	public String getB_pic_large() {
		return b_pic_large;
	}


	public void setB_pic_large(String b_pic_large) {
		this.b_pic_large = b_pic_large;
	}

	public Integer getB_show() {
		return b_show;
	}

	public void setB_show(Integer b_show) {
		this.b_show = b_show;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((b_author == null) ? 0 : b_author.hashCode());
		result = prime * result + ((b_bookname == null) ? 0 : b_bookname.hashCode());
		result = prime * result + ((b_booktype == null) ? 0 : b_booktype.hashCode());
		result = prime * result + ((b_description == null) ? 0 : b_description.hashCode());
		result = prime * result + ((b_id == null) ? 0 : b_id.hashCode());
		result = prime * result + ((b_pic == null) ? 0 : b_pic.hashCode());
		result = prime * result + ((b_pic_large == null) ? 0 : b_pic_large.hashCode());
		long temp;
		temp = Double.doubleToLongBits(b_price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((b_pubs == null) ? 0 : b_pubs.hashCode());
		result = prime * result + ((b_show == null) ? 0 : b_show.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (b_author == null) {
			if (other.b_author != null)
				return false;
		} else if (!b_author.equals(other.b_author))
			return false;
		if (b_bookname == null) {
			if (other.b_bookname != null)
				return false;
		} else if (!b_bookname.equals(other.b_bookname))
			return false;
		if (b_booktype == null) {
			if (other.b_booktype != null)
				return false;
		} else if (!b_booktype.equals(other.b_booktype))
			return false;
		if (b_description == null) {
			if (other.b_description != null)
				return false;
		} else if (!b_description.equals(other.b_description))
			return false;
		if (b_id == null) {
			if (other.b_id != null)
				return false;
		} else if (!b_id.equals(other.b_id))
			return false;
		if (b_pic == null) {
			if (other.b_pic != null)
				return false;
		} else if (!b_pic.equals(other.b_pic))
			return false;
		if (b_pic_large == null) {
			if (other.b_pic_large != null)
				return false;
		} else if (!b_pic_large.equals(other.b_pic_large))
			return false;
		if (Double.doubleToLongBits(b_price) != Double.doubleToLongBits(other.b_price))
			return false;
		if (b_pubs == null) {
			if (other.b_pubs != null)
				return false;
		} else if (!b_pubs.equals(other.b_pubs))
			return false;
		if (b_show == null) {
			if (other.b_show != null)
				return false;
		} else if (!b_show.equals(other.b_show))
			return false;
		return true;
	}
	
}
