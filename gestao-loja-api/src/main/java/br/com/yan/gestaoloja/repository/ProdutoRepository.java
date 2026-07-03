package br.com.yan.gestaoloja.repository;

import br.com.yan.gestaoloja.modelo.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findByLojaId(Integer lojaId);

    Optional<Produto> findByCodigoBarrasAndLojaId(String codigoBarras, Integer lojaId);

    Optional<Produto> findByIdAndLojaId(Long id, Integer lojaId);

    boolean existsByCodigoBarrasAndLojaId(String codigoBarras, Integer lojaId);

    boolean existsByCodigoBarrasAndLojaIdAndIdNot(String codigoBarras, Integer lojaId, Long id);

}
