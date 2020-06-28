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
import com.example.escola.modelo.dao.CursoDAO;

import java.util.List;

public class CursoActivity extends AppCompatActivity {

    private List<Curso> cursos;
    CursoDAO cursoDAO;
    private ListView listView;


    //retorno do result na tela de cadastro
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK)
        {
            this.cursos = cursoDAO.getList();
            listView.invalidateViews();
        }
    }

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
                Intent intent = new Intent(getBaseContext(),CursoAdicionarActivity.class);
                startActivityForResult(intent,1);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Curso curso = cursos.get(info.position);
        switch (item.getItemId()){
            case R.id.alterar:
                //Captura o sucesso ao salvar curso
                Intent intent = new Intent(getBaseContext(),CursoAdicionarActivity.class);

                Bundle infos = new Bundle();
                infos.putString("idCurso", String.valueOf(curso.getId()));
                infos.putString("nomeCurso", curso.getNome().toString());

                intent.putExtras(infos);
                startActivityForResult(intent,1);
                return true;
            case R.id.excluir:
                try {
                    cursoDAO.excluir(curso.getId());
                    cursos = cursoDAO.getList();
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        cursoDAO = new CursoDAO(getBaseContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curso);

        //   final Button btnAdd = findViewById(R.id.btnAddCurso);
        // btnAdd.setOnClickListener(new View.OnClickListener() {
        //   @Override
        // public void onClick(View v) {
        //   //Captura o sucesso ao salvar curso
        // Intent intent = new Intent(getBaseContext(),CursoAdicionarActivity.class);
        // startActivityForResult(intent,RESULT_OK);
        //}
        //  });

        cursos = cursoDAO.getList();
        listView = findViewById(R.id.listViewCurso);

        listView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return CursoActivity.this.cursos.size();
            }

            @Override
            public Object getItem(int position) {
                return CursoActivity.this.cursos.get(position);
            }

            @Override
            public long getItemId(int position) {
                return CursoActivity.this.cursos.get(position).getId();
            }

            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {


                View view = LayoutInflater.from(getBaseContext()).inflate(R.layout.item_curso, parent, false);

                final Curso curso = CursoActivity.this.cursos.get(position);

//                TextView id = (TextView) view.findViewById(R.id.tvId);
                TextView nome = (TextView) view.findViewById(R.id.tvNome);

//                Button btnExcluir = (Button) view.findViewById(R.id.btnExcluir);
//                Button btnEdit = (Button) view.findViewById(R.id.btnEdit);

                //Seta id e o nome
//                id.setText(Long.toString(curso.getId()));
                nome.setText(curso.getNome());

//
//                btnExcluir.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        try {
//                            cursoDAO.excluir(curso.getId());
//                            cursos = cursoDAO.getList();
//                            notifyDataSetChanged();
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
//
//
//                btnEdit.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        //Captura o sucesso ao salvar curso
//                        Intent intent = new Intent(getBaseContext(),CursoAdicionarActivity.class);
//
//                        Bundle infos = new Bundle();
//                        infos.putString("idCurso", String.valueOf(curso.getId()));
//                        infos.putString("nomeCurso", curso.getNome().toString());
//
//                        intent.putExtras(infos);
//
//                        startActivityForResult(intent,1);
//                    }
//                });
                return view;
            }
        });
        registerForContextMenu(listView);
    }

}
