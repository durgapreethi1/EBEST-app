

package com.eserv.client;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    GridLayout mainGrid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        for(int i=0; i<5; i++) {
            //  setContentView(R.layout.activity_serviceslist);
            LinearLayout linearLayout = new LinearLayout(this);
            Button button = new Button(this);
            button.setText(" Button Programmatically ");
            button.setTextSize(20);
            button.setGravity(Gravity.CENTER);

            linearLayout.addView(button);

            this.setContentView(linearLayout, new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT));
        }
        /*mainGrid = (GridLayout)findViewById(R.id.mainGrid);
        setevent(mainGrid);
    }

    public void setevent(GridLayout mainGrid) {

        for(int i = 0;i < mainGrid.getChildCount();i++) {
            CardView cardview = (CardView)mainGrid.getChildAt(i);
            final int final1 = i;
            cardview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getBaseContext(), "selected : "+final1, Toast.LENGTH_LONG).show();

                }
            });
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    }*/
    }
}
