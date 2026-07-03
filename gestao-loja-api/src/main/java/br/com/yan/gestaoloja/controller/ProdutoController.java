package br.com.yan.gestaoloja.controller;

import br.com.yan.gestaoloja.modelo.Produto;
import br.com.yan.gestaoloja.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;


    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Validated Produto produto) {
        if (repository.existsByCodigoBarrasAndLojaId(produto.getCodigoBarras(), produto.getLojaId())) {
            return ResponseEntity.badRequest().body("Erro: esse código de barras já existe cadastrado nesta loja.");
        }
        Produto novoProduto = repository.save(produto);
        return ResponseEntity.ok(novoProduto);
    }


    @GetMapping("/loja/{lojaId}")
    public ResponseEntity<List<Produto>> listarPorLoja(@PathVariable Integer lojaId) {
        List<Produto> produtos = repository.findByLojaId(lojaId);
        return ResponseEntity.ok(produtos);
    }


    @GetMapping("/loja/{lojaId}/codigo/{codigoBarras}")
    public ResponseEntity<Produto> buscarPorCodigoEId(
            @PathVariable Integer lojaId,
            @PathVariable String codigoBarras) {

        return repository.findByCodigoBarrasAndLojaId(codigoBarras, lojaId)
                .map(produto -> ResponseEntity.ok(produto))
                .orElse(ResponseEntity.notFound().build());
        // Retorna erro HTTP 404 caso o produto não exista nessa loja
    }

    @PutMapping("/loja/{lojaId}/{id}")
    public ResponseEntity<?> atualizar(
            @PathVariable Integer lojaId,
            @PathVariable Long id,
            @RequestBody Produto produtoAtualizado) {

        if (repository.existsByCodigoBarrasAndLojaIdAndIdNot(produtoAtualizado.getCodigoBarras(), lojaId, id)) {
            return ResponseEntity.badRequest().body("Erro Outro produto desta loja já está utilizando este código de barras.");
        }
        return repository.findByIdAndLojaId(id, lojaId)
                .map(produtoExistente -> {
                    produtoExistente.setNome(produtoAtualizado.getNome());
                    produtoExistente.setCodigoBarras(produtoAtualizado.getCodigoBarras());
                    produtoExistente.setPrecoCusto(produtoAtualizado.getPrecoCusto());
                    produtoExistente.setPrecoVenda(produtoAtualizado.getPrecoVenda());

                    produtoExistente.setQuantidade(produtoAtualizado.getQuantidade());

                    Produto produtoSalvo = repository.save(produtoExistente);
                    return ResponseEntity.ok(produtoSalvo);
                })
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/loja/{lojaId}/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer lojaId, @PathVariable Long id) {
        return repository.findByIdAndLojaId(id, lojaId)
                .map(produto -> {
                    repository.delete(produto);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

}