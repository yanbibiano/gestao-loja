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
import br.com.gestaoloja.modelo.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    public boolean cadastrarProduto(Produto produto) {
        String sql = "INSERT INTO produtos (loja_id, codigo_barras, nome, preco_custo, preco_venda, quantidade) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conexao = FabricaConexao.obterConexao();
            PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, produto.getLojaId());
            stmt.setString(2, produto.getCodigoBarras());
            stmt.setString(3, produto.getNome());
            stmt.setDouble(4, produto.getPrecoCusto());
            stmt.setDouble(5, produto.getPrecoVenda());
            stmt.setInt(6, produto.getQuantidade());

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar produto no banco: " + e.getMessage());
            return false;
        }
    }

    public Produto buscarPorCodigoBarras(String codigoBarras, int lojaId) {
        String sql = "SELECT * FROM produtos WHERE codigo_barras = ? AND loja_id = ?";

        try (Connection conexao = FabricaConexao.obterConexao();
            PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, codigoBarras);
            stmt.setInt(2, lojaId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Produto produto = new Produto();
                    produto.setId(rs.getInt("id"));
                    produto.setLojaId(rs.getInt("loja_id"));
                    produto.setCodigoBarras(rs.getString("codigo_barras"));
                    produto.setNome(rs.getString("nome"));
                    produto.setPrecoCusto(rs.getDouble("preco_custo"));
                    produto.setPrecoVenda(rs.getDouble("preco_venda"));
                    produto.setQuantidade(rs.getInt("quantidade"));
                    return produto;
                }
            }
        }catch (SQLException e) {
            System.err.println("Erro ao buscar produto por código de barras =: " + e.getMessage());
        }
        return null;
    }

    public boolean atualiazarQuantidade(int produtoId, int novaQuantidade) {
        String sql = "UPDATE produtos SET quantidade = ? WHERE id = ?";

        try (Connection conexao = FabricaConexao.obterConexao();
            PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, novaQuantidade);
            stmt.setInt(2, produtoId);

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar quantidade do produto" + e.getMessage());
            return false;
        }
    }

    public List<Produto> listaProdutosPorLoja(int lojaid) {
        String sql = "SELECT * FROM produtos WHERE loja_id = ? ORDER BY nome";
        List<Produto> lista = new ArrayList<>();

        try (Connection conexao = FabricaConexao.obterConexao();
            PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, lojaid);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Produto p = new Produto();
                    p.setId(rs.getInt("id"));
                    p.setLojaId(rs.getInt("loja_id"));
                    p.setCodigoBarras(rs.getString("codigo_barras"));
                    p.setNome(rs.getString("nome"));
                    p.setPrecoCusto(rs.getDouble("preco_custo"));
                    p.setPrecoVenda(rs.getDouble("preco_venda"));
                    p.setQuantidade(rs.getInt("quantidade"));
                    lista.add(p);

                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar produtos da loja " + e.getMessage());
        }
        return lista;
    }


}
