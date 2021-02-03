package dto;

import java.sql.Timestamp;

public class MemoDTO {
	//f
	private int no;
	private String writer;
	private String content;
	private Timestamp regiDate;
	
	//c
	public MemoDTO() {
		// TODO Auto-generated constructor stub
	}

	//getter and setter
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getRegiDate() {
		return regiDate;
	}

	public void setRegiDate(Timestamp regiDate) {
		this.regiDate = regiDate;
	}
}
