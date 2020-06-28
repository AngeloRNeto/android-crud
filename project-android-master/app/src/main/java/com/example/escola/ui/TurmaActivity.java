package com.example.escola.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.escola.R;
import com.example.escola.modelo.Curso;
import com.example.escola.modelo.Turma;
import com.example.escola.modelo.dao.TurmaDAO;

import java.util.List;

public class TurmaActivity extends AppCompatActivity {

    private List<Turma> turmas;
    TurmaDAO turmaDAO;
    private ListView listView;


//retorno do result na tela de cadastro ==============
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK)
        {
            this.turmas = turmaDAO.getList();
            listView.invalidateViews();
        }
    }

//Menu Barra ========================================
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.curso_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.new_Curso:
                Intent intent = new Intent(getBaseContext(),TurmaAdicionarActivity.class);
                startActivityForResult(intent,1);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
//======================================================


//Menu lista
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Turma turma = turmas.get(info.position);
        switch (item.getItemId()){
            case R.id.alterar:
                //Captura o sucesso ao salvar curso
                Intent intent = new Intent(getBaseContext(),TurmaAdicionarActivity.class);

                Bundle infos = new Bundle();
                infos.putString("idTurma", String.valueOf(turma.getId()));
                infos.putString("sala", turma.getSala().toString());
                infos.putString("alunos", String.valueOf(turma.getQtdAluno()));
                infos.putString("curso", String.valueOf(turma.getCurso()));
                infos.putString("idCurso", String.valueOf(turma.getCurso().getId()));

                intent.putExtras(infos);
                startActivityForResult(intent,1);
                return true;
            case R.id.excluir:
                try {
                    turmaDAO.excluir(turma.getId());
                    turmas = turmaDAO.getList();
                    listView.invalidateViews();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.curso_lista_menu,menu);
    }
//======================================================


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turma);

        turmaDAO = new TurmaDAO(getBaseContext());
        turmas = turmaDAO.getList();

        listView = findViewById(R.id.listViewTurma);
        listView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return TurmaActivity.this.turmas.size();
            }

            @Override
            public Object getItem(int position) {
                return TurmaActivity.this.turmas.get(position);
            }

            @Override
            public long getItemId(int position) {
                return TurmaActivity.this.turmas.get(position).getId();
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = LayoutInflater.from(getBaseContext()).inflate(R.layout.item_turma, parent, false);
                final Turma turma = TurmaActivity.this.turmas.get(position);

                TextView sala = (TextView) view.findViewById(R.id.tvSala);
                TextView curso = (TextView) view.findViewById(R.id.tvCurso);
                TextView alunos = (TextView) view.findViewById(R.id.tvAlunos);

                //Seta id e o nome
                sala.setText(turma.getSala());
                alunos.setText("Qtd Alunos:" + turma.getQtdAluno());
                curso.setText(turma.getCurso().getNome());

                view.findViewById(R.id.listViewTurma);

                return view;
            }
        });
        registerForContextMenu(listView);
    }
}
