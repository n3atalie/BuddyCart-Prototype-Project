package com.example.finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private Context context;
    private List<CartItem> cartItems;
    private TextView totalPriceText;

    public CartAdapter(Context context, List<CartItem> cartItems, TextView totalPriceText) {
        this.context = context;
        this.cartItems = cartItems;
        this.totalPriceText = totalPriceText;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImage;
        TextView itemName, itemQuantity, itemSubtotal;
        Button btnIncrease, btnDecrease;
        ImageButton btnDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.itemImage);
            itemName = itemView.findViewById(R.id.itemName);
            itemQuantity = itemView.findViewById(R.id.itemQuantity);
            itemSubtotal = itemView.findViewById(R.id.itemSubtotal);
            btnIncrease = itemView.findViewById(R.id.btnIncrease);
            btnDecrease = itemView.findViewById(R.id.btnDecrease);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {
        CartItem item = cartItems.get(position);
        holder.itemName.setText(item.getProduct().getName());
        holder.itemImage.setImageResource(item.getProduct().getImageResId());
        holder.itemQuantity.setText(String.valueOf(item.getQuantity()));
        double subtotal = item.getQuantity() * item.getProduct().getPrice();
        holder.itemSubtotal.setText("Subtotal: $" + subtotal);

        // + ボタン
        holder.btnIncrease.setOnClickListener(v -> {
            item.increaseQuantity();
            notifyItemChanged(holder.getAdapterPosition());
            updateTotal();
        });

        // - ボタン
        holder.btnDecrease.setOnClickListener(v -> {
            item.decreaseQuantity();
            notifyItemChanged(holder.getAdapterPosition());
            updateTotal();
        });

        // 🗑 削除ボタン
        holder.btnDelete.setOnClickListener(v -> {
            cartItems.remove(holder.getAdapterPosition());
            notifyItemRemoved(holder.getAdapterPosition());
            updateTotal();
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    private void updateTotal() {
        double total = 0;
        for (CartItem item : cartItems) {
            total += item.getProduct().getPrice() * item.getQuantity();
        }
        totalPriceText.setText("Total: $" + total);
    }
}
