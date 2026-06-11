package com.example.evaluacion4charlottegabriel;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

public class TarotDreamsApplication extends Application {
    private int visibleActivities;

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            }

            @Override
            public void onActivityStarted(Activity activity) {
                visibleActivities++;
                MagicSettingsManager.applyToActivity(activity);
            }

            @Override
            public void onActivityResumed(Activity activity) {
                MagicSettingsManager.applyToActivity(activity);
            }

            @Override
            public void onActivityPaused(Activity activity) {
            }

            @Override
            public void onActivityStopped(Activity activity) {
                visibleActivities = Math.max(0, visibleActivities - 1);
                if (visibleActivities == 0) {
                    MagicSettingsManager.pauseSound();
                }
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                if (visibleActivities == 0) {
                    MagicSettingsManager.releaseSound();
                }
            }
        });
    }
}
