import java.io.BufferedReader;
import java.io.Reader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;



public class DBLayer {
	private Vector<NameValues> executeQuery(Connection con, String sql)
			throws Exception {
		Statement stmt = null;
		ResultSet rs = null;
		Vector<NameValues> records = null;

		if (con == null) {
			throw new SQLException("Socket closed", null, 17410);
		}
		try {
			records = new Vector<NameValues>();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			ResultSetMetaData rs_meta_data = rs.getMetaData();
			String field_name = null;
			String field_type = null;
			int num_cols = rs_meta_data.getColumnCount();
			// for every record, stored as a NameValues, then inserted into a
			// Vector.
			while (rs.next()) {
				NameValues row = new NameValues(num_cols);
				Object obj = null;
				for (int i = 0; i < num_cols; i++) {
					field_name = rs_meta_data.getColumnName(i + 1)
							.toUpperCase();
			
					field_type = rs_meta_data.getColumnTypeName(i + 1)
							.toUpperCase();
					
					obj = rs.getObject(i + 1);
					if (field_type.equals("CLOB")) {
						Clob obj1 = (Clob) obj;
						StringBuilder sb = new StringBuilder();
						if(obj1!=null) {
							Reader reader = obj1.getCharacterStream();
							BufferedReader br = new BufferedReader(reader);
							
							String line;
							while (null != (line = br.readLine())) {
								sb.append(line);
							}
							br.close();
							obj=sb.toString();
						} 
									
					} 

					if (obj == null) {
						obj = "";
					}
					row.put(field_name, obj);
				}
				records.addElement(row);
			}
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {
//					ErrorLog.error(e, "executeQuery");
				}
			}

			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
//					ErrorLog.error(e, "executeQuery");
				}
			}
		}
		return records;
	}
}
