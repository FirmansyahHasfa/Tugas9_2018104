package com.example.tugas4;

import androidx.appcompat.app.AppCompatActivity;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import com.example.tugas4.databinding.ActivityRestApiBinding;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
public class RestAPI extends AppCompatActivity implements
        View.OnClickListener{
    //declaration variable
    private ActivityRestApiBinding binding;
    String index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setup view binding
        binding =
                ActivityRestApiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.fetchButton.setOnClickListener(this);
    }
    //onclik button fetch
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.fetch_button){
            index = binding.inputId.getText().toString();
            try {
                getData();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }
    //get data using api link
    public void getData() throws MalformedURLException {
        Uri uri = Uri.parse("https://kodepos-2d475.firebaseio.com/kota_kab/k69.json?print=pretty")
                .buildUpon().build();
        URL url = new URL(uri.toString());
        new DOTask().execute(url);
    }
    class DOTask extends AsyncTask<URL, Void, String>{
        //connection request
        @Override
        protected String doInBackground(URL... urls) {
            URL url = urls [0];
            String data = null;
            try {
                data = NetworkUtils.makeHTTPRequest(url);
            }catch (IOException e){
                e.printStackTrace();
            }
            return data;
        }
        @Override
        protected void onPostExecute(String s){
            try {
                parseJson(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        //get data json
        public void parseJson(String data) throws JSONException{
            JSONArray jsonArray = null;
            try {
                jsonArray = new JSONArray(data);
            }catch (JSONException e){
                e.printStackTrace();
            }

            for (int i =0; i <jsonArray.length(); i++){
                JSONObject obj =
                        jsonArray.getJSONObject(i);
                String Sobj = obj.get("kelurahan").toString();
                if (Sobj.equals(index)){
                    String kecamatan = obj.get("kecamatan").toString();
                    binding.resultKecamatan.setText(kecamatan);
                    String kodepos = obj.get("kodepos").toString();
                    binding.resultKodepos.setText(kodepos);
                    String kelurahan = obj.get("kelurahan").toString();
                    binding.resultKelurahan.setText(kelurahan);
                    break;

                }
                else{
//                    binding.resultName.setText("Not Found");
                }
            }
        }
    }
}
