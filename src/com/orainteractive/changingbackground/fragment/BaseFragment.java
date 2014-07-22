package com.orainteractive.changingbackground.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.orainteractive.changingbackground.R;

/**
 * Parent Class for a Fragment which contains a label and button.
 * Parent Activity is expected to implement the
 * {@link BaseFragment.ButtonListener} interface
 * in order to handle the button press.
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = BaseFragment.class.getSimpleName();

    /** @return {@link String} text to display in the main label. */
    protected abstract String getLabelText();

    /** @return {@link String} text to display in the main button. */
    protected abstract String getButtonText();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View layout = inflater.inflate(R.layout.base_fragment, container, false);
        final TextView label = (TextView) layout.findViewById(R.id.base_fragment_label);
        final Button button = (Button) layout.findViewById(R.id.base_fragment_button);
        label.setText(getLabelText());
        button.setText(getButtonText());
        button.setOnClickListener(this);
        return layout;
    }

    /** Local OnClickListener used to transfer the button click action over to the parent Activity. */
    @Override
    public void onClick(View v) {
        if (getActivity() instanceof ButtonListener) {
            ((ButtonListener) getActivity()).onClick();
        } else {
            Log.w(TAG, "Button click not handled: Parent Activity must implement interface ButtonListener.");
        }
    }

    /** Interface called when the button in a BaseFragment is pressed. */
    public static interface ButtonListener {
        public void onClick();
    }
}
