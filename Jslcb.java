package com.freewalk.util;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.dihua.dhbsp.common.dao.jdbc.ICommonDihuaJdbcDao;
import org.epvision.waf.ComponentFactory;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
public class Jslcb {
public static void main(String[] args) {
	
}
//根据订单交期计算里程碑阶段的最晚时间节点
public Map<String,String> getTimePoint(String nowDate,String jqdate){
	Map<String,String> m = new HashMap<String,String>();
	DateTimeFormatter format  =  DateTimeFormat.forPattern("yyyy-MM-dd");
	int days = 0;
		DateTime dstart = DateTime.parse(nowDate,format);
		DateTime dend   = DateTime.parse(jqdate,format);
		days = Days.daysBetween(dstart, dend).getDays();
    if(days<=0){
    	 return new HashMap<String,String>();
     }
	//查询里程碑百分比
	String sql = "SELECT DISTINCT  FLCBMC,FLCBBH,FLCBJDBZSJ from zsm004 ORDER BY FLCBBH";
	ICommonDihuaJdbcDao dao=(ICommonDihuaJdbcDao) ComponentFactory.getBean("commonDihuaDao");
	List<String[]> lists = dao.query(sql);
	String temp = "";
	int i = 0;
	for(String[] strs:lists){
		String bfb = strs[2].replace("%", ""); 
		String nowper ="";
		if(!"".equals(temp)){
			temp = String.valueOf(Integer.valueOf(bfb)+Integer.valueOf(temp));
			//当前百分比阶段数目
			nowper = temp;
		}else{
			nowper = bfb;
			temp  = bfb;
		}
		//计算距离起始日期的天数
		int point =(int)Math.round(days*Integer.valueOf(nowper)*0.01);
		//最晚完成时间
		if(i==lists.size()-1)
			m.put(strs[1], jqdate);
		else
		m.put(strs[1], dstart.plusDays(point).toString("yyyy-MM-dd"));
		i++;
	}
	return m;
}
}
