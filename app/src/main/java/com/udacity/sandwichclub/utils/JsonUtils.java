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

        final String JSON_EMPTY_RETURN = "No value for";
        final String SANDWICH_NAME_CONTAINER = "name";
        final String SANDWICH_NAME_MAIN = "mainName";
        final String SANDWICH_NAME_ALTERNATIVES = "alsoKnownAs";
        final String SANDWICH_ORIGIN = "placeOfOrigin";
        final String SANDWICH_DESCRIPTION = "description";
        final String SANDWICH_IMAGE_URL = "image";
        final String SANDWICH_INGREDIENTS = "ingredients";

        try {
            JSONObject object = new JSONObject(json);

            JSONObject name = object.getJSONObject(SANDWICH_NAME_CONTAINER);

            String sandwichName = (String) name.get(SANDWICH_NAME_MAIN);

            List<String> sandwichAKAs = new ArrayList<>();
            try {
                JSONArray alsoKnowAsArray = name.getJSONArray(SANDWICH_NAME_ALTERNATIVES);
                if (alsoKnowAsArray != null) {
                    int length = alsoKnowAsArray.length();
                    for(int i = 0; i < length; i++) {
                        sandwichAKAs.add((String) alsoKnowAsArray.get(i));
                    }
                }
            } catch (JSONException e) {
                if (!e.getMessage().contains(JSON_EMPTY_RETURN)) {
                    e.printStackTrace();
                }
            }

            String sandwichOrigin = (String) object.get(SANDWICH_ORIGIN);

            String sandwichDescription = (String) object.get(SANDWICH_DESCRIPTION);

            String sandwichImage = (String) object.get(SANDWICH_IMAGE_URL);

            List<String> sandwichIngredients = new ArrayList<>();
            try {
                JSONArray ingredientsArray = object.getJSONArray(SANDWICH_INGREDIENTS);
                if (ingredientsArray != null) {
                    int length = ingredientsArray.length();
                    for(int i = 0; i < length; i++) {
                        sandwichIngredients.add((String) ingredientsArray.get(i));
                    }
                }
            } catch (JSONException e) {
                if (!e.getMessage().contains(JSON_EMPTY_RETURN)) {
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
