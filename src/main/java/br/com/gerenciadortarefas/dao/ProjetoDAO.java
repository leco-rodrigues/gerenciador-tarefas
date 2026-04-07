package br.com.gerenciadortarefas.dao;

import br.com.gerenciadortarefas.conexao.Conexao;
import br.com.gerenciadortarefas.model.Projeto;
import br.com.gerenciadortarefas.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProjetoDAO {

    public void salvar(Projeto projeto) {

        Connection conn = new Conexao().conectar();
        
        String sql = "INSERT INTO projeto (nome, descricao, usuario_id) VALUES (?, ?, ?)";

        try {

            PreparedStatement prep = conn.prepareStatement(sql);

            prep.setString(1, projeto.getNome());
            prep.setString(2, projeto.getDescricao());
            prep.setInt(3, projeto.getUsuario().getId());

            prep.execute();

        } catch(Exception e) {

            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void atualizarUsuario(int id, Usuario usuario) {

        Connection conn = new Conexao().conectar();

        String sql = "UPDATE projeto SET usuario_id = ? WHERE id = ?";
        
        try {

            PreparedStatement prep = conn.prepareStatement(sql);

            prep.setInt(1, usuario.getId());
            prep.setInt(2, id);

            prep.executeUpdate();

        } catch(Exception e) {

            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void excluir(int id) {

        Connection conn = new Conexao().conectar();

        String sql = "DELETE FROM projeto WHERE id = ?";

        try {

            PreparedStatement prep = conn.prepareStatement(sql);

            prep.setInt(1, id);

            prep.executeUpdate();

        } catch(Exception e) {

            System.out.println("Erro: " + e.getMessage());
        }
    }

    public ArrayList<Projeto> listar() {

        Connection conn = new Conexao().conectar();
        ArrayList<Projeto> lista =  new ArrayList<>();
        
        String sql = "SELECT * FROM projeto";

        try {

            PreparedStatement prep = conn.prepareStatement(sql);
            ResultSet rs = prep.executeQuery();

            while (rs.next()) {

                Projeto projeto = new Projeto();

                projeto.setId(rs.getInt("id"));
                projeto.setNome(rs.getString("nome"));
                projeto.setDescricao(rs.getString("descricao"));

                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("usuario_id"));

                projeto.setUsuario(usuario);

                lista.add(projeto);
            }

        } catch (Exception e) {

            System.out.println("Erro: " + e.getMessage());
        }

        return lista;
    }
}
