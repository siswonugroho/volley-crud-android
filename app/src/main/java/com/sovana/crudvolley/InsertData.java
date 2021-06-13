package com.sovana.crudvolley;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.sovana.crudvolley.Util.AppController;
import com.sovana.crudvolley.Util.ServerAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class InsertData extends AppCompatActivity {
  EditText id_user, username, id_grup, nama, password;
  Button btnbatal, btnsimpan, btndelete;
  ProgressDialog pd;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_insert_data);

    /*get data from intent*/
    Intent data = getIntent();
    final int update = data.getIntExtra("update", 0);
    String intent_username = data.getStringExtra("username");
    String intent_id = data.getStringExtra("id");
    String intent_id_grup = data.getStringExtra("id_grup");
    String intent_nama = data.getStringExtra("nama");
    String intent_password = data.getStringExtra("password");
    /*end get data from intent*/

    id_user = (EditText) findViewById(R.id.inp_id);
    username = (EditText) findViewById(R.id.inp_username);
    id_grup = (EditText) findViewById(R.id.inp_grup);
    nama = (EditText) findViewById(R.id.inp_nama);
    password = (EditText) findViewById(R.id.inp_password);
    btnbatal = (Button) findViewById(R.id.btn_cancel);
    btnsimpan = (Button) findViewById(R.id.btn_simpan);
    btndelete = (Button) findViewById(R.id.btn_delete);
    pd = new ProgressDialog(InsertData.this);

    /*kondisi update / insert */
    if (update == 1) {
      btnsimpan.setText("Update Data");
      id_user.setText(intent_id);
      username.setText(intent_username);
      id_grup.setText(intent_id_grup);
      nama.setText(intent_nama);
      password.setText(intent_password);
    } else {
      id_user.setVisibility(View.GONE);
      btndelete.setVisibility(View.GONE);
    }

    btnsimpan.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if (update == 1) {
          Update_data();
        } else {
          simpanData();
        }
      }
    });

    btnbatal.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        finish();
      }
    });

    btndelete.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        deleteData();
      }
    });
  }

  private void Update_data() {
    pd.setMessage("Update Data");
    pd.setCancelable(false);
    pd.show();
    StringRequest updateReq = getStringRequest(Request.Method.POST, ServerAPI.URL_UPDATE);
    AppController.getInstance().addToRequestQueue(updateReq);
  }

  private StringRequest getStringRequest(int reqMethod, String url) {
    return new StringRequest(reqMethod, url, responseListener(), errorListener()) {
      @Override
      protected Map<String, String> getParams() throws AuthFailureError {
        Map<String, String> map = new HashMap<>();
        if (url == ServerAPI.URL_INSERT) {
          map.put("username", username.getText().toString());
          map.put("grup", id_grup.getText().toString());
          map.put("nama", nama.getText().toString());
          map.put("password", password.getText().toString());
        } else if (url == ServerAPI.URL_UPDATE) {
          map.put("username", username.getText().toString());
          map.put("grup", id_grup.getText().toString());
          map.put("nama", nama.getText().toString());
          map.put("password", password.getText().toString());
          map.put("id", id_user.getText().toString());
        } else if (url == ServerAPI.URL_DELETE) {
          map.put("id", id_user.getText().toString());
        }
        return map;
      }
    };
  }

  private Response.ErrorListener errorListener() {
    return new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {
        pd.cancel();
      }
    };
  }

  private void simpanData() {
    pd.setMessage("Menyimpan Data");
    pd.setCancelable(false);
    pd.show();
    StringRequest sendData = getStringRequest(Request.Method.POST, ServerAPI.URL_INSERT);
    AppController.getInstance().addToRequestQueue(sendData);
  }

  private void deleteData() {
    pd.setMessage("Menghapus data");
    pd.setCancelable(false);
    pd.show();
    StringRequest delReq = getStringRequest(Request.Method.POST, ServerAPI.URL_DELETE);
    AppController.getInstance().addToRequestQueue(delReq);
  }

  private Response.Listener<String> responseListener() {
    return new Response.Listener<String>() {
      @Override
      public void onResponse(String response) {
        pd.cancel();
        try {
          JSONObject res = new JSONObject(response);
          Toast.makeText(InsertData.this, res.getString("message"),
                  Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
          Toast.makeText(InsertData.this, e.getMessage(),
                  Toast.LENGTH_SHORT).show();
        }
        finish();
//        startActivity(n);
      }
    };
  }
}

