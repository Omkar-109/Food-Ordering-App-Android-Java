package com.appkotlin.isa2android2310;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FoodAdapter extends BaseAdapter {

    boolean flag=false;
    List<FoodItem> selectedItems = new ArrayList<>();

    private List<FoodItem> foodItems;
    private Context context;
    private double totalPrice = 0;

    public FoodAdapter(Context context, List<FoodItem> foodItems) {
        this.context = context;
        this.foodItems = foodItems;
    }

    @Override
    public int getCount() {
        return foodItems.size();
    }

    @Override
    public Object getItem(int position) {
        return foodItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_food, parent, false);
            holder = new ViewHolder();
            holder.foodName = convertView.findViewById(R.id.text_food_name);
            holder.foodPrice = convertView.findViewById(R.id.text_food_price);
            holder.foodImage = convertView.findViewById(R.id.image_food);
            holder.buttonAddToCart = convertView.findViewById(R.id.button_add_to_cart);
            holder.textQuantity = convertView.findViewById(R.id.text_quantity);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final FoodItem item = foodItems.get(position);
        holder.foodName.setText(item.getName());
        holder.foodPrice.setText("Rs." + item.getPrice());
        holder.foodImage.setImageResource(item.getImageResource());
        holder.textQuantity.setText("QTY"+"\n" + item.getQuantity());

        holder.buttonAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.incrementQuantity();
                totalPrice += item.getPrice();
                ((MainActivity) context).updateTotalPrice(totalPrice);
                notifyDataSetChanged();
            }
        });


        return convertView;
    }

    private static class ViewHolder {
        ImageView foodImage;
        TextView foodName;
        TextView foodPrice;
        Button buttonAddToCart;
        TextView textQuantity;
    }

    public List<FoodItem> getSelectedItems() {

        for (FoodItem item : foodItems) {
            if (item.getQuantity() > 0) {
                selectedItems.add(item);
            }
        }
        return selectedItems;
    }

    public void clearSelectedItems() {
        selectedItems.clear();
    }


    public void resetSelection() {
        for (FoodItem item : selectedItems) {
            item.setQuantity(0);
        }
        selectedItems.clear();
        totalPrice = 0.0f;
        flag=true;
        notifyDataSetChanged();
    }

}

