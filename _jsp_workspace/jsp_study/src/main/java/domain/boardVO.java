package domain;

public class boardVO {
	private int bno;
	private String title;
	private String writer;
	private String content;
	private String regdate;
	private String moddate;
	private int readCount;
	private String imageFile;
	
	public boardVO() {}

	//insert: title, writer, content
	public boardVO(String title, String writer, String content) {
		this.title = title;
		this.writer = writer;
		this.content = content;
	}
	//list: bno, title, writer, regdate, readCount
	public boardVO(int bno, String title, String writer, String regdate, int readcount) {
		super();
		this.bno = bno;
		this.title = title;
		this.writer = writer;
		this.regdate = regdate;
		this.readCount = readcount;
	}

	//update: bno, title, content
	public boardVO(int bno, String title, String content) {
		super();
		this.bno = bno;
		this.title = title;
		this.content = content;
	}
	
	//detail: all
	public boardVO(int bno, String title, String writer, String content, String regdate, String moddate,
			int readcount, String imageFile) {
		this.bno = bno;
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.regdate = regdate;
		this.moddate = moddate;
		this.readCount = readcount;
		this.imageFile = imageFile;
	}
	//getter setter 

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getModdate() {
		return moddate;
	}

	public void setModdate(String moddate) {
		this.moddate = moddate;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readcount) {
		this.readCount = readcount;
	}
	
	
	public String getImageFile() {
		return imageFile;
	}

	public void setImageFile(String imageFile) {
		this.imageFile = imageFile;
	}

	@Override
	public String toString() {
		return "boardVO [bno=" + bno + ", title=" + title + ", writer=" + writer + ", content=" + content + ", regdate="
				+ regdate + ", moddate=" + moddate + ", readCount=" + readCount + ", imageFile=" + imageFile + "]";
	}	
}
