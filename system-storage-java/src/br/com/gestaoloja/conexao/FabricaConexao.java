/**
 * © 2026 Yan Deryc Rodrigues Bibiano. Todos os direitos reservados.
 *
 * Este arquivo é parte integrante do Sistema de Gestão de Lojas.
 * Desenvolvido originalmente como portfólio de engenharia de software.
 *
 * @author Yan Deryc Rodrigues Bibiano
 * @version 1.0
 */
package br.com.gestaoloja.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaConexao {
    private static final String URL = "jdbc:mysql://localhost:3306/db_gestao_loja";

    private static final String USUARIO = System.getenv("DB_USER");
    private static final String SENHA = System.getenv("DB_PASS");

    public static Connection obterConexao() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Erro crítico: Driver do MySQL não foi encontrado", e);
        }
    }
}
