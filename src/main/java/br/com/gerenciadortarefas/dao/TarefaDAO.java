package br.com.gerenciadortarefas.dao;

import br.com.gerenciadortarefas.conexao.Conexao;
import br.com.gerenciadortarefas.model.Projeto;
import br.com.gerenciadortarefas.model.Tarefa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TarefaDAO {

    public void salvar(Tarefa tarefa) {

        Connection conn = new Conexao().conectar();
        
        String sql = "INSERT INTO tarefa (titulo, descricao, status, projeto_id) VALUES (?, ?, ?, ?)";

        try {

            PreparedStatement prep = conn.prepareStatement(sql);

            prep.setString(1, tarefa.getTitulo());
            prep.setString(2, tarefa.getDescricao());
            prep.setString(3, tarefa.getStatus());
            prep.setInt(4, tarefa.getProjeto().getId());

            prep.execute();

        } catch(Exception e) {

            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void atualizarStatus(int id, String status) {
        
        Connection conn = new Conexao().conectar();

        String sql = "UPDATE tarefa SET status = ? WHERE id = ?";
        
        try {

            PreparedStatement prep = conn.prepareStatement(sql);
            
            prep.setString(1, status);
            prep.setInt(2, id);
            
            prep.executeUpdate();
            
        } catch(Exception e) {

            System.out.println("Erro: " + e.getMessage());
        }
    }

    public ArrayList<Tarefa> listar() {

        Connection conn = new Conexao().conectar();
        ArrayList<Tarefa> lista = new ArrayList<>();

        String sql = "SELECT * FROM tarefa";

        try {

            PreparedStatement prep = conn.prepareStatement(sql);
            ResultSet rs = prep.executeQuery();

            while (rs.next()) {

                Tarefa tarefa = new Tarefa();

                tarefa.setId(rs.getInt("id"));
                tarefa.setTitulo(rs.getString("titulo"));
                tarefa.setDescricao(rs.getString("descricao"));
                tarefa.setStatus(rs.getString("status"));
                
                Projeto projeto = new Projeto();
                projeto.setId(rs.getInt("projeto_id"));

                tarefa.setProjeto(projeto);

                lista.add(tarefa);
            }

        } catch (Exception e) {

            System.out.println("Erro: " + e.getMessage());
        }

        return lista;
    }
}
