package pi2.econommarket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Listagem extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Produtos> items = new ArrayList<>();
    private List<Produtos> itemsOrdenados = new ArrayList<>();

    private String cat;
    private String prod;

    public List<Produtos> quickSort(List<Produtos> lista){

        if (lista.size() <= 1){
            return lista;
        }

        List<Produtos> listaordenada = new ArrayList<>();
        List<Produtos> menor = new ArrayList<>();
        List<Produtos> maior = new ArrayList<>();

        Produtos pivot = lista.get(lista.size()-1);
        for (int i = 0; i < lista.size()-1; i++) {
            if (lista.get(i).getPreco() < pivot.getPreco() ) menor.add(lista.get(i));
            else maior.add(lista.get(i));
        }
        menor = quickSort(menor);
        maior = quickSort(maior);

        //Realiza a concatenação das listas
        menor.add(pivot);
        menor.addAll(maior);
        listaordenada = menor;

        return listaordenada;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtragem);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        prod = getIntent().getStringExtra("prod");
        cat = getIntent().getStringExtra("cat");

        DatabaseReference ref = ConfiguracaoFirebase.getFirebase();

        ref.child("pao").child(prod).child(cat).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                itemsOrdenados.clear();
                items.clear();

                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Produtos produto = postSnapshot.getValue(Produtos.class);
                    itemsOrdenados.add(produto);
                }

                items=quickSort(itemsOrdenados);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Listagem.this,"Não foi possivel conectar a base de dados! ",Toast.LENGTH_LONG).show();
            }

        });

        mAdapter = new MyAdapter(items);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent it = new Intent(Listagem.this, Setores.class);
        startActivity(it);
        finish();
    }

}

