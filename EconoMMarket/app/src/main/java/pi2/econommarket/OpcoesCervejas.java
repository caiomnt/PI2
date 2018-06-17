package pi2.econommarket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

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
                startActivity(intent);
                finish();
            }
        });

        btnCervejaEspecial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OpcoesCervejas.this,Listagem.class);
                intent.putExtra("cat", "Cervejas Especiais");
                startActivity(intent);
                finish();
            }
        });

    }

}
