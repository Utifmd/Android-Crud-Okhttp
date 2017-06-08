package com.doricovix.utif.phpdroid;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;


public class SaveActivity extends AppCompatActivity {

    private String BASU_URL = "http://192.168.100.12/trynerror/php_gridroid/insert.php";
    private OkHttpClient client = new OkHttpClient();

    EditText etDesc, etImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        etDesc = (EditText) findViewById(R.id.et_description);
        etImg = (EditText) findViewById(R.id.et_image);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sDesc = etDesc.getText().toString().trim();
                String sImg = etImg.getText().toString().trim();
                cmdSubmit(sDesc, sImg);
            }
        });
    }

    private void cmdSubmit(String sDesc, String sImg) {
        RequestBody body = new FormEncodingBuilder()
                .add("description", sDesc)
                .add("image", sImg)
                .build();

        Request request = new Request.Builder()
                .url(BASU_URL)
                .post(body)
                .build();

        Call call = client.newCall(request);

        call.enqueue(new Callback() {

            @Override
            public void onFailure(Request request, IOException e) {
                System.out.println("Registration Error" + e.getMessage());
            }

            @Override
            public void onResponse(Response response) throws IOException {
                try {
                    String resp = response.body().string();
                    System.out.println(resp);
                    if (response.isSuccessful()) {
                        finish();
                    } else {
                        finish();
                    }
                } catch (IOException e) {
                    System.out.println("Exception caught" + e.getMessage());
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
