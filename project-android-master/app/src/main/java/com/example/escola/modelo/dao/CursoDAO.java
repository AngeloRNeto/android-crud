    package com.example.escola.modelo.dao;

    import android.content.ContentValues;
    import android.content.Context;
    import android.database.Cursor;
    import android.database.sqlite.SQLiteDatabase;

    import com.example.escola.modelo.Curso;

    import java.util.ArrayList;
    import java.util.List;

    public class CursoDAO {
        private CriarBanco banco;

        public CursoDAO(Context context) {
            this.banco = new CriarBanco(context);
        }

        public void inserir(Curso curso) throws Exception {
            SQLiteDatabase db = this.banco.getWritableDatabase();

            ContentValues newCurso = new ContentValues();
            newCurso.put("Nome",curso.getNome());

            long res = db.insert("Curso",null,newCurso);
            db.close();
            if(res == -1)
            {
                throw new Exception("Erro ao inserir dados");
            }
        }

        public void alterar(Curso curso) throws Exception {

            SQLiteDatabase db = this.banco.getWritableDatabase();

            ContentValues newCurso = new ContentValues();
            newCurso.put("Nome",curso.getNome());

            long res = db.update("Curso",newCurso,"_Id=?",new String[]{(String.valueOf(curso.getId()))});

            if(res == -1)
            {
                throw new Exception("Erro ao alterar o registro do curso.");
            }

            db.close();
        }

        public void excluir(int id) throws Exception {
            SQLiteDatabase db = this.banco.getWritableDatabase();

            long res = db.delete("Curso","_Id=?", new String[]{String.valueOf(id)});

            if(res == -1)
            {
                throw new Exception("Erro ao deletar o registro.");
            }

            db.close();
        }

        public List<Curso> getList(){
            String[] campos = {"_Id","Nome"};
            List<Curso> cursos = new ArrayList<>();

            SQLiteDatabase db = this.banco.getReadableDatabase();
            Cursor c = db.query("Curso",campos,null,null,null,null,null,null);

            while (c.moveToNext()){
                cursos.add(
                        new Curso(
                                c.getInt(c.getColumnIndexOrThrow("_Id")),
                                c.getString(c.getColumnIndexOrThrow("Nome"))
                                )
                );
            }
            db.close();

            return cursos;
        }
    }
