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
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Entity
@Table(name = "produtos", uniqueConstraints = {
        @UniqueConstraint(
                name = "uk_produto_loja_coddigo",
                columnNames = {"loja_id", "codigo_barras"}
        )
})
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "loja_id")
    @NotNull(message = "O ID da loja é obrigatório.")
    private Integer lojaId;

    @NotBlank(message = "O código de barras é obrigatório.")
    private String codigoBarras;

    @NotBlank(message = "O nome do produto é obrigatório e não pode ficar em branco.")
    private String nome;

    @NotNull(message = "O preço de custo é obrigatório.")
    @PositiveOrZero(message = "O preço de venda não pode ser negativo")
    private BigDecimal precoCusto;

    @NotNull(message = "O preço de venda é obrigatório.")
    @PositiveOrZero(message = "O preço de venda não pode ser negativo.")
    private BigDecimal precoVenda;

   @NotNull(message = "A quantidade é obrigatória.")
   @PositiveOrZero(message = "A quantidade não pode ser negativa.")
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
