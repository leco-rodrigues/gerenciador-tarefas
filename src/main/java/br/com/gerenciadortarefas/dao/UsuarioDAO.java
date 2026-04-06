package br.com.gerenciadortarefas.dao;

import br.com.gerenciadortarefas.conexao.Conexao;
import br.com.gerenciadortarefas.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UsuarioDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<Usuario> listagem = new ArrayList<>();
    
    public void salvar(Usuario usuario) {

         conn = new Conexao().conectar();
        
        String sql = "INSERT INTO usuario (nome, email) VALUES (?, ?)";

        try {

            prep = this.conn.prepareStatement(sql);

            prep.setString(1, usuario.getNome());
            prep.setString(2, usuario.getEmail());

            prep.execute();

        } catch(Exception e) {

            System.out.println("Erro: " + e.getMessage());
        }
    }

    public ArrayList<Usuario> listar() {

        ArrayList<Usuario> lista = new ArrayList<>();
        Connection conn = new Conexao().conectar();

        String sql = "SELECT * FROM usuario";

        try {

            PreparedStatement prep = conn.prepareStatement(sql);
            ResultSet rs = prep.executeQuery();

            while (rs.next()) {

                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));

                lista.add(usuario);
            }

        } catch (Exception e) {

            System.out.println("Erro: " + e.getMessage());

        }

        return lista;
    }
}