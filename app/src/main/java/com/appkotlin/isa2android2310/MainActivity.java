package com.appkotlin.isa2android2310;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    BottomAppBar bottomAppBar;
    ScrollView scrollView;
    GridView gridView;
    private SearchView searchView;
    private FoodAdapter foodAdapter;
    private double totalPrice = 0;
    List<FoodItem> MaincopySelecteditems;
    FloatingActionButton cartbutton;

    //String[] data = {"Apple", "Banana", "Cherry", "Date", "Fig", "Grape","Apple", "Banana", "Cherry", "Date", "Fig", "Grape","Apple", "Banana", "Cherry", "Date", "Fig", "Grape"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Check if the Android version is Lollipop or higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Change the status bar color to black
            getWindow().setStatusBarColor(Color.BLACK);
        }

        gridView = findViewById(R.id.griditems);
        scrollView = findViewById(R.id.scrollView2);
        searchView = findViewById(R.id.searchView);

        cartbutton=findViewById(R.id.floatingActionButton);

        List<FoodItem> foodItems = generateFoodItems();
        foodAdapter = new FoodAdapter(this, foodItems);
        gridView.setAdapter(foodAdapter);


        cartbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(foodAdapter.flag){
                    totalPrice=0.0;
                    foodAdapter.flag=false;
                }

                // Pass selected items to CartActivity
                Intent intent1 = new Intent(MainActivity.this, CartActivity.class);


                Bundle bundle1 = new Bundle();

                bundle1.putParcelableArrayList("selectedItems", (ArrayList<? extends    Parcelable>) foodAdapter.getSelectedItems());
                bundle1.putDouble("total",totalPrice);
                intent1.putExtras(bundle1);
                startActivity(intent1);

                Toast.makeText(MainActivity.this, "Order Total: Rs." + totalPrice +"/-", Toast.LENGTH_SHORT).show();
            }
        });

//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
//                android.R.layout.simple_list_item_1, android.R.id.text1, data);
//        gridView.setAdapter(adapter);


        bottomAppBar=findViewById(R.id.bottomAppBar);
        bottomAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if(item.getItemId()==R.id.home){

                    gridView.setSelection(0);
                    Toast.makeText(MainActivity.this, "Home", Toast.LENGTH_SHORT).show();
                }


                if(item.getItemId()==R.id.assist){
                    Toast.makeText(MainActivity.this, "contact us", Toast.LENGTH_SHORT).show();
                    openPhoneDialer();
                }

                if(item.getItemId()==R.id.setting){
                    Toast.makeText(MainActivity.this, "Settings", Toast.LENGTH_SHORT).show();
                }

                if(item.getItemId()==R.id.lastorder){
                    Toast.makeText(MainActivity.this, "Last Order", Toast.LENGTH_SHORT).show();
                    Intent il=new Intent(MainActivity.this, LastOrder.class);
                    startActivity(il);
                }

                if (item.getItemId() == R.id.resetmain) {
                    Toast.makeText(MainActivity.this, "Clear Selection", Toast.LENGTH_SHORT).show();
                    foodAdapter.resetSelection();
                    //totalPrice = 0;
                    return true; // Return true to indicate that the item is handled
                }
                return false;
            }
        });



        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // This method is called when the user submits the query (e.g., presses search button)
                // Here we do not filter, but instead scroll to the item if found
                searchAndScroll(query, foodItems);
                return true; // Return true to indicate that the query has been handled
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // This method is called when the text in the search bar changes
                // You can use this for real-time filtering of results
                // newText contains the current text in the search bar
                return false; // We don't handle text change here
            }
        });


    }

    public List<FoodItem> generateFoodItems() {
        List<FoodItem> foodItems = new ArrayList<>();
        // Adding hardcoded food items
        foodItems.add(new FoodItem("Pizza", 300, R.drawable.pizza));
        foodItems.add(new FoodItem("Burger", 70, R.drawable.burger));
        foodItems.add(new FoodItem("Salad", 80, R.drawable.salad));
        foodItems.add(new FoodItem("Pasta", 110, R.drawable.pasta));
        foodItems.add(new FoodItem("Chole Bhature", 140, R.drawable.cholebhature));
        foodItems.add(new FoodItem("Dal", 90, R.drawable.dal));
        foodItems.add(new FoodItem("Veg Thali",120 , R.drawable.vegthali));
        foodItems.add(new FoodItem("Masala Dosa", 90, R.drawable.masaladosa));
        foodItems.add(new FoodItem("Chicken Biryani", 160, R.drawable.chickenbiryani));
        foodItems.add(new FoodItem("Butter Chicken", 290, R.drawable.butterchicken));
        foodItems.add(new FoodItem("Fish Thali", 240, R.drawable.fishthali2));
        foodItems.add(new FoodItem("Goan King Fish", 290, R.drawable.goankingfishfry));
        foodItems.add(new FoodItem("Pomfret Fish Fry", 160, R.drawable.pomfretfishfry));
        foodItems.add(new FoodItem("Chicken Lollipop", 190, R.drawable.chickenlollipop));
        foodItems.add(new FoodItem("Prawn Fry", 110, R.drawable.prawnfry));


        return foodItems;
    }

    public void updateTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    private void openPhoneDialer() {
        String phoneNumber = getString(R.string.contactus);
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(intent);
    }

    private void searchAndScroll(String query, List<FoodItem> foodItems) {
        boolean found = false;
        for (int i = 0; i < foodItems.size(); i++) {
            FoodItem foodItem = foodItems.get(i);
            if (foodItem.getName().toLowerCase().contains(query.toLowerCase())) {
                gridView.smoothScrollToPosition(i);
                Toast.makeText(this, "Scrolling to: " + foodItem.getName(), Toast.LENGTH_SHORT).show();
                found = true;
                break; // Stop searching after the first match
            }
        }
        if (!found) {
            Toast.makeText(this, "Item not found", Toast.LENGTH_SHORT).show();
        }
    }

}