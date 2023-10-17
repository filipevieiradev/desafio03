package com.example.desafio03.controller;

import com.example.desafio03.entidades.Produto;
import com.example.desafio03.repositorio.ProdutoRepositorio;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    ProdutoRepositorio produtoRepositorio;


    @PostMapping("/salvar")
    public ResponseEntity<String> salvarProduto(@RequestBody Produto produto) {
        // Verificar se o nome do produto já existe no banco de dados
        boolean nomeExiste = produtoRepositorio.existsByNome(produto.getNome());

        if (nomeExiste) {
            return new ResponseEntity<>("Nome do produto já existe", HttpStatus.BAD_REQUEST);
        }

        produtoRepositorio.save(produto);
        return new ResponseEntity<>("Produto salvo com sucesso", HttpStatus.CREATED);
    }

    @GetMapping("/update")
    public void atualiza(@RequestBody @NotNull Produto produto) {

        Optional<Produto> optionalProduto = produtoRepositorio.findById(produto.getId());

        if (optionalProduto.isPresent()) {
            // Modify the fields of the entity object
            Produto produtoobj = optionalProduto.get();

            produtoobj.setNome(produto.getNome());

            // Save the entity
            produtoRepositorio.save(produtoobj);
            //return new ResponseEntity<>("Produto atualizado com sucesso", HttpStatus.CREATED);
        }
        //return new ResponseEntity<>("Produto atualizado com sucesso", HttpStatus.CREATED);

    }

    @GetMapping("/buscar/{id}")
    public Optional<Produto> buscarporId(@PathVariable Long id){
        return produtoRepositorio.findById(id);
    }

    @GetMapping("/buscartodos")
    public List<Produto> buscartudo(){
        return produtoRepositorio.findAll();
    }



}
