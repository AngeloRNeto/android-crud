package com.example.escola.modelo.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CriarBanco extends SQLiteOpenHelper {

    public CriarBanco(@Nullable Context context) {
        super(context, "Escola.db", null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String tableCurso =
              "CREATE TABLE Curso (" +
                        "_Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "Nome TEXT NOT NULL);";


        String tableTurma =
              "CREATE TABLE Turma(" +
                      "_Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                      "Sala TEXT NOT NULL," +
                      "QtdAluno INTEGER," +
                      "Id_Curso INTEGER," +
                      "FOREIGN KEY(Id_Curso) REFERENCES Curso(_Id));";


        db.execSQL(tableCurso);
        db.execSQL(tableTurma);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE Cursos");
        onCreate(db);
    }
}
