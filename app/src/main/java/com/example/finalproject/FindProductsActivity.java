package com.example.finalproject;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.GridLayoutManager;
import java.util.List;
import java.util.ArrayList;
import android.widget.ImageButton;
import android.view.View;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class FindProductsActivity extends AppCompatActivity {

    RecyclerView categoryRecyclerView;
    List<Category> categoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_products);

        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Back to top page
            }
        });

        categoryRecyclerView = findViewById(R.id.categoryRecyclerView);
        categoryList = new ArrayList<>();
        categoryList.add(new Category("Fresh Fruits & Vegetable", R.drawable.fruits));
        categoryList.add(new Category("Dairy & Eggs", R.drawable.eggs));
        categoryList.add(new Category("Cooking Oil & Ghee", R.drawable.oil));
        categoryList.add(new Category("Bakery & Snacks", R.drawable.snacks));
        categoryList.add(new Category("Beverages", R.drawable.beverages));
        categoryList.add(new Category("Meat & Fish", R.drawable.meat));

        CategoryAdapter adapter = new CategoryAdapter(this, categoryList);
        categoryRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        categoryRecyclerView.setAdapter(adapter);
    }

}


