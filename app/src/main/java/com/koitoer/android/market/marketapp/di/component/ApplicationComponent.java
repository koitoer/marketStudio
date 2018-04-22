package com.koitoer.android.market.marketapp.di.component;

import android.app.Application;
import android.content.Context;

import com.koitoer.android.market.marketapp.MainActivity;
import com.koitoer.android.market.marketapp.MarketApplication;
import com.koitoer.android.market.marketapp.data.SharedPrefsHelper;
import com.koitoer.android.market.marketapp.di.ApplicationContext;
import com.koitoer.android.market.marketapp.di.module.ApplicationModule;
import com.koitoer.android.market.marketapp.repository.CognitoCredentialProvider;
import com.koitoer.android.market.marketapp.repository.ProductRepository;
import com.koitoer.android.market.marketapp.ui.fragments.AddItemFragment;
import com.koitoer.android.market.marketapp.ui.fragments.BaseFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by mmena on 4/21/18.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(MarketApplication demoApplication);

    void inject(AddItemFragment addItemFragment);

    void inject(BaseFragment fragment);

    void inject(MainActivity mainActivity);

    @ApplicationContext
    Context getContext();

    Application getApplication();

    SharedPrefsHelper getPreferenceHelper();

    ProductRepository getProductRepository();

    CognitoCredentialProvider getCognitoCredentialProvider();

}
