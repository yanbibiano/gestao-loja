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
import br.com.gestaoloja.modelo.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    public boolean cadastraUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (loja_id, nome, cargo, senha_hash) VALUES (?,?,?,?)";

        try (Connection conexao = FabricaConexao.obterConexao();
            PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, usuario.getLojaId());
            stmt.setString(2, usuario.getNome());
            stmt.setString(3, usuario.getCargo());
            stmt.setString(4, usuario.getSenhaHash());

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar usuário no bando: " + e.getMessage());
            return false;
        }
    }

    public Usuario buscarPorNomeELoja(String nome, int lojaId) {
        String sql = "SELECT id, loja_id, nome, cargo, senha_hash FROM usuarios WHERE nome = ? AND loja_ai = ?";

        try (Connection conexao = FabricaConexao.obterConexao();
            PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setInt(2, lojaId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Usuario usuarioEncontrado = new Usuario();
                    usuarioEncontrado.setId(rs.getInt("id"));
                    usuarioEncontrado.setLojaId(rs.getInt("loja_id"));
                    usuarioEncontrado.setNome(rs.getString("nome"));
                    usuarioEncontrado.setSenhaHash(rs.getString("senha_hash"));

                    return usuarioEncontrado;
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar usuários: " + e.getMessage());
        }

        return null;

    }
}
