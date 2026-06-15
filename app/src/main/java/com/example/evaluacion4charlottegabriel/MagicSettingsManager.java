package com.example.evaluacion4charlottegabriel;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public final class MagicSettingsManager {
    private static final String TAG = "MagicSettings";

    public static final String PREFS_NAME = "settings";
    public static final String KEY_SOUND_ENABLED = "pref_sound_enabled";
    public static final String KEY_PARTICLES_ENABLED = "pref_particles_enabled";
    public static final String KEY_CALM_MODE_ENABLED = "pref_calm_mode_enabled";

    private static final boolean DEFAULT_SOUND = true;
    private static final boolean DEFAULT_PARTICLES = true;
    private static final boolean DEFAULT_CALM_MODE = false;
    private static final float CALM_BRIGHTNESS = 0.55f;
    private static final float AMBIENT_VOLUME = 0.28f;

    private static MediaPlayer ambientPlayer;

    private MagicSettingsManager() {
    }

    public static boolean isSoundEnabled(Context context) {
        return readBoolean(context, KEY_SOUND_ENABLED, DEFAULT_SOUND);
    }

    public static void setSoundEnabled(Context context, boolean enabled) {
        writeBoolean(context, KEY_SOUND_ENABLED, enabled);
        applySound(context);
    }

    public static void playMagicChime(Context context) {
        int resId = context.getResources().getIdentifier("magic_chime", "raw", context.getPackageName());
        if (resId == 0) return;

        MediaPlayer chime = MediaPlayer.create(context.getApplicationContext(), resId);
        if (chime == null) return;

        chime.setVolume(1f, 1f);
        chime.setOnCompletionListener(MediaPlayer::release);
        chime.setOnErrorListener((player, what, extra) -> {
            Log.w(TAG, "Magic chime failed: " + what + "/" + extra);
            player.release();
            return true;
        });
        chime.start();
    }

    public static boolean areParticlesEnabled(Context context) {
        return readBoolean(context, KEY_PARTICLES_ENABLED, DEFAULT_PARTICLES);
    }

    public static void setParticlesEnabled(Context context, boolean enabled) {
        writeBoolean(context, KEY_PARTICLES_ENABLED, enabled);
    }

    public static boolean isCalmModeEnabled(Context context) {
        return readBoolean(context, KEY_CALM_MODE_ENABLED, DEFAULT_CALM_MODE);
    }

    public static void setCalmModeEnabled(Activity activity, boolean enabled) {
        writeBoolean(activity, KEY_CALM_MODE_ENABLED, enabled);
        applyCalmBrightness(activity);
    }

    public static void applyToActivity(Activity activity) {
        applyCalmBrightness(activity);
        applySound(activity);
    }

    public static void applySound(Context context) {
        if (isSoundEnabled(context)) {
            startAmbientSound(context);
        } else {
            pauseSound();
        }
    }

    public static void pauseSound() {
        if (ambientPlayer == null) return;
        try {
            if (ambientPlayer.isPlaying()) {
                ambientPlayer.pause();
            }
        } catch (IllegalStateException ignored) {
            releaseSound();
        }
    }

    public static void releaseSound() {
        if (ambientPlayer == null) return;
        try {
            ambientPlayer.release();
        } catch (IllegalStateException ignored) {
            // Already unusable, just clear the shared reference.
        }
        ambientPlayer = null;
    }

    private static void startAmbientSound(Context context) {
        Context appContext = context.getApplicationContext();
        if (ambientPlayer == null && !createAmbientPlayer(appContext)) {
            return;
        }
        try {
            if (!ambientPlayer.isPlaying()) {
                ambientPlayer.start();
            }
        } catch (IllegalStateException ignored) {
            releaseSound();
        }
    }

    private static boolean createAmbientPlayer(Context context) {
        int resId = context.getResources().getIdentifier("magic_ambient", "raw", context.getPackageName());
        if (resId == 0) {
            // TODO: Agregar magic_ambient.mp3 en res/raw para activar música real.
            return false;
        }
        AudioAttributes attributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build();
        ambientPlayer = MediaPlayer.create(context, resId, attributes, 0);
        if (ambientPlayer == null) return false;
        ambientPlayer.setLooping(true);
        ambientPlayer.setVolume(AMBIENT_VOLUME, AMBIENT_VOLUME);
        ambientPlayer.setOnErrorListener((player, what, extra) -> {
            Log.w(TAG, "Magic ambient failed: " + what + "/" + extra);
            releaseSound();
            return true;
        });
        return true;
    }

    private static void applyCalmBrightness(Activity activity) {
        Window window = activity.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.screenBrightness = isCalmModeEnabled(activity)
                ? CALM_BRIGHTNESS
                : WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_NONE;
        window.setAttributes(params);
    }

    private static boolean readBoolean(Context context, String key, boolean fallback) {
        SharedPreferences preferences = preferences(context);
        if (preferences.contains(key)) {
            return preferences.getBoolean(key, fallback);
        }
        String legacyKey = legacyKey(key);
        if (legacyKey != null && preferences.contains(legacyKey)) {
            return preferences.getBoolean(legacyKey, fallback);
        }
        return fallback;
    }

    private static void writeBoolean(Context context, String key, boolean value) {
        preferences(context).edit().putBoolean(key, value).apply();
    }

    private static SharedPreferences preferences(Context context) {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    private static String legacyKey(String key) {
        if (KEY_SOUND_ENABLED.equals(key)) return "sound";
        if (KEY_PARTICLES_ENABLED.equals(key)) return "particles";
        if (KEY_CALM_MODE_ENABLED.equals(key)) return "calm";
        return null;
    }
}
