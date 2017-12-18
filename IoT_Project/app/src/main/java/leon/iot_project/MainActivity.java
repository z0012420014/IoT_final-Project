package leon.iot_project;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import leon.iot_project.custom.TrashCan;
import leon.iot_project.custom_adapter.TrashCanAdapter;

public class MainActivity extends AppCompatActivity {
    final static String TAG = "123123";
    public  static final List<TrashCan> trashCanList = new ArrayList<TrashCan>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init object
        final TextView textView = (TextView)findViewById(R.id.textView);
        final ListView listView = (ListView)findViewById(R.id.listView);
        final Button refreshButton = (Button) findViewById(R.id.button2);
        final Button mapButton = (Button) findViewById(R.id.button3);

        FirebaseDatabase fireDB = FirebaseDatabase.getInstance();
        DatabaseReference myRef = fireDB.getReference("message");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i(TAG, "yoyoyoy");
                textView.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //List<TrashCan> trashCanList = new ArrayList<>();

        final DatabaseReference data0 = fireDB.getReference("iot_final").child("0");
        DatabaseReference data1 = fireDB.getReference("iot_final").child("1");
        DatabaseReference data2 = fireDB.getReference("iot_final").child("2");
        DatabaseReference data3 = fireDB.getReference("iot_final").child("3");
        DatabaseReference data4 = fireDB.getReference("iot_final").child("4");

//        final List<String> data0_full = new ArrayList<>();
//        List<String> data1_full = new ArrayList<>();
//        List<String> data2_full = new ArrayList<>();
//        List<String> data3_full = new ArrayList<>();
//        List<String> data4_full = new ArrayList<>();
//        final List<String> data0_time = new ArrayList<>();
//        List<String> data1_time = new ArrayList<>();
//        List<String> data2_time = new ArrayList<>();
//        List<String> data3_time = new ArrayList<>();
//        List<String> data4_time = new ArrayList<>();


        for(int i = 0; i < 5; i++){
            //init trashcan_size to 5
            trashCanList.add(new TrashCan(i, 0, "", 0, 0));
        }

        data0.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<TrashCan> list = new ArrayList<TrashCan>();
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    int full = Integer.parseInt(ds.child("full").getValue().toString());
                    String time = ds.child("time").getValue().toString();

                    //33.644746, -117.841637  ics
                    list.add(new TrashCan(0, full, time, -117.841637, 33.644746));
                }
                trashCanList.remove(0);
                trashCanList.add(0, list.get(list.size() - 1));
                TrashCanAdapter trashCanAdapter = new TrashCanAdapter(MainActivity.this, trashCanList);
                listView.setAdapter(trashCanAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        data1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<TrashCan> list = new ArrayList<TrashCan>();
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    int full = Integer.parseInt(ds.child("full").getValue().toString());
                    String time = ds.child("time").getValue().toString();
                    //33.645867, -117.845790  ayala
                    list.add(new TrashCan(1, full, time, -117.845790, 33.645867));
                }
                trashCanList.remove(1);
                trashCanList.add(1, list.get(list.size() - 1));
                TrashCanAdapter trashCanAdapter = new TrashCanAdapter(MainActivity.this, trashCanList);
                listView.setAdapter(trashCanAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        data2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<TrashCan> list = new ArrayList<TrashCan>();
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    int full = Integer.parseInt(ds.child("full").getValue().toString());
                    String time = ds.child("time").getValue().toString();
                    //humanity hall 33.647836, -117.844256
                    list.add(new TrashCan(2, full, time, -117.844256 ,33.647836));
                }
                trashCanList.remove(2);
                trashCanList.add(2, list.get(list.size() - 1));
                TrashCanAdapter trashCanAdapter = new TrashCanAdapter(MainActivity.this, trashCanList);
                listView.setAdapter(trashCanAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        data3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<TrashCan> list = new ArrayList<TrashCan>();
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    int full = Integer.parseInt(ds.child("full").getValue().toString());
                    String time = ds.child("time").getValue().toString();
                    // 33.649037, -117.842872 student center
                    list.add(new TrashCan(3, full, time,-117.842872,33.649037));
                }
                trashCanList.remove(3);
                trashCanList.add(3, list.get(list.size() - 1));
                TrashCanAdapter trashCanAdapter = new TrashCanAdapter(MainActivity.this, trashCanList);
                listView.setAdapter(trashCanAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        data4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<TrashCan> list = new ArrayList<TrashCan>();
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    int full = Integer.parseInt(ds.child("full").getValue().toString());
                    String time = ds.child("time").getValue().toString();
                    // Langson 33.647778, -117.841053
                    list.add(new TrashCan(4, full, time, -117.841053,33.647778));
                }
                trashCanList.remove(4);
                trashCanList.add(4, list.get(list.size() - 1));
                TrashCanAdapter trashCanAdapter = new TrashCanAdapter(MainActivity.this, trashCanList);
                listView.setAdapter(trashCanAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Click");
                for(TrashCan trashCan : trashCanList){
                    Log.i(TAG, "-----------------");
                    Log.i(TAG, String.valueOf(trashCan.getId()));
                    Log.i(TAG, String.valueOf(trashCan.getTime()));
                    Log.i(TAG, String.valueOf(trashCan.getFull()));
                    Log.i(TAG, String.valueOf(trashCan.getLatitude()));
                    Log.i(TAG, String.valueOf(trashCan.getLongitude()));
                    Log.i(TAG, "-----------------");
                }
                TrashCanAdapter trashCanAdapter = new TrashCanAdapter(MainActivity.this, trashCanList);
                listView.setAdapter(trashCanAdapter);
            }
        });


        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });

//        TrashCanAdapter trashCanAdapter = new TrashCanAdapter(this, trashCanList);
//        listView.setAdapter(trashCanAdapter);
    }
}
