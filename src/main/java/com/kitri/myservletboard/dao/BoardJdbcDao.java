package com.kitri.myservletboard.dao;

import com.kitri.myservletboard.data.Board;
import com.kitri.myservletboard.data.Pagination;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

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
//        Connection connection = null;
//        PreparedStatement ps = null;
//        ResultSet rs =null;
//
//        ArrayList<Board> boards = new ArrayList<>();
//
//        try{
//            connection = connectDB();
//            String sql = "SELECT * FROM board";
//            ps = connection.prepareStatement(sql);
//            rs = ps.executeQuery(sql);
//
//            while(rs.next()) {
//                Long id = rs.getLong("id");
//                String title = rs.getString("title");
//                String content = rs.getString("content");
//                String writer = rs.getString("Writer");
//                LocalDateTime createdAt =  rs.getTimestamp("created_at").toLocalDateTime();
//                int viewCount = rs.getInt("view_count");
//                int commentCount = rs.getInt("comment_count");
//
//                boards.add(new Board(id,title,content,writer,createdAt,viewCount,commentCount));
//            }
//        } catch (Exception e){
//
//        } finally {
//            try {
//                rs.close();
//                ps.close();
//                connection.close();
//            } catch (Exception e){
//                e.printStackTrace();;
//            }
//        }
//        return boards;
        return null;
    }

    @Override
    public ArrayList<Board> getAll(Pagination pagination) {
//        Connection connection = null;
//        PreparedStatement ps = null;
//        ResultSet rs =null;
//
//        ArrayList<Board> boards = new ArrayList<>();
//
//        try{
//            connection = connectDB();
//            String sql = "SELECT * FROM board order by " + pagination.getOrderBy() +" desc limit ?, ?";
//            ps = connection.prepareStatement(sql);
//            ps.setInt(1, ((pagination.getCurrentPage())-1)*pagination.getRecordsPerPage());
//            ps.setInt(2, pagination.getRecordsPerPage());
//            rs = ps.executeQuery();
//
//            while(rs.next()) {
//                Long id = rs.getLong("id");
//                String title = rs.getString("title");
//                String content = rs.getString("content");
//                String writer = rs.getString("Writer");
//                LocalDateTime createdAt =  rs.getTimestamp("created_at").toLocalDateTime();
//                int viewCount = rs.getInt("view_count");
//                int commentCount = rs.getInt("comment_count");
//
//                boards.add(new Board(id,title,content,writer,createdAt,viewCount,commentCount));
//            }
//        } catch (Exception e){
//
//        } finally {
//            try {
//                rs.close();
//                ps.close();
//                connection.close();
//            } catch (Exception e){
//                e.printStackTrace();;
//            }
//        }
//        return boards;
        return null;
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
                Long member_id = rs.getLong("member_id");

                board = new Board(id2, title, content, writer, createdAt, viewCount, commentCount, member_id);
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
            String sql = "INSERT INTO board (title, content, writer, member_id )values (?,?,?,?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1,board.getTitle());
            ps.setString(2,board.getContent());
            ps.setString(3,board.getWriter());
            ps.setLong(4,board.getMember_id());
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

//    public int count(Pagination pagination){
//        Connection connection = null;
//        PreparedStatement ps = null;
//        ResultSet rs =null;
//        int count = 0;
//        try{
//            connection = connectDB();
//            String sql;
//            if(pagination.getType().equals("title"))
//                sql="SELECT count(*) FROM board where title LIKE ?";
//            else sql = "SELECT count(*) FROM board where writer LIKE ?";
//            ps = connection.prepareStatement(sql);
//            ps.setString(1, pagination.getKeyword() + '%');
//            rs = ps.executeQuery();
//            rs.next();
//            count = rs.getInt("count(*)");
//
//        } catch (Exception e){
//
//        } finally {
//            try {
//                rs.close();
//                ps.close();
//                connection.close();
//            } catch (Exception e){
//                e.printStackTrace();;
//            }
//        }
//        return count;
//    }
//    public ArrayList<Board> getConc(Pagination pagination){
//        Connection connection = null;
//        PreparedStatement ps = null;
//        ResultSet rs =null;
//        ArrayList<Board> boards = new ArrayList<>();
//        try{
//            connection = connectDB();
//            String sql;
//            if(pagination.getType().equals("title"))
//                sql="SELECT * FROM board where title LIKE ? order by id limit ?, ?";
//            else sql = "SELECT * FROM board where writer LIKE ? order by id limit ?, ?";
//            ps = connection.prepareStatement(sql);
//            ps.setString(1, pagination.getKeyword() + '%');
//            ps.setInt(2, ((pagination.getCurrentPage())-1)*pagination.getRecordsPerPage());
//            ps.setInt(3, pagination.getRecordsPerPage());
//            rs = ps.executeQuery();
//            while(rs.next()) {
//                Long id = rs.getLong("id");
//                String title = rs.getString("title");
//                String content = rs.getString("content");
//                String writer = rs.getString("Writer");
//                LocalDateTime createdAt =  rs.getTimestamp("created_at").toLocalDateTime();
//                int viewCount = rs.getInt("view_count");
//                int commentCount = rs.getInt("comment_count");
//                boards.add(new Board(id,title,content,writer,createdAt,viewCount,commentCount));
//            }
//
//        } catch (Exception e){
//        } finally {
//            try {
//                rs.close();
//                ps.close();
//                connection.close();
//            } catch (Exception e){
//                e.printStackTrace();;
//            }
//        }
//        return boards;
//    }

    @Override
    public ArrayList<Board> getConc(Pagination pagination) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs =null;
        ArrayList<Board> boards = new ArrayList<>();
        String ord = "desc";
        try{
            connection = connectDB();
            String sql="";
            if(Objects.equals(pagination.getOrderBy(), "accuracy") && !pagination.getKeyword().isEmpty()){
                pagination.setOrderBy(pagination.getType());
                ord = "asc";
            }
            switch (pagination.getTerm()) {
                    case "all":
                        sql = "SELECT * FROM board where " + pagination.getType() + " LIKE ? order by " + pagination.getOrderBy() + " " + ord + " limit ?, ?";
                        break;
                    case "day":
                        sql = "SELECT * FROM board where " + pagination.getType() + " LIKE ? AND created_at BETWEEN DATE_ADD( DATE(NOW()), INTERVAL -1 DAY ) AND NOW() order by " + pagination.getOrderBy() + " " + ord + " limit ?, ?";
                        break;
                    case "week":
                        sql = "SELECT * FROM board where " + pagination.getType() + " LIKE ? AND created_at BETWEEN DATE_ADD( DATE(NOW()), INTERVAL -1 WEEK ) AND NOW() order by " + pagination.getOrderBy() + " " + ord + " limit ?, ?";
                        break;
                    case "month":
                        sql = "SELECT * FROM board where " + pagination.getType() + " LIKE ? AND created_at BETWEEN DATE_ADD( DATE(NOW()), INTERVAL -1 MONTH ) AND NOW() order by " + pagination.getOrderBy() + " " + ord + " limit ?, ?";
                        break;
                    case "half_mon":
                        sql = "SELECT * FROM board where " + pagination.getType() + " LIKE ? AND created_at BETWEEN DATE_ADD( DATE(NOW()), INTERVAL -6 MONTH ) AND NOW()  order by " + pagination.getOrderBy() + " " + ord + " limit ?, ?";
                        break;
                    case "year":
                        sql = "SELECT * FROM board where " + pagination.getType() + " LIKE ? AND created_at BETWEEN DATE_ADD( DATE(NOW()), INTERVAL -1 YEAR ) AND NOW()  order by " + pagination.getOrderBy() + " " + ord + " limit ?, ?";
                        break;
            }
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
                Long member_id = rs.getLong("member_id");
                boards.add(new Board(id,title,content,writer,createdAt,viewCount,commentCount, member_id));
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
        if(Objects.equals(ord, "asc")) pagination.setOrderBy("accuracy");
        return boards;
    }

    @Override
    public int count(Pagination pagination) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs =null;
        int count = 0;
        try{
            connection = connectDB();
            String sql="";
            switch (pagination.getTerm()){
                case "all":
                    sql="SELECT count(*) FROM board where " + pagination.getType() + " LIKE ?";
                    break;
                case "day":
                    sql="SELECT count(*) FROM board where " + pagination.getType() + " LIKE ? AND created_at BETWEEN DATE_ADD( DATE(NOW()), INTERVAL -1 DAY ) AND NOW()";
                    break;
                case "week":
                    sql="SELECT count(*) FROM board where " + pagination.getType() + " LIKE ? AND created_at BETWEEN DATE_ADD(NOW(), INTERVAL -1 WEEK ) AND NOW()";
                    break;
                case "month":
                    sql="SELECT count(*) FROM board where " + pagination.getType() + " LIKE ? AND created_at BETWEEN DATE_ADD( DATE(NOW()), INTERVAL -1 MONTH ) AND NOW()";
                    break;
                case "half_mon":
                    sql="SELECT count(*) FROM board where " + pagination.getType() + " LIKE ? AND created_at BETWEEN DATE_ADD( DATE(NOW()), INTERVAL -6 MONTH ) AND NOW()";
                    break;
                case "year":
                    sql="SELECT count(*) FROM board where " + pagination.getType() + " LIKE ? AND created_at BETWEEN DATE_ADD( DATE(NOW()), INTERVAL -1 YEAR ) AND NOW()";
                    break;
            }
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

    public ArrayList<String> getUserId(){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs =null;
        ArrayList<String> userIds = null;
        try{
            connection = connectDB();
            String sql = "SELECT login_id from member";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()) {
                String userId = rs.getString("login_id");
                userIds.add(userId);
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
        return userIds;
    }
}
