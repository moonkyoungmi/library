package com.team.lib.command;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.team.lib.dao.LibDao;
import com.team.lib.dto.QnADto;
import com.team.lib.util.Constant;

public class QnAViewCommand implements LibCommand {

	@Override
	public void execute(HttpServletRequest request, Model model) {

		LibDao ldao = Constant.ldao;
		String qnano = request.getParameter("qnaNo");
		int qnaNo = Integer.parseInt(qnano);
		
		QnADto dto = ldao.qna_view(qnaNo);
		
		if(dto != null) {
			model.addAttribute("qna_view", dto);
		}
	}

}
