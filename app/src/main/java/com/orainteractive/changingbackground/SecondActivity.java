package com.orainteractive.changingbackground;

import android.app.Activity;
import android.app.Application;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * The main Activity of the app
 */
public class SecondActivity extends Activity {

    private Button transBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        transBt = (Button) findViewById(R.id.trans_bt);
        final Activity that = this;

        transBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                that.finish();
                overridePendingTransition(R.anim.lr_in, R.anim.lr_out);
                //TransitionDrawable d = (TransitionDrawable) getWindow().getDecorView().getBackground();
                //d.startTransition(2000);
            }
        });


    }


}