package com.tech.prjm09.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.tech.prjm09.dto.BDto;
import com.tech.prjm09.dto.RebrdimgDto;

@Mapper
public interface IDao {
//	public ArrayList<BDto> list();
//	public ArrayList<BDto> list(int start,int end);
	public ArrayList<BDto> list(int start,int end,String sk,String selNum);
	public void write(String bname,
			String btitle,String bcontent);
	
	public int selBid();//max
	public void imgwrite(int bid,String orginalFile,
			String changeFile);
	public ArrayList<RebrdimgDto> selectImg(String sbid);
	
	
	public BDto contentView(String sbid);
	public void upHit(String sbid);
	
	public void modify(String bid,String bname,
			String btitle,String bcontent);
	public BDto reply_view(String sbid);
	public void reply(String bid,String bname,
			String btitle,String bcontent,
			int bgroup,int bstep,
			int bindent);
	public void replyShape(int strgroup,
			int strstep);
	public void delete(String sbid);
	
//	public int selectBoardCount();
	public int selectBoardCount(String sk,String selNum);
	
	public void updatebgroup(int bid);
	
	
}
