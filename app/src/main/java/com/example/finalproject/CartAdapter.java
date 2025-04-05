package com.example.finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    Context context;
    List<Product> cartItems;

    public CartAdapter(Context context, List<Product> cartItems) {
        this.context = context;
        this.cartItems = cartItems;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Product product = cartItems.get(position);
        holder.name.setText(product.getName());
        holder.price.setText("$" + String.format("%.2f", product.getPrice()));
        holder.quantity.setText("x" + product.getQuantity());
        holder.image.setImageResource(product.getImageResource());

        // Decrease quantity button click listener
        holder.btnDecrease.setOnClickListener(v -> {
            if (product.getQuantity() > 1) {
                product.setQuantity(product.getQuantity() - 1);
                holder.quantity.setText("x" + product.getQuantity());
                notifyItemChanged(position); // Update the item in the cart
            } else {
                // Remove item if quantity is 1
                CartManager.getInstance().removeFromCart(product);
                cartItems.remove(position);
                notifyItemRemoved(position); // Remove item from RecyclerView
                Toast.makeText(context, product.getName() + " removed from cart", Toast.LENGTH_SHORT).show();
            }
        });

        // Increase quantity button click listener
        holder.btnIncrease.setOnClickListener(v -> {
            int currentQuantity = product.getQuantity();
            int stock = product.getStock(); // Assuming you have a stock field in Product

            if (currentQuantity < stock) {
                product.setQuantity(currentQuantity + 1);
                holder.quantity.setText("x" + product.getQuantity());
                notifyItemChanged(position); // Update the item in the cart
            } else {
                Toast.makeText(context, "Quantity reached", Toast.LENGTH_SHORT).show();
            }
        });

        // Remove button click listener
        holder.btnRemove.setOnClickListener(v -> {
            CartManager.getInstance().removeFromCart(product);
            cartItems.remove(position);
            notifyItemRemoved(position); // Remove item from RecyclerView
            Toast.makeText(context, product.getName() + " removed from cart", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView name, price, quantity;
        ImageView image;
        Button btnDecrease, btnIncrease, btnRemove;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.cartProductName);
            price = itemView.findViewById(R.id.cartProductPrice);
            quantity = itemView.findViewById(R.id.cartProductQuantity);
            image = itemView.findViewById(R.id.cartProductImage);
            btnDecrease = itemView.findViewById(R.id.btnDecrease);
            btnIncrease = itemView.findViewById(R.id.btnIncrease);
            btnRemove = itemView.findViewById(R.id.btnRemove);
        }
    }
}


