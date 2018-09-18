package com.example.tequilagrexit.testme;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Objects;

import static com.example.tequilagrexit.testme.seconddate.pikmonat;
import static java.util.Calendar.getInstance;


public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    static final String TEST1_SCORE = "t1Score";
    //static final String EBH1_SCORE = "e1Score";
    int t1 = 1;
    String schicht;

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt(TEST1_SCORE, t1);
        super.onSaveInstanceState(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar3);
        setSupportActionBar(myToolbar);


        NumberPicker np = findViewById(R.id.NumberPicker1);
        np.setMinValue(0);
        np.setMaxValue(11);
        np.setDisplayedValues(seconddate.pikmonat);
        np.getValue();
        np.setOnValueChangedListener(onValueChangeListener);
        if (savedInstanceState != null) {
            t1 = savedInstanceState.getInt(TEST1_SCORE);
            np.setValue(t1);
        }

        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });

    }


   /* @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }*/

    NumberPicker.OnValueChangeListener onValueChangeListener =
            new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker np, int i, int i1) {

                    TextView t = findViewById(R.id.textView);
                    TextView f = findViewById(R.id.toolbar_text);
                    t.setText(seconddate.pikmonat[np.getValue()]);
                    f.setText("" + np.getValue());
                    t1 = np.getValue();
                }
            };

    public void wahl(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        EditText editText = (EditText) findViewById(R.id.textInputEditText2);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        //startActivity(intent);
        TextView l = findViewById(R.id.textView);
        l.setText(message);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void nudate(View view) {
        seconddate sc = new seconddate();
        Calendar work = getInstance();
        Calendar help = getInstance();
        TextView lcode = findViewById(R.id.textInputEditText2);
        work.clear();
        if (sc.string_ok(lcode)) {
            work.set(Calendar.YEAR, sc.readlotyear(lcode));
            work.set(Calendar.WEEK_OF_YEAR, sc.readlotweek(lcode));
            work.set(Calendar.DAY_OF_WEEK, sc.readlotday(lcode));
            String nudatum = "" + work.get(Calendar.DATE) + "." + (work.get(Calendar.MONTH) + 1) + "." + work.get(Calendar.YEAR);
            TextView l = findViewById(R.id.textView);
            if (work.compareTo(help) > 0)
                l.setText("falsch");
            else {
                String finaler = nudatum + sc.readlotshift(lcode);
                l.setText(finaler);
            }

        }else {
                 TextView l = findViewById(R.id.textView);
                 l.setText("Eingabe zu kurz");}

    }
}

