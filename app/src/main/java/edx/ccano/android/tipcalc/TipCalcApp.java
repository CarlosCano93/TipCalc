package edx.ccano.android.tipcalc;

import android.app.Application;

/**
 * Created by Carlos on 12/06/2016.
 */
public class TipCalcApp extends Application {
    private final static String ABOUT_URL = "http://carloscanoperez.esy.es/";

    public String getAboutUrl() {
        return ABOUT_URL;
    }
}
