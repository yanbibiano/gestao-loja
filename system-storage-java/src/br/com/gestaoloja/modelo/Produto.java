/**
 * © 2026 Yan Deryc Rodrigues Bibiano. Todos os direitos reservados.
 *
 * Este arquivo é parte integrante do Sistema de Gestão de Lojas.
 * Desenvolvido originalmente como portfólio de engenharia de software.
 *
 * @author Yan Deryc Rodrigues Bibiano
 * @version 1.0
 */
package br.com.gestaoloja.modelo;

public class Produto {
    private int id;
    private int lojaId;
    private String codigoBarras;
    private String nome;
    private double precoCusto;
    private double precoVenda;
    private int quantidade;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getLojaId() {
        return lojaId;
    }
    public void setLojaId(int lojaId) {
        this.lojaId = lojaId;
    }

    public String getCodigoBarras(){
        return codigoBarras;
    }
    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getNome(){
        return  nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }

    public double getPrecoCusto() {
        return precoCusto;
    }
    public void setPrecoCusto(double precoCusto) {
        this.precoCusto = precoCusto;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }
    public void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }





}
