package pl.gratitude.it_ebooks.common.helpers;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created 11.11.2015.
 *
 * @author Sławomir
 */
public class Utility {

    /**
     * Metoda ukrywająca klawiaturę.
     *
     * @param view    - można tutaj wepchać każdy obiekt, który jest bądź dziedziczy po klasie View {@link android.view.View}
     * @param context - kontekst.
     */
    public static void hideKeyboard(View view, Context context) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * Metoda ukrywająca klawiaturę.
     *
     * @param view     - można tutaj wepchać każdy obiekt, który jest bądź dziedziczy po klasie View {@link android.view.View}
     * @param activity - aktywność.
     */
    public static void hideKeyboard(View view, Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public static boolean isKeyboardActive(Context context) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        return inputMethodManager.isActive();
    }

    public static boolean isKeyboardActive(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        return inputMethodManager.isActive();
    }

}
