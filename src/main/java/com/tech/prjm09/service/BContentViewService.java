package com.tech.prjm09.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.tech.prjm09.dao.IDao;
import com.tech.prjm09.dto.BDto;
import com.tech.prjm09.dto.RebrdimgDto;
import com.tech.prjm09.util.SearchVO;

public class BContentViewService implements BServiceInter{
	private IDao iDao;
	
	public BContentViewService(IDao iDao) {
		this.iDao=iDao;
	}
	@Override
	public void execute(Model model) {
		System.out.println(">>>BContentViewService");
		Map<String, Object> map=model.asMap();
		HttpServletRequest request=
				(HttpServletRequest) map.get("request");
		
		
		
		String bid=request.getParameter("bid");
		
		iDao.upHit(bid);
		
		BDto dto=iDao.contentView(bid);
		model.addAttribute("content_view",dto);
		
//		이미지테이블에서 파일이름가져오기
		List<RebrdimgDto> imgList=iDao.selectImg(bid);
		model.addAttribute("imgList",imgList);
		
	}

}
