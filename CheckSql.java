package com.mes.gy.zmds067;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.SQLException;



import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
public class CheckSql {
	public  static String changeChar(String kv,String dest){
		//System.out.println("kv: "+kv+",  DEST:"+dest);  
		//kv: H-30.00;L-50.00;R-215.00;r-;B-20.00,  DEST:H+1.5
		boolean isr = false;
		boolean isR = false;
		if(dest==null||dest.equals("")){
			return dest = "0";    
		}
		if(kv!=null&&!"".equals(kv)){
			if(dest.contains("POWER")){
				isR = true;
				dest = dest.replace("POWER", "TTTTT");
			}
			String[] temp  =kv.split(";");
			for(String s:temp){
				String[] kvv =s.split("-");
				if("r".equals(kvv[0])){
					isr=true;
					dest= dest.replace("floor", "PPPP");
				}
				dest = dest.replace(kvv[0], kvv[1]);
			}
			if(isr){
				dest = dest.replace("PPPP", "floor");
			}
			if(isR){
				dest = dest.replace("TTTTT", "POWER");
			}
		}
		return dest;
	}
	/*public static void main(String [] args){
	
		String kv = "H-"+3+";L-"+3+";R-"+3+";r-"+3+";B-"+3;
		String ss = "B+R-POWER((POWER(R,2)-POWER((L/2),2)),0.5)+0.6+1.8";
		String sql = JdbcTest.retByGS( changeChar(kv, ss));
		System.out.println(sql);
		
		
	}*/
	public static void main(String[] args) {
		String excel= "H:/abc.xls";  
		  try {
	            POIFSFileSystem poifsFileSystem = new POIFSFileSystem(new FileInputStream(excel));
	            HSSFWorkbook workbook = new HSSFWorkbook(poifsFileSystem);
	            HSSFSheet sheet = workbook.getSheetAt(0);
	            StringBuilder sb = new StringBuilder();
	            for (int k = 0; k <= sheet.getLastRowNum(); k++) {
	                HSSFRow row = sheet.getRow(k);
	                    HSSFCell cell0 = row.getCell(0);
	                    HSSFCell cell1 = row.getCell(1);
	                    HSSFCell cell2 = row.getCell(2);
	                    HSSFCell cell3 = row.getCell(3);
	                    HSSFCell cell4 = row.getCell(4);
	                    HSSFCell cell5 = row.getCell(5);
	                    String micM =cell0.getStringCellValue();
	                    
	                    String  gsbh =cell1.getStringCellValue();
	                    kkg = gsbh;
	                    
	                    String cgs ="";
	                    	if(cell2.getCellType()==0){
	                    		cgs = cell2.getNumericCellValue()+"";
	                    	}else if(cell2.getCellType()==1){
	                    		cgs =cell2.getStringCellValue();
	                    	}else if(cell2.getCellType()==3){
	                    		cgs = "";
	                    	}
	                    String kgs="";
	                    if(cell3.getCellType()==0){
	                    	kgs = cell3.getNumericCellValue()+"";
                  	}else if(cell2.getCellType()==1){
                  		kgs =cell3.getStringCellValue();
                  	}else if(cell3.getCellType()==3){
                  		kgs = "";
                  	}
	                    String ghs="";
	                    if(cell4.getCellType()==0){
	                    	ghs = cell4.getNumericCellValue()+"";
                  	}else if(cell4.getCellType()==1){
                  		ghs =cell4.getStringCellValue();
                  	}else if(cell4.getCellType()==3){
                  		ghs = "";
                  	}
	                    String cpcl="";
	                    if(cell5.getCellType()==0){
	                    	cpcl = cell5.getNumericCellValue()+"";
                  	}else if(cell5.getCellType()==1){
                  		cpcl =cell5.getStringCellValue();
                  	}else if(cell5.getCellType()==3){
                  		cpcl = "";
                  	}
	                    String key = micM;
	                    abc =micM;
	                    if("F".equals(micM.substring(0,1))){
	                    	micM = "F";
	                    }else if(micM.contains("YH")){
	                    	micM="H";
	                    }else if(micM.contains("Y0")){
	                    	micM="Y";
	                    }else if(micM.contains("TYW")){
	                    	micM="TYW";
	                    }else if(micM.contains("TRW")){
	                    	micM="TRW";
	                    }else if(micM.contains("MBW")){
	                    	micM="MBW";
	                    }
	                    if( "".equals(cpcl)){}else{
	                    	 cpcl = checksql( micM,cpcl);
	                    }
	                    if("".equals(ghs)){}{
	                    	ghs= checksql( micM,ghs);
	                    }
	                    if( "".equals(kgs)){}{  
	                    	kgs = checksql( micM,kgs);
	                    }
	                    if( "".equals(cgs)){}{        
	                    	cgs =  checksql( micM,cgs);
	                    }
	                   // System.out.println("MIC:"+key+" | 公式编号："+gsbh+" | 长公式："+cgs+" | 宽公式："+kgs+" | 高公式:"+ghs+" | 成品出率："+cpcl); 
	                    sb.append("MIC:"+key+" | 公式编号："+gsbh+" | 长公式："+cgs+" | 宽公式："+kgs+" | 高公式:"+ghs+" | 成品出率："+cpcl).append(System.getProperty("line.separator"));
	                   
	            }
	            ff.write(sb.toString().getBytes());
              ff.close();
	        } catch (Exception e) {
	        	System.out.println("mic:"+abc);
	        	System.out.println("gsbm:"+kkg);
            //  System.out.println("MIC:"+key+" | 公式编号："+gsbh+" | 长公式："+cgs+" | 宽公式："+kgs+" | 高公式:"+ghs+" | 成品出率："+cpcl); 
	            e.printStackTrace();
	        }
	}
/*	public static void main(String[] args) {
		String excel= "H:/abc.xls";   
		String outFile = "H:/abc.txt";
		String abc = "";
		String kkg = "";
	        try {
	            POIFSFileSystem poifsFileSystem = new POIFSFileSystem(new FileInputStream(excel));
	            HSSFWorkbook workbook = new HSSFWorkbook(poifsFileSystem);
	            FileOutputStream ff  = new FileOutputStream(outFile);
	            HSSFSheet sheet = workbook.getSheetAt(0);
	            StringBuilder sb = new StringBuilder();
	            for (int k = 0; k <= sheet.getLastRowNum(); k++) {
	                HSSFRow row = sheet.getRow(k);
	                    HSSFCell cell0 = row.getCell(0);
	                    HSSFCell cell1 = row.getCell(1);
	                    HSSFCell cell2 = row.getCell(2);
	                    HSSFCell cell3 = row.getCell(3);
	                    HSSFCell cell4 = row.getCell(4);
	                    HSSFCell cell5 = row.getCell(5);
	                    String micM =cell0.getStringCellValue();
	                    
	                    String  gsbh =cell1.getStringCellValue();
	                    kkg = gsbh;
	                    
	                    String cgs ="";
	                    	if(cell2.getCellType()==0){
	                    		cgs = cell2.getNumericCellValue()+"";
	                    	}else if(cell2.getCellType()==1){
	                    		cgs =cell2.getStringCellValue();
	                    	}else if(cell2.getCellType()==3){
	                    		cgs = "";
	                    	}
	                    String kgs="";
	                    if(cell3.getCellType()==0){
	                    	kgs = cell3.getNumericCellValue()+"";
                    	}else if(cell2.getCellType()==1){
                    		kgs =cell3.getStringCellValue();
                    	}else if(cell3.getCellType()==3){
                    		kgs = "";
                    	}
	                    String ghs="";
	                    if(cell4.getCellType()==0){
	                    	ghs = cell4.getNumericCellValue()+"";
                    	}else if(cell4.getCellType()==1){
                    		ghs =cell4.getStringCellValue();
                    	}else if(cell4.getCellType()==3){
                    		ghs = "";
                    	}
	                    String cpcl="";
	                    if(cell5.getCellType()==0){
	                    	cpcl = cell5.getNumericCellValue()+"";
                    	}else if(cell5.getCellType()==1){
                    		cpcl =cell5.getStringCellValue();
                    	}else if(cell5.getCellType()==3){
                    		cpcl = "";
                    	}
	                    String key = micM;
	                    abc =micM;
	                    if("F".equals(micM.substring(0,1))){
	                    	micM = "F";
	                    }else if(micM.contains("YH")){
	                    	micM="H";
	                    }else if(micM.contains("Y0")){
	                    	micM="Y";
	                    }else if(micM.contains("TYW")){
	                    	micM="TYW";
	                    }else if(micM.contains("TRW")){
	                    	micM="TRW";
	                    }else if(micM.contains("MBW")){
	                    	micM="MBW";
	                    }
	                    if( "".equals(cpcl)){}else{
	                    	 cpcl = checksql( micM,cpcl);
	                    }
	                    if("".equals(ghs)){}{
	                    	ghs= checksql( micM,ghs);
	                    }
	                    if( "".equals(kgs)){}{  
	                    	kgs = checksql( micM,kgs);
	                    }
	                    if( "".equals(cgs)){}{        
	                    	cgs =  checksql( micM,cgs);
	                    }
	                   // System.out.println("MIC:"+key+" | 公式编号："+gsbh+" | 长公式："+cgs+" | 宽公式："+kgs+" | 高公式:"+ghs+" | 成品出率："+cpcl); 
	                    sb.append("MIC:"+key+" | 公式编号："+gsbh+" | 长公式："+cgs+" | 宽公式："+kgs+" | 高公式:"+ghs+" | 成品出率："+cpcl).append(System.getProperty("line.separator"));
	                   
	            }
	            ff.write(sb.toString().getBytes());
                ff.close();
	        } catch (Exception e) {
	        	System.out.println("mic:"+abc);
	        	System.out.println("gsbm:"+kkg);
              //  System.out.println("MIC:"+key+" | 公式编号："+gsbh+" | 长公式："+cgs+" | 宽公式："+kgs+" | 高公式:"+ghs+" | 成品出率："+cpcl); 
	            e.printStackTrace();
	        }
	}*/
	public static String  checksql(String key,String s) throws SQLException{

			if("F".equals(key)){
				String kv = "A-"+3+";B-"+3+";C-"+3;
				String sql = JdbcTest.retByGS( changeChar(kv, s));
				return sql;
				
		}else if("Y".equals(key)){
			String kv = "D-"+3+";H-"+3;
			String sql = JdbcTest.retByGS( changeChar(kv, s));
			return sql;

		}else if("H".equals(key)){
		//G WJ NJ   
		String kv = "D1-"+3+";H-"+3;
		String sql = JdbcTest.retByGS( changeChar(kv, s));
		return sql;
		}else if("TYW".equals(key)){// H  L   R  r  B
		String kv = "H-"+3+";L-"+3+";R-"+3+";r-"+3+";B-"+3;
		String sql = JdbcTest.retByGS( changeChar(kv, s));
		return sql;  
		}else if("TRW".equals(key)){
		String kv = "H-"+3+";L-"+3+";R-"+3+";r-"+3+";B-"+3;
		String sql = JdbcTest.retByGS( changeChar(kv, s));
		return sql;
		}else if("MBW".equals(key)){      
		String kv = "H-"+3+";L-"+3+";R-"+3+";r-0;B-"+3;      
		String sql = JdbcTest.retByGS( changeChar(kv, s));
		return sql;
		}else {return "";}
	
	}
}

