package pi2.econommarket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OpcoesCervejas extends AppCompatActivity {

    Button btnCerveja;
    Button btnCervejaEspecial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cervejas);


        btnCerveja = (Button) findViewById(R.id.btnCerveja);
        btnCervejaEspecial = (Button) findViewById(R.id.btnCervejaEspecial);

        btnCerveja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OpcoesCervejas.this,Listagem.class);
                intent.putExtra("cat", "Cervejas");
                intent.putExtra("prod", "cerveja");
                startActivity(intent);
                finish();
            }
        });

        btnCervejaEspecial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OpcoesCervejas.this,Listagem.class);
                intent.putExtra("cat", "Cervejas Especiais");
                intent.putExtra("prod", "cerveja");
                startActivity(intent);
                finish();
            }
        });

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent it = new Intent(OpcoesCervejas.this, Setores.class);
        startActivity(it);
        finish();
    }

}
