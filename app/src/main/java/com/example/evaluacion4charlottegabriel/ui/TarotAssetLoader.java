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
    private static final String ASSET_ROOT = "tarot_cards";
    private static final String[] ASSET_DIRS = {
            "arcanos_mayores",
            "chispas",
            "gotitas",
            "estrellas",
            "brotes"
    };
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
            try (InputStream stream = context.getAssets().open(assetName)) {
                return Drawable.createFromStream(stream, assetName);
            }
        } catch (Exception ignored) {
            return null;
        }
    }

    private static String getAssetName(Context context, int firebaseId) throws Exception {
        if (assetMap == null) {
            assetMap = new HashMap<>();
            for (String dir : ASSET_DIRS) {
                String assetDir = ASSET_ROOT + "/" + dir;
                String[] files = context.getAssets().list(assetDir);
                if (files != null) {
                    for (String file : files) {
                        Matcher matcher = CARD_PATTERN.matcher(file);
                        if (matcher.matches()) {
                            assetMap.put(Integer.parseInt(matcher.group(1)), assetDir + "/" + file);
                        }
                    }
                }
            }
        }
        return assetMap.get(firebaseId);
    }
}
