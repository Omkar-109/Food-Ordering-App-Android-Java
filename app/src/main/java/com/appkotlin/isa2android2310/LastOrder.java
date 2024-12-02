package com.appkotlin.isa2android2310;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class LastOrder extends AppCompatActivity {

    ListView lastitem1;
    Button backlast;
    TextView lasttotal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_order);
// Check if the Android version is Lollipop or higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Change the status bar color to black
            getWindow().setStatusBarColor(Color.BLACK);
        }
        lastitem1=findViewById(R.id.cartlistviewlast);
        backlast=findViewById(R.id.lastbackbtn);
        lasttotal=findViewById(R.id.displayTotallast);


//        Bundle bundle = getIntent().getExtras();
//
//        double t = bundle.getDouble("lasttotal");
//
//        List<FoodItem> mylastselectedItems = bundle.getParcelableArrayList("lastSelectedItems");

        SingletonLastList foodItemListSingleton = SingletonLastList.getInstance();

        List<FoodItem> mylastselectedItems =foodItemListSingleton.getFoodItemList();
        // Create adapter for the list view
        LastOrderAdapter adapter1 = new LastOrderAdapter(this, mylastselectedItems);


        // Set adapter to list view
        lastitem1.setAdapter(adapter1);


        lasttotal.setText("Rs. "+foodItemListSingleton.getTotal());

        backlast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k=new Intent(LastOrder.this, MainActivity.class);
                startActivity(k);
            }
        });



    }
}