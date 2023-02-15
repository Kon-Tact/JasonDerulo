package parse.JSon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private AdapterRecyclerView adapterRecycler;
    private ArrayList<ModelItem> itemArrayList;
    private RequestQueue requestQueue;
    private RecyclerView rvRecycler;

    private String search;
    private String searchToString;
    private EditText etSearch;
    private Button searchBtn;

    public void initUi() {
        etSearch = findViewById(R.id.etSearch);
        searchBtn = findViewById(R.id.searchBtn);
    }

    public void initRecyclerView() {
        rvRecycler = findViewById(R.id.rvRecycler);
        rvRecycler.setHasFixedSize(true);
        rvRecycler.setLayoutManager(new LinearLayoutManager(this));

        itemArrayList = new ArrayList<>();

        requestQueue = Volley.newRequestQueue(this);

    }

    public void parsejSonDERULO() {

        String pixaKey = "33662186-30adb95a3fc428d34a1e0153d";

        // https://pixabay.com/api/?key=33662186-30adb95a3fc428d34a1e0153d&q=yellow+flowers&image_type=photo
        String urlJSonDeruloFile = "https://pixabay.com/api/"
                + "?key=" + pixaKey
                + "&q=" + search
                + "&image_type=photo"
                + "&orientation=horizontal"
                + "&pretty=true";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, urlJSonDeruloFile, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // On récupère le tableau de données JSON à partir de notre objet JsonObjectRequest
                            // dans un try catch ajouter en auto en corrigeant l'erreur
                            JSONArray jsonArray = response.getJSONArray("hits");

                            // On récupère dans un premier temps toutes les données présentent dans le Array avec une boucle for
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);

                                // Puis on sélectionne celles dn on à besoin soit user - likes - webformatURL
                                String author = hit.getString("user");
                                int likes = hit.getInt("likes");
                                String imageUrl = hit.getString("webformatURL");

                                // On ajoute les données à notre tableau en utilisant son model
                                itemArrayList.add(new ModelItem(imageUrl, author, likes));
                            }
                            // On adapte le tableau de données
                            adapterRecycler = new AdapterRecyclerView(MainActivity.this, itemArrayList);
                            // Noter MainActivity.this car nous sommes dans une classe interne
                            // Puis on lie l'adpter au Recycler
                            rvRecycler.setAdapter(adapterRecycler);

                            /** #10.3 On peut alors ajouter le listener **/
//                            adapterRecycler.setOnItemClickListener(MainActivity.this);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(request);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initUi();
        initRecyclerView();
        parsejSonDERULO();

    }


}