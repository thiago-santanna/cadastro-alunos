package com.tsswebapps.Curso.CadastroAluno;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ServiceAluno {
    public ServiceAluno(){}

    public List<Aluno> findAllService( String nome, Integer idade, List<Aluno> alunos) throws AlunoNaoEncontradoException {
        if(nome == null && idade == null) {
            return alunos;
        }

        if(nome != null && idade != null){
            Stream<Aluno>stm = alunos.stream()
                    .filter(aluno -> aluno.getNome().contains(nome)).collect(Collectors.toList())
                    .stream().filter(aluno -> aluno.getIdade().equals(idade));

            if(stm == null){
                throw new AlunoNaoEncontradoException("Aluno não encontrado!");
            }
            else{
                return stm.collect(Collectors.toList());
            }
        }

        if(nome != null){
            Stream<Aluno>stm = alunos.stream().filter(aluno -> aluno.getNome().contains(nome));
            if(stm == null){
                throw new AlunoNaoEncontradoException("Aluno não encontrado!");
            }else{
                return stm.collect(Collectors.toList());
            }
        }

        if(idade != null){
            Stream<Aluno> stm = alunos.stream().filter(aluno -> aluno.getIdade().equals(idade));
            if(stm == null){
                throw new AlunoNaoEncontradoException("Aluno não encontrado!");
            }
            else{
                return stm.collect(Collectors.toList());
            }
        }

        throw new AlunoNaoEncontradoException("Aluno não encontrado!");
    }

    public Aluno findByIdService(Integer id, List<Aluno> alunos) throws AlunoNaoEncontradoException {
        return alunos.stream()
                .filter( alu -> alu.getId().equals(id))
                .findFirst().orElseThrow(() -> new AlunoNaoEncontradoException( "Aluno não existe na base de dados " ));
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
