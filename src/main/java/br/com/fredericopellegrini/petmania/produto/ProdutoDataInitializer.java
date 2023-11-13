// ProdutoDataInitializer.java
package br.com.fredericopellegrini.petmania.produto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class ProdutoDataInitializer {

    private final ProdutoRepository produtoRepository;

    @PostConstruct
    public void initializeData() {
        Produto shampoo = new Produto();
        shampoo.setId(1L);
        shampoo.setNome("Shampoo Canino");
        shampoo.setDescricao("350ml");
        shampoo.setPreco(49.99);
        shampoo.setQuantidade(10);  // Exemplo: Inicializando com 10 unidades em estoque
        produtoRepository.save(shampoo);

        Produto coleira = new Produto();
        coleira.setId(2L);
        coleira.setNome("Coleira");
        coleira.setDescricao("35cm");
        coleira.setPreco(12.99);
        coleira.setQuantidade(20);
        produtoRepository.save(coleira);

        Produto roupinha = new Produto();
        roupinha.setId(3L);
        roupinha.setNome("Roupinha Batman Gato");
        roupinha.setDescricao("Tamanho P");
        roupinha.setPreco(39.99);
        roupinha.setQuantidade(15);
        produtoRepository.save(roupinha);
    }
}
