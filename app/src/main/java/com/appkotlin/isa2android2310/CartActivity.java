package com.appkotlin.isa2android2310;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    ListView cartitems;
    TextView total;

    Button orderbtn;

    List<FoodItem> selectedItems;
    CartItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        // Check if the Android version is Lollipop or higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Change the status bar color to black
            getWindow().setStatusBarColor(Color.BLACK);
        }
        cartitems = findViewById(R.id.cartlistview);
        total=findViewById(R.id.displayTotal);

        orderbtn=findViewById(R.id.orderbtn);

        Bundle bundle1 = getIntent().getExtras();

        double t = bundle1.getDouble("total");

        selectedItems = bundle1.getParcelableArrayList("selectedItems");

        // Create adapter for the list view
        adapter = new CartItemAdapter(this, selectedItems);

        // Set adapter to list view
        cartitems.setAdapter(adapter);

        total.setText("Rs. "+t);

        orderbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<FoodItem> copySelecteditems = new ArrayList<>(selectedItems);
                SingletonLastList foodItemListSingleton = SingletonLastList.getInstance();

                foodItemListSingleton.setFoodItemList(copySelecteditems);
                foodItemListSingleton.setTotal(t);
                //intent for last order
                //Intent i = new Intent(getApplicationContext(), LastOrder.class);
                //List<FoodItem> copySelecteditems = new ArrayList<>(selectedItems);
                //Bundle bundle = new Bundle();
                //bundle.putParcelableArrayList("lastSelectedItems", (ArrayList<? extends Parcelable>) copySelecteditems);
                //bundle.putDouble("lasttotal",t);
                //i.putExtras(bundle);



                Intent intent = new Intent(CartActivity.this, SuccessActivity.class);
                startActivity(intent);

                selectedItems.clear();

                total.setText("Rs. 0.00");
                adapter.notifyDataSetChanged();



                //FoodAdapter.clearSelectedItems();
            }
        });



    }

//    @Override
//    public void onBackPressed() {
//        selectedItems.clear();
//
//        total.setText("Rs. 0.00");
//        adapter.notifyDataSetChanged();
//        super.onBackPressed();
//    }

}