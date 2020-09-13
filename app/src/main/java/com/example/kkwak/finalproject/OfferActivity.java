package com.example.kkwak.finalproject;

import android.graphics.Color;
import android.graphics.Typeface;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class OfferActivity extends AppCompatActivity implements View.OnClickListener {

    static final String[] MONTHS = {"January", "February", "March", "April", "May", "Jun",
            "July", "August", "September", "October", "November", "December"};

    private TextView month;
    private TextView mon;
    private TextView tue;
    private TextView wed;
    private TextView thur;
    private TextView fri;

    private List<Meal> mealList;

    private ImageView image_gut;
    private ImageView image_gourmet;
    private ImageView image_wok;
    private ImageView image_prima;

    private TextView name_gut;
    private TextView name_gourmet;
    private TextView name_wok;
    private TextView name_prima;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_offer);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        Calendar now = Calendar.getInstance();
        String getMonth = MONTHS[now.get(Calendar.MONTH)];
        List<String> week = getWeek();

        month = (TextView)findViewById(R.id.month);
        month.setText(getMonth);

        mon = (TextView)findViewById(R.id.mon);
        mon.setOnClickListener(this);
        mon.setText(week.get(0));

        tue = (TextView)findViewById(R.id.tue);
        tue.setOnClickListener(this);
        tue.setText(week.get(1));

        wed = (TextView)findViewById(R.id.wed);
        wed.setOnClickListener(this);
        wed.setText(week.get(2));

        thur = (TextView)findViewById(R.id.thur);
        thur.setOnClickListener(this);
        thur.setText(week.get(3));

        fri = (TextView)findViewById(R.id.fri);
        fri.setOnClickListener(this);
        fri.setText(week.get(4));


        name_gut = (TextView)findViewById(R.id.name_gut);
        name_gourmet = (TextView)findViewById(R.id.name_gourmet);
        name_wok = (TextView)findViewById(R.id.name_wok);
        name_prima = (TextView)findViewById(R.id.name_prima);

        image_gut = (ImageView)findViewById(R.id.image_gut);
        image_gourmet = (ImageView)findViewById(R.id.image_gourmet);
        image_wok = (ImageView)findViewById(R.id.image_wok);
        image_prima = (ImageView)findViewById(R.id.image_prima);


        mealList = xmlParser();
        int day = now.get(Calendar.DAY_OF_WEEK);
        if (day == 1 || day == 7) {
            setMenu(mealList.get(4));
        }
        else {
            setMenu(mealList.get(now.get(day - 2)));
        }
    }

    public static List<String> getWeek() {
        List<String> week = new ArrayList<>();
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("dd");
        Calendar cal = Calendar.getInstance();

        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        week.add(formatter.format(cal.getTime()));

        cal.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
        week.add(formatter.format(cal.getTime()));

        cal.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
        week.add(formatter.format(cal.getTime()));

        cal.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
        week.add(formatter.format(cal.getTime()));

        cal.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
        week.add(formatter.format(cal.getTime()));

        return week;
    }

    public List<Meal> xmlParser() {
        List<Meal> mealList = new ArrayList<>();

        try {
            XmlPullParser parser = getResources().getXml(R.xml.mensa_meal);
            int eventType = parser.getEventType();

            while(eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG) {
                    if ("meal".equals(parser.getName())) {
                        String day = parser.getAttributeValue(0);

                        while(eventType != XmlPullParser.END_TAG) {
                            if (eventType == XmlPullParser.START_TAG) {
                                if ("menu".equals(parser.getName())) {
                                    String gut = parser.getAttributeValue(0);
                                    String gourmet = parser.getAttributeValue(1);
                                    String wok = parser.getAttributeValue(2);
                                    String prima = parser.getAttributeValue(3);

                                    Meal meal = new Meal(day, gut, gourmet, wok, prima);
                                    mealList.add(meal);
                                }
                            }
                            eventType = parser.next();
                        }
                    }
                }
                eventType = parser.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return mealList;
    }

    public int findImage(String name) {
        String resourceName = name.toLowerCase().replace(" ", "");
        int imageId = getResources().getIdentifier(resourceName, "drawable", getPackageName());

        return imageId;
    }

    public void setMenu(Meal meal) {

        String day = meal.getDay();
        switch (day) {
            case "mon" :
                mon.setTypeface(null, Typeface.BOLD);
                mon.setBackgroundColor(Color.LTGRAY);
                break;
            case "tue" :
                tue.setTypeface(null, Typeface.BOLD);
                tue.setBackgroundColor(Color.LTGRAY);
                break;
            case "wed" :
                wed.setTypeface(null, Typeface.BOLD);
                wed.setBackgroundColor(Color.LTGRAY);
                break;
            case "thur" :
                thur.setTypeface(null, Typeface.BOLD);
                thur.setBackgroundColor(Color.LTGRAY);
                break;
            case "fri" :
                fri.setTypeface(null, Typeface.BOLD);
                fri.setBackgroundColor(Color.LTGRAY);
                break;
        }

        String gut = meal.getGut();
        String gourmet = meal.getGourmet();
        String wok = meal.getWok();
        String prima = meal.getPrima();

        name_gut.setText(gut);
        name_gourmet.setText(gourmet);
        name_wok.setText(wok);
        name_prima.setText(prima);

        image_gut.setImageResource(findImage(gut));
        image_gourmet.setImageResource(findImage(gourmet));
        image_wok.setImageResource(findImage(wok));
        image_prima.setImageResource(findImage(prima));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mon :
                setDefault();
                mon.setTypeface(null, Typeface.BOLD);
                mon.setBackgroundColor(Color.LTGRAY);
                setMenu(mealList.get(0));
                break;
            case R.id.tue :
                setDefault();
                tue.setTypeface(null, Typeface.BOLD);
                tue.setBackgroundColor(Color.LTGRAY);
                setMenu(mealList.get(1));
                break;
            case R.id.wed :
                setDefault();
                wed.setTypeface(null, Typeface.BOLD);
                wed.setBackgroundColor(Color.LTGRAY);
                setMenu(mealList.get(2));
                break;
            case R.id.thur :
                setDefault();
                thur.setTypeface(null, Typeface.BOLD);
                thur.setBackgroundColor(Color.LTGRAY);
                setMenu(mealList.get(3));
                break;
            case R.id.fri :
                setDefault();
                fri.setTypeface(null, Typeface.BOLD);
                fri.setBackgroundColor(Color.LTGRAY);
                setMenu(mealList.get(4));
                break;
        }
    }

    public void setDefault() {
        mon.setTypeface(null, Typeface.NORMAL);
        mon.setBackgroundColor(Color.TRANSPARENT);

        tue.setTypeface(null, Typeface.NORMAL);
        tue.setBackgroundColor(Color.TRANSPARENT);

        wed.setTypeface(null, Typeface.NORMAL);
        wed.setBackgroundColor(Color.TRANSPARENT);

        thur.setTypeface(null, Typeface.NORMAL);
        thur.setBackgroundColor(Color.TRANSPARENT);

        fri.setTypeface(null, Typeface.NORMAL);
        fri.setBackgroundColor(Color.TRANSPARENT);
    }
}
