package com.kitri.myservletboard.dao;

import com.kitri.myservletboard.data.Comment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class CommentJdbcDao {
    private static final CommentJdbcDao instance = new CommentJdbcDao();

    public static CommentJdbcDao getInstance(){
        return instance;
    }

    private CommentJdbcDao(){};

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

    public ArrayList<Comment> getComment(Long id){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs =null;
        ArrayList<Comment> comments = new ArrayList<>();
        try{
            connection = connectDB();
            String sql = "SELECT * from comment where board_id like ?";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            while(rs.next()) {
                Long comment_id = rs.getLong("id");
                String content = rs.getString("content");
                LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
                String name = rs.getString("nick_name");
                Long member_id = rs.getLong("member_id");
                comments.add(new Comment(comment_id,content,createdAt,member_id,name, 0L));
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
        return comments;
    }

    public void insertComment(Comment cm){
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = connectDB();
            String sql = "INSERT INTO comment (board_id, member_id, content, nick_name) values (?,?,?,?)";
            ps = connection.prepareStatement(sql);
            ps.setLong(1,cm.getBoard_id());
            ps.setLong(2,cm.getMember_id());
            ps.setString(3, cm.getContent());
            ps.setString(4, cm.getName());
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

    public void deleteComment(long id){
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = connectDB();
            String sql = "DELETE FROM comment where id = ?";
            ps = connection.prepareStatement(sql);
            ps.setLong(1, id);
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
}
