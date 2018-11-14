package com.takhaki.todaystaskhack;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Locale;

import io.realm.Realm;

public class AddActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    EditText titleEdit;
    EditText timeEdit;
    EditText notifEdit;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Realm.init(getApplicationContext());
        realm = Realm.getDefaultInstance();

        titleEdit = (EditText) findViewById(R.id.tittleEditText);
        timeEdit = (EditText) findViewById(R.id.timeEditText);
        notifEdit = (EditText) findViewById(R.id.notifEfitText);


        timeEdit.setKeyListener(null);

    }

    public void editTextToString(View view) {

        //タイトルを取得する
        final String title = titleEdit.getText().toString();
        final String time = timeEdit.getText().toString();
        final String content = notifEdit.getText().toString();
        final Boolean isDone = false;

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Memo memo = realm.createObject(Memo.class);
                memo.addData(title, time, content, isDone);
            }
        });

        //更新が終了したら
        finish();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String str = String.format(Locale.JAPAN, "%d/%d/%d", year, month + 1, dayOfMonth);

        timeEdit.setText(str);
    }

    public void showDatePickerDialog(View view) {
        DialogFragment fragment = new DatePickerDialogFragment();
        fragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();

        realm.close();
    }


}
