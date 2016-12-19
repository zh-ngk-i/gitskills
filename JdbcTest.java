package com.mes.gy.zmds067;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.UUID;

public class JdbcTest { 

	 private static String url="jdbc:oracle:thin:@192.168.1.56:1521:eptest";    
	   //system为登陆oracle数据库的用户名  
	   private static String user="MESDEV";  
	   //manager为用户名system的密码  
	   private static String password="MESDEV";  
	   public static Connection conn;  
	   public static PreparedStatement ps;  
	   public static ResultSet rs;  
	   public static Statement st ;     
	   //连接数据库的方法  
	   public static void getConnection(){  
	       try {  
	           //初始化驱动包  
	           Class.forName("oracle.jdbc.driver.OracleDriver");  
	           //根据数据库连接字符，名称，密码给conn赋值  
	           conn=DriverManager.getConnection(url, user, password);  
	             
	       } catch (Exception e) {  
	           // TODO: handle exception  
	           e.printStackTrace();  
	       }  
	   }  
	    //测试能否与oracle数据库连接成功  
	    public static void main(String[] args) {  
	    	getConnection();
	       JdbcTest basedao=new JdbcTest();   
	       if(conn==null){  
	           System.out.println("与oracle数据库连接失败！");  
	       }else{  
	           System.out.println("与oracle数据库连接成功！");  
	       }  
	    }  
		/*public static String retByGS(String jsgs) throws SQLException{  
		     getConnection(); //同样先要获取连接，即连接到数据库     
		      String dj  ="";
		    //  System.out.println("公式： "+jsgs);   
		    
		          String sql = "select "+jsgs+" from dual";     // 查询数据的sql语句     
		          st = (Statement) conn.createStatement();    //创建用于执行静态sql语句的Statement对象，st属局部变量     
		              
		          ResultSet rs = st.executeQuery(sql);    //执行sql查询语句，返回查询数据的结果集     
		          
		          while(rs.next()){
		        	 dj =  rs.getString(1);    
		          }    
		          conn.close();   //关闭数据库连接    
		 
		      if(dj.length()>15){
					BigDecimal db = new BigDecimal(dj);
					String abc = db.toPlainString();
					DecimalFormat    df   = new DecimalFormat("######0.00"); 
					Double dd = Double.valueOf(abc);
					dj = df.format(dd);
				}
				 if(dj.indexOf(".") > 0){
				     //正则表达
				           dj = dj.replaceAll("0+?$", "");//去掉后面无用的零
				           dj = dj.replaceAll("[.]$", "");//如小数点后面全是零则去掉小数点   
				     }

				  return dj;     
		  } */
		public static String getMainCodeid(String mic) throws SQLException{  
		     getConnection(); //同样先要获取连接，即连接到数据库     
		      String dj  ="";
		    //  System.out.println("公式： "+jsgs);   
		          String sql = "select FCODEID from ZMDS067 WHERE FJJGMICM='"+mic+"'";     // 查询数据的sql语句     
		          st = (Statement) conn.createStatement();    //创建用于执行静态sql语句的Statement对象，st属局部变量     
		          ResultSet rs = st.executeQuery(sql);    //执行sql查询语句，返回查询数据的结果集     
		          while(rs.next()){
		        	 dj =  rs.getString(1);       
		          }    
		          conn.close();   //关闭数据库连接    
				  return dj;     
		  } 
		public static void deleteBymic(String mic) throws SQLException{     
		     getConnection(); //同样先要获取连接，即连接到数据库     
		          String sql = "DELETE FROM  ZMDS067_ITEM WHERE FCBJJGMICM='"+mic+"'";     // 查询数据的sql语句     
		          st = (Statement) conn.createStatement();    //创建用于执行静态sql语句的Statement对象，st属局部变量     
		         st.executeUpdate(sql);    //执行sql查询语句，返回查询数据的结果集     
		          conn.close();   //关闭数据库连接    
		  } 
		public static void insert(String [] args) throws SQLException{  
		     getConnection(); //同样先要获取连接，即连接到数据库     
		          String sql = "INSERT INTO ZMDS067_ITEM(FCODEID,MAIN_CODEID,FCBJJGMICM,FXHMICMZDXLH,FLXMPZGS,FLXMPKGS,FLXMPGGS,FCPCLGS) VALUES('"+args[0]+"','"+args[1]+"','"+args[2]+"','"+args[3]+"','"+args[4]+"','"+args[5]+"','"+args[6]+"','"+args[7]+"')";     // 查询数据的sql语句     
		          st = (Statement) conn.createStatement();    //创建用于执行静态sql语句的Statement对象，st属局部变量     
		         st.executeUpdate(sql);    //执行sql查询语句，返回查询数据的结果集     
		  } 
	
}
