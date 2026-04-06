package br.com.gerenciadortarefas.dao;

import br.com.gerenciadortarefas.conexao.Conexao;
import br.com.gerenciadortarefas.model.Projeto;
import br.com.gerenciadortarefas.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ProjetoDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<Projeto> listagem = new ArrayList<>();

    public void salvar(Projeto projeto) {

         conn = new Conexao().conectar();
        
        String sql = "INSERT INTO projeto (nome, descricao, usuario_id) VALUES (?, ?, ?)";

        try {

            prep = this.conn.prepareStatement(sql);

            prep.setString(1, projeto.getNome());
            prep.setString(2, projeto.getDescricao());
            prep.setInt(3, projeto.getUsuario().getId());

            prep.execute();

        } catch(Exception e) {

            JOptionPane.showMessageDialog(null, "Erro no cadastro de projeto! Por favor, verifique os dados inseridos.");
        }
    }
    public ArrayList<Projeto> listar() {

        ArrayList<Projeto> lista = new ArrayList<>();
        Connection conn = new Conexao().conectar();

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
