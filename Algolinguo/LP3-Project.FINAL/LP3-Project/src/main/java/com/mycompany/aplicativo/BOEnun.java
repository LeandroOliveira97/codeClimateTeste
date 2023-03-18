package com.mycompany.aplicativo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BOEnun {

  /*  void salvarEnunciado(CadastroExercicio cadastroExercicio) {
        ResolucaoExercicio re = new ResolucaoExercicio();
        re.jl_Enunciado.setText(cadastroExercicio.jtf_criaEnun.getText());
        re.setVisible(true);
        re.setLocationRelativeTo(null);
        cadastroExercicio.dispose();
    }*/
    
    private static final String HOST = "jdbc:mysql://localhost/";
    private static final String DB = "algoliguo";
    private static final String USER = "root";
    private static final String PASSWORD = "";
     
    public void select() {
        String sqlQuery = "SELECT * FROM exercicios WHERE titulo = ?";
         
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection(HOST+DB,USER,PASSWORD);
             
            PreparedStatement preparedSelect = conn.prepareStatement(sqlQuery);
            
            //Equivale ao digitado no Query do SGBD
            preparedSelect.setString(2, "Calculadora");
            
            //"rodar/" linha executavel 
            ResultSet rs = preparedSelect.executeQuery();
             
            while (rs.next()) {
                int id = rs.getInt(1);
                String titulo = rs.getString(2);
                String resp = rs.getString(3);
             //   int age = rs.getInt(4);
               // System.out.printf("%d\t%s\t%s",id,titulo, resp/*,surname,age*/);
            }
             
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Erro no BOEnum");
        }
 
    }
    
}
