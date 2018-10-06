package eus.arabyte.android.izendegia.activity.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import eus.arabyte.android.izendegia.R;
import eus.arabyte.android.izendegia.utils.PropertiesReader;
import eus.arabyte.android.izendegia.utils.Utils;

/**
 * Created by ichigo on 15/4/18.
 */
public class AboutFragment extends Fragment implements View.OnClickListener {

    public AboutFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.content_about, container, false);

//        String versionName = BuildConfig.VERSION_NAME;

        LinearLayout urlText = rootView.findViewById(R.id.app_rate);
        urlText.setOnClickListener(this);

        LinearLayout urlIzenpedia = rootView.findViewById(R.id.app_url);
        urlIzenpedia.setOnClickListener(this);

        return rootView;

    }

    /**
     * Método al que se invoca cuando se ha creado la vista
     *
     * @param view               View
     * @param savedInstanceState Bundle
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle(R.string.nav_about);
    }

    /**
     * Open a webpage on the browser
     *
     * @param view View
     */
    @Override
    public void onClick(final View view) {
        String url = null;

        PropertiesReader propertiesReader = PropertiesReader.getInstance(getContext());

        switch (view.getId()) {
            case R.id.app_rate:
                url = propertiesReader.getUrlGoogleplay();
                break;
            case R.id.app_url:
                url = propertiesReader.getUrlIzenpedia(getResources().getConfiguration().locale);
                break;
            default:
                break;

        }

        if(!Utils.isBlank(url)){
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(browserIntent);
        }

    }
}