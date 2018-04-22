package com.koitoer.android.market.marketapp.repository;

import android.util.Log;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBScanExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.koitoer.android.market.marketapp.domain.ConstantUtil;
import com.koitoer.android.market.marketapp.domain.Product;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * DynamoDB repository with the product table.
 */
@Singleton
public class ProductRepository {

    private AmazonDynamoDB amazonDynamoDB;

    private DynamoDBMapper dynamoDBMapper;

    @Inject
    public ProductRepository(CognitoCredentialProvider cognitoCredentialProvider) {
        amazonDynamoDB = new AmazonDynamoDBClient(cognitoCredentialProvider.getCredentials());
        dynamoDBMapper = DynamoDBMapper.builder().dynamoDBClient(amazonDynamoDB).build();
    }

    public String getTableInformation() {
        return Observable.fromCallable(() ->
                amazonDynamoDB.describeTable(ConstantUtil.PRODUCT_TABLE).getTable().getTableArn())
                .subscribeOn(Schedulers.io()).blockingSingle();
    }

    public boolean createProduct(Product product) {
        try {
            dynamoDBMapper.save(product);
            return true;
        } catch (RuntimeException e) {
            Log.e("createProductError", e.getMessage(), e);
            return false;
        }
    }

    public Observable<Product> getProduct(String key, Long range) {
        try {
            return Observable.fromCallable(() -> dynamoDBMapper.load(Product.class, key, range));
        } catch (RuntimeException e) {
            Log.e("getProductError", e.getMessage(), e);
            return null;
        }
    }

    public List<Product> getAllProducts() {
        try {
            PaginatedScanList<Product> products = dynamoDBMapper.scan(Product.class, new DynamoDBScanExpression());
            return products;
        } catch (RuntimeException e) {
            Log.e("getAllProductsError", e.getMessage(), e);
            return null;
        }
    }

    public int count(){
        try {
            return dynamoDBMapper.count(Product.class, new DynamoDBScanExpression());
        } catch (RuntimeException e) {
            Log.e("countError", e.getMessage(), e);
            return -1;
        }
    }

    public boolean deleteProduct(Product product) {
        try {
            dynamoDBMapper.delete(product);
            return true;
        } catch (RuntimeException e) {
            Log.e("deleteProductError", e.getMessage(), e);
            return true;
        }
    }

}
