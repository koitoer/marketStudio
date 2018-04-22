package com.koitoer.android.market.marketapp;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.koitoer.android.market.marketapp.domain.ConstantUtil;
import com.koitoer.android.market.marketapp.domain.Product;
import com.koitoer.android.market.marketapp.repository.CognitoCredentialProvider;
import com.koitoer.android.market.marketapp.repository.ProductRepository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

@RunWith(AndroidJUnit4.class)
public class ProductRepositoryTest {

    @Inject
    private ProductRepository productRepository;

    @Test
    public void roundTripTest() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        CognitoCredentialProvider cognitoCredentialProvider = new CognitoCredentialProvider(appContext);
        ProductRepository productRepository = new ProductRepository(cognitoCredentialProvider);

        //TODO Add builder
        Product product = new Product();
        product.setUserName(ConstantUtil.KOITOER);
        product.setRange(0l);
        product.setName("test-record");

        Assert.assertTrue(productRepository.createProduct(product));
        Assert.assertTrue(productRepository.getTableInformation().contains(ConstantUtil.PRODUCT_TABLE));
        Assert.assertTrue(productRepository.getAllProducts().contains(product));
        Assert.assertEquals(productRepository.count(), 1);
        Assert.assertTrue(productRepository.deleteProduct(product));
        Assert.assertEquals(productRepository.count(), 0);
        Assert.assertEquals(productRepository.getAllProducts().size(), 0);

    }
}
