package com.ep.crm.zsm022.dwr;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import javax.sql.DataSource;
import org.epvision.waf.ComponentFactory;
public class AsyncUtil {
	public String checkYPBH(String userID) {
		//dataSource
		DataSource ds  =(DataSource)ComponentFactory.getBean("dataSource");
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CallableStatement proc=null;
		try {
			proc = conn.prepareCall("{call check_struc_p(?,?)}");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			proc.setString(1, userID);
			proc.registerOutParameter(2, Types.VARCHAR);
			proc.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String ret=null;
		try {
			ret = proc.getString(2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return ret;
	}

}
