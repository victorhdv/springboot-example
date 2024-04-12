package com.example.springboot.controllers;

import com.example.springboot.dtos.ProdutoRecordDto;
import com.example.springboot.models.ProdutoModel;
import com.example.springboot.repositories.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping("/produtos")
    public ResponseEntity<ProdutoModel> SalvarProduto(@RequestBody @Valid ProdutoRecordDto produtoRecordDto) {
        var produtoModel = new ProdutoModel();
        BeanUtils.copyProperties(produtoRecordDto, produtoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produtoModel));
    }


}
