package com.orainteractive.changingbackground;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * The main Activity of the app
 */
public class MainActivity extends Activity {

    private Button nextBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blank);
        //fire up the activity

        final Intent intent = new Intent(this, FirstActivity.class);
        startActivity(intent);
//
//        nextBt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(intent);
//                overridePendingTransition(R.anim.rl_in, R.anim.rl_out);
//                //TransitionDrawable d = (TransitionDrawable) getWindow().getDecorView().getBackground();
//                //d.startTransition(2000);
//            }
//        });


    }


}