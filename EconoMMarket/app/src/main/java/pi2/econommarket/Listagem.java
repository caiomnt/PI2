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
    private List<Produtos> items2 = new ArrayList<>();
    private List<Produtos> items3 = new ArrayList<>();
    private List<Produtos> items4 = new ArrayList<>();
    private List<Produtos> items5 = new ArrayList<>();
    private String cat;private String cat2;
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

        if(cat.equals("Cervejas") || cat.equals("Cervejas Especiais")){ cat2 = cat;}
        if(cat.equals("Leite e Achocolatado")){ cat2 = "Leite tradicional";}
        if(cat.equals("Biscoitos Doces")){ cat2 = "Biscoitos doces";}
        if(cat.equals("Molhos Diversos")){ cat2 = "Molhos especiais";}
        if(cat.equals("Molhos de Tomate")){ cat2 = "Atomatados";}

        DatabaseReference ref = ConfiguracaoFirebase.getFirebase();

        ref.child("pao").child(prod).child(cat).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                items.clear();
                items2.clear();

                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Produtos produto = postSnapshot.getValue(Produtos.class);
                    items.add(produto);
                }

                items2.addAll(quickSort(items));
                items5.add(items2.get(0));
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Listagem.this,"Não foi possivel conectar a base de dados! ",Toast.LENGTH_LONG).show();
            }

        });


        ref.child("extra").child(prod).child(cat2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                items3.clear();
                items4.clear();

                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Produtos produto = postSnapshot.getValue(Produtos.class);
                    items3.add(produto);
                }

                items4.addAll(quickSort(items3));
                items5.add(items4.get(0));
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Listagem.this,"Não foi possivel conectar a base de dados! ",Toast.LENGTH_LONG).show();
            }

        });

        mAdapter = new MyAdapter(items5);
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

