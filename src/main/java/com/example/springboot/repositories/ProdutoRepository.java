package com.example.springboot.repositories;


import com.example.springboot.models.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/*
4 esteriotipos comuns que serão gerenciados pelo @Bean
@Component uma classe generica
@Repository transações com base de dados
@Service regras de negocios
@Controller requisições http
*/

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoModel, UUID> {
}
