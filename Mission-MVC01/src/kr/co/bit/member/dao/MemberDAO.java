package kr.co.bit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.co.bit.member.vo.MemberVO;
import kr.co.bit.util.ConnectionFacotry;

public class MemberDAO {
	//전체 회원 조회
	public List<MemberVO> selectAll(){
		
		List<MemberVO> list = new ArrayList<>();
		
		
		//sql
		StringBuilder sql = new StringBuilder();
		sql.append("select id, name, email_id, email_domain, tel1, tel2, tel3, post, basic_addr, detail_addr, type, ");
		sql.append(" to_char(reg_date, 'yyyy-mm-dd') as reg_date " );
		sql.append(" from t_member ");
		
		try (
				Connection conn = new ConnectionFacotry().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
				){
			ResultSet rs = pstmt.executeQuery();
			
			
			
			while(rs.next()){
				
				MemberVO member = new MemberVO();
				
				member.setId(rs.getString("id"));
				member.setName(rs.getString("name"));
				member.setEmailId(rs.getString("email_id"));
				member.setEmailDomain(rs.getString("email_domain"));
				member.setTel1(rs.getString("tel1"));
				member.setTel2(rs.getString("tel2"));
				member.setTel3(rs.getString("tel3"));
				member.setPost(rs.getString("post"));
				member.setBasicAddr(rs.getString("basic_addr"));
				member.setDetailAddr(rs.getString("detail_addr"));
				member.setType(rs.getString("type"));
				member.setRegDate(rs.getString("reg_date"));
				
				list.add(member);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	//로그인 select MemberVO로 받아오기
	public MemberVO loginSelect(String id, String pw){
		StringBuilder sql = new StringBuilder();
		sql.append("select * from t_member ");
		sql.append(" where id = ? and password = ?");
		MemberVO member = new MemberVO();
		
		try (
				Connection conn = new ConnectionFacotry().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
				){
			
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				member.setId(rs.getString("id"));
				member.setName(rs.getString("name"));
				member.setEmailId(rs.getString("email_id"));
				member.setEmailDomain(rs.getString("email_domain"));
				member.setTel1(rs.getString("tel1"));
				member.setTel2(rs.getString("tel2"));
				member.setTel3(rs.getString("tel3"));
				member.setPost(rs.getString("post"));
				member.setBasicAddr(rs.getString("basic_addr"));
				member.setDetailAddr(rs.getString("detail_addr"));
				member.setType(rs.getString("type"));
				member.setRegDate(rs.getString("reg_date"));
				
			}
			//System.out.println(member);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return member;
	}
	
	//회원가입 Insert
	public void insertMember(String id, String name, String password, String emailId, String emailDomain, String tel1, String tel2, String tel3,
			String post, String basicAddr, String detailAddr){
		
		StringBuffer sql = new StringBuffer();
		sql.append("insert into t_member(id, name, password, email_id, email_domain, tel1, tel2, tel3, post, basic_addr, detail_addr) ");
		sql.append("values(?,?,?,?,?,?,?,?,?,?,?)");
		
		try(
				Connection conn = new ConnectionFacotry().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
				){
			
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, password);
			pstmt.setString(4, emailId);
			pstmt.setString(5, emailDomain);
			pstmt.setString(6, tel1);
			pstmt.setString(7, tel2);
			pstmt.setString(8, tel3);
			pstmt.setString(9, post);
			pstmt.setString(10, basicAddr);
			pstmt.setString(11, detailAddr);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public int confirmMember(String id, String pw){		//수정시 개인정보 확인을 위한 메소드
		int result = 0;
		
		StringBuilder sql = new StringBuilder();
		sql.append("select id, password ");
		sql.append(" from t_member ");
		sql.append(" where id = ? and password = ?");
		
		try (
				Connection conn = new ConnectionFacotry().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
				){
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){
				result=1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	//회원 update
		public void updateMember(String id, String name, String password, String emailId, String emailDomain, String tel1, String tel2, String tel3,
				String post, String basicAddr, String detailAddr){
			
			StringBuffer sql = new StringBuffer();
			sql.append("update t_member ");
			sql.append(" set name = ? , password = ?, email_id = ?, email_domain = ?, tel1 = ?, tel2 = ?, tel3 = ?, post = ?, basic_addr = ?, detail_addr = ? ");
			sql.append(" where id = ?");
			try(
					Connection conn = new ConnectionFacotry().getConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql.toString());
					){
				
				pstmt.setString(1, name);
				pstmt.setString(2, password);
				pstmt.setString(3, emailId);
				pstmt.setString(4, emailDomain);
				pstmt.setString(5, tel1);
				pstmt.setString(6, tel2);
				pstmt.setString(7, tel3);
				pstmt.setString(8, post);
				pstmt.setString(9, basicAddr);
				pstmt.setString(10, detailAddr);
				pstmt.setString(11, id);
				
				pstmt.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	
	//ID 중복 검사
	public int checkID(String id){
		
		int result = 0;
		StringBuilder sql = new StringBuilder();
		sql.append("select id ");
		sql.append(" from t_member ");
		sql.append(" where id = ? ");
		
		try (
				Connection conn = new ConnectionFacotry().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			){
			
			pstmt.setString(1, id);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
