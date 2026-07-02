/**
 * © 2026 Yan Deryc Rodrigues Bibiano. Todos os direitos reservados.
 *
 * Este arquivo é parte integrante do Sistema de Gestão de Lojas.
 * Desenvolvido originalmente como portfólio de engenharia de software.
 *
 * @author Yan Deryc Rodrigues Bibiano
 * @version 1.0
 */
package br.com.gestaoloja.principal;

import br.com.gestaoloja.dao.LojaDAO;
import br.com.gestaoloja.dao.ProdutoDAO;
import br.com.gestaoloja.modelo.Loja;
import br.com.gestaoloja.modelo.Produto;
import br.com.gestaoloja.util.Criptografia;

import java.util.Scanner;

public class SistemaMain {

    private static final LojaDAO lojaDAO = new LojaDAO();
    private static final Scanner teclado = new Scanner(System.in);
    private static final  ProdutoDAO produtoDAO = new ProdutoDAO();


    static void main(String[] args) {
        int opcao = 0;

        do {
            System.out.println("====== SISTEMA LOJA ======");
            System.out.println("1. Criar Nova Loja");
            System.out.println("2. Acessar Loja Existente");
            System.out.println("3. Sair");
            System.out.println("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(teclado.nextLine());
            } catch (NumberFormatException e) {
                opcao = 0;
            }
            switch (opcao) {
                case 1:
                    menuCriarLoja();
                    break;
                case 2:
                    menuAcessarLoja();
                    break;
                case 3:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        } while (opcao != 3);
    }

    private static void menuCriarLoja() {
        System.out.println("\n--- CADASTRAR LOJA ---");
        System.out.println("Digite o nome da loja: ");
        String nome = teclado.nextLine().trim();

        if (nome.isEmpty()) {
            System.out.println("O nome da loja não pode ser vazio");
            return;
        }

        if (lojaDAO.buscarPorNome(nome) != null) {
            System.out.println("já existe uma loja cadastrada com esse nome");
            return;
        }

        System.out.println("Digite a senha mastar da loja: ");
        String senha = teclado.nextLine();

        String senhaMascarada = Criptografia.gerarHash(senha);

        Loja novaLoja = new Loja();
        novaLoja.setNome(nome);
        novaLoja.setSenhaHash(senhaMascarada);

        if (lojaDAO.cadastrarLoja(novaLoja)) {
            System.out.println("Loja " + nome + " cadastrada com sucesso");
        } else {
            System.err.println("Erro interno ao tentar salvas a loja");
        }
    }

    private static void menuAcessarLoja() {
        System.out.println("\n--- ACESSAR LOJA ---");
        System.out.println("Nome da loja: ");
        String nome = teclado.nextLine().trim();

        Loja loja = lojaDAO.buscarPorNome(nome);

        if (loja == null) {
            System.out.println("Loja não encontrada");
            return;
        }

        System.out.println("Senha master da loja: ");
        String senhaInformada = teclado.nextLine();

        String hashSenhaInformada = Criptografia.gerarHash(senhaInformada);

        if (loja.getSenhaHash().equals(hashSenhaInformada)) {
            System.out.println("Acesso liberado para a loja: " + loja.getNome().toUpperCase());
            System.out.println("ID da loja no Banco: " + loja.getId() + ")");

            menuInternoLoja(loja);

        } else {
            System.out.println("Senha incorreta");
        }
    }

    private static void menuInternoLoja(Loja lojaLogada) {
        int opcao = 0;

        br.com.gestaoloja.dao.UsuarioDAO usuarioDAO = new br.com.gestaoloja.dao.UsuarioDAO();


        do {
            System.out.println("===============================");
            System.out.println("PAINEL GERENCIAL: " + lojaLogada.getNome().toUpperCase());
            System.out.println("===============================");
            System.out.println("1. Cadastrar Novo Usuário/Funcionário");
            System.out.println("2. Votlar ao Menu Principal (Deslogar)");
            System.out.println("3. Gerenciar Estoque");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(teclado.nextLine());
            } catch (NumberFormatException e) {
                opcao = 0;
            }

            switch (opcao) {
                case 1:
                    menuCadastrarUsuario(lojaLogada, usuarioDAO);
                    break;
                case 2:
                    System.out.println("Fazendo logout da loja" + lojaLogada.getNome() + "'...");
                    break;
                case 3:
                    gerenciarEstoque(teclado, lojaLogada.getId(), produtoDAO);
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        } while (opcao != 2);
    }

    private static void menuCadastrarUsuario(Loja lojaLogada, br.com.gestaoloja.dao.UsuarioDAO usuarioDAO) {
        System.out.println("\n--- CADASTRAR NOVO USUÁRIO ---");
        System.out.print("Digite o usuário (login): ");
        String usuario = teclado.nextLine().trim();

        if (usuario.isEmpty()) {
            System.out.println("O nome de usuário não pode ser vazio");
            return;
        }

        System.out.println("Digite a senha do usuário: ");
        String senha = teclado.nextLine();

        System.out.println("Digite o cargo (ex:GERENTE, CAIXA): ");
        String cargo = teclado.nextLine().trim().toUpperCase();

        String senhaHash = Criptografia.gerarHash(senha);

        br.com.gestaoloja.modelo.Usuario novoUsuario = new br.com.gestaoloja.modelo.Usuario();
        novoUsuario.setNome(usuario);
        novoUsuario.setSenhaHash(senhaHash);
        novoUsuario.setCargo(cargo);

        novoUsuario.setLojaId(lojaLogada.getId());

        if (usuarioDAO.cadastraUsuario(novoUsuario)) {
            System.out.println("Usuário '" + usuario + "'cadastrado com sucesso para esta loja");
        } else {
            System.err.println("Erro ao tentar cadastrar o usuário no banco");
        }
    }
    private static void gerenciarEstoque(Scanner teclado, int lojaId, ProdutoDAO produtoDAO) {
        int opcao = 0;

        while (opcao != 5) {
            System.out.println("\n--- GERENCIAMENTO DE ESTOQUE ---");
            System.out.println("1. Cadastrar Novo Produto");
            System.out.println("2. Consultar Por Código de Barras");
            System.out.println("3. Dar Entrada/Baixa (Atualizar Quantidade)");
            System.out.println("4. Listar Todo o Estoque");
            System.out.println("5. Voltar ao Menu Anterior");
            System.out.print("Escolha uma opção: ");

            opcao = teclado.nextInt();
            teclado.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("\n--- CADASTRAR PRODUTO ---");
                    Produto novoProduto = new Produto();
                    novoProduto.setLojaId(lojaId);

                    System.out.print("Bipe ou digite o código de barras: ");
                    novoProduto.setCodigoBarras(teclado.nextLine());

                    System.out.println("Nome do produto: ");
                    novoProduto.setNome(teclado.nextLine());

                    System.out.println("Preço de Custo (Ex: 10,50): ");
                    novoProduto.setPrecoCusto(teclado.nextDouble());

                    System.out.println("Preço de Venda (Ex: 19,90): ");
                    novoProduto.setPrecoVenda(teclado.nextDouble());

                    System.out.println("Quantidade inicial: ");
                    novoProduto.setQuantidade(teclado.nextInt());

                    if (produtoDAO.cadastrarProduto(novoProduto)) {
                        System.out.println("Produto cadastrado com sucesso.");
                    } else {
                        System.err.println("Falha ao cadastrar produto.");
                    }
                    break;
                case 2:
                    System.out.println("\n--- CONSULTAR PRODUTO ---");
                    System.out.println("Bipe ou digite o código de barras: ");
                    String codigo = teclado.nextLine();

                    Produto produtoEncontrado = produtoDAO.buscarPorCodigoBarras(codigo, lojaId);

                    if (produtoEncontrado != null) {
                        System.out.println("Produto Encontrado: ");
                        System.out.println("Nome: " + produtoEncontrado.getNome());
                        System.out.printf("Preço: R$ %.2f\n", produtoEncontrado.getPrecoVenda());
                        System.out.println("Estoque Atual: " + produtoEncontrado.getQuantidade() + " unidades");
                    } else {
                        System.err.println("Produto não localizado nesta loja.");
                    }
                    break;
                case 3:
                    System.out.println("\n--- ATUALIZAAR QUANTIDADE ---");
                    System.out.println("Bipe ou digite o código de barras: ");
                    String codAtualizar = teclado.nextLine();

                    Produto pAtualizar = produtoDAO.buscarPorCodigoBarras(codAtualizar, lojaId);

                    if (pAtualizar != null) {
                        System.out.println("Produto atual: " + pAtualizar.getNome() + "Qtd atual: " + pAtualizar.getQuantidade() + ")");
                        System.out.println("Digite a NOVA quantidade total no estoque: ");
                        int novaQtd = teclado.nextInt();

                        if (produtoDAO.atualiazarQuantidade(pAtualizar.getId(), novaQtd)) {
                            System.out.println("Estoque atualizado com sucesso.");
                        } else {
                            System.err.println("Erro ao tualiazar o estoque");
                        }
                    } else {
                        System.err.println("Produto não encontrado.");
                    }
                    break;
                case 4:
                    System.out.println("\n--- ESTOQUE COMPLETO ---");
                    var lista = produtoDAO.listaProdutosPorLoja(lojaId);

                    if (lista.isEmpty()) {
                        System.out.println("O estoque datá vazio.");
                    } else {
                        System.out.printf("%-15s | %-25s | %-10s | %-6s\n", "CÓDIGO", "NOME", "PREÇO", "QTD");
                        System.out.println("--------------------------------------------------------------------------");
                        for (Produto p : lista) {
                            System.out.printf("%-15s | %-25s | R$ %-7.2f | %-6d\n",
                                p.getCodigoBarras(), p.getNome(), p.getPrecoVenda(), p.getQuantidade());
                        }
                    }
                    break;
                case 5:
                    System.out.println("Voltando...");
                    break;
                default:
                    System.err.println("Opção inválida");
            }
        }
    }
}
