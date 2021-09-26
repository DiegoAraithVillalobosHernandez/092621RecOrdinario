package com.example.U1ExamenOrdinario_4BVillalobosHernandezDiegoAraith.server;

import com.example.U1ExamenOrdinario_4BVillalobosHernandezDiegoAraith.database.ConnectionMysql;
import com.example.U1ExamenOrdinario_4BVillalobosHernandezDiegoAraith.database.User;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Handler {
     Connection con;
     PreparedStatement pstm;
     ResultSet rs;
     Statement st;

    public boolean createUser(String name, String lastname, String email, String password, int status){
        boolean state = false;
        try {
            con = ConnectionMysql.getConnection();
            String query = "insert into user(name,lastname,email,password,status) values(?,?,?,?,?);";
            pstm = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1,name);
            pstm.setString(2,lastname);
            pstm.setString(3,email);
            pstm.setString(4,password);
            pstm.setInt(5,status);
            state = pstm.executeUpdate() == 1;
            rs = pstm.getGeneratedKeys();
            if (rs.next()){
                System.out.println("Insercion correcta");
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            closeConnection();
        }
        return state;
    }

    public List<User> findAll(){
        List<User> listUsers = new ArrayList<>();
        try {
            con = ConnectionMysql.getConnection();
            String query = "select  name, lastname, email, date_registered, status from user;";
            st = con.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()){
                User user = new User();
                user.setName(rs.getString("name"));
                user.setLastname(rs.getString("lastname"));
                user.setEmail(rs.getString("email"));
                user.setDateRegistered(rs.getString("date_registered"));
                user.setStatus(rs.getInt("status"));
                listUsers.add(user);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            closeConnection();
        }
        return listUsers;
    }

    public boolean updateUser(String email, String name, String lastname, int status){
        boolean state = false;
        try {
            con = ConnectionMysql.getConnection();
            String query = "UPDATE user SET name = ?, lastname = ?, status = ? WHERE email = ?;";
            pstm = con.prepareStatement(query);
            pstm.setString(1,name);
            pstm.setString(2,lastname);
            pstm.setInt(3,status);
            pstm.setString(4,email);
            state = pstm.executeUpdate() == 1;
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            closeConnection();
        }
        return state;
    }

    public boolean deleteUser(String email){
        boolean state = false;
        try {
            con = ConnectionMysql.getConnection();
            String query = "DELETE FROM user WHERE email = ?;";
            pstm = con.prepareStatement(query);
            pstm.setString(1,email);
            state = pstm.executeUpdate() == 1;
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            closeConnection();
        }
        return state;
    }

    public void closeConnection(){
        try {
            if (con!=null){
                con.close();
            }
            if (pstm != null){
                pstm.close();
            }
            if (rs != null){
                rs.close();
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

}
