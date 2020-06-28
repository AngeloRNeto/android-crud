package com.example.escola.modelo;

public class Turma {
    private int id;
    private String sala;
    private int qtdAluno;
    private Curso curso;


    public Turma(int id, String sala, int qtdAluno, Curso curso) {
        this.id = id;
        this.sala = sala;
        this.qtdAluno = qtdAluno;
        this.curso = curso;
    }

    public Turma(String sala, int qtdAluno, Curso curso) {
       // this.id = id;
        this.sala = sala;
        this.qtdAluno = qtdAluno;
        this.curso = curso;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public int getQtdAluno() {
        return qtdAluno;
    }

    public void setQtdAluno(int qtdAluno) {
        this.qtdAluno = qtdAluno;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}
