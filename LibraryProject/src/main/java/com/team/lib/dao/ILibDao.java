package com.team.lib.dao;

import java.util.ArrayList;

import com.team.lib.dto.BookApplicationDto;
import com.team.lib.dto.BookBorrowInfoDto;
import com.team.lib.dto.BookBorrowListDto;
import com.team.lib.dto.BookCommentDto;
import com.team.lib.dto.BookListDto;
import com.team.lib.dto.BookReservationDto;
import com.team.lib.dto.ClassDto;
import com.team.lib.dto.EventDto;
import com.team.lib.dto.LibCalendarDto;
import com.team.lib.dto.LibUserDto;
import com.team.lib.dto.QnAAnswerDto;
import com.team.lib.dto.QnADto;
import com.team.lib.dto.nDto;

public interface ILibDao {
		
	public String join(LibUserDto dto); 				// ȸ������
	public int idCheck(String pid);						//���̵��ߺ�üũ
	public LibUserDto login(String pid); 				// �α���
	public ArrayList<LibUserDto> LibUserInfo(String pid);	// ȸ������ ��������
	public void update(LibUserDto dto);					// ȸ������ ����
	public String pwCheck(String pid);					// ��й�ȣ Ȯ��
	void update_pw(LibUserDto dto);						// ��й�ȣ ����
	
	/* Q & A */
	public ArrayList<QnADto> qnaList(); 				// qna ���
	public String writeQnAOK(QnADto dto); 				// qna ���
	public QnADto qna_view(int qnaNo); 					// qna �󼼺���
	public ArrayList<QnADto> qna_pageList(String pageNo);	// qna ����¡
	public void qnaModify(QnADto dto);					// qna ����
	public void qnaDelete(int qnaNo);					// qna ����
	public void qnaAnswerOK(String answerWriter, String answerContent, int qnaNo); // qna �亯���
	public QnAAnswerDto qna_answer(int qnaNo);			// qna �亯����
	public ArrayList<QnADto> qnaSearch(QnADto dto);		// qna �˻�
	
	/* �����ڷ� �˻� */
	public ArrayList<BookListDto> searchMain(BookListDto bdto);	 // top-menu�� �˻�
	// public ArrayList<BookListDto> searchPage(String pageNo);	 // top-menu�� �˻� ����¡ (����) 
	public ArrayList<BookListDto> searchBook(BookListDto bdto);  // �����ڷ� �˻�


	
	/* ���� ���� */
	public ArrayList<BookListDto> bookNewList(); 			// �Ű� ���� ���
	public ArrayList<BookListDto> newListSearch(BookListDto dto); //�Ű����� �˻�
	public ArrayList<BookListDto> bookList4();			// �Ű� ���� ���� 4�� ���
	public ArrayList<BookListDto> newList_page(String pageNo); // �Ű����� ����¡
	public String bookRegistration(BookListDto dto); 	// ���� ���
	public BookListDto content_view(int bookId); 		// ���� �� ����
	public void bookComment(String commentWriter, String commentContent, int bookId); // ���� ���� ���
	public ArrayList<BookCommentDto> book_comment(int bookId);		// ���� ���� ����
	public void commentDel(BookCommentDto dto);			// ���� ����
	public ArrayList<BookBorrowInfoDto> bookBorrowInfo(int bookId); //���� ���� ��Ȳ
	public void plus_possession(String organName, String referenceRoom,  //���� ���� ���
			String callNum, String regiNum, String bookPossible, String booking, int bookId);
	
	public ArrayList<BookListDto> recList();	  		// ��õ ���� ���
	public ArrayList<BookListDto> recListSearch(BookListDto dto); //�Ű����� �˻�
	public ArrayList<BookListDto> recBookList4();		// ��õ ���� ���� 4�� ���
	public ArrayList<BookListDto> recList_page(String pageNo); // ��õ���� ����¡
	public ArrayList<BookListDto> bookBestList(); 		// ����Ʈ���� ���
	public ArrayList<BookListDto> bestSellerSearch(BookListDto dto); //����Ʈ���� �˻�
	public ArrayList<BookListDto> bestBookList4();		// ����Ʈ���� ���� 4�� ���
	public ArrayList<BookListDto> bestList_page(String pageNo); // ����Ʈ���� ����¡
	
	/* ���� ���� */
	public ArrayList<nDto> nList();		  				// ���
	public ArrayList<nDto> nPageList(String pageNo);	// ��� ����¡
	public nDto nContentView(int nId);	  				// �Խù� ����
	public void writeNotice(nDto nDto);				// �Խù� �ۼ�
	public void modifyN(nDto ndto);						// �Խù� ����
	public nDto noticeModify(String nId);				// ���� ������ ����
	public void deleteN(int nId);						// �Խù� ����
	public ArrayList<nDto> noticeList4();				// ���� ���ο� 4�� ���

	
	/* ���� ���� */
	public ArrayList<ClassDto> cList();					// ��� 
	public ArrayList<ClassDto> cPageList(String pageNo); // ���� ����¡
	public ClassDto cContentView(int cId);				// ���� �� ����
	public String writeClass(ClassDto cdto);			// ���� ���
	public void deleteClass(int cId);					// ���� ����
	public void modifyClass(ClassDto cdto);				// ���� ����
	public ArrayList<ClassDto> searchClass(ClassDto cdto); // ���� �˻�
	
	/* ��ȭ ��� */
	public ArrayList<EventDto> eventList();					// ���
	public EventDto eContentView(int eId);					// ��� �� ����
	public String writeEvent(EventDto edto);				// ��� ���
	public void deleteEvent(int eId);						// ��� ����
	public void modifyEvent(EventDto edto);					// ��� ����
	public ArrayList<EventDto> ePageList(String pageNo);	// ��� ��� ����¡
	public ArrayList<EventDto> searchEvent(EventDto edto);	// ��� �˻�
	public ArrayList<EventDto> eventList3();				// ���� ��� ���
	
	/* ���� ������ */
	public ArrayList<BookListDto> bookList();			//��������
	public ArrayList<LibUserDto> userList();			// ȸ������ ������ ȸ�� ����Ʈ
	public ArrayList<LibUserDto> userPageList(String pageNo); // ȸ������ ������ ȸ�� ����Ʈ ����¡
	public void authorityChange(LibUserDto dto);		//���Ѻ���(������)
	public void authorityChange2(LibUserDto dto);		//���Ѻ���(�Ϲ�ȸ��)
	public ArrayList<LibUserDto> adminUserSearch(LibUserDto dto); //ȸ���˻�
	public LibUserDto userDetail_view(String pid); 		// ȸ�� ������
	public void adminUserDelete(String pid);			// ȸ��Ż��(���α���)
	public void bookModify(BookListDto dto); 			//���� ���� ����
	public void bookDel(BookListDto dto);				//���� ����
	public ArrayList<BookListDto> aBookPList(String pageNo); //�������� ������
	public ArrayList<BookListDto> adminBookSearch(BookListDto dto);	// �������� �˻�
	public void adminBookBorrow(BookBorrowListDto dto); //��������
	public void bookBorrow(BookBorrowInfoDto dto);  	//���� ���� ����
	public void bookReturn(BookBorrowInfoDto dto);  	//���� �ݳ�
	public void bookReservationState(BookReservationDto dto);  	//���� ����� ������������ ������� ����
	public void bookReservationState2(BookReservationDto dto);  //���� �ݳ��� ������������ ������� ����
	public ArrayList<BookBorrowListDto> adminBorrowList(); //��ü������
	public ArrayList<BookBorrowListDto> aBorrowPList(String pageNo); //��ü���� ����¡
	public ArrayList<BookBorrowListDto> adminBorrowSearch(BookBorrowListDto dto);	//��ü���� �˻�
	public void adminBookRendre(BookBorrowListDto dto);	//�ݳ�			
	
	/* Ķ���� */
	public void calendarInsert(LibCalendarDto dto);		//�����߰�
	public ArrayList<LibCalendarDto> calendarList(String cId); //Ķ���� ����Ʈ
	public void calendarUpdate(LibCalendarDto dto);		//Ķ���� ����
	public void calendarDelete(LibCalendarDto dto);		//Ķ���� ����
	
	/* ������� */
	public void bookApplication(BookApplicationDto dto); //������� ��û
	public ArrayList<BookApplicationDto> adminApplication(); //���� ������� ���
	public ArrayList<BookApplicationDto> adminAppList(String pageNo); //������� ����¡
	public void appStateChange(BookApplicationDto dto);	//������� ó������ ����

	/* ���������� */
	public ArrayList<BookApplicationDto> userApplication(String user_id); //���������û����
	public ArrayList<BookReservationDto> userReservation(String user_id); //���������û����
	public ArrayList<BookBorrowListDto> userBList(String user_id); //���⳻��
	public void userReservationCancel(int borrowId); //���� ���
	public void userBookBorrow(BookBorrowListDto dto); //���൵�� ����
	
	/* ���� ���� */
	public void bookReservation(String reservationTitle, String reservationUser, int bookId, int borrowId, String callNum, String bookPossible, String writer); //����
	public void booking(BookBorrowInfoDto dto); //������º���
	public BookBorrowInfoDto bookReservationPage(int borrowId); //����
	public ArrayList<BookReservationDto> adminReservationList(); //���� ���� ���
	public void adminReservationCancel(int borrowId); //���� ���� ���
	public void adminReservationChange(BookBorrowInfoDto dto);	//���� ���� ��ҽ� ������� ����
	public ArrayList<BookReservationDto> adminReservationPageList(String pageNo); //���� ����¡
	public int reservation5Check(String reservationUser);	//���̵��ߺ�üũ
}
