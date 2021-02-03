package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import db.DbExam;
import dto.MemoDTO;


public class MemoDAO {
	//f
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	//m
	public Connection getConn() {
		conn = DbExam.dbConn();
		return conn;
	}
	
	public void getConnClose(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		DbExam.dbConnClose(rs, pstmt, conn);
	}
	
	public int setInsert(MemoDTO dto) {
		conn = getConn();
		int result = 0;
		try {
			String sql = "insert into memo values(seq_memo.nextval,?,?,default)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getWriter());
			pstmt.setString(2, dto.getContent());
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getConnClose(rs, pstmt, conn);
		}
		return result;
	}
	
	public ArrayList<MemoDTO> getList(int startRecord, int lastRecord){
		conn = getConn();
		ArrayList<MemoDTO> list = new ArrayList<>();
		try {
			String basicSql = "";
			basicSql += "select * from memo where no >?";
			basicSql += "order by no asc";
			
			String sql = "";
			sql += "select * from (select A.*, Rownum Rnum from("+ basicSql + ") A)";
			sql += "where Rnum >= ? and Rnum <= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 0);
			pstmt.setInt(2, startRecord);
			pstmt.setInt(3, lastRecord);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemoDTO dto = new MemoDTO();
				dto.setNo(rs.getInt("no"));
				dto.setWriter(rs.getString("writer"));
				dto.setContent(rs.getString("content"));
				dto.setRegiDate(rs.getTimestamp("regiDate"));
				list.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getConnClose(rs, pstmt, conn);
		}
		return list;
	}	
	public int getTotalRecord() {
		getConn();
		int result = 0;
		try {
			String sql = "select count(*) from memo where no > ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 0);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}	
}
