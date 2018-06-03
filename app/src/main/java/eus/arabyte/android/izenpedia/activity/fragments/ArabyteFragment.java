package eus.arabyte.android.izenpedia.activity.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import eus.arabyte.android.izenpedia.BuildConfig;
import eus.arabyte.android.izenpedia.R;
import eus.arabyte.android.izenpedia.utils.PropertiesReader;
import eus.arabyte.android.izenpedia.utils.Utils;

/**
 * Created by ichigo on 15/4/18.
 */
public class ArabyteFragment extends Fragment implements View.OnClickListener {

    public ArabyteFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.content_arabyte, container, false);

        Button urlButton = rootView.findViewById(R.id.inigo_linkedin);
        urlButton.setOnClickListener(this);

        urlButton = rootView.findViewById(R.id.inigo_github);
        urlButton.setOnClickListener(this);

//        urlButton = rootView.findViewById(R.id.ibai_linkedin);
//        urlButton.setOnClickListener(this);
//
//        urlButton = rootView.findViewById(R.id.ibai_github);
//        urlButton.setOnClickListener(this);

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
            case R.id.inigo_linkedin:
                url = propertiesReader.getInigoLinkedin();
                break;
            case R.id.inigo_github:
                url = propertiesReader.getInigoGithub();
                break;
//            case R.id.ibai_linkedin:
//                url = propertiesReader.getIbaiLinkedin();
//                break;
//            case R.id.ibai_github:
//                url = propertiesReader.getIbaiGithub();
//                break;
            case R.id.rate_text:
                url = propertiesReader.getUrlGoogleplay();
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