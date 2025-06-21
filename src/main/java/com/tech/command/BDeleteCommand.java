package com.tech.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.tech.prjm09.dao.BDao;
import com.tech.prjm09.dto.BDto;

public class BDeleteCommand implements BCommand {

	@Override
	public void execute(Model model) {
		
		Map<String, Object> map=model.asMap();
		HttpServletRequest request=
				(HttpServletRequest) map.get("request");
		
		String bid=request.getParameter("bid");	
//		System.out.println(bid);
		
		BDao dao=new BDao();
		dao.delete(bid);
//		BDto dto=dao.contentView(bid);
		
//		model.addAttribute("content_view",dto);

	}

}
