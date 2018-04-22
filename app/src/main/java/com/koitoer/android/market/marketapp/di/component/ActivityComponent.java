package com.koitoer.android.market.marketapp.di.component;

import com.koitoer.android.market.marketapp.BaseActivity;
import com.koitoer.android.market.marketapp.MainActivity;
import com.koitoer.android.market.marketapp.di.PerActivity;
import com.koitoer.android.market.marketapp.di.module.ActivityModule;
import com.koitoer.android.market.marketapp.ui.fragments.AddItemFragment;
import com.koitoer.android.market.marketapp.ui.fragments.BaseFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

    void inject(AddItemFragment addItemFragment);

    void inject(BaseFragment fragment);

    void inject(BaseActivity baseActivity);

}