package com.example.escola.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.escola.R;
import com.example.escola.modelo.Curso;
import com.example.escola.modelo.Turma;
import com.example.escola.modelo.dao.CursoDAO;
import com.example.escola.modelo.dao.TurmaDAO;

import java.util.ArrayList;
import java.util.List;

public class TurmaAdicionarActivity extends AppCompatActivity {

    CursoDAO daoCurso;
    TurmaDAO daoTurma;
    Curso cursoObj;

    boolean edicao = false;
    String idTurma, sala, qtdAlunos,curso,idCurso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turma_adicionar);

        //GetListCursos
        daoCurso = new CursoDAO(getBaseContext());
        List<Curso> cursos = daoCurso.getList();

        daoTurma = new TurmaDAO(getBaseContext());


        Button add = findViewById(R.id.addTurma);
        //Add new turma
        final EditText edtTurma = findViewById(R.id.text_turma);
        final EditText edtAlun = findViewById(R.id.text_alunos);

        //Populate spinner
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        final ArrayAdapter<Curso> dataAdapter = new ArrayAdapter<Curso>(this, android.R.layout.simple_spinner_item, cursos);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);



        //Info para edição
        Bundle infos = getIntent().getExtras();

        if(infos != null){
            idTurma = infos.getString("idTurma");
            sala = infos.getString("sala");
            qtdAlunos = infos.getString("alunos");
            curso = infos.getString("curso");
            idCurso = infos.getString("idCurso");
            edicao = idTurma != "" && qtdAlunos != "" && sala !="";

            if(edicao){
                edtTurma.setText(sala);
                edtAlun.setText(qtdAlunos);
//                int a = dataAdapter.getPosition();
//                spinner.setSelection(a);


                Curso c = new Curso();
                c.setId(Integer.parseInt(idCurso));
                spinner.setSelection(cursos.indexOf(c));

                add.setText("Salvar");
            }
        }

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{

                    Curso cursoSpinner = dataAdapter.getItem(spinner.getSelectedItemPosition());

                    Turma turma = new Turma(
                            edtTurma.getText().toString(),
                            Integer.parseInt(edtAlun.getText().toString()),
                            cursoSpinner);

                    if(edicao){
                        daoTurma.alterar(turma);
                    }
                    else{
                        daoTurma.inserir(turma);
                    }

                    setResult(RESULT_OK);
                    finish();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}
