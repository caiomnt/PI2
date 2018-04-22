package pi2.econommarket;

import android.content.Intent;
import android.media.MediaExtractor;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class CadastrarActivity extends AppCompatActivity {

    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastrar);


        Button viewCadastrar = findViewById(R.id.btnEfetuar);
        viewCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadastrar();
            }
        });
    }

    /* tem que ter letras e numeros/char especiais de tamanho de 6 a 16*/
    private static final String PASSWORD_PATTERN = "((?=.*[a-zA-Z])(?=.*[0-9@#$%!]).{6,16})";

    public boolean validar(final String senha) {
        return Pattern.compile(PASSWORD_PATTERN).matcher(senha).matches();
    }

    private void cadastrar() {

        EditText edtEmail = findViewById(R.id.edtEmail);
        EditText edtSenhaInicial = findViewById(R.id.edtSenhaInicial);
        EditText edtSenhaFinal = findViewById(R.id.edtSenhaFinal);

        if (edtSenhaInicial.getText().toString().equals(edtSenhaFinal.getText().toString())){
            if(validar(edtSenhaFinal.getText().toString())){
                Task<AuthResult> processo = auth.createUserWithEmailAndPassword(edtEmail.getText().toString(),edtSenhaFinal.getText().toString());
                processo.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Intent it = new Intent(CadastrarActivity.this, LoginActivity.class);
                            startActivity(it);
                        }else{
                            Toast.makeText(CadastrarActivity.this, "Ocorreu algum erro ao procesar!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }else{
                Toast.makeText(CadastrarActivity.this, "Senhas devem conter 6 à 16 digitos e possui numeros ou caracteres especiais.", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(CadastrarActivity.this, "Senhas não conferem!", Toast.LENGTH_LONG).show();
        }
    }
}
