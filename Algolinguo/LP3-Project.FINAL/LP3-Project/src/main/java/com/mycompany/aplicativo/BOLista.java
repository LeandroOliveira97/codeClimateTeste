package com.mycompany.aplicativo;

import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BOLista {

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

    ListarExercicio list = new ListarExercicio(null);
    int numex = 3;
    
    ArrayList<BOListaEx> listCadas = new ArrayList<BOListaEx>();
    ArrayList<BOExercicio> exList = new ArrayList<BOExercicio>();
    ArrayList<BOUsuario> alList = new ArrayList<BOUsuario>();

    public ArrayList<BOExercicio> selectLista() {
        String sqlQuery = "SELECT id, titulo FROM exercicios where titulo <> ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(HOST + DB, USER, PASSWORD);

            PreparedStatement preparedSelect = conn.prepareStatement(sqlQuery);

            //Equivale ao digitado no Query do SGBD
            preparedSelect.setString(1, "teste");

            //"rodar/" linha executavel 
            ResultSet rs = preparedSelect.executeQuery();

            while (rs.next()) {

                //Array sempre precisa criar novo objeto para cada indice!
                BOExercicio ex = new BOExercicio();

                String id = rs.getString(1);
                String titulo = rs.getString(2);

               
                System.out.println(id);
                System.out.println(titulo);
                //atribuição dos valores lidos na query para o objeto criado
                ex.setId(id);
                ex.setTitulo(titulo);
                
                //adiciona novo elemento no ArrayList, no caso ex
                exList.add(ex);

            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Erro no BOLista");
        }

        //ex = selectLista();
        return exList;
    }
    
    public ArrayList<BOUsuario> selectAluno() {
        String sqlQuery = "SELECT id, nome FROM usuario where tipoUsuario = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(HOST + DB, USER, PASSWORD);

            PreparedStatement preparedSelect = conn.prepareStatement(sqlQuery);

            //Equivale ao digitado no Query do SGBD
            preparedSelect.setString(1, "1");

            //"rodar/" linha executavel 
            ResultSet rs = preparedSelect.executeQuery();

           while (rs.next()) {
               //Array sempre precisa criar novo objeto para cada indice!
                BOUsuario al = new BOUsuario();

                String id = rs.getString(1);
                String nome = rs.getString(2);

                
                
                System.out.println(id);
                System.out.println(nome);
                //atribuição dos valores lidos na query para o objeto criado
                al.setId(id);
                al.setNome(nome);
                //adiciona novo elemento no ArrayList, no caso al
                alList.add(al);

            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Erro no BOLista");
        }

        //ex = selectLista();
        return alList;
    }
     
    public void cadastroLista(String nomeLista, String exercicios, String autor,String idAluno){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/algoliguo?useeSSL=false","root","");
                                                                  
            String sqlQuery = "INSERT INTO `listaex`(`nomeLista`, `exLista`, `nomeAutor`, `alunosVinculados`) VALUES (?,?,?,?)";
            
            PreparedStatement preparedSelect = conn.prepareStatement(sqlQuery);

            
            preparedSelect.setString(1,nomeLista);
            preparedSelect.setString(2,exercicios);
            preparedSelect.setString(3,autor);
            preparedSelect.setString(4,idAluno);
            
            preparedSelect.executeUpdate();
            
            conn.close();
              
    } catch(ClassNotFoundException | SQLException e){
        e.printStackTrace();
    }
            
      
}
    
    public ArrayList<BOListaEx> selectListaCadastrada(String nomeAutor) {
        String sqlQuery = "SELECT `id`, `nomeLista`, `exLista`  FROM `listaex` WHERE `nomeAutor` = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(HOST + DB, USER, PASSWORD);

            PreparedStatement preparedSelect = conn.prepareStatement(sqlQuery);

            //Equivale ao digitado no Query do SGBD
            preparedSelect.setString(1, nomeAutor);

            //"rodar/" linha executavel 
            ResultSet rs = preparedSelect.executeQuery();

            while (rs.next()) {

                //Array sempre precisa criar novo objeto para cada indice!
                BOListaEx ex = new BOListaEx();

                String id = rs.getString(1);
                String nomeLista = rs.getString(2);
                String exLista = rs.getString(3);
                
               
                System.out.println(id);
                System.out.println(nomeLista);
                System.out.println(exLista);
                //atribuição dos valores lidos na query para o objeto criado
                ex.setIdLista(id);
                ex.setNomeLista(nomeLista);
                ex.setExDaLista(exLista);
                
                
                //adiciona novo elemento no ArrayList, no caso ex
                listCadas.add(ex);

            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Erro no BOLista");
        }

        //ex = selectLista();
        return listCadas;
    }
    
    public ArrayList<BOListaEx> selectListaCadastradaAluno() {
        String sqlQuery = "SELECT `nomeLista`, `exLista`  FROM `listaex` WHERE `alunosVinculados` LIKE ?";
        

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(HOST + DB, USER, PASSWORD);

            PreparedStatement preparedSelect = conn.prepareStatement(sqlQuery);

            //Equivale ao digitado no Query do SGBD
            //preparedSelect.setString(1, "'");
            preparedSelect.setString(1, "%" + BOUsuario.getNomeUser() + "%");
           // preparedSelect.setString(3, "'");
            
            System.out.println(sqlQuery);

            //"rodar/" linha executavel 
            ResultSet rs = preparedSelect.executeQuery();

            while (rs.next()) {

                //Array sempre precisa criar novo objeto para cada indice!
                BOListaEx ex = new BOListaEx();

                //String id = rs.getString(1);
                String nomeLista = rs.getString(1);
                String exLista = rs.getString(2);
                
               
               // System.out.println(id);
                System.out.println(nomeLista);
                System.out.println(exLista);
                //atribuição dos valores lidos na query para o objeto criado
              //  ex.setIdLista(id);
                ex.setNomeLista(nomeLista);
                ex.setExDaLista(exLista);
                
                
                //adiciona novo elemento no ArrayList, no caso ex
                listCadas.add(ex);

            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Erro no BOLista");
        }

        //ex = selectLista();
        return listCadas;
    }
}
