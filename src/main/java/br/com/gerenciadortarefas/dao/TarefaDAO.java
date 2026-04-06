package br.com.gerenciadortarefas.dao;

import br.com.gerenciadortarefas.conexao.Conexao;
import br.com.gerenciadortarefas.model.Projeto;
import br.com.gerenciadortarefas.model.Tarefa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class TarefaDAO {
    Connection conn;
    PreparedStatement prep;
    ResultSet rs;
    ArrayList<Tarefa> listagem = new ArrayList<>();
    
    public void salvar(Tarefa tarefa) {

         conn = new Conexao().conectar();
        
        String sql = "INSERT INTO tarefa (titulo, descricao, status, projeto_id) VALUES (?, ?, ?, ?)";

        try {

            prep = this.conn.prepareStatement(sql);

            prep.setString(1, tarefa.getTitulo());
            prep.setString(2, tarefa.getDescricao());
            prep.setString(3, tarefa.getStatus());
            prep.setInt(4, tarefa.getProjeto().getId());

            prep.execute();

        } catch(Exception e) {

            JOptionPane.showMessageDialog(null, "Erro no cadastro de tarefa! Por favor, verifique os dados inseridos.");
        }
    }

    public ArrayList<Tarefa> listar() {

        ArrayList<Tarefa> lista = new ArrayList<>();
        conn = new Conexao().conectar();

        String sql = "SELECT * FROM tarefa";

        try {

            prep = conn.prepareStatement(sql);
            rs = prep.executeQuery();

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

    public void atualizarStatus(int id, String status) {
        
        conn = new Conexao().conectar();
        String sql = "UPDATE tarefa SET status = ? WHERE id = ?";
        
        try {
            prep = this.conn.prepareStatement(sql);
            
            prep.setString(1, status);
            prep.setInt(2, id);
            
            prep.executeUpdate();
            
        } catch(Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
