package pi2.econommarket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OpcoesLeite extends AppCompatActivity {

    Button btnLeite;
    Button btnLeiteBiscoitos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leite);


        btnLeite = (Button) findViewById(R.id.btnLeite);
        btnLeiteBiscoitos = (Button) findViewById(R.id.btnLeiteBiscoito);

        btnLeite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OpcoesLeite.this,Listagem.class);
                intent.putExtra("cat", "Leite e Achocolatado");
                intent.putExtra("prod", "leite");
                startActivity(intent);
                finish();
            }
        });

        btnLeiteBiscoitos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OpcoesLeite.this,Listagem.class);
                intent.putExtra("cat", "Biscoitos Doces");
                intent.putExtra("prod", "leite");
                startActivity(intent);
                finish();
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent it = new Intent(OpcoesLeite.this, Setores.class);
        startActivity(it);
        finish();
    }

}