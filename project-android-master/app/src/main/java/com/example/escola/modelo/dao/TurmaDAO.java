package com.example.escola.modelo.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.escola.modelo.Curso;
import com.example.escola.modelo.Turma;

import java.util.ArrayList;
import java.util.List;

public class TurmaDAO {

    private CriarBanco banco;

    public TurmaDAO(Context context) {
        this.banco = new CriarBanco(context);
    }

    public void inserir(Turma turma) throws Exception {
        SQLiteDatabase db = this.banco.getWritableDatabase();

        ContentValues newSala = new ContentValues();
        newSala.put("Sala",turma.getSala());
        newSala.put("QtdAluno",turma.getQtdAluno());
        newSala.put("Id_Curso",turma.getCurso().getId());

        long res = db.insert("Turma",null,newSala);
        db.close();
        if(res == -1)
        {
            throw new Exception("Erro ao inserir dados");
        }
    }

    public void alterar(Turma turma) throws Exception {

        SQLiteDatabase db = this.banco.getWritableDatabase();

        ContentValues newSala = new ContentValues();
        newSala.put("Sala",turma.getSala());
        newSala.put("QtdAluno",turma.getQtdAluno());
        newSala.put("Id_Curso",turma.getCurso().getId());

        long res = db.update("Turma",newSala,"_Id=?",new String[]{ String.valueOf(turma.getId())});

        if(res == -1)
        {
            throw new Exception("Erro ao alterar o registro da turma.");
        }

        db.close();
    }

    public void excluir(int id) throws Exception {
        SQLiteDatabase db = this.banco.getWritableDatabase();

        long res = db.delete("Turma","_Id=?",new String[]{ String.valueOf(id)});

        if(res == -1)
        {
            throw new Exception("Erro ao deletar o registro.");
        }

        db.close();
    }

    public List<Turma> getList(){
        String[] campos = {"_Id","Sala","QtdAluno","Id_Curso"};
        List<Turma> turmas = new ArrayList<>();

        SQLiteDatabase db = this.banco.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT *, T._Id AS Turma_id FROM Turma AS T " +
                                    "INNER JOIN Curso AS C ON C._id = T.id_curso", null);

        while (c.moveToNext()){
            turmas.add(
                    new Turma(
                            c.getInt(c.getColumnIndexOrThrow("Turma_id")),
                            c.getString(c.getColumnIndexOrThrow("Sala")),
                            c.getInt(c.getColumnIndexOrThrow("QtdAluno")),
                            new Curso(
                                    c.getInt(c.getColumnIndexOrThrow("Id_Curso")),
                                    c.getString(c.getColumnIndexOrThrow("Nome")))

                    )
            );
        }
        db.close();

        return turmas;
    }
}
