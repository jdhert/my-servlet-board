package com.kitri.myservletboard.dao;

import com.kitri.myservletboard.data.Board;
import com.kitri.myservletboard.data.Member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class MemberJdbcDao implements MemberDao{
    private static final MemberJdbcDao instance = new MemberJdbcDao();

    public static MemberJdbcDao getInstance(){
        return instance;
    }

    private MemberJdbcDao(){};

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
    public ArrayList<String> getUserIds(){

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs =null;
        ArrayList<String> userIds = new ArrayList<>();
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

    public void regist(Member member){
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = connectDB();
            String sql = "INSERT INTO member (login_id, password, name, email )values (?,?,?,?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1,member.getLogin_id());
            ps.setString(2,member.getPassword());
            ps.setString(3, member.getName());
            ps.setString(4, member.getEmail());
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


    public HashMap<String,String> getUserPass(){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs =null;
        HashMap<String,String> userInfo = new HashMap<>();
        try{
            connection = connectDB();
            String sql = "SELECT login_id, password from member";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()) {
                String userId = rs.getString("login_id");
                String userPassword = rs.getString("password");
                userInfo.put(userId,userPassword);
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
        return userInfo;
    }

    public Member getInfo(String id){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs =null;
        Member info = null;
        try{
            connection = connectDB();
            String sql = "SELECT * from member where login_id like ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1,id);
            rs = ps.executeQuery();
            while(rs.next()) {
                Long id2 = rs.getLong("id");
                String login_id = rs.getString("login_id");
                String password = rs.getString("password");
                String name = rs.getString("name");
                String email = rs.getString("email");

                info = new Member(id2,login_id,password, name, email);
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
        return info;
    }
}
