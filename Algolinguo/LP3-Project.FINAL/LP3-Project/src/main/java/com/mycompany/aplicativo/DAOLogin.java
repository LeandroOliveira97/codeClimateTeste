package com.mycompany.aplicativo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOLogin {
    
    public boolean loginStatus;
    public boolean nomeExiste;
    private String hash;
    BOUsuario boUser = new BOUsuario();
    
    public BOUsuario encontrarLogin(String usuario, String senha){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/algoliguo?useeSSL=false","root","");
                                                                  
            String sqlQuery = "SELECT id, tipoUsuario, nome, senha FROM usuario WHERE nome=? and senha=?";
            
            PreparedStatement preparedSelect = conn.prepareStatement(sqlQuery);
                       
            hash = HashUtils.getHashMd5(senha);
            preparedSelect.setString(1,usuario);
            preparedSelect.setString(2,hash);
            
            ResultSet rs = preparedSelect.executeQuery();
            
            if (rs.next()) {
                
                //se o username e a senha estiverem corretos 
                
                //System.out.printf(HashUtils.getHashMd5(senha));
                //System.out.printf(usuario, senha);
                boUser.setId(rs.getString(1));
                boUser.setTipoUser(rs.getString(2));
                boUser.setNome(rs.getString(3));
                
                BOUsuario.setNomeUser(boUser.getNome());
                
                loginStatus = true;
                
                
                
            } else {
                loginStatus = false;
            }
            
            
            conn.close();
            
        } catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        return boUser;
    }
    
    public void cadastro(String usuario, String senha, int tipoUser){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/algoliguo?useeSSL=false","root","");
                                                                  
            String sqlQuery = "SELECT nome FROM usuario WHERE nome=?";
            
            PreparedStatement preparedSelect = conn.prepareStatement(sqlQuery);
                       
            preparedSelect.setString(1,usuario);
            
            ResultSet rs = preparedSelect.executeQuery();
            
            if (rs.next()) {
                //se o username ja existir 
                
                nomeExiste = true;    
                
                conn.close();
            } else {
                nomeExiste = false;
                sqlQuery = "INSERT INTO usuario(nome, senha, tipoUsuario) VALUES(?,?,?)";
                    try{
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/algoliguo?useeSSL=false","root","");

                        PreparedStatement preparedInsert = conn.prepareStatement(sqlQuery);

                        preparedInsert.setString(1,usuario);
                        preparedInsert.setString(2,HashUtils.getHashMd5(senha));
                        preparedInsert.setString(3,tipoUser+"");

                        preparedInsert.executeUpdate();

                        conn.close();

                } catch(ClassNotFoundException | SQLException e){
                    e.printStackTrace();
                }
            }
            
            conn.close();
            
        } catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
      
}
    
    

}
