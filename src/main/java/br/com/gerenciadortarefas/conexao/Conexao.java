package br.com.gerenciadortarefas.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexao {

    private static final String URL = "jdbc:mysql://localhost/gerenciador_tarefas";
    private static final String USER = "root";
    private static final String PASS = "root";

    public Connection conectar(){

        Connection conn = null;

        try {

            conn = DriverManager.getConnection(URL, USER, PASS);

        } catch (SQLException ex){

            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage());
        }

        return conn;
    }
}
