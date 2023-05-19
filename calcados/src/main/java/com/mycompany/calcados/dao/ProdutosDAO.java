/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.calcados.dao;

import com.mycompany.calcados.classes.Clientes;
import com.mycompany.calcados.classes.Produtos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author kayla
 */
public class ProdutosDAO {
    public static boolean cadastrarP(Produtos obj){
        boolean RetornoP = false;
        Connection conexao = null;
        
        try{
            // Passo 1 - Carregar o drive
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/lojacalcados";
            
            // Passo 2 - Abrir a conexão
            conexao = DriverManager.getConnection(url, "root", "");
            
            //Passo 3 - Preparar o comando SQL
           PreparedStatement comandoSQL = conexao.prepareStatement("INSERT INTO produtos " 
                    + "(cod_prod, preco, tamanho, modelo, marca, cor, genero) VALUES(?,?,?,?,?,?,?)");
           
           comandoSQL.setInt(1,obj.getCod_prod());
           comandoSQL.setDouble(2,obj.getPreco());
           comandoSQL.setInt(3,obj.getTamanho());
           comandoSQL.setString(4,obj.getModelo());
           comandoSQL.setString(5,obj.getMarca());
           comandoSQL.setString(6,obj.getCor());
           comandoSQL.setString(7,obj.getGenero());
           
           // Passo 4 - Executar comando SQL
          int linhasAfetadas = comandoSQL.executeUpdate();
          
          if(linhasAfetadas>0){
              RetornoP = true;
          }
            
        } catch (ClassNotFoundException ex) {
           System.out.println("Erro ao carregar o Driver"); 
        } catch (SQLException ex) {
           System.out.println("Erro ao abrir a conexão");
        }
        
        return RetornoP;
    } // Fim do metódo insert
}
