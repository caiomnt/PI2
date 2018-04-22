package pi2.econommarket;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;


public class EnvioFirebaseActivity extends AppCompatActivity {

    FirebaseAuth auth;

    EditText edtNome;
    EditText edtDataNasc;
    EditText edtSexo;

    FirebaseDatabase database;
    FirebaseStorage storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.envio_firebase);

        edtNome = findViewById(R.id.edtNome);
        edtDataNasc = findViewById(R.id.edtDataNasc);
        edtSexo = findViewById(R.id.edtSexo);

        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();
        auth = FirebaseAuth.getInstance();

        Button btn = findViewById(R.id.btnGravar);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gravar();
                /*auth.signOut();*/
            }
        });

        Button btn2 = findViewById(R.id.btnSair);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                Intent it = new Intent(EnvioFirebaseActivity.this, LoginActivity.class);
                startActivity(it);
            }
        });

        Button btn3 = findViewById(R.id.btnFoto);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                if(it.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(it, 1);
                }
            }
        });

    }

    private void gravar(){

      /* tem que incluir no banco um armazenamento para a foto*/
        String nome = edtNome.getText().toString();
        String dataNasc = edtDataNasc.getText().toString();
        String sexo = edtSexo.getText().toString();

        FirebaseUser user = auth.getCurrentUser();
        String uid = user.getUid();

        DatabaseReference usuarios = database.getReference("/usuario");
        usuarios.child(uid).child("nome").setValue(nome);
        usuarios.child(uid).child("dataNasc").setValue(dataNasc);
        usuarios.child(uid).child("sexo").setValue(sexo);


        Toast.makeText(EnvioFirebaseActivity.this, "Cadastrado no banco do Firebase!", Toast.LENGTH_LONG).show();
    }
}
