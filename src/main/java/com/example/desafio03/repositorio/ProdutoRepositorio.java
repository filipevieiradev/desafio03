package com.example.desafio03.repositorio;

import com.example.desafio03.entidades.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepositorio extends JpaRepository<Produto, Long> {

    boolean existsByNome(String nome);
}
