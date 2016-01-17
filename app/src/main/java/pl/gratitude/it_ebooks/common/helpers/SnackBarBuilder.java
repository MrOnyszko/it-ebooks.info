package pl.gratitude.it_ebooks.common.helpers;

import android.support.design.widget.Snackbar;
import android.view.View;

import org.jetbrains.annotations.NotNull;

/**
 * Created 09.11.2015.
 *
 * @author Sławomir
 */
public class SnackBarBuilder {

    private View view;
    private Snackbar snackBar;

    private int duration;

    public SnackBarBuilder(@NotNull View view) {
        this.view = view;
        this.duration = Snackbar.LENGTH_SHORT;
    }

    public Snackbar build(String message) {
        return snackBar = Snackbar.make(view, message, duration);
    }

    public Snackbar build(@NotNull View view, String message) {
        return snackBar = Snackbar.make(view, message, duration);
    }

    public Snackbar build(@NotNull View view, String message, int duration) {
        return snackBar = Snackbar.make(view, message, duration);
    }

    public Snackbar setAction(CharSequence actionMessage, @NotNull View.OnClickListener onClickListener) {
        this.snackBar.setAction(actionMessage, onClickListener);
        return snackBar;
    }

    public Snackbar showSnackbar() {
        snackBar.show();
        return snackBar;
    }

    public Snackbar setDuration(int duration) {
        this.duration = duration;
        return snackBar;
    }

    public Snackbar setView(@NotNull View view) {
        this.view = view;
        return snackBar;
    }

    public Snackbar dismiss() {
        snackBar.dismiss();
        return snackBar;
    }

}
