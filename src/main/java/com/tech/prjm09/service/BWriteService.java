package com.tech.prjm09.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.tech.prjm09.dao.IDao;
import com.tech.prjm09.dto.BDto;
import com.tech.prjm09.util.SearchVO;

public class BWriteService implements BServiceInter{
	private IDao iDao;
	
	public BWriteService(IDao iDao) {
		this.iDao=iDao;
	}
	@Override
	public void execute(Model model) {
		System.out.println(">>>BWriteService");
		Map<String, Object> map=model.asMap();
		MultipartHttpServletRequest mtfRequest=
				(MultipartHttpServletRequest) map.get("mtfRequest");
		
		
		String bname=mtfRequest.getParameter("bname");
		String btitle=mtfRequest.getParameter("btitle");
		String bcontent=mtfRequest.getParameter("bcontent");
		
		iDao.write(bname,btitle,bcontent);
		int bid=iDao.selBid(); //mariadb때 추가로 대치
		iDao.updatebgroup(bid);
		
		
		String workPath=System.getProperty("user.dir");
//		String root="C:\\24sts4\\24springwork\\prjm29replyboard_mpsupdown_multi\\"
//				+ "src\\main\\resources\\static\\files";
		String root=workPath+"\\src\\main\\resources\\static\\files";
		
//		int bid=iDao.selBid();
		
		List<MultipartFile> fileList=mtfRequest.getFiles("file");
		for (MultipartFile mf : fileList) {
			String orginalFile=mf.getOriginalFilename();
			System.out.println("오리지널파일 : "+orginalFile);
			
			long longtime=System.currentTimeMillis();
			String changeFile=longtime+"_"+orginalFile;
			System.out.println("변형파일 : "+changeFile);
			String pathfile=root+"\\"+changeFile;
			
			try {
				if(!orginalFile.equals("")) {
					mf.transferTo(new File(pathfile));
					System.out.println("다중업로드성공");
//					db에 기록
					iDao.imgwrite(bid,orginalFile,changeFile);
					System.out.println("rebrdimgtb write 성공");
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}	
		}
		
	}

}
