// ProdutoRepository.java
package br.com.fredericopellegrini.petmania.produto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    Produto findByNome(String nome);
    boolean existsByNome(String nome);
}
