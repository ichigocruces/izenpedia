package eus.arabyte.android.izenpedia.activity.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import eus.arabyte.android.izenpedia.R;
import eus.arabyte.android.izenpedia.activity.IzenaActivity;
import eus.arabyte.android.izenpedia.activity.listeners.IzenaOnClickListener;
import eus.arabyte.android.izenpedia.adapter.IzenaAdapter;
import eus.arabyte.android.izenpedia.adapter.SimpleIzenaAdapter;
import eus.arabyte.android.izenpedia.dao.IzenaDAO;
import eus.arabyte.android.izenpedia.dao.IzenaDAOImpl;
import eus.arabyte.android.izenpedia.utils.Constants;
import eus.arabyte.android.izenpedia.utils.ListType;

/**
 * Created by ichigo on 5/11/17.
 */

public class PopularFragment extends Fragment {

    public PopularFragment() {
        super();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.popular_fragment, container, false);

        // Setting ViewPager for each Tabs
        ViewPager viewPager = (ViewPager) rootView.findViewById(R.id.pager_popular);
        setupViewPager(viewPager);
        // Set Tabs inside Toolbar
        TabLayout tabs = (TabLayout) rootView.findViewById(R.id.tab_layout_popular);
        tabs.setupWithViewPager(viewPager);


        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle(R.string.nav_popular);
    }


    // Add Fragments to Tabs
    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getChildFragmentManager());
        adapter.addFragment(new PopularBoyFragment(), getResources().getString(R.string.nav_boys));
        adapter.addFragment(new PopularGirlFragment(), getResources().getString(R.string.nav_girls));
        viewPager.setAdapter(adapter);

    }


    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public Adapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


}
