package pl.gratitude.it_ebooks.common.helpers;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created 09.11.2015.
 *
 * @author Sławomir
 */
public class UserInterfaceHelper {

    /**
     * @param view {@link View} pass CoordinatorLayout for good UX.
     * @param message - your message
     * @return snackbar object.
     */
    public static Snackbar showSimpleSnackbar(View view, String message) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT);
        snackbar.show();
        return snackbar;
    }

}
