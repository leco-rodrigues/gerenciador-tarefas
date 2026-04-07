package br.com.gerenciadortarefas.dao;

import br.com.gerenciadortarefas.conexao.Conexao;
import br.com.gerenciadortarefas.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UsuarioDAO {

    public void salvar(Usuario usuario) {

        Connection conn = new Conexao().conectar();
        
        String sql = "INSERT INTO usuario (nome, email) VALUES (?, ?)";

        try {

            PreparedStatement prep = conn.prepareStatement(sql);

            prep.setString(1, usuario.getNome());
            prep.setString(2, usuario.getEmail());

            prep.execute();

        } catch(Exception e) {

            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void atualizar(int id, String nome, String email) {

        Connection conn = new Conexao().conectar();

        String sql = "UPDATE usuario SET nome = ?, email = ? WHERE id = ?";

        try {

            PreparedStatement prep = conn.prepareStatement(sql);

            prep.setString(1, nome);
            prep.setString(2, email);
            prep.setInt(3, id);

            prep.executeUpdate();

        } catch(Exception e) {

            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void excluir(int id) {

        Connection conn = new Conexao().conectar();

        String sql = "DELETE FROM usuario WHERE id = ?";

        try {

            PreparedStatement prep = conn.prepareStatement(sql);

            prep.setInt(1, id);

            prep.executeUpdate();

        } catch(Exception e) {

            System.out.println("Erro: " + e.getMessage());
        }
    }

    public ArrayList<Usuario> listar() {

        Connection conn = new Conexao().conectar();
        ArrayList<Usuario> lista = new ArrayList<>();

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
