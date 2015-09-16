package com.example.tato.imageviewer;

import android.app.AlertDialog;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.graphics.Color;
import android.os.Environment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;
import java.io.File;

public class SecondaryActivity extends AppCompatActivity implements  View.OnClickListener {


    private ViewFlipper viewFlipper;
    private TextView textView;
    private Button previous;
    private Button next;
    private Button stop;
    private File imageFile [];

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);
        RelativeLayout relativeLayout = (RelativeLayout)findViewById(R.id.relativeLayout);
        relativeLayout.setBackgroundColor(Color.rgb(45, 69, 77));
        assignListener();

        viewFlipper = (ViewFlipper)findViewById(R.id.viewFlipper);          //To flip through Images

        textView = (TextView)findViewById(R.id.textView);

        viewFlipper.setFlipInterval(2000);
        readExternalStorage();
        viewFlipper.startFlipping();
    }

    public void readExternalStorage(){
        if(isExternalStorageReadable()){

            String directory = Environment.getExternalStorageDirectory().getParent().toString();        //String representation of Directory
            String [] toSD = directory.substring(1).split("/");
            String SDdirectory = "/"+toSD[0]+"/extSdCard/UCTImages";

            File fExists = new File(SDdirectory);
            if(fExists.isDirectory() && fExists.exists()){                      //assert Folder exists and is a directory
                onImageGallery(SDdirectory);
            }

        else {
            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle("SD Card Issue");
            alertDialog.setMessage("Create UCTImages Folder in SD Card to Read from.");
            alertDialog.setNeutralButton("Close", null);
            alertDialog.show();
        }}
    }
    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.next:
                viewFlipper.showNext();
                int i = viewFlipper.getDisplayedChild();
                textView.setText(imageFile[i].getName());
                break;
            case R.id.previous:
                int j = viewFlipper.getDisplayedChild();
                viewFlipper.showPrevious();
                textView.setText(imageFile[j].getName());
                break;
            case R.id.stop:
                if(viewFlipper.isFlipping()){
                     stop.setText("RESUME");
                    int k = viewFlipper.getDisplayedChild();
                    viewFlipper.stopFlipping();

                    textView.setText(imageFile[k].getName());
                }
                else {
                    stop.setText("STOP");
                    textView.setText("");
                    viewFlipper.startFlipping();
                }
                break;
        }
    }
    public void assignListener() {
        previous = (Button)findViewById(R.id.previous);
        next = (Button)findViewById(R.id.next);
        stop = (Button)findViewById(R.id.stop);

        previous.setOnClickListener(this);
        stop.setOnClickListener(this);
        next.setOnClickListener(this);
    }
    /**
     * Decode Images From SD Card
     * @param SDdirectory
     */
    public void onImageGallery(String SDdirectory) {
        File images = new File(SDdirectory);
        imageFile = images.listFiles();
        int i = 0;
        for(File img: imageFile) {
            Bitmap bitmap = BitmapFactory.decodeFile(img.getAbsolutePath());
            ImageView imgView = new ImageView(this);
            imgView.setImageBitmap(bitmap);
            viewFlipper.addView(imgView);

        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_secondary, menu);
        return true;
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
    /**Checks if External Storage is available to read*/
    public boolean isExternalStorageReadable(){
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;

    }
}
