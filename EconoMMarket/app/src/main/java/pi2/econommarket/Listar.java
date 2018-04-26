package pi2.econommarket;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Listar extends AppCompatActivity {


    public class Aluno{
        public String matricula;
        public String nome;
        public String curso;

        public Aluno(String matricula,String nome,String curso){
            this.matricula = matricula;
            this.nome = nome;
            this.curso = curso;
        }
    }

    private List<Aluno> listaAlunos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listar);


        Aluno aluno1 = new Aluno("1234", "Joao", "CCO");
        Aluno aluno2 = new Aluno("1222", "Jose", "ENG");

        listaAlunos.add(aluno1);
        listaAlunos.add(aluno2);

        RecyclerView lista = findViewById(R.id.lista);
        lista.setLayoutManager(new LinearLayoutManager(this));
        lista.setItemAnimator(new DefaultItemAnimator());


        AlunoAdapter adapter = new AlunoAdapter(this);
        lista.setAdapter(adapter);
    }

    public class AlunoViewHolder extends RecyclerView.ViewHolder{


        public TextView txtNome;
        public TextView txtMatricula;
        public TextView txtCurso;

        public AlunoViewHolder(View view){
            super(view);

            txtNome = view.findViewById(R.id.txtNome);
            txtMatricula = view.findViewById(R.id.txtMatricula);
            txtCurso = view.findViewById(R.id.txtCurso);

        }
    }

    public class AlunoAdapter extends RecyclerView.Adapter<AlunoViewHolder>{

        private Context nContext;

        public AlunoAdapter(Context context){
            nContext = context;
        }

        @Override
        public AlunoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(nContext).inflate(R.layout.item_lista, parent, false);
            AlunoViewHolder holder = new AlunoViewHolder(v);
            return holder;
        }

        @Override
        public void onBindViewHolder(AlunoViewHolder holder, int position) {
            Aluno a = listaAlunos.get(position);

            holder.txtNome.setText(a.nome);
            holder.txtMatricula.setText(a.matricula);
            holder.txtCurso.setText(a.curso);

        }

        @Override
        public int getItemCount() {
            return listaAlunos.size();
        }
    }
}
