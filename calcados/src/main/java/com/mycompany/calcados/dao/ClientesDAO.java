/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.calcados.dao;

import com.mycompany.calcados.classes.Clientes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author kayla 
 */
public class ClientesDAO {
    public static boolean cadastrar(Clientes obj){
        boolean Retorno = false;
        Connection conexao = null;
        
        try{
            // Passo 1 - Carregar o drive
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/lojacalcados";
            
            // Passo 2 - Abrir a conexão
            conexao = DriverManager.getConnection(url, "root", "");
            
            //Passo 3 - Preparar o comando SQL
           PreparedStatement comandoSQL = conexao.prepareStatement("INSERT INTO clientes " 
                    + "(CPF, nome, email, genero, est_civil, cep, rua, num_casa) VALUES(?,?,?,?,?,?,?,?)");
           
           comandoSQL.setString(1,obj.getCpf());
           comandoSQL.setString(2,obj.getNome());
           comandoSQL.setString(3,obj.getEmail());
           comandoSQL.setString(4,obj.getGenero());
           comandoSQL.setString(5,obj.getEst_civil());
           comandoSQL.setString(6,obj.getCep());
           comandoSQL.setString(7,obj.getRua());
           comandoSQL.setInt(8,obj.getNum_casa());
           
           // Passo 4 - Executar comando SQL
          int linhasAfetadas = comandoSQL.executeUpdate();
          
          if(linhasAfetadas>0){
              Retorno = true;
          }
            
        } catch (ClassNotFoundException ex) {
           System.out.println("Erro ao carregar o Driver"); 
        } catch (SQLException ex) {
           System.out.println("Erro ao abrir a conexão");
        }
        
        return Retorno;
    } // Fim do metódo insert
    
     public static ArrayList<Clientes> listar(){
        ArrayList<Clientes> busca = new ArrayList(); 
        Connection conexao = null;
        
        try{
            // Passo 1 - Carregar o drive
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Passo 2 - Abrir a conexão
            String url = "jdbc:mysql://localhost:3306/lojacalcados";
            conexao = DriverManager.getConnection(url, "root", "");
            
            //Passo 3 - Preparar o comando SQL
           PreparedStatement comandoSQL = conexao.prepareStatement("SELECT * FROM clientes");
           
           // Passo 4 - Executar comando SQL
          ResultSet Rs_select = comandoSQL.executeQuery();
          
          if(Rs_select != null){
              // Percorrendo o result e passando os valores a um obj
              while(Rs_select.next()){
                  Clientes select = new Clientes();
                  select.setCpf(Rs_select.getString("CPF"));
                  select.setNome(Rs_select.getString("nome"));
                  select.setEmail(Rs_select.getString("email"));
                  select.setGenero(Rs_select.getString("genero"));
                  select.setEst_civil(Rs_select.getString("est_civil"));
                  select.setCep(Rs_select.getString("cep"));
                  select.setRua(Rs_select.getString("rua"));
                  select.setNum_casa(Rs_select.getInt("num_casa"));
                  
                  busca.add(select);
              }
          }
            
        } catch (ClassNotFoundException ex) {
           System.out.println("Erro ao carregar o Driver"); 
        } catch (SQLException ex) {
           System.out.println("Erro ao abrir a conexão");
        }
        
        return busca;
    } // Fim do metodo de select
    
    public static boolean excluir(String CPF){
        boolean Retorno = false;
        Connection conexao = null;
        
        try{
            // Passo 1 - Carregar o drive
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/lojacalcados";
            
            // Passo 2 - Abrir a conexão
            conexao = DriverManager.getConnection(url, "root", "");
            
            //Passo 3 - Preparar o comando SQL
           PreparedStatement comandoSQL = conexao.prepareStatement("DELETE FROM clientes WHERE CPF =? ");
           comandoSQL.setString(1, CPF);
           
           // Passo 4 - Executar comando SQL
          int linhasAfetadas = comandoSQL.executeUpdate();
          
          if(linhasAfetadas>0){
              Retorno = true;
          }
            
        } catch (ClassNotFoundException ex) {
           System.out.println("Erro ao carregar o Driver"); 
        } catch (SQLException ex) {
           System.out.println("Erro ao abrir a conexão");
        }
        
        return Retorno;
    } // Fim do metódo delete
    
        public static boolean alterar( String Valor_coluna, String CPF ){
        boolean Retorno = false;
        Connection conexao = null;
        
        try{
            // Passo 1 - Carregar o drive
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/lojacalcados";
            
            // Passo 2 - Abrir a conexão
            conexao = DriverManager.getConnection(url, "root", "");
            
            //Passo 3 - Preparar o comando SQL
           PreparedStatement comandoSQL = conexao.prepareStatement(" UPDATE clientes SET email = ? WHERE CPF = ?");
           
           comandoSQL.setString(1, Valor_coluna);
           comandoSQL.setString(2, CPF);
           
           // Passo 4 - Executar comando SQL
          int linhasAfetadas = comandoSQL.executeUpdate();
          
          if(linhasAfetadas>0){
              Retorno = true;
          }
            
        } catch (ClassNotFoundException ex) {
           System.out.println("Erro ao carregar o Driver"); 
        } catch (SQLException ex) {
           System.out.println("Erro ao abrir a conexão");
        }
        
        return Retorno;
    } // Fim do metódo update
        
     public static ArrayList<Clientes> listarCarrinho(String CPF){
        ArrayList<Clientes> buscaC = new ArrayList(); 
        Connection conexao = null;
        
        try{
            // Passo 1 - Carregar o drive
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Passo 2 - Abrir a conexão
            String url = "jdbc:mysql://localhost:3306/lojacalcados";
            conexao = DriverManager.getConnection(url, "root", "");
            
            //Passo 3 - Preparar o comando SQL
           PreparedStatement comandoSQL = conexao.prepareStatement("SELECT * FROM clientes WHERE CPF = ?");
           comandoSQL.setString(1, CPF);
           
           // Passo 4 - Executar comando SQL
          ResultSet Rs_select = comandoSQL.executeQuery();
          
          if(Rs_select != null){
              // Percorrendo o result e passando os valores a um obj
              while(Rs_select.next()){
                  Clientes select = new Clientes();
                  select.setCpf(Rs_select.getString("CPF"));
                  select.setNome(Rs_select.getString("nome"));
                  
                  buscaC.add(select);
              }
          }
            
        } catch (ClassNotFoundException ex) {
           System.out.println("Erro ao carregar o Driver"); 
        } catch (SQLException ex) {
           System.out.println("Erro ao abrir a conexão");
        }
        
        return buscaC;
    } // Fim do metodo de select com parametro
}
