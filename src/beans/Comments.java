package beans;

public class Comments {
	
	private Integer cid;
	private Integer uid;
	private Integer bid;
	private String u_name;
	private String time;
	private String content;
	
	public Comments() {
		// TODO Auto-generated constructor stub
	}

	public Comments(Integer cid, Integer uid, Integer bid, String u_name, String time, String content) {
		super();
		this.cid = cid;
		this.uid = uid;
		this.bid = bid;
		this.u_name = u_name;
		this.time = time;
		this.content = content;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getBid() {
		return bid;
	}

	public void setBid(Integer bid) {
		this.bid = bid;
	}
	
	

	public String getTime() {
		return time;
	}



	public void setTime(String time) {
		this.time = time;
	}



	public void setU_name(String u_name) {
		this.u_name = u_name;
	}



	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
	public String getU_name() {
		return u_name;
	}
	public void setUname(String u_name) {
		this.u_name = u_name;
	}


	@Override
	public String toString() {
		return "Comments [cid=" + cid + ", uid=" + uid + ", bid=" + bid + ", u_name=" + u_name + ", time=" + time
				+ ", content=" + content + "]";
	}
}
