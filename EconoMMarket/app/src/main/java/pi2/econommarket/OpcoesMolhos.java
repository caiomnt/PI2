package pi2.econommarket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class OpcoesMolhos extends AppCompatActivity {

    Button btnMolhosTomate;
    Button btnMolhosDiversos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_molhos);


        btnMolhosTomate = (Button) findViewById(R.id.btnMolhosTomate);
        btnMolhosDiversos = (Button) findViewById(R.id.btnMolhosDiversos);

        btnMolhosTomate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OpcoesMolhos.this,Listagem.class);
                intent.putExtra("cat", "Molhos de Tomate");
                intent.putExtra("prod", "molhos");
                startActivity(intent);
                finish();
            }
        });

        btnMolhosDiversos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OpcoesMolhos.this,Listagem.class);
                intent.putExtra("cat", "Molhos Diversos");
                intent.putExtra("prod", "molhos");
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent it = new Intent(OpcoesMolhos.this, Setores.class);
        startActivity(it);
        finish();
    }

}