package com.example.escola.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.escola.R;
import com.example.escola.modelo.Curso;
import com.example.escola.modelo.dao.CursoDAO;

import javax.xml.transform.Result;

public class CursoAdicionarActivity extends AppCompatActivity {

    CursoDAO cursoDAO;
    Curso curso;
    boolean edicao = false;
    String idCurso, nomeCurso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curso_adicionar);
        cursoDAO = new CursoDAO(getBaseContext());

        //Info para edição
        Bundle infos = getIntent().getExtras();
        final EditText edtCurso = findViewById(R.id.text_addCurso);
        Button add = findViewById(R.id.addCurso);

        if(infos != null){
            idCurso = infos.getString("idCurso");
            nomeCurso = infos.getString("nomeCurso");
            edicao = idCurso != "" && nomeCurso != "";

            if(edicao){
                edtCurso.setText(nomeCurso);
                add.setText("Salvar");
            }
        }


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    curso = new Curso();
                    curso.setNome(edtCurso.getText().toString());
                    if(edicao){
                        curso.setId(Integer.parseInt(idCurso));
                        cursoDAO.alterar(curso);
                    }
                    else {
                        cursoDAO.inserir(curso);
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
