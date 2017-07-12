package kr.co.bit.board.vo;

public class BoardFileVO {
	private int no;
	private int boradNo;
	private String fileOriName;
	private String fileSaveName;
	private int fileSize;
	public BoardFileVO() {
		super();
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getBoradNo() {
		return boradNo;
	}
	public void setBoradNo(int boradNo) {
		this.boradNo = boradNo;
	}
	public String getFileOriName() {
		return fileOriName;
	}
	public void setFileOriName(String fileOriName) {
		this.fileOriName = fileOriName;
	}
	public String getFileSaveName() {
		return fileSaveName;
	}
	public void setFileSaveName(String fileSaveName) {
		this.fileSaveName = fileSaveName;
	}
	public int getFileSize() {
		return fileSize;
	}
	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}
	@Override
	public String toString() {
		return "BoardFileVO [no=" + no + ", boradNo=" + boradNo + ", fileOriName=" + fileOriName + ", fileSaveName="
				+ fileSaveName + ", fileSize=" + fileSize + "]";
	}
	
	
}	
