/**
 * © 2026 Yan Deryc Rodrigues Bibiano. Todos os direitos reservados.
 *
 * Este arquivo é parte integrante do Sistema de Gestão de Lojas.
 * Desenvolvido originalmente como portfólio de engenharia de software.
 *
 * @author Yan Deryc Rodrigues Bibiano
 * @version 1.0
 */
package br.com.yan.gestaoloja.modelo;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "produtos")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "loja_id")
    private Integer lojaId;

    @Column(name = "codigo_barras", nullable = false, unique = true, length = 13)
    private String codigoBarras;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(name = "preco_custo", nullable = false, precision = 10, scale = 2)
    private BigDecimal precoCusto;

    @Column(name = "preco_venda", nullable = false, precision = 10, scale = 2)
    private BigDecimal precoVenda;

    @Column(nullable = false)
    private Integer quantidade;

    public Produto() {

    }

    public Produto(String codigoBarras, String nome, BigDecimal precoCusto, BigDecimal precoVenda, Integer quantidade) {
        this.codigoBarras = codigoBarras;
        this.nome = nome;
        this.precoCusto = precoCusto;
        this.precoVenda = precoVenda;
        this.quantidade = quantidade;
    }

    public Long getId() { return id;}
    public void setId(Long id) { this.id = id; }

    public Integer getLojaId() { return lojaId;}
    public void setLojaId(Integer lojaId) {this.lojaId = lojaId;}

    public String getCodigoBarras(){ return codigoBarras;}
    public void setCodigoBarras(String codigoBarras) {this.codigoBarras = codigoBarras;}

    public String getNome() { return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public BigDecimal getPrecoCusto(){return precoCusto;}
    public void setPrecoCusto(BigDecimal precoCusto) {this.precoCusto = precoCusto;}

    public BigDecimal getPrecoVenda() {return precoVenda;}
    public void setPrecoVenda(BigDecimal precoVenda) {this.precoVenda = precoVenda;}

    public Integer getQuantidade(){return quantidade;}
    public void setQuantidade(Integer quantidade){this.quantidade = quantidade;}
}
