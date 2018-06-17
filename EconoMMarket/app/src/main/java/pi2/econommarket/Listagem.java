package pi2.econommarket;

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

    private String cat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtragem);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        // use this setting to
        // improve performance if you know that changes
        // in content do not change the layout size
        // of the RecyclerView
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

         cat = getIntent().getStringExtra("cat");

        DatabaseReference ref = ConfiguracaoFirebase.getFirebase();


        ref.child("pao").child("cerveja").child(cat).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                items.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Produtos produto = postSnapshot.getValue(Produtos.class);
                    items.add(produto);
                }
                // Reload na lista
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
}

