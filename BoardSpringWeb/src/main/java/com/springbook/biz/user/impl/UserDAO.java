package com.springbook.biz.user.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Repository;

import com.springbook.biz.common.JDBCUtil;
import com.springbook.biz.user.UserService;
import com.springbook.biz.user.UserVO;

@Repository("userDAO")
public class UserDAO implements UserService {
	
	// JDBC 멤버변수  
	private Connection conn = null; // 데이터베이스 접속 
	private PreparedStatement stmt = null; // SQL 구문 ex) select * from ...
	private ResultSet rs = null; // SQL 실행결과
	
	
	// SQL 구문(CRUD => Create, Read, Update, Delete(Destroy))
	private final String USER_GET = "select * from users where id=? and password=?";
	
	
	@Override
	public UserVO getUser(UserVO vo) {
		
		System.out.println("=======> getUser");
		UserVO user = null;
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(USER_GET);
			stmt.setString(1, vo.getId());
			stmt.setString(2, vo.getPassword());
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				user = new UserVO();
				user.setId(rs.getString("ID"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setName(rs.getString("NAME"));
				user.setRole(rs.getString("ROLE"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		
		return user;
	}

}
