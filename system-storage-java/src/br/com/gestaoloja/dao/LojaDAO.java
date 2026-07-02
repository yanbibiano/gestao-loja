/**
 * © 2026 Yan Deryc Rodrigues Bibiano. Todos os direitos reservados.
 *
 * Este arquivo é parte integrante do Sistema de Gestão de Lojas.
 * Desenvolvido originalmente como portfólio de engenharia de software.
 *
 * @author Yan Deryc Rodrigues Bibiano
 * @version 1.0
 */
package br.com.gestaoloja.dao;

import br.com.gestaoloja.conexao.FabricaConexao;
import br.com.gestaoloja.modelo.Loja;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LojaDAO {

    public boolean cadastrarLoja(Loja loja) {
        String sql = "INSERT INTO lojas (nome, senha_hash) VALUES (?, ?)";

        try (Connection conexao = FabricaConexao.obterConexao();
            PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, loja.getNome());
            stmt.setString(2, loja.getSenhaHash());

            int linhaAfetada = stmt.executeUpdate();
            return linhaAfetada > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar loja no banco: " + e.getMessage());
            return false;
        }
    }

    public Loja buscarPorNome(String nome) {
        String sql = "SELECT id, nome, senha_hash FROM lojas WHERE nome = ?";

        try (Connection conexao = FabricaConexao.obterConexao();
            PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, nome);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Loja lojaEncontrada = new Loja();
                    lojaEncontrada.setId(rs.getInt("id"));
                    lojaEncontrada.setNome(rs.getString("nome"));
                    lojaEncontrada.setSenhaHash(rs.getString("senha_hash"));

                    return lojaEncontrada;
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar loja por nome " + e.getMessage());
        }
        return null;

    }
}
