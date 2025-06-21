package com.tech.prjm09.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.tech.prjm09.dao.IDao;
import com.tech.prjm09.dto.BDto;
import com.tech.prjm09.util.SearchVO;

public class BReplyViewService implements BServiceInter{
	private IDao iDao;
	
	public BReplyViewService(IDao iDao) {
		this.iDao=iDao;
	}
	@Override
	public void execute(Model model) {
		System.out.println(">>>BReplyViewService");
		Map<String, Object> map=model.asMap();
		HttpServletRequest request=
				(HttpServletRequest) map.get("request");
		
		
		String bid=request.getParameter("bid");		
		BDto dto=iDao.reply_view(bid);

		model.addAttribute("reply_view",dto);
		
		
	}

}
