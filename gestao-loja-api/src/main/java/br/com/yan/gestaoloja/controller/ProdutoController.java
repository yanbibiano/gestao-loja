package br.com.yan.gestaoloja.controller;

import br.com.yan.gestaoloja.modelo.Produto;
import br.com.yan.gestaoloja.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    /**
     * POST /produtos
     * Substitui o 'cadastrarProduto(Produto)' do seu DAO.
     * Salva o produto vinculado à loja informada no JSON.
     */
    @PostMapping
    public ResponseEntity<Produto> cadastrar(@RequestBody Produto produto) {
        Produto novoProduto = repository.save(produto);
        return ResponseEntity.ok(novoProduto);
    }

    /**
     * GET /produtos/loja/{lojaId}
     * Substitui o 'listaProdutosPorLoja(int lojaid)' do seu DAO.
     * Retorna apenas os produtos pertencentes àquela loja específica.
     */
    @GetMapping("/loja/{lojaId}")
    public ResponseEntity<List<Produto>> listarPorLoja(@PathVariable Integer lojaId) {
        List<Produto> produtos = repository.findByLojaId(lojaId);
        return ResponseEntity.ok(produtos);
    }

    /**
     * GET /produtos/loja/{lojaId}/codigo/{codigoBarras}
     * Substitui o 'buscarPorCodigoBarras(String, int)' do seu DAO.
     * Busca um produto específico validando o código e se ele pertence àquela loja.
     */
    @GetMapping("/loja/{lojaId}/codigo/{codigoBarras}")
    public ResponseEntity<Produto> buscarPorCodigoEId(
            @PathVariable Integer lojaId,
            @PathVariable String codigoBarras) {

        return repository.findByCodigoBarrasAndLojaId(codigoBarras, lojaId)
                .map(produto -> ResponseEntity.ok(produto))
                .orElse(ResponseEntity.notFound().build());
        // Retorna erro HTTP 404 caso o produto não exista nessa loja
    }
}