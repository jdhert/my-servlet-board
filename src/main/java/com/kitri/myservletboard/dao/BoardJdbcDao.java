package com.kitri.myservletboard.dao;

import com.kitri.myservletboard.data.Board;
import com.kitri.myservletboard.data.Pagination;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class BoardJdbcDao implements BoardDao{


    private static final BoardJdbcDao instance = new BoardJdbcDao();

    public static BoardJdbcDao getInstance(){
        return instance;
    }

    private BoardJdbcDao(){};

    public Connection connectDB(){
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/my_servlet_board";
            String user = "root";
            String pwd = "1234";
            conn = DriverManager.getConnection(url,user,pwd);
        } catch (Exception e){
            e.printStackTrace();
        }
        return conn;
    }
    @Override
    public ArrayList<Board> getAll() {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs =null;

        ArrayList<Board> boards = new ArrayList<>();

        try{
            connection = connectDB();
            String sql = "SELECT * FROM board";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery(sql);

            while(rs.next()) {
                Long id = rs.getLong("id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String writer = rs.getString("Writer");
                LocalDateTime createdAt =  rs.getTimestamp("created_at").toLocalDateTime();
                int viewCount = rs.getInt("view_count");
                int commentCount = rs.getInt("comment_count");

                boards.add(new Board(id,title,content,writer,createdAt,viewCount,commentCount));
            }
        } catch (Exception e){

        } finally {
            try {
                rs.close();
                ps.close();
                connection.close();
            } catch (Exception e){
                e.printStackTrace();;
            }
        }
        return boards;
    }

    @Override
    public ArrayList<Board> getAll(Pagination pagination) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs =null;

        ArrayList<Board> boards = new ArrayList<>();

        try{
            connection = connectDB();
            String sql = "SELECT * FROM board order by id limit ?, ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, ((pagination.getCurrentPage())-1)*pagination.getRecordsPerPage());
            ps.setInt(2, pagination.getRecordsPerPage());
            // currentPage 1 -> 0 2-> 10 3-> 20 4-> 30
            rs = ps.executeQuery();

            while(rs.next()) {
                Long id = rs.getLong("id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String writer = rs.getString("Writer");
                LocalDateTime createdAt =  rs.getTimestamp("created_at").toLocalDateTime();
                int viewCount = rs.getInt("view_count");
                int commentCount = rs.getInt("comment_count");

                boards.add(new Board(id,title,content,writer,createdAt,viewCount,commentCount));
            }
        } catch (Exception e){

        } finally {
            try {
                rs.close();
                ps.close();
                connection.close();
            } catch (Exception e){
                e.printStackTrace();;
            }
        }
        return boards;
    }

    @Override
    public Board getById(Long id) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs =null;
        Board board = null;
        try {
            connection = connectDB();
            String sql = "SELECT * FROM board where id = ?";
            ps = connection.prepareStatement(sql);
            ps.setLong(1 ,id);
            rs = ps.executeQuery();

            while(rs.next()) {
                Long id2 = rs.getLong("id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String writer = rs.getString("Writer");
                LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
                int viewCount = rs.getInt("view_count");
                int commentCount = rs.getInt("comment_count");

                board = new Board(id2, title, content, writer, createdAt, viewCount, commentCount);
            }
        } catch (Exception e){

        }finally {
            try {
                rs.close();
                ps.close();
                connection.close();
            } catch (Exception e){
                e.printStackTrace();;
            }
        }
        return board;
    }
    @Override
    public void save(Board board) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = connectDB();
            String sql = "INSERT INTO board (title, content, writer )values (?,?,?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1,board.getTitle());
            ps.setString(2,board.getContent());
            ps.setString(3,board.getWriter());
            ps.executeUpdate();
        } catch (Exception e){
        }finally {
            try {
                ps.close();
                connection.close();
            } catch (Exception e){
                e.printStackTrace();;
            }
        }
    }

    @Override
    public void update(Board board) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = connectDB();
            String sql = "UPDATE board SET title = ?, content = ?, writer = ? where id = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1,board.getTitle());
            ps.setString(2,board.getContent());
            ps.setString(3,board.getWriter());
            ps.setLong(4,board.getId());
            ps.executeUpdate();
        } catch (Exception e){
        }finally {
            try {
                ps.close();
                connection.close();
            } catch (Exception e){
                e.printStackTrace();;
            }
        }
    }

    @Override
    public void delete(Board board) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = connectDB();
            String sql = "DELETE FROM board where id = ?";
            ps = connection.prepareStatement(sql);
            ps.setLong(1,board.getId());
            ps.executeUpdate();
        } catch (Exception e){
        }finally {
            try {
                ps.close();
                connection.close();
            } catch (Exception e){
                e.printStackTrace();;
            }
        }
    }

    @Override
    public int count() {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs =null;
        int count = 0;
        try{
            connection = connectDB();
            String sql = "SELECT count(*) FROM board";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            rs.next();
            count = rs.getInt("count(*)");

        } catch (Exception e){

        } finally {
            try {
                rs.close();
                ps.close();
                connection.close();
            } catch (Exception e){
                e.printStackTrace();;
            }
        }
        return count;
    }

    public int count(Pagination pagination){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs =null;
        int count = 0;
        try{
            connection = connectDB();
            String sql = "";
            if(pagination.getType().equals("title"))
                sql="SELECT count(*) FROM board where title LIKE ?";
            else sql = "SELECT count(*) FROM board where writer LIKE ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, pagination.getKeyword() + '%');
            rs = ps.executeQuery();
            rs.next();
            count = rs.getInt("count(*)");

        } catch (Exception e){

        } finally {
            try {
                rs.close();
                ps.close();
                connection.close();
            } catch (Exception e){
                e.printStackTrace();;
            }
        }
        return count;
    }
    public ArrayList<Board> getConc(Pagination pagination){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs =null;
        ArrayList<Board> boards = new ArrayList<>();
        try{
            connection = connectDB();
            String sql;
            if(pagination.getType().equals("title"))
                sql="SELECT * FROM board where title LIKE ? order by id limit ?, ?";
            else sql = "SELECT * FROM board where writer LIKE ? order by id limit ?, ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, pagination.getKeyword() + '%');
            ps.setInt(2, ((pagination.getCurrentPage())-1)*pagination.getRecordsPerPage());
            ps.setInt(3, pagination.getRecordsPerPage());
            rs = ps.executeQuery();
            while(rs.next()) {
                Long id = rs.getLong("id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String writer = rs.getString("Writer");
                LocalDateTime createdAt =  rs.getTimestamp("created_at").toLocalDateTime();
                int viewCount = rs.getInt("view_count");
                int commentCount = rs.getInt("comment_count");
                boards.add(new Board(id,title,content,writer,createdAt,viewCount,commentCount));
            }

        } catch (Exception e){
        } finally {
            try {
                rs.close();
                ps.close();
                connection.close();
            } catch (Exception e){
                e.printStackTrace();;
            }
        }
        return boards;
    }
}
