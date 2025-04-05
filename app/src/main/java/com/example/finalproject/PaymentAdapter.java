package com.example.finalproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.ViewHolder> {

    private ArrayList<PaymentMethod> paymentList;
    private int selectedPosition = -1;

    public PaymentAdapter(ArrayList<PaymentMethod> paymentList) {
        this.paymentList = paymentList;
    }

    public void updateData(ArrayList<PaymentMethod> newList) {
        this.paymentList = newList;
        notifyDataSetChanged();
    }

    public PaymentMethod getSelectedPayment() {
        if (selectedPosition >= 0 && selectedPosition < paymentList.size()) {
            return paymentList.get(selectedPosition);
        }
        return null;
    }

    @NonNull
    @Override
    public PaymentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.payment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentAdapter.ViewHolder holder, int position) {
        PaymentMethod method = paymentList.get(position);

        String cardType = getCardType(method.cardNumber);
        String masked = "•••• " + method.cardNumber.substring(method.cardNumber.length() - 4);

        holder.cardName.setText(cardType);
        holder.cardNumber.setText(masked);

        // Set the icon based on card type
        if (cardType.equals("Visa")) {
            holder.cardIcon.setImageResource(R.drawable.ic_visa);
        } else if (cardType.equals("MasterCard")) {
            holder.cardIcon.setImageResource(R.drawable.ic_mastercard);
        } else if (cardType.equals("American Express")) {
            holder.cardIcon.setImageResource(R.drawable.ic_amex);
        } else {
            // No image for unknown types (can set visibility or leave blank)
            holder.cardIcon.setImageDrawable(null);
        }

        // Highlight selected item
        holder.itemView.setBackgroundResource(
                position == selectedPosition ? R.drawable.card_selected_bg : R.drawable.card_unselected_bg
        );

        holder.itemView.setOnClickListener(v -> {
            selectedPosition = holder.getAdapterPosition();
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return paymentList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView cardName, cardNumber;
        ImageView cardIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardName = itemView.findViewById(R.id.cardName);
            cardNumber = itemView.findViewById(R.id.cardNumber);
            cardIcon = itemView.findViewById(R.id.cardIcon);
        }
    }

    private String getCardType(String number) {
        if (number == null) return "";

        number = number.replaceAll("\\s+", "");

        if (number.startsWith("4")) return "Visa";
        if (number.matches("^5[1-5].*")) return "MasterCard";
        if (number.matches("^3[47].*")) return "American Express";

        return "";
    }
}
