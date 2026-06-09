package com.example.evaluacion4charlottegabriel;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

import android.graphics.Bitmap;
import android.view.View;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.FileOutputStream;

@RunWith(AndroidJUnit4.class)
public class ScreenshotTest {

    @Test
    public void captureHome() throws Exception {
        try (ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class)) {
            Thread.sleep(2000);
            saveScreenshot("home.png");
        }
    }

    @Test
    public void captureCartaDelDia() throws Exception {
        try (ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class)) {
            scenario.onActivity(activity -> activity.inciarActividadCartaDelDia(new View(activity)));
            Thread.sleep(5000);
            saveScreenshot("carta-del-dia.png");
        }
    }

    @Test
    public void captureSiONo() throws Exception {
        try (ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class)) {
            scenario.onActivity(activity -> activity.iniciarActividadSiyno(new View(activity)));
            Thread.sleep(5000);
            saveScreenshot("si-o-no.png");
        }
    }

    @Test
    public void captureTarotParejas() throws Exception {
        try (ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class)) {
            scenario.onActivity(activity -> activity.iniciarActividadTarotPareja(new View(activity)));
            Thread.sleep(6000);
            saveScreenshot("tarot-parejas.png");
        }
    }

    private void saveScreenshot(String fileName) throws Exception {
        Bitmap bitmap = getInstrumentation().getUiAutomation().takeScreenshot();
        if (bitmap == null) {
            throw new IllegalStateException("No se pudo capturar screenshot");
        }
        File dir = new File("/sdcard/Download/tarot-screenshots");
        if (!dir.exists() && !dir.mkdirs()) {
            throw new IllegalStateException("No se pudo crear directorio de screenshots");
        }
        File output = new File(dir, fileName);
        try (FileOutputStream stream = new FileOutputStream(output)) {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            stream.flush();
        }
    }
}
