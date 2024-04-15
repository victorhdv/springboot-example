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
    public ResponseEntity<ProdutoModel> SalvarProduto(@RequestBody @Valid ProdutoRecordDto produtoRecordDto) {
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
    public ResponseEntity<List<ProdutoModel>> ListarProdutos() {
        return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.findAll());
    }


    /**
     * Retorna os detalhes de um produto com base no ID fornecido.
     *
     * @param id o ID do produto a ser buscado
     * @return um ResponseEntity contendo o produto encontrado ou uma mensagem de erro se o produto não for encontrado
     */
    @GetMapping("/produtos/{id}")
    public ResponseEntity<Object> ListarUmProduto(@PathVariable(value = "id") UUID id) {
        Optional<ProdutoModel> produtoBuscado = produtoRepository.findById(id);
        if (produtoBuscado.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(produtoBuscado.get());
    }



}
