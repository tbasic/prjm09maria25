package com.tech.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.tech.prjm09.dao.BDao;

public class BWriteCommand implements BCommand {

	@Override
	public void execute(Model model) {
		
		Map<String, Object> map=model.asMap();
		HttpServletRequest request=
				(HttpServletRequest) map.get("request");
		String bname=request.getParameter("bname");
		String btitle=request.getParameter("btitle");
		String bcontent=request.getParameter("bcontent");
//		System.out.println("bname : "+bname);
		
		BDao dao=new BDao();
		dao.write(bname,btitle,bcontent);

	}

}
