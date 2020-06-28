package com.example.escola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.escola.ui.CursoActivity;
import com.example.escola.ui.TurmaActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button listCurso = findViewById(R.id.btnListCurso);
        listCurso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(this,"Teste",Toast.LENGTH_LONG).show();
                Intent i = new Intent(getBaseContext(),CursoActivity.class); // -- CHAMADA DE OUTRA TELA ("ACTIVITY")
                startActivity(i);
            }
        });

        Button listTurma = findViewById(R.id.btnListTurma);
        listTurma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(this,"Teste",Toast.LENGTH_LONG).show();
                Intent i = new Intent(getBaseContext(), TurmaActivity.class); // -- CHAMADA DE OUTRA TELA ("ACTIVITY")
                startActivity(i);
            }
        });
    }

}
