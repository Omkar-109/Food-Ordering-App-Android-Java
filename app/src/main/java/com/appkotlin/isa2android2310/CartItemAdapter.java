package com.appkotlin.isa2android2310;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CartItemAdapter extends ArrayAdapter<FoodItem> {

    private List<FoodItem> items;
    private Context context;

    public CartItemAdapter(Context context, List<FoodItem> items) {
        super(context, 0, items);
        this.context = context;
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        }

        FoodItem currentItem = items.get(position);

        TextView nameTextView = listItemView.findViewById(R.id.text_cart_item_name);
        nameTextView.setText(currentItem.getName());

        TextView priceTextView = listItemView.findViewById(R.id.text_cart_item_price);
        priceTextView.setText("Price per item: Rs." + currentItem.getPrice());

        TextView quantityTextView = listItemView.findViewById(R.id.text_cart_item_quantity);
        quantityTextView.setText("Quantity: " + currentItem.getQuantity());

        ImageView imageView = listItemView.findViewById(R.id.imageView2);
        imageView.setImageResource(currentItem.getImageResource());

        return listItemView;
    }
}
