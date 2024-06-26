package com.example.springboot.controllers;

import com.example.springboot.dtos.ProdutoRecordDto;
import com.example.springboot.models.ProdutoModel;
import com.example.springboot.repositories.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    /**
     * Cria um novo produto.
     *
     * @param produtoRecordDto o DTO com os valores do produto a ser salvo
     * @return um ResponseEntity contendo o produto criado e o status 201 (CREATED) ou 400(BADREQUEST) em caso de dados inválidos
     */
    @PostMapping("/produtos")
    public ResponseEntity<ProdutoModel> salvarProduto(@RequestBody @Valid ProdutoRecordDto produtoRecordDto) {

        var produtoModel = new ProdutoModel();

        BeanUtils.copyProperties(produtoRecordDto, produtoModel);//similar ao automap no C#

        return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produtoModel));
    }

    /**
     * Retorna a listagem de produtos.
     *
     * @return ResponseEntity com a listagem dos produtos e o status 200 (OK)
     */
    @GetMapping("/produtos")
    public ResponseEntity<List<ProdutoModel>> listarTodosProdutos() {

        List<ProdutoModel> listaProdutos = produtoRepository.findAll();

        if (!listaProdutos.isEmpty()){
            for (ProdutoModel produto : listaProdutos){

                UUID id = produto.getIdProduto();

                produto.add(linkTo(methodOn(ProdutoController.class).listarUmProduto(id)).withSelfRel());
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(listaProdutos);
    }


    /**
     * Retorna os detalhes de um produto com base no ID fornecido.
     *
     * @param id o ID do produto a ser buscado
     * @return um ResponseEntity contendo o produto encontrado e o status 200 (OK) ou uma mensagem de erro com status 404 (NOT FOUND) se o produto não for encontrado
     */
    @GetMapping("/produtos/{id}")
    public ResponseEntity<Object> listarUmProduto(@PathVariable(value = "id") UUID id) {

        Optional<ProdutoModel> produtoBuscado = produtoRepository.findById(id);

        if (produtoBuscado.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado!");

        produtoBuscado.get().add(linkTo(methodOn(ProdutoController.class).listarTodosProdutos()).withRel("Lista de produtos"));

        return ResponseEntity.status(HttpStatus.OK).body(produtoBuscado.get());
    }

    /**
     * Atualizar um produto pelo id.
     *
     * @param id o ID do produto a ser atualizado
     * @param produtoRecordDto o DTO com os valores do produto a ser atualizado
     * @return um ResponseEntity contendo o produto atualizado e o status 200 (OK) ou uma mensagem de erro com status 404 (NOT FOUND) se o produto não for encontrado
     */
    @PutMapping("/produtos/{id}")
    public ResponseEntity<Object> atualizarProduto(@PathVariable(value="id") UUID id,
                                                   @RequestBody @Valid ProdutoRecordDto produtoRecordDto){

        Optional<ProdutoModel> produtoBuscado = produtoRepository.findById(id);

        if (produtoBuscado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado!");
        }

        var produtoModel = produtoBuscado.get();

        BeanUtils.copyProperties(produtoRecordDto, produtoModel);

        return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.save(produtoModel));
    }

    /**
     * Remover produto pelo id
     *
     * @param id o ID do produto a ser removido
     * @return um ResponseEntity com o status 200 (OK) com uma mensagem de sucesso ou uma mensagem de erro com status 404 (NOT FOUND) se o produto não for encontrado
     */
    @DeleteMapping("/produtos/{id}")
    public ResponseEntity<Object> deletarProduto(@PathVariable(value = "id") UUID id){

        Optional<ProdutoModel> produtoBuscado = produtoRepository.findById(id);

        if (produtoBuscado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado!");
        }

        produtoRepository.delete(produtoBuscado.get());

        return ResponseEntity.status(HttpStatus.OK).body("Produto removido com sucesso!");
    }

}
