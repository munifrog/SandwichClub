package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich = null;

        try {
            JSONObject object = new JSONObject(json);

            JSONObject name = object.getJSONObject("name");

            String sandwichName = (String) name.get("mainName");

            List<String> sandwichAKAs = new ArrayList<>();
            try {
                JSONArray alsoKnowAsArray = name.getJSONArray("alsoKnownAs");
                if (alsoKnowAsArray != null) {
                    int length = alsoKnowAsArray.length();
                    for(int i = 0; i < length; i++) {
                        sandwichAKAs.add((String) alsoKnowAsArray.get(i));
                    }
                }
            } catch (JSONException e) {
                if (!e.getMessage().contains("No value for")) {
                    e.printStackTrace();
                }
            }

            String sandwichOrigin = (String) object.get("placeOfOrigin");

            String sandwichDescription = (String) object.get("description");

            String sandwichImage = (String) object.get("image");

            List<String> sandwichIngredients = new ArrayList<>();
            try {
                JSONArray ingredientsArray = object.getJSONArray("ingredients");
                if (ingredientsArray != null) {
                    int length = ingredientsArray.length();
                    for(int i = 0; i < length; i++) {
                        sandwichIngredients.add((String) ingredientsArray.get(i));
                    }
                }
            } catch (JSONException e) {
                if (!e.getMessage().contains("No value for")) {
                    e.printStackTrace();
                }
            }

            sandwich = new Sandwich(
                    sandwichName,
                    sandwichAKAs,
                    sandwichOrigin,
                    sandwichDescription,
                    sandwichImage,
                    sandwichIngredients
            );
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sandwich;
    }
}
