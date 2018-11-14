package com.takhaki.todaystaskhack;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    public Realm realm;
    public ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //追加画面へ遷移する
                newIntent();
            }
        });

        Realm.init(getApplicationContext());
        realm = Realm.getDefaultInstance();

        listView = (ListView) findViewById(R.id.listView);
    }

    public void setListView() {

        RealmResults<Memo> results = realm.where(Memo.class).findAll();
        List<Memo> items = realm.copyFromRealm(results);

        MemoAdapter adapter = new MemoAdapter(this, R.layout.layout_item_memo, items);
        listView.setAdapter(adapter);
    }

    private void newIntent() {
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume(){
        super.onResume();

        setListView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        realm.close();
    }
}
