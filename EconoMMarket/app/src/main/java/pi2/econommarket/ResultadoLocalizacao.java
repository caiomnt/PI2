package pi2.econommarket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class ResultadoLocalizacao extends AppCompatActivity {

    public interface LocalizacaoService {
        @GET("lojas/listar")
        Call<List<Localizacao>> listarLocalizacoes();
    }

    LocalizacaoService localService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_resultado_busca);

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://www.gpabr.com/wp-content/plugins/gpa/public/")
                .build();

        localService = retrofit.create(LocalizacaoService.class);

        Call<List<Localizacao>> req = localService.listarLocalizacoes();

        req.enqueue(new Callback<List<Localizacao>>() {
            @Override
            public void onResponse(Call<List<Localizacao>> req, Response<List<Localizacao>> resp) {
                for (Localizacao l : resp.body()) {
                    System.out.println(l);
                }
            }

            @Override
            public void onFailure(Call<List<Localizacao>> req, Throwable erro) {
                Toast.makeText(ResultadoLocalizacao.this, erro.getMessage(), Toast.LENGTH_LONG).show();
            }

        });

    }

}
