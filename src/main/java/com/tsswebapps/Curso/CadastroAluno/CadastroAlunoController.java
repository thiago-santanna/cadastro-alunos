package com.tsswebapps.Curso.CadastroAluno;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/alunos")
public class CadastroAlunoController {
    private final List<Aluno> Alunos;

    public CadastroAlunoController() {
        this.Alunos = new ArrayList<>();
    }

    @GetMapping
    public List<Aluno> findAll(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) Integer idade){

        if(nome != null && idade != null){
            return this.Alunos.stream()
            .filter(aluno -> aluno.getNome().contains(nome)).collect(Collectors.toList())
                    .stream().filter(aluno -> aluno.getIdade().equals(idade)).collect(Collectors.toList());
        }

        if(nome != null){
            return this.Alunos.stream()
                    .filter(aluno -> aluno.getNome().contains(nome)).collect(Collectors.toList());
        }

        if(idade != null){
            return this.Alunos.stream()
                    .filter(aluno -> aluno.getIdade().equals(idade)).collect(Collectors.toList());
        }

        return Alunos;
    }

    @GetMapping("/{id}")
    public Aluno findById(@PathVariable("id") Integer id){
        return this.Alunos.stream()
                .filter( alu -> alu.getId().equals(id))
                .findFirst().orElse(null);
    }

    @PostMapping
    public ResponseEntity<Integer> insert(@RequestBody final Aluno aluno){
        if (aluno.getId() == null){
            aluno.setId(this.Alunos.size()+1);
        }

        this.Alunos.add(aluno);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity update(@RequestBody final Aluno aluno){
        this.Alunos.stream()
                .filter(alunoFilter -> alunoFilter.getId().equals(aluno.getId()))
                .forEach(alunoFinder -> {
                    alunoFinder.setNome(aluno.getNome());
                    alunoFinder.setIdade(aluno.getIdade());
                });
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id){
        this.Alunos.removeIf(alu -> alu.getId().equals(id));
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
