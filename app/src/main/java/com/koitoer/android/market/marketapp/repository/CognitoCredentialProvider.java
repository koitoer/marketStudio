package com.koitoer.android.market.marketapp.repository;

import android.content.Context;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSSessionCredentials;
import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.auth.CognitoCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.koitoer.android.market.marketapp.di.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Cognito Provide for the AWS credentials
 */
@Singleton
public class CognitoCredentialProvider implements AWSCredentialsProvider {

    private CognitoCredentialsProvider cognitoCredentialProvider;

    private AWSSessionCredentials awsSessionCredentials;

    private Context context;

    @Inject
    public CognitoCredentialProvider(@ApplicationContext Context context) {
        this.context = context;
        cognitoCredentialProvider = new CognitoCachingCredentialsProvider(
                context,
                "us-east-1:", // Identity pool ID
                Regions.US_EAST_1 // Region
        );

        awsSessionCredentials = Observable.just(cognitoCredentialProvider.getCredentials())
                .subscribeOn(Schedulers.io()).blockingSingle();
    }

    @Override
    public AWSSessionCredentials getCredentials() {
        return awsSessionCredentials;
    }

    @Override
    public void refresh() {

    }
}
