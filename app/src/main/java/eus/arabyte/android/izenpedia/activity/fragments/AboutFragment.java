package eus.arabyte.android.izenpedia.activity.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import eus.arabyte.android.izenpedia.BuildConfig;
import eus.arabyte.android.izenpedia.R;

/**
 * Created by ichigo on 15/4/18.
 */
public class AboutFragment extends Fragment {

    public AboutFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.content_about, container, false);

        String versionName = BuildConfig.VERSION_NAME;

        TextView versionText = rootView.findViewById(R.id.version_text);
        versionText.setText(versionName);

        return rootView;

    }

    /**
     * Método al que se invoca cuando se ha creado la vista
     *
     * @param view View
     * @param savedInstanceState Bundle
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle(R.string.nav_about);
    }

}