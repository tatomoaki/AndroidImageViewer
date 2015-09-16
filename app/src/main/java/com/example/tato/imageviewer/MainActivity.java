package com.example.tato.imageviewer;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout relativeLayout = (RelativeLayout)findViewById(R.id.relativeLayout);
        relativeLayout.setBackgroundColor(Color.rgb(48,155,191));

        TextView message = (TextView)findViewById(R.id.message);
        setMessage(message);
        setButton((Button)findViewById(R.id.button));
    }
    public void setMessage(TextView textView) {

        String message = "Welcome To UCT Image Viewer.\n" +
                "This app serves to introduce the University of\n" +
                "Cape Town. Draw in your enthusiasm to enroll in a \n" +
                "world recognised institute. Click on quotes - link to UCT\n" +
                "home page. ";
        textView.setText(message);
        textView.setTextColor(Color.BLACK);
        textView.setGravity(Gravity.CENTER);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public void Redirect(View view) {
        Uri uri = Uri.parse("http://www.uct.ac.za");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void newActivity(View view) {
        Intent intent = new Intent(this, SecondaryActivity.class);
        startActivity(intent);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void setButton(Button button) {
        button.setBackgroundColor(Color.rgb(58,66,99));
        button.setTextColor(Color.rgb(216,228,232));
        button.animate();
    }
}
