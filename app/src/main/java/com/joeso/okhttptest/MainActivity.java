package com.joeso.okhttptest;

import androidx.appcompat.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    OkHttpClient client = new OkHttpClient();
    TextView tvResult;
    EditText txtUrl;
    Button bnGet;

    String url="https://raw.github.com/square/okhttp/master/README.md";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtUrl=findViewById(R.id.txt_url);
        txtUrl.setText(url);
        tvResult=findViewById(R.id.tv_result);
        bnGet=findViewById(R.id.bn_get);
        bnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new OkHttpHandler().execute(txtUrl.getText().toString());
            }
        });
    }

    private class OkHttpHandler extends AsyncTask <String,Object,String>{

        OkHttpClient client = new OkHttpClient();

        @Override
        protected String doInBackground(String... urls) {
            try {
                Request.Builder builder = new Request.Builder();
                builder.url(urls[0]);
                Request request = builder.build();
                Response response = client.newCall(request).execute();
                return response.body().string();
            }catch (Exception e){
                return e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            tvResult.setText(result);
        }
    }
}



