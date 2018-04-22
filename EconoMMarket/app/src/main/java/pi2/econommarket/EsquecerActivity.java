package pi2.econommarket;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class EsquecerActivity extends AppCompatActivity {

    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.esquecer);

        Button viewCadastrar = findViewById(R.id.btnEnviar);
        viewCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviar();
            }
        });
    }


    private void enviar() {

        EditText edtEmail = findViewById(R.id.edtEmail);
        Task processo = auth.sendPasswordResetEmail(edtEmail.getText().toString());
        processo.addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Intent it = new Intent(EsquecerActivity.this, LoginActivity.class);
                    Toast.makeText(EsquecerActivity.this, "Enviado!", Toast.LENGTH_LONG).show();
                    startActivity(it);
                }else{
                    Toast.makeText(EsquecerActivity.this, "Ocorreu algum erro ao procesar!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
