package com.example.evaluacion4charlottegabriel.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.example.evaluacion4charlottegabriel.R;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class TarotAssetLoader {
    private static final String ASSET_DIR = "tarot_cards/arcanos_mayores";
    private static final Pattern CARD_PATTERN = Pattern.compile("^(\\d{2})_.*\\.png$");
    private static Map<Integer, String> assetMap;

    private TarotAssetLoader() {
    }

    public static void loadCard(ImageView imageView, int firebaseId, int rotation) {
        Drawable drawable = getCardDrawable(imageView.getContext(), firebaseId);
        if (drawable != null) {
            imageView.setImageDrawable(drawable);
        } else {
            imageView.setImageResource(R.drawable.ic_launcher_background);
        }
        imageView.setRotation(rotation);
    }

    public static Drawable getCardDrawable(Context context, int firebaseId) {
        try {
            String assetName = getAssetName(context, firebaseId);
            if (assetName == null) {
                return null;
            }
            try (InputStream stream = context.getAssets().open(ASSET_DIR + "/" + assetName)) {
                return Drawable.createFromStream(stream, assetName);
            }
        } catch (Exception ignored) {
            return null;
        }
    }

    private static String getAssetName(Context context, int firebaseId) throws Exception {
        if (assetMap == null) {
            assetMap = new HashMap<>();
            String[] files = context.getAssets().list(ASSET_DIR);
            if (files != null) {
                for (String file : files) {
                    Matcher matcher = CARD_PATTERN.matcher(file);
                    if (matcher.matches()) {
                        assetMap.put(Integer.parseInt(matcher.group(1)), file);
                    }
                }
            }
        }
        return assetMap.get(firebaseId);
    }
}
