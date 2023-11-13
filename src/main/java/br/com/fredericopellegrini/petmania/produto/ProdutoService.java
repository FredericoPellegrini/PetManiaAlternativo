// ProdutoService.java
package br.com.fredericopellegrini.petmania.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    public Produto adicionarProduto(Produto produto) {
        Produto produtoExistente = produtoRepository.findByNome(produto.getNome());
    
        if (produtoExistente == null) {
            return produtoRepository.save(produto);
        } else {
            return null;
        }
    }

    public Produto editarProduto(Long id, Produto produtoAtualizado) {
        Optional<Produto> produtoExistenteOptional = produtoRepository.findById(id);
    
        if (produtoExistenteOptional.isPresent()) {
            Produto produtoExistente = produtoExistenteOptional.get();
    
            // Atualizar as informações do produto
            produtoExistente.setNome(produtoAtualizado.getNome());
            produtoExistente.setDescricao(produtoAtualizado.getDescricao());
            produtoExistente.setPreco(produtoAtualizado.getPreco());
            produtoExistente.setQuantidade(produtoAtualizado.getQuantidade());
    
            return produtoRepository.save(produtoExistente);
        } else {
            // Produto não encontrado
            return null;
        }
    }

    public boolean excluirProduto(Long id) {
        Optional<Produto> produtoExistenteOptional = produtoRepository.findById(id);

        if (produtoExistenteOptional.isPresent()) {
            produtoRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
