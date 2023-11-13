package br.com.fredericopellegrini.petmania;

import br.com.fredericopellegrini.petmania.produto.Produto;
import br.com.fredericopellegrini.petmania.produto.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataInitializer {

    private final ProdutoRepository produtoRepository;

    @Autowired
    public DataInitializer(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @PostConstruct
    public void initializeData() {
        cadastrarProduto(1L, "Shampoo Canino", "350ml", 49.99);
        cadastrarProduto(2L, "Coleira", "35cm", 12.99);
        cadastrarProduto(3L, "Roupinha Batman Gato", "Tamanho P", 39.99);
    }

    private void cadastrarProduto(Long id, String nome, String descricao, double preco) {
        Produto produto = new Produto();
        produto.setId(id);
        produto.setNome(nome);
        produto.setDescricao(descricao);
        produto.setPreco(preco);
        produtoRepository.save(produto);
    }
}
