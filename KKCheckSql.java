package com.mes.gy.zmds067;
import java.io.FileInputStream;
import java.util.UUID;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
public class KKCheckSql {
	public static void main(String[] args) {
		String excel= "H:/YBSJ.xls";  
		  try {
	            POIFSFileSystem poifsFileSystem = new POIFSFileSystem(new FileInputStream(excel));
	            HSSFWorkbook workbook = new HSSFWorkbook(poifsFileSystem);
	            HSSFSheet sheet = workbook.getSheetAt(0);
	            StringBuilder sb = new StringBuilder();
	          /*  for (int k = 0; k <= sheet.getLastRowNum(); k++) {
	                HSSFRow row = sheet.getRow(k);
	                HSSFCell cell0 = row.getCell(0);
	                String micM =cell0.getStringCellValue();
	                System.out.println(micM);   
	                //清理从表数据   
	                JdbcTest.deleteBymic(micM);
	            }*/
	            //插入从表数据     
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
	                 String maincodeid = JdbcTest.getMainCodeid(micM);
	                 System.out.println(maincodeid);
					String s = UUID.randomUUID().toString();// 
	                 JdbcTest.insert(new String[]{s,maincodeid,micM,gsbh,cgs,kgs,ghs,cpcl});  
	            }   
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}
}

