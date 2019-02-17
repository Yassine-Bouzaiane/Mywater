package tn.esprit.mywatertunisie.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import tn.esprit.mywatertunisie.Entities.ChantierElectricite;
import tn.esprit.mywatertunisie.R;
import tn.esprit.mywatertunisie.URLs;

import android.content.Context;
import android.content.Intent;

import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class ChantierActivity extends AppCompatActivity {


    GridView Grid;
    GRIDAdapter adapter;


    private Context context;
    //private List<ChantierElectricite> listChantiers = new ArrayList<>();
    private List<String> image = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

////////////////////////REQUEST////////////////////////////////////
        Log.e("BEFORE STRING REQUEST", "DDDDDDDDDD");

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLs.URL_IMAGES_CHANTIERS, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    //         JSONObject jsonObject = new JSONObject(response);

                    JSONArray array = new JSONArray(response);

                    for (int i = 0; i < array.length(); i++) {

                        JSONObject o = array.getJSONObject(i);
                        ChantierElectricite ct = new ChantierElectricite(
                                o.getInt("id_chantier"),
                                o.getInt("idElect"),
                                o.getString("imageChantier"));

//                    listChantiers.add(ct);
                        image.add(ct.getImageChantier());
                        Log.e("Entité Chantier >>>>", ct.toString());
//
                    }

                } catch (JSONException e) {

                    Log.e("JSONExcep chantier >>>", image.toString());
                }
            }
        }
                ,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("onErrorResponse", error.toString());

                    }
                });
        Log.e("AFTER STRING REQUEST", "We got here");

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

////////////////////////REQUEST////////////////////////////////////


        Log.e("Listaa GRID >>>>>>>>> ", image.toString());
        Grid = (GridView) findViewById(R.id.MyGrid);
        adapter = new GRIDAdapter(getApplicationContext(), R.layout.grid_item, image);

        Grid.setAdapter(adapter);
        Grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
//                Intent go = new Intent(getApplicationContext(), Details.class);
//                go.putExtra("image", image[position]);
//                //By position Clicked
//                startActivity(go);
            }
        });

    }


    public class GRIDAdapter extends ArrayAdapter {
        private List<String> Image;
        private int resource;
        private LayoutInflater inflater;

        public GRIDAdapter(Context context, int resource, List<String> image) {
            super(context, resource, image);
            Image = image;
            this.resource = resource;
            inflater = (LayoutInflater) ChantierActivity.this.getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder Holder = null;

            if (convertView == null) {
                Holder = new ViewHolder();
                convertView = inflater.inflate(resource, null);
                Holder.IMAGE = convertView.findViewById(R.id.imageID);
                convertView.setTag(Holder);
            } else {
                Holder = (ViewHolder) convertView.getTag();
            }
            //            Holder.IMAGE.setImageResource(Image[position]);
            //          Holder.IMAGE.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Picasso.get().load(String.valueOf(Image.get(position))).centerCrop().into(Holder.IMAGE);

            return convertView;
        }

        class ViewHolder {
            private ImageView IMAGE;
        }
    }

}
