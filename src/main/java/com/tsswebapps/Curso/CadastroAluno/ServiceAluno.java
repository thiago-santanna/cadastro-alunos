package com.tsswebapps.Curso.CadastroAluno;

import java.util.List;
import java.util.stream.Collectors;

public class ServiceAluno {
    public ServiceAluno(){}

    public List<Aluno> findAllService( String nome, Integer idade, List<Aluno> alunos){
        if(nome != null && idade != null){
            return alunos.stream()
                    .filter(aluno -> aluno.getNome().contains(nome)).collect(Collectors.toList())
                    .stream().filter(aluno -> aluno.getIdade().equals(idade)).collect(Collectors.toList());
        }

        if(nome != null){
            return alunos.stream()
                    .filter(aluno -> aluno.getNome().contains(nome)).collect(Collectors.toList());
        }

        if(idade != null){
            return alunos.stream()
                    .filter(aluno -> aluno.getIdade().equals(idade)).collect(Collectors.toList());
        }

        return alunos;
    }

    public Aluno findByIdService(Integer id, List<Aluno> alunos){
        return alunos.stream()
                .filter( alu -> alu.getId().equals(id))
                .findFirst().orElse(null);
    }

    public Aluno insertServive(Aluno aluno, List<Aluno> alunos){
        if (aluno.getId() == null){
            aluno.setId(alunos.size()+1);
        }
        return aluno;
    }

    public void updateService(Aluno aluno, List<Aluno> alunos){
        alunos.stream()
                .filter(alunoFilter -> alunoFilter.getId().equals(aluno.getId()))
                .forEach(alunoFinder -> {
                    alunoFinder.setNome(aluno.getNome());
                    alunoFinder.setIdade(aluno.getIdade());
                });
    }

    public void deleteService(Integer id, List<Aluno> alunos){
        alunos.removeIf(alu -> alu.getId().equals(id));
    }
}
