package kr.co.bit.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.bit.board.vo.BoardFileVO;
import kr.co.bit.board.vo.BoardVO;
import kr.co.bit.util.ConnectionFacotry;

public class BoardDAO {
	
	/**
	 * 전체 게시물 목록 조회하는 기능
	 */
	public List<BoardVO> selectAllBoard(){
		
		List<BoardVO> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = new ConnectionFacotry().getConnection();
			StringBuilder sql = new StringBuilder();
			/*sql.append("select * from ( ");
			sql.append(" select rownum rnum, no, title, writer ");
			sql.append("     , to_char(reg_date, 'yyyy-mm-dd') as reg_date ");
			sql.append(" from t_board ");
			sql.append(" order by no desc) ");
			sql.append(" where rnum between 1 and 5");*/
			
			sql.append(" select rownum rnum, no, title, writer ");
			sql.append("     , to_char(reg_date, 'yyyy-mm-dd') as reg_date ");
			sql.append(" from t_board ");
			sql.append(" order by no desc ");
			
			pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				//결과값 변수에 저장
				int no = rs.getInt("no");
				String title = rs.getString("title");
				String writer = rs.getString("writer");
				String regDate = rs.getString("reg_date");
				
				//변수들을 BoardVO에 저장
				BoardVO board = new BoardVO();
				board.setNo(no);
				board.setTitle(title);
				board.setWriter(writer);
				board.setRegDate(regDate);
				
				//리스트에 BoardVO 전송
				list.add(board);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFacotry.disConnection(conn, pstmt, null);;
		}
		
		return list;
	}
	
	
	public void insertBoard(BoardVO board){		//새글쓰기
		StringBuilder sql = new StringBuilder();
		sql.append("insert into t_board (no, title, writer, content) ");
		sql.append(" values(?, ?, ?, ?)" );
		
		try (
				Connection conn = new ConnectionFacotry().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			int loc = 1;
			pstmt.setInt(loc++, board.getNo());
			pstmt.setString(loc++, board.getTitle());
			pstmt.setString(loc++, board.getWriter());
			pstmt.setString(loc++, board.getContent());
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 게시물 번호에 의한 조회서비스
	 */
	
	public BoardVO selectByNo(int boardNo){		//게시물 번호에 의한 조회서비스
		BoardVO board = new BoardVO();
		Connection conn = new ConnectionFacotry().getConnection();
		StringBuilder sql = new StringBuilder();
		sql.append("select no, title, writer, content, view_cnt, ");
		sql.append(" to_char(reg_date, 'yyyy-mm-dd') as reg_date ");
		sql.append(" from t_board ");
		sql.append(" where no = ? ");
		
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, boardNo);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){
				board.setNo(rs.getInt("no"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setContent(rs.getString("content"));
				board.setViewCnt(rs.getInt("view_cnt"));
				board.setRegDate(rs.getString("reg_date"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionFacotry.disConnection(conn, pstmt, null);
		}
		
		return board;
	}
	
	//조회수 증가 및 수정
	public void updateViewCnt(int no, boolean check){
		
		StringBuilder sql = new StringBuilder();
		sql.append("update t_board ");
		sql.append(" set view_cnt = view_cnt + 1 "  );
		sql.append(" where no = ? ");
		if(check){
			try(
					Connection conn = new ConnectionFacotry().getConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			) {
				pstmt.setInt(1, no);
				pstmt.executeQuery();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	//게시물 수정
	public void updateBoard(BoardVO board){
		StringBuilder sql = new StringBuilder();
		sql.append("update t_board ");
		sql.append(" set title = ?, content = ? ");
		sql.append(" where no = ? ");
		
		try(
				Connection conn = new ConnectionFacotry().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			){
			
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setInt(3, board.getNo());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//DB에서 게시물 삭제
	public void deleteBoard(int boardNo){
		StringBuilder sql = new StringBuilder();
		sql.append("delete from t_board where no = ?");
		
		try (
				Connection conn = new ConnectionFacotry().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
				){
			pstmt.setInt(1, boardNo);
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//DB에서 첨부파일 삭제
	public void deleteBoardFile(int boardNo){
		StringBuilder sql = new StringBuilder();
		sql.append("delete from t_board_file where board_no = ?");
		
		try (
				Connection conn = new ConnectionFacotry().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
				){
			pstmt.setInt(1, boardNo);
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//게시글 번호 추출하는 서비스
	public int selectNo(){
		//int no = 0;
		String sql = "select seq_t_board_no.nextval from dual";
		
		try (
				Connection conn = new ConnectionFacotry().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				) {
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			return rs.getInt(1);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	//첨부파일 관련 CRUD
	/**
	 * 첨부파일 등록 서비스
	 */
	public void insertFile(BoardFileVO fileVO){
		StringBuilder sql = new StringBuilder();
		sql.append("insert into t_board_file ");
		sql.append(" values(seq_t_board_file_no.nextval, ?, ?, ?, ? ) ");
		
		try (
			Connection conn = new ConnectionFacotry().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			pstmt.setInt(1, fileVO.getBoradNo());
			pstmt.setString(2, fileVO.getFileOriName());
			pstmt.setString(3, fileVO.getFileSaveName());
			pstmt.setInt(4, fileVO.getFileSize());
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 게시물 번호에 첨부된 파일리스트 조회서비스, 첨부 파일 삭제 위한 파일 리스트 조회서비스
	 */
	public List<BoardFileVO> selectFileByNo(int boardNo){
		List<BoardFileVO> fileList = new ArrayList<>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select no, file_ori_name, file_save_name, file_size ");
		sql.append(" from t_board_file ");
		sql.append(" where board_no = ? ");
		
		try (
			Connection conn = new ConnectionFacotry().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
				){
			
			pstmt.setInt(1, boardNo);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				BoardFileVO boardFile = new BoardFileVO();
				boardFile.setNo(rs.getInt("no"));
				boardFile.setFileOriName(rs.getString("file_ori_name"));
				boardFile.setFileSaveName(rs.getString("file_save_name"));
				boardFile.setFileSize(rs.getInt("file_size"));
				System.out.println(boardFile);
				fileList.add(boardFile);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return fileList;
	}
	
	//첨부파일 다운로드위한 BoardFileVO객체 저장
	public BoardFileVO selectFileByFileNo(int fileNo){
		
		BoardFileVO boardFile = new BoardFileVO();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select no, file_ori_name, file_save_name, file_size ");
		sql.append(" from t_board_file ");
		sql.append(" where no = ? ");
		
		try (
			Connection conn = new ConnectionFacotry().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
				){
			
			pstmt.setInt(1, fileNo);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				boardFile.setNo(rs.getInt("no"));
				boardFile.setFileOriName(rs.getString("file_ori_name"));
				boardFile.setFileSaveName(rs.getString("file_save_name"));
				boardFile.setFileSize(rs.getInt("file_size"));
				System.out.println(boardFile);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return boardFile;
	}
	
	
}
