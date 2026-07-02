package br.com.yan.gestaoloja.controller;

import br.com.yan.gestaoloja.modelo.Produto;
import br.com.yan.gestaoloja.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @GetMapping
    public List<Produto> listarTodos() {
        return repository.findAll();
    }

    @PostMapping
    public Produto cadastrar(@RequestBody Produto novoProduto) {
        return repository.save(novoProduto);
    }
}
