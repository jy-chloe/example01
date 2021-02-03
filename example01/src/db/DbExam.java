package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DbExam {
	public static Connection dbConn() {
		Db db = new DbOracle();
		return db.dbConn();
	}
	
	public static void dbConnClose(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		Db db = new DbOracle();
		db.dbConnClose(rs, pstmt, conn);
	}
}
