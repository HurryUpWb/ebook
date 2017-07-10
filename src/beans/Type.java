package beans;

public class Type {
	/**
	 * 类别NO
	 */
	public String TYPENO;
	
	/**
	 * 类别INFO
	 */
	public String TYPEINFO;
	
	public Type() {
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public String toString() {
		return "Type [TYPENO=" + TYPENO + ", TYPEINFO=" + TYPEINFO + "]";
	}

	public String getTYPENO() {
		return TYPENO;
	}

	public void setTYPENO(String tYPENO) {
		TYPENO = tYPENO;
	}

	public String getTYPEINFO() {
		return TYPEINFO;
	}

	public void setTYPEINFO(String tYPEINFO) {
		TYPEINFO = tYPEINFO;
	}
	
	
}
