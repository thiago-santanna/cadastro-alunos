package com.tsswebapps.Curso.CadastroAluno;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/alunos")
public class CadastroAlunoController {
    private final List<Aluno> Alunos;
    private final ServiceAluno serviceAluno;

    public CadastroAlunoController() {
        this.Alunos = new ArrayList<>();
        serviceAluno = new ServiceAluno();
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> findAll(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) Integer idade){
        try {
            return new ResponseEntity<>(serviceAluno.findAllService(nome, idade, this.Alunos), HttpStatus.OK);
        }
        catch (AlunoNaoEncontradoException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> findById(@PathVariable("id") Integer id){
        try {
            return new ResponseEntity<>( serviceAluno.findByIdService(id, this.Alunos), HttpStatus.OK );
        } catch (AlunoNaoEncontradoException e) {
            return new ResponseEntity<>( null, HttpStatus.NOT_FOUND );
        }
    }

    @PostMapping
    public ResponseEntity<Integer> insert(@RequestBody final Aluno aluno){
        this.Alunos.add(serviceAluno.insertServive(aluno, this.Alunos));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity update(@RequestBody final Aluno aluno){
        serviceAluno.updateService(aluno, this.Alunos);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id){
        serviceAluno.deleteService(id, this.Alunos);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
