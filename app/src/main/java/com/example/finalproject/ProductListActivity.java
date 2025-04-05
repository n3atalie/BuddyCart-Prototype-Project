package com.example.finalproject;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;
import android.widget.ImageButton;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class ProductListActivity extends AppCompatActivity {

    RecyclerView productRecyclerView;
    TextView categoryTitle;
    EditText searchInput;
    List<Product> productList;
    List<Product> filteredList;
    ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        // UI要素を取得
        categoryTitle = findViewById(R.id.categoryTitle);
        productRecyclerView = findViewById(R.id.productRecyclerView);
        searchInput = findViewById(R.id.editTextProductSearch);
        ImageButton btnBack = findViewById(R.id.btnBackCheckout);

        // 戻るボタン
        btnBack.setOnClickListener(v -> finish());

        // カテゴリ名の取得と表示
        String category = getIntent().getStringExtra("category");
        categoryTitle.setText(category);

        // 商品一覧（全体）
        productList = new ArrayList<>();
        if (category.equals("Fresh Fruits & Vegetable")) {
            productList.add(new Product("Apple", R.drawable.apple, 1.50, 10));
            productList.add(new Product("Banana", R.drawable.banana, 0.90, 15));
        } else if (category.equals("Dairy & Eggs")) {
            productList.add(new Product("Milk", R.drawable.milk, 2.80, 8));
            productList.add(new Product("Eggs", R.drawable.egg, 3.00, 12));
        } else if (category.equals("Cooking Oil & Ghee")) {
            productList.add(new Product("Sunfoil", R.drawable.sunfoil, 2.80, 8));
            productList.add(new Product("Vegetable oil", R.drawable.vegetableoil, 3.00, 12));
        } else if (category.equals("Bakery & Snacks")) {
            productList.add(new Product("Muffin", R.drawable.muffin, 2.80, 8));
            productList.add(new Product("French bread", R.drawable.frenchbread, 3.00, 12));
        } else if (category.equals("Beverages")) {
            productList.add(new Product("Pepsi", R.drawable.pepsi, 2.80, 8));
            productList.add(new Product("Coca Cola", R.drawable.cola, 3.00, 12));
            productList.add(new Product("Diet Cola", R.drawable.dietcola, 3.00, 12));
            productList.add(new Product("Pepsi", R.drawable.pepsi, 2.80, 8));
            productList.add(new Product("Coca Cola", R.drawable.cola, 3.00, 12));
            productList.add(new Product("Diet Cola", R.drawable.dietcola, 3.00, 12));
            productList.add(new Product("Pepsi", R.drawable.pepsi, 2.80, 8));
            productList.add(new Product("Coca Cola", R.drawable.cola, 3.00, 12));
            productList.add(new Product("Diet Cola", R.drawable.dietcola, 3.00, 12));
            productList.add(new Product("Sprite", R.drawable.sprite, 2.80, 8));
        } else if (category.equals("Meat & Fish")) {
            productList.add(new Product("Beef", R.drawable.beef, 2.80, 8));
            productList.add(new Product("Pork", R.drawable.pork, 3.00, 12));
        }

        // 検索用のフィルターリスト（初期値：すべて）
        filteredList = new ArrayList<>(productList);

        // アダプター設定
        adapter = new ProductAdapter(this, filteredList);
        productRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        productRecyclerView.setAdapter(adapter);

        // 検索欄のテキストが変わるたびにリストを更新
        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterProducts(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });
    }

    // 検索機能：名前に含まれる商品を表示
    private void filterProducts(String query) {
        filteredList.clear();
        for (Product product : productList) {
            if (product.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(product);
            }
        }
        adapter.notifyDataSetChanged();
    }
}
