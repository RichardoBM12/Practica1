package com.example.practica1.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.practica1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

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
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendTimer(view);
            }
        });
        int pos = 0;
        Intent intent = getIntent();
        String message = intent.getStringExtra(LoginActivity.RESPONSE);
        StringTokenizer st = new StringTokenizer(message);
        lv = (ListView) findViewById(R.id.listView);
        while (st.hasMoreTokens()) {
            String v = st.nextToken();
            if(v.equals("true")){
                v = st.nextToken();
            }
            System.out.println(v);
            if (v.indexOf("https://www.youtube.com") != -1) {
                ligas.add(v);
            }else{
                if(pos == 0) {
                    ligas.add("https://www.youtube.com/watch?v=xMw1LHjAumo");
                    ligas.add("https://www.youtube.com/watch?v=RHEiY0rOQ2k");
                    ligas.add("https://www.youtube.com/watch?v=6pMW_0_vZIo");
                    ligas.add("https://www.youtube.com/watch?v=THwprYAmsNs");
                    ligas.add("https://www.youtube.com/watch?v=kkAh0ArGOt4");
                    ligas.add("https://www.youtube.com/watch?v=40kDkd2A2tc");
                    ligas.add("https://www.youtube.com/watch?v=3gDtTxdjRIM");
                    ligas.add("https://www.youtube.com/watch?v=G3rF4VwXImk");
                    pos += 1;
                }
            }
            if(v.indexOf("google.streetview")!=-1) {
                ligas.add(v);
            }else{
                if(pos == 1) {
                    ligas.add("google.streetview:cbll=19.223590,-99.030364");
                    ligas.add("google.streetview:cbll=19.302501,-99.191553");
                    pos += 1;
                }
            }
            if(v.indexOf("?q=") == -1 && v.indexOf("geo:")!=-1) {
                ligas.add(v);
            }else{
                if(pos == 2) {
                    ligas.add("geo:19.311326,-99.196483");
                    ligas.add("geo:19.324295,-99.179478");
                    pos+=1;
                }
            }
            if((v.indexOf("geo:0,0?q=")) != -1) {
                ligas.add(v);
            }else{
                if(pos==3) {
                    ligas.add("geo:0,0?q=cines");
                    ligas.add("geo:0,0?q=escuelas");
                    pos += 1;
                }
            }
            if((v.indexOf("https://")) != -1 && pos == 4) {
                ligas.add(v);
            }else{
                if(pos==4) {
                    ligas.add("https://es.wikipedia.org/");
                    ligas.add("https://twitter.com/");
                    pos+=1;
                }
            }
            if((v.indexOf("google.navigation")!=-1)){
                ligas.add(v);
                ligas.add("google.navigation:q=Universidad+Pedagogica+Nacional,+Mexico");
                ligas.add("google.navigation:q=Prepa+8,+Mexico");
            }
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                R.layout.activity_list,
                R.id.textView3,ligas );

        lv.setAdapter(arrayAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Uri liga = Uri.parse(ligas.get(position));
                Intent intento = new Intent(Intent.ACTION_VIEW, liga);
                Toast.makeText(DisplayMessageActivity.this,  ligas.get(position), Toast.LENGTH_LONG).show();
                PackageManager packageManager = getPackageManager();
                List<ResolveInfo> activities = packageManager.queryIntentActivities(intento, 0);
                boolean isIntentSafe = activities.size() > 0;
                if (isIntentSafe) {
                    startActivity(intento);
                }

            }
        });
    }
    public void sendTimer(View view) {
        Intent intent = new Intent(this, TomarTiempo.class);
        startActivity(intent);
    }
}
