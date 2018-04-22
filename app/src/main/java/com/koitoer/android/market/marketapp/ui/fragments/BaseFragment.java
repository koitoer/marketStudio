package com.koitoer.android.market.marketapp.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.koitoer.android.market.marketapp.di.DependencyInjector;
import com.koitoer.android.market.marketapp.di.component.ApplicationComponent;

/**
 * Created by mmena on 4/22/18.
 */

public class BaseFragment extends Fragment {


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onInject(DependencyInjector.applicationComponent());
    }

    @SuppressWarnings("WeakerAccess")
    protected void onInject(ApplicationComponent applicationComponent) {
        applicationComponent.inject(this);
    }

}