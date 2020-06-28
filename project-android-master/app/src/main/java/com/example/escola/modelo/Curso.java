package com.example.escola.modelo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Curso {
    private int Id;
    private String Nome;

    public Curso() {

    }

    public Curso(int id, String nome) {
        Id = id;
        Nome = nome;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    @Override
    public String toString() {
        return this.getNome();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return ((Curso) obj).getId() == this.getId();
    }
}
