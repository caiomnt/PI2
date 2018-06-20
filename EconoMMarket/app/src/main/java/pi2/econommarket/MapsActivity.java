package pi2.econommarket;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {


    public interface LocalizacaoService {
        @GET("lojas/listar")
        Call<List<Localizacao>> listarLocalizacoes();
    }

    private GoogleMap mMap;
    ResultadoLocalizacao.LocalizacaoService localService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://www.gpabr.com/wp-content/plugins/gpa/public/")
                .build();

        localService = retrofit.create(ResultadoLocalizacao.LocalizacaoService.class);

        Call<List<Localizacao>> req = localService.listarLocalizacoes();

        req.enqueue(new Callback<List<Localizacao>>() {
            @Override
            public void onResponse(Call<List<Localizacao>> req, Response<List<Localizacao>> resp) {

                if (resp.isSuccessful()) {
                    List<Localizacao> lista = resp.body();

                    if (lista != null && lista.size() > 0) {
                        /*
                        Localizacao x = lista.get(0);

                        LatLng mercado = new LatLng(Double.parseDouble(x.latitude), Double.parseDouble(x.longitude));
                        mMap.addMarker(new MarkerOptions().position(mercado).title(x.tipo));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mercado, 15));
                        */
                        for (Localizacao x : lista) {
                            LatLng mercado = new LatLng(Double.parseDouble(x.latitude), Double.parseDouble(x.longitude));
                            mMap.addMarker(new MarkerOptions().position(mercado).title(x.tipo));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mercado, 15));
                        }
                    }
                }
            }
            @Override
            public void onFailure(Call<List<Localizacao>> req, Throwable erro) {
                Toast.makeText(MapsActivity.this, erro.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent it = new Intent(MapsActivity.this, HomeActivity.class);
        startActivity(it);
        finish();
    }
}