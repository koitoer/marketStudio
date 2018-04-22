package com.koitoer.android.market.marketapp.di;

import com.koitoer.android.market.marketapp.MarketApplication;
import com.koitoer.android.market.marketapp.di.component.ApplicationComponent;
import com.koitoer.android.market.marketapp.di.component.DaggerApplicationComponent;
import com.koitoer.android.market.marketapp.di.module.ApplicationModule;

/**
 * Created by mmena on 4/22/18.
 */
public class DependencyInjector {

    private static ApplicationComponent applicationComponent;

    public static void initialize(MarketApplication marketApplication) {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(marketApplication))
                .build();
    }

    public static ApplicationComponent applicationComponent() {
        return applicationComponent;
    }

    private DependencyInjector(){}
}