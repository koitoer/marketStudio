package com.koitoer.android.market.marketapp;


import android.app.Application;
import android.content.Context;

import com.koitoer.android.market.marketapp.di.component.ApplicationComponent;
import com.koitoer.android.market.marketapp.di.component.DaggerApplicationComponent;
import com.koitoer.android.market.marketapp.di.module.ApplicationModule;

public class MarketApplication extends Application {

    public static ApplicationComponent applicationComponent;

    public static MarketApplication get(Context context) {
        return (MarketApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        applicationComponent.inject(this);
    }

    public ApplicationComponent getComponent(){
        return applicationComponent;
    }

}