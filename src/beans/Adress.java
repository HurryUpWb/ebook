package beans;

public class Adress {
	private Integer uid;
	private String adress;
	
	public Adress() {
		// TODO Auto-generated constructor stub
	}
	
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}

	public Adress(Integer uid, String adress) {
		super();
		this.uid = uid;
		this.adress = adress;
	}

	@Override
	public String toString() {
		return "Adress [uid=" + uid + ", adress=" + adress + "]";
	}
}
