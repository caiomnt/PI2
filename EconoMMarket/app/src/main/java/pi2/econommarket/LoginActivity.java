package pi2.econommarket;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {

    FirebaseAuth auth = FirebaseAuth.getInstance();
    private CallbackManager callbackManager;
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        /* ------------ Ações de botões ------------ */


        /* Botao de entrar */
        Button viewLogin = findViewById(R.id.btnLogin);
        viewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        /* Botao de criar uma nova conta */
        Button viewCadastrar = findViewById(R.id.btnEfetuar);
        viewCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadastrar();
            }
        });

        /* Texto de esqueci minha senha */
        TextView viewEsquecer = findViewById(R.id.btnEsquecer);
        viewEsquecer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                esquecer();
            }
        });

        /* Imagem de logar com o Google */
        ImageView viewGoogle = findViewById(R.id.btnGoogle);
        viewGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginGoogle();
            }
        });


        /* Imagem de logar com o Facebook */
        ImageView viewFacebook = findViewById(R.id.btnFacebook);
        viewFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginFacebook();
            }
        });

        /* ------------ Verificar se está logado ------------ */

        FirebaseUser user = auth.getCurrentUser();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        if(user != null || account != null){
            Intent it = new Intent(LoginActivity.this, EnvioFirebaseActivity.class);
            startActivity(it);
            finish();
        }
    }



    /* ------------ Metodos auxiliares ------------ */

    private void loginFacebook() {
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));

        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {}

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(LoginActivity.this, "Ocorreu algum erro!", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                Toast.makeText(LoginActivity.this, "Falhou ao logar com o Google", Toast.LENGTH_LONG).show();
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                Toast.makeText(LoginActivity.this, "Falhou ao logar com o Google", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void handleFacebookAccessToken(AccessToken token){
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        auth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Intent it = new Intent(LoginActivity.this, LoginActivity.class);
                    startActivity(it);
                } else {
                    Toast.makeText(LoginActivity.this, "Erro ao autenticar!", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    private void loginGoogle() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void signIn() {
        Intent it = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(it, 88776);
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        auth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Intent it = new Intent(LoginActivity.this, LoginActivity.class);
                    startActivity(it);
                } else {
                    Toast.makeText(LoginActivity.this, "Falha na autentificação.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    private void login() {

        EditText edtEmail = findViewById(R.id.edtEmail);
        EditText edtSenha = findViewById(R.id.edtSenha);
        if (edtEmail.getText().toString().matches("") || edtSenha.getText().toString().matches("")){
          Toast.makeText(LoginActivity.this, "Informe o usuário e a senha.", Toast.LENGTH_LONG).show();
        }else{
          Task<AuthResult> processo = auth.signInWithEmailAndPassword(edtEmail.getText().toString(),edtSenha.getText().toString());
          processo.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
              @Override
              public void onComplete(@NonNull Task<AuthResult> task) {
                  if(task.isSuccessful()) {
                      Intent it = new Intent(LoginActivity.this, EnvioFirebaseActivity.class);
                      startActivity(it);
                  }else{
                      Toast.makeText(LoginActivity.this, "E-mail ou senha inválido!", Toast.LENGTH_LONG).show();
                  }
              }
          });
        }
    }

    private void cadastrar() {
        Intent it = new Intent(LoginActivity.this, CadastrarActivity.class);
        startActivity(it);
    }

    private void esquecer() {
        Intent it = new Intent(LoginActivity.this, EsquecerActivity.class);
        startActivity(it);
    }
}
