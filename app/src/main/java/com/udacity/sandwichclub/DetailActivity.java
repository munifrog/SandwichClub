package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

public class DetailActivity extends AppCompatActivity {
    String TAG = DetailActivity.class.getSimpleName();

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    Sandwich mSandwich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        mSandwich = JsonUtils.parseSandwichJson(json);
        if (mSandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI();
        Picasso.with(this)
                .load(mSandwich.getImage())
                .into(ingredientsIv);

        setTitle(mSandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {
        TextView alsoKnownTv = findViewById(R.id.also_known_tv);
        TextView ingredientTv = findViewById(R.id.ingredients_tv);
        TextView originTv = findViewById(R.id.origin_tv);
        TextView describeTv = findViewById(R.id.description_tv);

        List<String> akaList = mSandwich.getAlsoKnownAs();
        alsoKnownTv.setText("");
        int size = akaList.size();
        for(int i = 0; i < size; i++) {
            if(i != 0) {
                alsoKnownTv.append("\n");
            }
            alsoKnownTv.append(akaList.get(i));
        }

        List<String> ingredientList = mSandwich.getIngredients();
        ingredientTv.setText("");
        size = ingredientList.size();
        for(int i = 0; i < size; i++) {
            if(i != 0) {
                ingredientTv.append("\n");
            }
            ingredientTv.append(ingredientList.get(i));
        }

        originTv.setText(mSandwich.getPlaceOfOrigin());
        describeTv.setText(mSandwich.getDescription());
    }
}
