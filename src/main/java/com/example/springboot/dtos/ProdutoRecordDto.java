package com.example.springboot.dtos;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

//Record são imutáveis
public record ProdutoRecordDto(@NotBlank @Size(min = 3, max = 100) String nome,
                               @NotNull @Positive @Digits(integer = 10, fraction = 2) BigDecimal valor) {
}
