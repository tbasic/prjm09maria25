package com.tech.prjm09.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.tech.prjm09.dao.IDao;
import com.tech.prjm09.util.SearchVO;

public class BListService implements BServiceInter{
	private IDao iDao;
	
	public BListService(IDao iDao) {
		this.iDao=iDao;
	}
	@Override
	public void execute(Model model) {
		System.out.println(">>>BListService");
		Map<String, Object> map=model.asMap();
		HttpServletRequest request=
				(HttpServletRequest) map.get("request");
		
		SearchVO searchVO= 
				(SearchVO) map.get("searchVO");
		
		
//		search
		
		String btitle="";
		String bcontent="";
		
		String[] brdTitle=request.getParameterValues("searchType");
		if(brdTitle!=null) {
			for (int i = 0; i < brdTitle.length; i++) {
				System.out.println("brdTitle:"+brdTitle[i]);
			}
		}
		if(brdTitle!=null) {
			for (String val : brdTitle) {
				if(val.equals("btitle")) {
					model.addAttribute("btitle","true");
					btitle="btitle";
				}
				if(val.equals("bcontent")) {
					model.addAttribute("bcontent","true");
					bcontent="bcontent";
				}
			}
		}
		String searchKeyword=request.getParameter("sk");
		if(searchKeyword==null)
			searchKeyword="";
		model.addAttribute("searchKeyword",searchKeyword);
//		------------검색후에 조건들-----------------
		int total=0;
		if(btitle.equals("btitle") && bcontent.equals("")) {
			total=iDao.selectBoardCount(searchKeyword,"1");
			System.out.println("total 11111111 go>>>>>");
		}else if(btitle.equals("") && bcontent.equals("bcontent")) {
			total=iDao.selectBoardCount(searchKeyword,"2");
			System.out.println("total 22222222 go>>>>>");
		}else if(btitle.equals("btitle") && bcontent.equals("bcontent")) {
			total=iDao.selectBoardCount(searchKeyword,"3");
			System.out.println("total 33333333 go>>>>>");
		}else if(btitle.equals("") && bcontent.equals("")) {
			total=iDao.selectBoardCount(searchKeyword,"4");
			System.out.println("total 44444444 go>>>>>");
		}
		
		
//		paging
		String strPage=request.getParameter("page");
//		null검사
		if(strPage==null) {
			strPage="1";
		}
		//System.out.println("page : "+strPage);
		int page=Integer.parseInt(strPage);
		searchVO.setPage(page);
		
//		글의 총갯수 구하기
//////		int total=iDao.selectBoardCount();
//		System.out.println("total : "+total);
		searchVO.pageCalculate(total);
		
//		page정보확인
		System.out.println("total : "+total);
		System.out.println("clickpage : "+strPage);
		System.out.println("pageStart : "+searchVO.getPageStart());
		System.out.println("pageEnd : "+searchVO.getPageEnd());
		System.out.println("pageTotal : "+searchVO.getTotPage());
		System.out.println("rowStart : "+searchVO.getRowStart());
		System.out.println("rowEnd : "+searchVO.getRowEnd());
		
		int rowStart=searchVO.getRowStart();
		rowStart--;
		int rowEnd=searchVO.getRowEnd();
		
//		ArrayList<BDto> list=iDao.list(rowStart,rowEnd);

		if(btitle.equals("btitle") && bcontent.equals("")) {
			model.addAttribute("list",iDao.list(rowStart,rowEnd,searchKeyword,"1"));
			System.out.println("list 11111111 go>>>>>");
		}else if(btitle.equals("") && bcontent.equals("bcontent")) {
			model.addAttribute("list",iDao.list(rowStart,rowEnd,searchKeyword,"2"));
			System.out.println("list 22222222 go>>>>>");
		}else if(btitle.equals("btitle") && bcontent.equals("bcontent")) {
			model.addAttribute("list",iDao.list(rowStart,rowEnd,searchKeyword,"3"));
			System.out.println("list 33333333 go>>>>>");
		}else if(btitle.equals("") && bcontent.equals("")) {
			model.addAttribute("list",iDao.list(rowStart,rowEnd,searchKeyword,"4"));
			System.out.println("list 44444444 go>>>>>");
		}
		
		
//		model.addAttribute("list",list);
		model.addAttribute("totRowcnt",total);
		model.addAttribute("searchVO",searchVO);
		
		
	}

}
