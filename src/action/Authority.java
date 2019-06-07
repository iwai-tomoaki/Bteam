package action;

public class Authority {
	private int nuid = 0;
	private String nupa = null;
	public Authority() {}
	public Authority(int nuid,String nupa) {
		this.nuid = nuid;
		this.nupa = nupa;
	}
	public int getNuid() {return nuid;}
	public String getNupa() {return nupa;}

}
