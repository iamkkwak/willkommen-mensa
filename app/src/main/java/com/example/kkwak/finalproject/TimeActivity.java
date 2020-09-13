package com.example.kkwak.finalproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.TextView;

import java.util.Calendar;

public class TimeActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);

        toolbar = (Toolbar)findViewById(R.id.toolbar_time);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        state = (TextView)findViewById(R.id.state);
        String stateText = setState();
        state.setText(stateText);
        state.setTextColor(setColor(stateText));
    }

    public static String setState() {

        Calendar now = Calendar.getInstance();

        int day = now.get(Calendar.DAY_OF_WEEK);
        int hour = now.get(Calendar.HOUR_OF_DAY);
        int min = now.get(Calendar.MINUTE);

        int time = Integer.parseInt("" + hour + String.format("%02d", min));

        Calendar holidayStart = Calendar.getInstance();
        holidayStart.set(2018, Calendar.DECEMBER, 22);
        Calendar holidayEnd = Calendar.getInstance();
        holidayEnd.set(2019, Calendar.JANUARY, 6);

        Calendar vacationStart = Calendar.getInstance();
        vacationStart.set(2019, Calendar.FEBRUARY, 18);
        Calendar vacationEnd = Calendar.getInstance();
        vacationEnd.set(2019, Calendar.MARCH, 1);


        boolean closed = (day == Calendar.SUNDAY) || (day == Calendar.SATURDAY)
                || (now.after(holidayStart) && now.before(holidayEnd))
                || (now.after(vacationStart) && now.before(vacationEnd));


        if (closed)
            return "CLOSED";
        else if (day == Calendar.FRIDAY) {    // For Friday.
            if (1145 <= time && time < 1315)
                return "OPEN";
            else if (1315 <= time && time < 1330)
                return "CLOSING SOON";
            else
                return "CLOSED";
        } else {  // For Monday to Thursday.
            if (1145 <= time && time < 1330)
                return "OPEN";
            else if (1330 <= time && time < 1345)
                return "CLOSING SOON";
            else
                return "CLOSED";
        }
    }

    public int setColor(String state) {
        if(state.equals("OPEN"))
            return getResources().getColor(android.R.color.holo_green_dark);
        else if(state.equals("CLOSING SOON"))
            return getResources().getColor(android.R.color.holo_orange_light);
        else
            return getResources().getColor(android.R.color.holo_red_light);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return true;
    }
}
