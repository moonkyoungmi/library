package com.team.lib.dto;

public class EventDto {
	private int eId;				// id
	private String eTitle;			// ��� �̸�
	private String eGroup;			// ��� �з�
	private String eContent;		// ��� ����
	private String eWriteDate;		// �ۼ���
	private String ePhoto;			// ��� ����
	private String eStartReg;		// ���� ����
	private String eEndReg;			// ���� ����
	private String ePlace;			// ��� ���
	private String eHour;			// ��� �ð�
	private String eStart;			// ��� ����
	private String eEnd;			// ��� ��
	
	// �˻��� 
	private String ekeyword;	
	
	public EventDto() {
		super();
	}
	

	public EventDto(int eId, String eTitle, String eGroup, String eContent, String eWriteDate, String ePhoto,
			String eStartReg, String eEndReg, String ePlace, String eHour, String eStart, String eEnd, String ekeyword) {
		super();
		this.eId = eId;
		this.eTitle = eTitle;
		this.eGroup = eGroup;
		this.eContent = eContent;
		this.eWriteDate = eWriteDate;
		this.ePhoto = ePhoto;
		this.eStartReg = eStartReg;
		this.eEndReg = eEndReg;
		this.ePlace = ePlace;
		this.eHour = eHour;
		this.eStart = eStart;
		this.eEnd = eEnd;
		this.ekeyword = ekeyword;
	}





	public EventDto(int eId, String eTitle, String eGroup, String eContent, String eWriteDate, String ePhoto,
			String eStartReg, String eEndReg, String ePlace, String eHour, String eStart, String eEnd) {
		super();
		this.eId = eId;
		this.eTitle = eTitle;
		this.eGroup = eGroup;
		this.eContent = eContent;
		this.eWriteDate = eWriteDate;
		this.ePhoto = ePhoto;
		this.eStartReg = eStartReg;
		this.eEndReg = eEndReg;
		this.ePlace = ePlace;
		this.eHour = eHour;
		this.eStart = eStart;
		this.eEnd = eEnd;
	}
	
	
	// ��� ����� ���� ������ (File ���)
	public EventDto(int eId, String eTitle, String eGroup, String eContent, String eWriteDate, String eStartReg,
			String eEndReg, String ePlace, String eHour, String eStart, String eEnd) {
		super();
		this.eId = eId;
		this.eTitle = eTitle;
		this.eGroup = eGroup;
		this.eContent = eContent;
		this.eWriteDate = eWriteDate;
		this.eStartReg = eStartReg;
		this.eEndReg = eEndReg;
		this.ePlace = ePlace;
		this.eHour = eHour;
		this.eStart = eStart;
		this.eEnd = eEnd;
	}
	

	// getter, setter 
	public int geteId() {
		return eId;
	}


	public void seteId(int eId) {
		this.eId = eId;
	}


	public String geteTitle() {
		return eTitle;
	}


	public void seteTitle(String eTitle) {
		this.eTitle = eTitle;
	}


	public String geteGroup() {
		return eGroup;
	}


	public void seteGroup(String eGroup) {
		this.eGroup = eGroup;
	}


	public String geteContent() {
		return eContent;
	}


	public void seteContent(String eContent) {
		this.eContent = eContent;
	}


	public String geteWriteDate() {
		return eWriteDate;
	}


	public void seteWriteDate(String eWriteDate) {
		this.eWriteDate = eWriteDate;
	}


	public String getePhoto() {
		return ePhoto;
	}


	public void setePhoto(String ePhoto) {
		this.ePhoto = ePhoto;
	}


	public String geteStartReg() {
		return eStartReg;
	}


	public void seteStartReg(String eStartReg) {
		this.eStartReg = eStartReg;
	}


	public String geteEndReg() {
		return eEndReg;
	}


	public void seteEndReg(String eEndReg) {
		this.eEndReg = eEndReg;
	}


	public String getePlace() {
		return ePlace;
	}


	public void setePlace(String ePlace) {
		this.ePlace = ePlace;
	}


	public String geteHour() {
		return eHour;
	}


	public void seteHour(String eHour) {
		this.eHour = eHour;
	}


	public String geteStart() {
		return eStart;
	}


	public void seteStart(String eStart) {
		this.eStart = eStart;
	}


	public String geteEnd() {
		return eEnd;
	}


	public void seteEnd(String eEnd) {
		this.eEnd = eEnd;
	}

	// �˻��� ���� keyword
	public String geteKeyword() {
		return ekeyword;
	}


	public void seteKeyword(String ekeyword) {
		this.ekeyword = ekeyword;
	}

	

}