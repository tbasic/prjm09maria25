package com.tech.prjm09.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.tech.command.BCommand;
import com.tech.prjm09.dao.IDao;
import com.tech.prjm09.service.BContentViewService;
import com.tech.prjm09.service.BDeleteService;
import com.tech.prjm09.service.BDownLoadService;
import com.tech.prjm09.service.BListService;
import com.tech.prjm09.service.BModifyService;
import com.tech.prjm09.service.BReplyService;
import com.tech.prjm09.service.BReplyViewService;
import com.tech.prjm09.service.BServiceInter;
import com.tech.prjm09.service.BWriteService;
import com.tech.prjm09.util.SearchVO;

@Controller
public class BController {
	BCommand command;
	BServiceInter bServiceInter;
	@Autowired
	private IDao iDao;
	@RequestMapping("/")
	public String index(HttpServletRequest request,Model model) {
		System.out.println("index() ctr");

		return "index";
	}
	@RequestMapping("/list")
	public String list(HttpServletRequest request,SearchVO searchVO,Model model) {
		System.out.println("list() ctr");
	
		//searchVO=new SearchVO();
		model.addAttribute("request",request);
		model.addAttribute("searchVO",searchVO);
		bServiceInter=new BListService(iDao);
		bServiceInter.execute(model);
			
		return "list";
	}

	@RequestMapping("/write_view")
	public String write_view(Model model) {
		System.out.println("write_view() ctr");
		
		return "write_view";
	}
	
	@RequestMapping("/write")
	public String write(MultipartHttpServletRequest mtfRequest,
			Model model) {
		System.out.println("write() ctr");

		model.addAttribute("mtfRequest",mtfRequest);
		bServiceInter=new BWriteService(iDao);
		bServiceInter.execute(model);
		
		return "redirect:list";
	}
	@RequestMapping("/download")
	public String download(HttpServletRequest request,
			HttpServletResponse response,Model model) throws Exception {
		System.out.println("download() ctr");
		
		model.addAttribute("request",request);
		model.addAttribute("response",response);
		
		bServiceInter=new BDownLoadService(iDao);
		bServiceInter.execute(model);
		
		String bid=request.getParameter("bid");
		
		return "content_view?bid="+bid;
		
	}
	
	
	@RequestMapping("/content_view")
	public String content_view(HttpServletRequest request,
			Model model) {
		System.out.println("content_view() ctr");
		model.addAttribute("request",request);
		bServiceInter=new BContentViewService(iDao);
		bServiceInter.execute(model);

		return "content_view";
	}
	@RequestMapping(value = "/modify",method = RequestMethod.POST)
	public String modify(HttpServletRequest request,
			Model model) {
		System.out.println("modify() ctr");

		model.addAttribute("request",request);
		bServiceInter=new BModifyService(iDao);
		bServiceInter.execute(model);
		
		return "redirect:list";
	}
	@RequestMapping("/reply_view")
	public String reply_view(HttpServletRequest request,
			Model model) {
		System.out.println("reply_view() ctr");
			
		model.addAttribute("request",request);
		bServiceInter=new BReplyViewService(iDao);
		bServiceInter.execute(model);
		
		return "reply_view";
	}
	@RequestMapping("/reply")
	public String reply(HttpServletRequest request,
			Model model) {
		System.out.println("reply() ctr");
		model.addAttribute("request",request);
		bServiceInter=new BReplyService(iDao);
		bServiceInter.execute(model);
	
		return "redirect:list";
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request,
			Model model) {
		System.out.println("delete() ctr");
		
		model.addAttribute("request",request);
		bServiceInter=new BDeleteService(iDao);
		bServiceInter.execute(model);
	
		return "redirect:list";
	}
}
