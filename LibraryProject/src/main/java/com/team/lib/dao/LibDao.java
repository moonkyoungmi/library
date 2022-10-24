package com.team.lib.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

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

public class LibDao implements ILibDao {

	@Autowired
	private SqlSession sqlSession;
	
	//ȸ������
	@Override
	public String join(LibUserDto dto) {
		String result = null;
		try {
			int res = sqlSession.insert("join", dto);
			System.out.println("ȸ������ res: " + res);
			
			if(res > 0) {
				result = "success";
			}
			else {
				result = "failed";
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			result = "failed";
		}
		return result;
	}
	
	//���̵� �ߺ�üũ
	@Override
	public int idCheck(String pid) {
		int cnt = sqlSession.selectOne("idCheck", pid);
		System.out.println("result : " + cnt);
		return cnt;
	}
	
	//�α���
	@Override
	public LibUserDto login(String pid) {
		System.out.println(pid);
		LibUserDto result = sqlSession.selectOne("login", pid);
		
		return result;
	}
	
	//ȸ������ �ҷ�����
	@Override
	public ArrayList<LibUserDto> LibUserInfo(String pid) {
		System.out.println("LibUserInfo");
		ArrayList<LibUserDto> dtos = (ArrayList)sqlSession.selectList("login", pid);
		return dtos;
	}
	
	//ȸ������ ����
	@Override
	public void update(LibUserDto dto) {
		sqlSession.update("update_info", dto);
	}
	
	//��й�ȣ Ȯ��
	@Override
	public String pwCheck(String pid) {
		return sqlSession.selectOne("pwCheck", pid);
	}
	
	//��й�ȣ ����
	@Override
	public void update_pw(LibUserDto dto) {
		sqlSession.update("update_pw", dto);
	}
	
																			/* Q & A */
	//qna ��� �ҷ�����
	@Override
	public ArrayList<QnADto> qnaList() {
		System.out.println("qnaList");
		ArrayList<QnADto> dtos = (ArrayList)sqlSession.selectList("qnaList");
		
		return dtos;
	}
	
	//qna ���
	@Override
	public String writeQnAOK(QnADto dto) {
		System.out.println("writeQnAOK");
		String result;
		
		int res = sqlSession.insert("writeQnAOK", dto);
		
		if(res == 1) {
			result = "success";
		}
		else {
			result = "failed";
		}
		
		return result;
	}
	
	//qna �󼼺���
	@Override
	public QnADto qna_view(int qnaNo) {
		System.out.println("qna_view");
		QnADto dto = sqlSession.selectOne("qna_view", qnaNo);
		
		return dto;
	}
	
	//qna ����¡
	@Override
	public ArrayList<QnADto> qna_pageList(String pageNo) {
		
		int page = Integer.parseInt(pageNo);
		int startNo = (page - 1) * 10 + 1;
		System.out.println("startNo: " + startNo);
		ArrayList<QnADto> result = (ArrayList)sqlSession.selectList("qna_pageList", startNo); 
		
		return result;
	}
	
	//qna ����
	@Override
	public void qnaModify(QnADto dto) {
		sqlSession.update("qnaModify", dto);
	}
	
	//qna ����
	@Override
	public void qnaDelete(int qnaNo) {
		int res = sqlSession.delete("qnaDelete", qnaNo);
	}
	
	//qna �亯 ���
	@Override
	public void qnaAnswerOK(String answerWriter, String answerContent, int qnaNo) {
		QnAAnswerDto dto = new QnAAnswerDto(answerWriter, answerContent, qnaNo);
		int res = sqlSession.insert("qnaAnswerOK", dto);
	}
	
	//qna �亯 ����
	@Override
	public QnAAnswerDto qna_answer(int qnaNo) {
		QnAAnswerDto dto = sqlSession.selectOne("qna_answer", qnaNo);
		return dto;
	}
	
	//qna �˻�
	@Override
	public ArrayList<QnADto> qnaSearch(QnADto dto) {
		ArrayList<QnADto> result = (ArrayList)sqlSession.selectList("qnaSearch", dto);
		return result;
	}
	
																			/* ���� ���� */
	//�Ű����� ����Ʈ �ҷ�����
	@Override
	public ArrayList<BookListDto> bookNewList() {
		System.out.println("bookNewList");
		ArrayList<BookListDto> dtos = (ArrayList)sqlSession.selectList("bookNewList");
		
		return dtos;
	}
	
	// �Ű����� �˻�
	@Override
	public ArrayList<BookListDto> newListSearch(BookListDto dto) {
		ArrayList<BookListDto> result = (ArrayList)sqlSession.selectList("newListSearch", dto);
		return result;
	}
	
	//�Ű� 4��
	@Override
	public ArrayList<BookListDto> bookList4() {
		System.out.println("bookList4");
		ArrayList<BookListDto> dtos = (ArrayList)sqlSession.selectList("bookList4");
		
		return dtos;
	}
	
	//�Ű����� ����¡
	@Override
	public ArrayList<BookListDto> newList_page(String pageNo) {
		int page = Integer.parseInt(pageNo);
		int startNo = (page - 1) * 10 + 1;
		System.out.println("startNo: " + startNo);
		ArrayList<BookListDto> result = (ArrayList)sqlSession.selectList("newList_page", startNo); 
		
		return result;
	}
	
	// ���� ���
	@Override
	public String bookRegistration(BookListDto dto) {
		System.out.println("bookRegistration");
		String result;
		int res = sqlSession.insert("bookRegistration", dto);
		
		if(res == 1) {
			result = "success";
		}
		else {
			result = "failed";
		}
		return result;
	}
	
	// ���� �󼼺���
	@Override
	public BookListDto content_view(int bookId) {
		System.out.println("content_view");
		BookListDto dto = sqlSession.selectOne("content_view", bookId);
		
		return dto;
	}
	
	// ���� ���� ���
	@Override
	public void bookComment(String commentWriter, String commentContent, int bookId) {
		BookCommentDto dto = new BookCommentDto(commentWriter, commentContent, bookId);
		int res = sqlSession.insert("bookComment", dto);
	}
	
	// ���� ���� ����
	@Override
	public ArrayList<BookCommentDto> book_comment(int bookId) {
		ArrayList<BookCommentDto> dto = (ArrayList)sqlSession.selectList("book_comment", bookId);
		return dto;
	}

	// ���� ����
	@Override
	public void commentDel(BookCommentDto dto) {
		int res = sqlSession.delete("commentDel", dto);
	}
	
	// ���� ���� ��Ȳ
	@Override
	public ArrayList<BookBorrowInfoDto> bookBorrowInfo(int bookId) {
		ArrayList<BookBorrowInfoDto> dto = (ArrayList)sqlSession.selectList("bookBorrow_info", bookId);
		return dto;
	}
	
	//���� ���� ���
	@Override
	public void plus_possession(String organName, String referenceRoom, String callNum, String regiNum, String bookPossible, String booking, int bookId) {
		BookBorrowInfoDto dto = new BookBorrowInfoDto(organName, referenceRoom, callNum, regiNum, bookPossible, booking, bookId);
		sqlSession.insert("plus_possession", dto);
	}
	
	//���� ����
	@Override
	public void adminBookBorrow(BookBorrowListDto dto) {
		sqlSession.insert("adminBookBorrow", dto);
	}
	
	//���� ���� ���� ����
	@Override
	public void bookBorrow(BookBorrowInfoDto dto) {
		sqlSession.update("bookBorrow", dto);
	}
	
	//���� �ݳ� > ������� ����
	@Override
	public void bookReturn(BookBorrowInfoDto dto) {
		sqlSession.update("bookReturn", dto);
	}
	
	//���� �ݳ�
	@Override
	public void adminBookRendre(BookBorrowListDto dto) {
		sqlSession.update("adminBookRendre", dto);
	}
	
	//���� ����� ���� �������� ������� ����
	@Override
	public void bookReservationState(BookReservationDto dto) {
		sqlSession.update("bookReservationState", dto);
	}
	
	//���� �ݳ��� ���� �������� ������� ����
	@Override
	public void bookReservationState2(BookReservationDto dto) {
		sqlSession.update("bookReservationState2", dto);
	}
	
	// ��õ ���� ����
	@Override
	public ArrayList<BookListDto> recList() {
		ArrayList<BookListDto> result = (ArrayList)sqlSession.selectList("recList");
		return result;
	}
	
	// ��õ ���� �˻�
	@Override
	public ArrayList<BookListDto> recListSearch(BookListDto dto) {
		ArrayList<BookListDto> result = (ArrayList)sqlSession.selectList("recListSearch", dto);
		return result;
	}
	
	// ��õ���� 4��
	@Override
	public ArrayList<BookListDto> recBookList4() {
		System.out.println("recBookList4");
		ArrayList<BookListDto> dtos = (ArrayList)sqlSession.selectList("recBookList4");
		
		return dtos;
	}
	
	// ��õ ���� ����¡
	@Override
	public ArrayList<BookListDto> recList_page(String pageNo) {
		int page = Integer.parseInt(pageNo);
		int startNo = (page - 1) * 10 + 1;
		System.out.println("startNo: " + startNo);
		ArrayList<BookListDto> result = (ArrayList)sqlSession.selectList("recList_page", startNo); 
		
		return result;
	}
	
	// ����Ʈ���� ����
	@Override
	public ArrayList<BookListDto> bookBestList() {
		ArrayList<BookListDto> result = (ArrayList)sqlSession.selectList("bookBestList");
		return result;
	}
	
	// ����Ʈ���� �˻�
	@Override
	public ArrayList<BookListDto> bestSellerSearch(BookListDto dto) {
		ArrayList<BookListDto> result = (ArrayList)sqlSession.selectList("bestSellerSearch", dto);
		return result;
	}
	
	// ����Ʈ���� 4��
	@Override
	public ArrayList<BookListDto> bestBookList4() {
		System.out.println("bestBookList4");
		ArrayList<BookListDto> dtos = (ArrayList)sqlSession.selectList("bestBookList4");
		
		return dtos;
	}
	
	// ����Ʈ���� ����¡
	@Override
	public ArrayList<BookListDto> bestList_page(String pageNo) {
		int page = Integer.parseInt(pageNo);
		int startNo = (page - 1) * 10 + 1;
		System.out.println("startNo: " + startNo);
		ArrayList<BookListDto> result = (ArrayList)sqlSession.selectList("bestList_page", startNo); 
		
		return result;
	}	
	
																			/* ���� ���� */
	// ���� ���� ��� �ҷ�����
	@Override
	public ArrayList<nDto> nList() {
		ArrayList<nDto> result = (ArrayList)sqlSession.selectList("nList");
		return result;
	}
	
	// ���� ���� �� ����
	@Override
	public nDto nContentView(int nId) {
		nDto result = sqlSession.selectOne("nContentView", nId);
		return result;
	}
	
	// ���� ���� �ؿ� ������ �ѱ��
	@Override
	public ArrayList<nDto> nPageList(String pageNo) {
		System.out.println("nPageList �޼��� ����");
		int page = Integer.parseInt(pageNo);
		int startNo = (page-1)*7 + 1;
		System.out.println("startNo : " + startNo);
		ArrayList<nDto> result = (ArrayList)sqlSession.selectList("nPageList", startNo);
		return result;
	}
	
	// ���� ���� �ۼ�
	@Override
	public void writeNotice(nDto ndto) {
		System.out.println("writeNotice ó��");
		int res = sqlSession.insert("writeNotice", ndto);
	}
	
	
	// ���� ���� ���� ������ �̵� (������ ������ �� �ҷ�����)
	@Override
	public nDto noticeModify(String nId) {
		nDto result = sqlSession.selectOne("noticeModify", Integer.parseInt(nId));
		return result;
	}
	
	// ���� ���� ���� (����)
	@Override
	public void modifyN(nDto ndto) {
		sqlSession.update("modifyN", ndto);
		
	}
	
	// ���� ���� ����
	@Override
	public void deleteN(int nId) {
		int res = sqlSession.delete("deleteN", nId);
	}
	
	// ���� ���� 4��
	public ArrayList<nDto> noticeList4() {
		ArrayList<nDto> dtos = (ArrayList)sqlSession.selectList("noticeList4");
		return dtos;
	}
	
																	/* ��ȭ ���� */
	// ���� ���																		
	@Override
	public ArrayList<ClassDto> cList() {
		ArrayList<ClassDto> result = (ArrayList)sqlSession.selectList("cList");
		return result;
	}
	
	// ���� ����¡
	public ArrayList<ClassDto> cPageList(String pageNo) {
		System.out.println("���� ����¡ ����");
		int page = Integer.parseInt(pageNo);
		int startNo = (page-1)*7 + 1;
		System.out.println("startNo : " + startNo);
		ArrayList<ClassDto> result = (ArrayList)sqlSession.selectList("cPageList", startNo);
		return result;
	}
	
	// ���� �� ����
	@Override
	public ClassDto cContentView(int cId) {
		ClassDto result = sqlSession.selectOne("cContentView", cId);
		return result;
	}

	// ���� ���
	@Override
	public String writeClass(ClassDto cdto) {
		String result;
		int res = sqlSession.insert("writeClass", cdto);
		
		if(res == 1) {
			result = "success";
		} else {
			result = "failed";
		}
		return result;
	}	

	// ���� ����
	@Override
	public void deleteClass(int cId) {
		int res = sqlSession.delete("deleteClass", cId);
	}

	// ���� ����
	@Override
	public void modifyClass(ClassDto cdto) {
		int res = sqlSession.update("modifyClass", cdto);
	}
	
	// ���� �˻�
	@Override
	public ArrayList<ClassDto> searchClass(ClassDto cdto) {
		ArrayList<ClassDto> result = (ArrayList)sqlSession.selectList("searchClass", cdto);
		return result;
	}
	
	
																			/* ��ȭ ��� */
	// ��� ���
	@Override
	public ArrayList<EventDto> eventList() {
		ArrayList<EventDto> result = (ArrayList)sqlSession.selectList("eventList");
		return result;
	}
	
	// ��� �� ����
	@Override
	public EventDto eContentView(int eId) {
		EventDto result = sqlSession.selectOne("eContentView", eId);
		return result;
	}
	
	// ��� ���
	@Override
	public String writeEvent(EventDto edto) {
		String result;
		int res = sqlSession.insert("writeEvent", edto);
		
		if (res == 1) {
			result = "success";
		} else {
			result = "failed";
		}
		return result;
	}
	
	// ��� ����
	@Override
	public void deleteEvent(int eId) {
		int res = sqlSession.delete("deleteEvent", eId);
	}
	
	// ��� ����
	@Override
	public void modifyEvent(EventDto edto) {
		int res = sqlSession.update("modifyEvent", edto);
	}
	
	// ��� ���� ����¡
	@Override
	public ArrayList<EventDto> ePageList(String pageNo) {
		// TODO Auto-generated method stub
		System.out.println("��� ����¡ ����");
		int page = Integer.parseInt(pageNo);
		int startNo = (page-1)*7 + 1;
		System.out.println("startNo : " + startNo);
		ArrayList<EventDto> result = (ArrayList)sqlSession.selectList("ePageList", startNo);
		return result;
	}
	
	// ��� �˻�
	@Override
	public ArrayList<EventDto> searchEvent(EventDto edto) {
		ArrayList<EventDto> result = (ArrayList)sqlSession.selectList("searchEvent", edto);
		return result;
	}
	
	// ���� ��� ��� 3��
	@Override
	public ArrayList<EventDto> eventList3() {
		System.out.println("eventList3");
		ArrayList<EventDto> dtos = (ArrayList)sqlSession.selectList("eventList3");
		
		return dtos;
	}
	
	
	
	
	
																			/* ���� ������ */
	
	//�������� ����Ʈ �ҷ�����
	@Override
	public ArrayList<BookListDto> bookList() {
		System.out.println("bookList");
		ArrayList<BookListDto> dtos = (ArrayList)sqlSession.selectList("bookList");
		
		return dtos;
	}
		
	// ���������� ȸ������ ȸ�� ����Ʈ
	@Override
	public ArrayList<LibUserDto> userList() {
		ArrayList<LibUserDto> dto = (ArrayList)sqlSession.selectList("userList");
		return dto;
	}
	
	//ȸ�� ����Ʈ ����¡
	@Override
	public ArrayList<LibUserDto> userPageList(String pageNo) {
		int page = Integer.parseInt(pageNo);
		int startNo = (page - 1) * 10 + 1;
		System.out.println("startNo: " + startNo);
		
		ArrayList<LibUserDto> result = (ArrayList)sqlSession.selectList("userPageList", startNo);
		
		return result;
	}
	
	//���Ѻ���(������)
	@Override
	public void authorityChange(LibUserDto dto) {
		sqlSession.update("authorityChange", dto);
	}
	
	//���Ѻ���(�Ϲ�ȸ��)
	@Override
	public void authorityChange2(LibUserDto dto) {
		sqlSession.update("authorityChange2", dto);
	}
	
	//ȸ�� �˻�
	@Override
	public ArrayList<LibUserDto> adminUserSearch(LibUserDto dto) {
		ArrayList<LibUserDto> result = (ArrayList)sqlSession.selectList("adminUserSearch", dto);
		return result;
	}
	
	//ȸ�� ������
	@Override
	public LibUserDto userDetail_view(String pid) {
		LibUserDto dto = sqlSession.selectOne("userDetail", pid);
		
		return dto;
	}
	
	//ȸ��Ż��(���α���)
	@Override
	public void adminUserDelete(String pid) {
		int res = sqlSession.delete("adminUserDelete", pid);
	}
	
	//���� ���� ����
	@Override
	public void bookModify(BookListDto dto) {
		sqlSession.update("bookModify", dto);
	}
	
	//���� ����
	@Override
	public void bookDel(BookListDto dto) {
		sqlSession.delete("bookDel", dto);
	}
	
	//�������� ����¡
	@Override
	public ArrayList<BookListDto> aBookPList(String pageNo) {
		
		int page = Integer.parseInt(pageNo);
		int startNo = (page - 1) * 10 + 1;
		System.out.println("startNo: " + startNo);
		ArrayList<BookListDto> result = (ArrayList)sqlSession.selectList("aBookPList", startNo); 
		
		return result;
	}
	
	//�������� �˻�
	@Override
	public ArrayList<BookListDto> adminBookSearch(BookListDto dto) {
		ArrayList<BookListDto> result = (ArrayList)sqlSession.selectList("adminBookSearch", dto);
		return result;
	}
	
	//Ķ���� insert
	@Override
	public void calendarInsert(LibCalendarDto dto) {
		int res = sqlSession.insert("calendarInsert", dto);
	}
	
	//Ķ���� ����Ʈ
	@Override
	public ArrayList<LibCalendarDto> calendarList(String cId) {
		System.out.println("Ķ���� ����Ʈ �޼���");
		
		ArrayList<LibCalendarDto> list = (ArrayList)sqlSession.selectList("calendarList", cId);
		return list;
	}
	
	//Ķ���� ����
	@Override
	public void calendarUpdate(LibCalendarDto dto) {
		System.out.println("Ķ���� ���� �޼���");
		
		int res = sqlSession.update("calendarUpdate", dto);
	}
	
	//Ķ���� ����
	@Override
	public void calendarDelete(LibCalendarDto dto) {
		System.out.println("Ķ���� ���� �޼���");
		
		int res = sqlSession.delete("calendarDelete", dto);
	}
	
	//���⳻�� ����Ʈ �ҷ�����
	@Override
	public ArrayList<BookBorrowListDto> adminBorrowList() {
		System.out.println("adminBorrowList");
		ArrayList<BookBorrowListDto> dtos = (ArrayList)sqlSession.selectList("adminBorrowList");
		
		return dtos;
	}
	
	// ���⳻�� ����¡
	@Override
	public ArrayList<BookBorrowListDto> aBorrowPList(String pageNo) {
		int page = Integer.parseInt(pageNo);
		int startNo = (page - 1) * 10 + 1;
		System.out.println("startNo: " + startNo);
		ArrayList<BookBorrowListDto> result = (ArrayList)sqlSession.selectList("aBorrowPList", startNo); 
		
		return result;
	}
	
	//���⳻�� �˻�
	@Override
	public ArrayList<BookBorrowListDto> adminBorrowSearch(BookBorrowListDto dto) {
		ArrayList<BookBorrowListDto> result = (ArrayList)sqlSession.selectList("adminBorrowSearch", dto);
		return result;
	}
	
	
																	/* ������� ��û */
	//������� ��û
	public void bookApplication(BookApplicationDto dto) {
		System.out.println("������� ��û �޼���");
		
		sqlSession.insert("bookApplication", dto);
	}
	
	//������� ���
	public ArrayList<BookApplicationDto> adminApplication() {
		System.out.println("������� ��� �޼���");
		ArrayList<BookApplicationDto> dtos = (ArrayList)sqlSession.selectList("adminApplication");
		
		return dtos;
	}
	
	//����������� ����¡
	@Override
	public ArrayList<BookApplicationDto> adminAppList(String pageNo) {
		
		int page = Integer.parseInt(pageNo);
		int startNo = (page - 1) * 10 + 1;
		System.out.println("startNo: " + startNo);
		ArrayList<BookApplicationDto> result = (ArrayList)sqlSession.selectList("adminAppList", startNo); 
		
		return result;
	}
	
	//������� ó������ ����
	@Override
	public void appStateChange(BookApplicationDto dto) {
		sqlSession.update("appStateChange", dto);
	}

	
																			/* ���������� */
	//���������� ������� ���
	public ArrayList<BookApplicationDto> userApplication(String user_id) {
		System.out.println("ȸ�� ������� ��� �޼���");
		ArrayList<BookApplicationDto> dtos = (ArrayList)sqlSession.selectList("userApplication", user_id);
		
		return dtos;
	}
	
	//���������� �������� ���
	public ArrayList<BookReservationDto> userReservation(String user_id) {
		System.out.println("ȸ�� �������� ��� �޼���");
		ArrayList<BookReservationDto> dtos = (ArrayList)sqlSession.selectList("userReservation", user_id);
		
		return dtos;
	}
	
	//���� ���
	@Override
	public void userReservationCancel(int borrowId) {
		sqlSession.delete("userReservationCancel", borrowId);
	}
	
	//���������� ���൵�� ����
	@Override
	public void userBookBorrow(BookBorrowListDto dto) {
		sqlSession.insert("userBookBorrow", dto);
	}
	
	//���������� ���⳻��
	public ArrayList<BookBorrowListDto> userBList(String user_id) {
		System.out.println("ȸ�� ���� ���� �޼���");
		ArrayList<BookBorrowListDto> dtos = (ArrayList)sqlSession.selectList("userBList", user_id);
		
		return dtos;
	}
	
																			/* ���� ���� */
	//���� ����
	@Override
	public void bookReservation(String reservationTitle, String reservationUser, int bookId, int borrowId, String callNum, String bookPossible, String writer) {
		BookReservationDto dto =new BookReservationDto(reservationTitle, reservationUser, bookId, borrowId, callNum, bookPossible, writer);
		sqlSession.insert("bookReservation", dto);
	}
	
	//������� ����
	@Override
	public void booking(BookBorrowInfoDto dto) {
		sqlSession.update("booking", dto);
	}
	
	//����
	@Override
	public BookBorrowInfoDto bookReservationPage(int borrowId) {
		BookBorrowInfoDto dto = sqlSession.selectOne("bookReservationPage", borrowId);
		
		return dto;
	}
	
	//���� ���� ���
	public ArrayList<BookReservationDto> adminReservationList() {
		System.out.println("���� ��� �޼���");
		ArrayList<BookReservationDto> dtos = (ArrayList)sqlSession.selectList("adminReservationList");
		
		return dtos;
	}
	
	//���� ���
	@Override
	public void adminReservationCancel(int borrowId) {
		sqlSession.delete("adminReservationCancel", borrowId);
	}
	
	//���� ������� �� ������� ����
	@Override
	public void adminReservationChange(BookBorrowInfoDto dto) {
		sqlSession.update("adminReservationChange", dto);
	}
	
	//���� ����¡
	@Override
	public ArrayList<BookReservationDto> adminReservationPageList(String pageNo) {
		
		int page = Integer.parseInt(pageNo);
		int startNo = (page - 1) * 10 + 1;
		System.out.println("startNo: " + startNo);
		ArrayList<BookReservationDto> result = (ArrayList)sqlSession.selectList("adminReservationPageList", startNo); 
		
		return result;
	}
	
	//���� 5�� üũ
	@Override
	public int reservation5Check(String reservationUser) {
		int result = sqlSession.selectOne("reservation5Check", reservationUser);
		System.out.println("result : " + result);
		return result;
	}
	
	// top-menu�� �˻�
	@Override
	public ArrayList<BookListDto> searchMain(BookListDto bdto) {
		System.out.println("�˻��ϱ� ���� SQL���� �����մϴ�.");
		ArrayList<BookListDto> result = (ArrayList)sqlSession.selectList("searchMain", bdto);
		return result;
	}
	
	// ���� �ڷ� �˻�
	@Override
	public ArrayList<BookListDto> searchBook(BookListDto bdto) {
		System.out.println("���� �ڷḦ DB���� �˻��մϴ�.");
		ArrayList<BookListDto> result = (ArrayList)sqlSession.selectList("searchBook", bdto);
		return result;
	}

}
