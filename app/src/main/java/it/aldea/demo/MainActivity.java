package it.aldea.demo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import it.aldea.android.widget.SlideButton;
import it.aldea.android.widget.SlideButtonListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ((SlideButton) findViewById(R.id.unlockButton)).setSlideButtonListener(new SlideButtonListener() {
            @Override
            public void handleSlide() {
                System.out.println("Unlock");
            }
        });

        ((SlideButton) findViewById(R.id.unlockButtonV)).setSlideButtonListener(new SlideButtonListener() {
            @Override
            public void handleSlide() {
                System.out.println("Unlock Vertical");
            }
        });
    }


}
