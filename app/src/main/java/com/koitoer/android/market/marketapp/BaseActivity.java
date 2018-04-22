package com.koitoer.android.market.marketapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by mmena on 4/22/18.
 */

public class BaseActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //onInject(DependencyInjector.applicationComponent());
    }


}
