package com.tech.prjm09.service;

import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import com.tech.prjm09.dao.IDao;
import com.tech.prjm09.dto.BDto;
import com.tech.prjm09.util.SearchVO;

public class BDownLoadService implements BServiceInter{
	private IDao iDao;
	
	public BDownLoadService(IDao iDao) {
		this.iDao=iDao;
	}
	@Override
	public void execute(Model model) {
		System.out.println(">>>BDownLoadService");
		Map<String, Object> map=model.asMap();
		HttpServletRequest request=
				(HttpServletRequest) map.get("request");
				
		HttpServletResponse response=
				(HttpServletResponse) map.get("response");
				
		
		
		String fname=request.getParameter("f");
		String bid=request.getParameter("bid");
		
		try {
			response.setHeader("Content-Disposition",
					"Attachment;filename=" + URLEncoder.encode(fname, "utf-8"));//첨부, 한글
			String workPath=System.getProperty("user.dir");
			System.out.println("===== : "+workPath);
			
//			String realPath="C:\\24sts4\\24springwork\\prjm29replyboard_mpsupdown_multi\\"
//					+ "src\\main\\resources\\static\\files\\"+fname;
			String realPath=workPath+"\\src\\main\\resources\\static\\files\\"+fname;
			
			FileInputStream fin=new FileInputStream(realPath);
			ServletOutputStream sout=response.getOutputStream();
			
			byte[] buf=new byte[1024];
			int size=0;
			while ((size=fin.read(buf,0,1024))!=-1) {
				sout.write(buf,0,size);
			}
			fin.close();
			sout.close();
		} catch (Exception e) {
			// TODO: handle exception
		}	
	}
}
