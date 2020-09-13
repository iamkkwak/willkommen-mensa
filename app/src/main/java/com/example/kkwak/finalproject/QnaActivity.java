package com.example.kkwak.finalproject;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class QnaActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private EditText titleInput;
    private EditText emailInput;
    private EditText contentInput;

    private String title;
    private String email;
    private String content;

    private Button sendButton;
    private Button cancelButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qna);

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
            .permitDiskReads()
            .permitDiskWrites()
            .permitNetwork().build());

        toolbar = (Toolbar)findViewById(R.id.toolbar_qna);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        titleInput = (EditText)findViewById(R.id.input_title);
        emailInput = (EditText)findViewById(R.id.input_email);
        contentInput = (EditText)findViewById(R.id.input_content);

        emailInput.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);

        sendButton = (Button)findViewById(R.id.button_send);
        cancelButton = (Button)findViewById(R.id.button_cancel);

        sendButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                title = titleInput.getText().toString();
                email = emailInput.getText().toString();
                content = contentInput.getText().toString();

                if(title.isEmpty() || email.isEmpty() || !(checkEmail(email)) || content.isEmpty()) {
                    Toast.makeText(QnaActivity.this, "Please check your input.", Toast.LENGTH_LONG).show();
                }

                else {

                    try {
                        GMailSender sender = new GMailSender("admin@mensa.com", "GoogleKey");
                        sender.sendMail("[ Willkommen Mensa Q & A ] " + title,
                                "Sender : " + email + "\n\n" + content,
                                "admin@mensa.com");
                        Toast.makeText(QnaActivity.this, "Your mail is successfully sent.", Toast.LENGTH_LONG).show();
                        finish();

                    } catch (Exception e) {
                        Log.e("Send mail", e.getMessage(), e);
                    }
                }
            }
        });

        cancelButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                finish();
            }
        });
    }

    public static boolean checkEmail(String email){

        String regex = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);
        boolean isNormal = m.matches();
        return isNormal;

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return true;
    }
}
