package com.example.artemczenolegovic.pinechat;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ChatActivity extends Activity {

    RecyclerView.Adapter adapter;
    int i = 0;
    public String json_top=null;
    ArrayList<String> catNames=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);


        // получаем экземпляр элемента ListView
        ListView listView = (ListView) findViewById(R.id.listView);
        final EditText editText = (EditText) findViewById(R.id.editText);

        // Создаём пустой массив для хранения имен котов
        catNames = new ArrayList<>();

        // Создаём адаптер ArrayAdapter, чтобы привязать массив к ListView
        final ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, catNames);
        // Привяжем массив через адаптер к ListView
        listView.setAdapter(adapter);
        sendHttpGet("http://172.6.0.140/test/chat.php?action=select&data=4302");
        // Прослушиваем нажатия клавиш
        editText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                    if (keyCode == KeyEvent.KEYCODE_ENTER) {

                        catNames.add(0, editText.getText().toString());
                        adapter.notifyDataSetChanged();
                        editText.setText("");

                        return true;
                    }
                return false;
            }
        });
    }

    private void sendHttpGet(String url) {

        class HttpGetAsyncTask extends AsyncTask<String, Void, String> {

            private String lmresult = null;

            @Override
            protected String doInBackground(String... params) {

                String jsonData = null;
                String url = params[0];

                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(params[0])
                            .build();
                    Response responses = null;

                    try {
                        responses = client.newCall(request).execute();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                     jsonData = responses.body().string();
                    Log.e("res", jsonData);

            } catch (IOException e) {
                    e.printStackTrace();
                }
                return jsonData;
            }

                // Argument comes for this method according to the return type of the doInBackground() and
            //it is the third generic type of the AsyncTask
            @Override
            protected void onPostExecute(String result) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Отправлено", Toast.LENGTH_SHORT);
                toast.show();
                json_top=result;
                JSONArray mJsonArray = null;
                try {
                    mJsonArray = new JSONArray(result);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JSONObject mJsonObject = new JSONObject();
                ArrayList<String> arr = new ArrayList<String>();
                for (int i = 0; i < mJsonArray.length(); i++) {
                    String s = "";

                    try {
                        mJsonObject = mJsonArray.getJSONObject(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        s+=mJsonObject.getString("author");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        s+=":"+" "+mJsonObject.getString("text");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    catNames.add(s);
                }
                //jsonObject.getInt("")
                //catNames=arr;
                super.onPostExecute(result);
            }
        }

        // Initialize the AsyncTask class
        HttpGetAsyncTask httpGetAsyncTask = new HttpGetAsyncTask();
        httpGetAsyncTask.execute(url);

    }

}







/*
    private static int SIGN_IN_REQUEST_CODE = 1;
    private ListAdapter<Message> adapter;
    RelativeLayout activity_main;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        activity_main = (RelativeLayout)findViewById(R.id.activity_main);
        button = (Button)findViewById(R.id.button2);
    }

    private void displayChat() {

        ListView listMessages = (ListView)findViewById(R.id.listView);
        adapter = new FirebaseListAdapter<Message>(this, Message.class, R.layout.item, FirebaseDatabase.getInstance().getReference()) {
            @Override
            protected void populateView(View v, Message model, int position) {

                TextView textMessage, autor, timeMessage;
                textMessage = (TextView)v.findViewById(R.id.Message);
                autor = (TextView)v.findViewById(R.id.User);
                timeMessage = (TextView)v.findViewById(R.id.tvTime);

                textMessage.setText(model.getTextMessage());
                autor.setText(model.getAutor());
                timeMessage.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)", model.getTimeMessage()));
            }
        };
        listMessages.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SIGN_IN_REQUEST_CODE)
        {
            if (resultCode == RESULT_OK)
            {
                Snackbar.make(activity_main, "Вход выполнен", Snackbar.LENGTH_SHORT).show();
                displayChat();
            } else {
                Snackbar.make(activity_main, "Вход не выполнен", Snackbar.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_signout)
        {
            AuthUI.getInstance().signOut(this)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            Snackbar.make(activity_main, "Выход выполнен", Snackbar.LENGTH_SHORT).show();
                            finish();

                        }
                    });
        }
        return true;
    }
}
*/