package com.tech.prjm09.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.tech.prjm09.dao.IDao;
import com.tech.prjm09.dto.BDto;
import com.tech.prjm09.util.SearchVO;

public class BReplyService implements BServiceInter{
	private IDao iDao;
	
	public BReplyService(IDao iDao) {
		this.iDao=iDao;
	}
	@Override
	public void execute(Model model) {
		System.out.println(">>>BReplyService");
		Map<String, Object> map=model.asMap();
		HttpServletRequest request=
				(HttpServletRequest) map.get("request");
		
		String bid=request.getParameter("bid");	
		String bname=request.getParameter("bname");	
		String btitle=request.getParameter("btitle");	
		String bcontent=request.getParameter("bcontent");	
//		String bgroup=request.getParameter("bgroup");	
//		String bstep=request.getParameter("bstep");	
//		String bindent=request.getParameter("bindent");
		int bgroup=Integer.parseInt(request.getParameter("bgroup"));	
		int bstep=Integer.parseInt(request.getParameter("bstep"));	
		int bindent=Integer.parseInt(request.getParameter("bindent"));
		
		iDao.replyShape(bgroup,bstep);
		iDao.reply(bid,bname,btitle,
				bcontent,bgroup,bstep,bindent);
		
		
	}

}
