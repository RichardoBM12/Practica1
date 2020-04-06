package com.example.practica1.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.practica1.R;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class DisplayMessageActivity extends AppCompatActivity {
    private ListView lv;
    List<String> ligas = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
        Intent intent = getIntent();
        String message = intent.getStringExtra(LoginActivity.RESPONSE);
        StringTokenizer st = new StringTokenizer(message);
        lv = (ListView) findViewById(R.id.listView);
        while (st.hasMoreTokens()) {
                ligas.add(st.nextToken());
            }
        ligas.add("Nuevas");
        ligas.add("https://www.youtube.com/watch?v=xMw1LHjAumo");
        ligas.add("https://www.youtube.com/watch?v=RHEiY0rOQ2k");
        ligas.add("https://www.youtube.com/watch?v=6pMW_0_vZIo");
        ligas.add("https://www.youtube.com/watch?v=THwprYAmsNs");
        ligas.add("https://www.youtube.com/watch?v=kkAh0ArGOt4");
        ligas.add("https://www.youtube.com/watch?v=40kDkd2A2tc");
        ligas.add("https://www.youtube.com/watch?v=3gDtTxdjRIM");
        ligas.add("https://www.youtube.com/watch?v=G3rF4VwXImk");
        ligas.add("Repetidas");
        ligas.add("https://www.youtube.com/watch?v=xMw1LHjAumo");
        ligas.add("https://www.youtube.com/watch?v=RHEiY0rOQ2k");
        ligas.add("https://www.youtube.com/watch?v=6pMW_0_vZIo");
        ligas.add("https://www.youtube.com/watch?v=THwprYAmsNs");
        ligas.add("https://www.youtube.com/watch?v=kkAh0ArGOt4");
        ligas.add("https://www.youtube.com/watch?v=40kDkd2A2tc");
        ligas.add("https://www.youtube.com/watch?v=3gDtTxdjRIM");
        ligas.add("https://www.youtube.com/watch?v=G3rF4VwXImk");
        ligas.add("https://www.youtube.com/watch?v=xMw1LHjAumo");
        ligas.add("https://www.youtube.com/watch?v=RHEiY0rOQ2k");
        ligas.add("https://www.youtube.com/watch?v=6pMW_0_vZIo");
        ligas.add("https://www.youtube.com/watch?v=THwprYAmsNs");
        ligas.add("https://www.youtube.com/watch?v=kkAh0ArGOt4");
        ligas.add("https://www.youtube.com/watch?v=40kDkd2A2tc");
        ligas.add("https://www.youtube.com/watch?v=3gDtTxdjRIM");
        ligas.add("https://www.youtube.com/watch?v=G3rF4VwXImk");

        ligas.remove(0);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                R.layout.activity_list,
                R.id.textView3,ligas );

        lv.setAdapter(arrayAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(DisplayMessageActivity.this,  ligas.get(position), Toast.LENGTH_LONG).show();
            }
        });
    }
}
