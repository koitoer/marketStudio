package com.koitoer.android.market.marketapp.domain;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBVersionAttribute;

@DynamoDBTable(tableName = "ProductTable")
public class Product {

    @DynamoDBHashKey
    private String userName;

    @DynamoDBRangeKey
    private Long range;

    @DynamoDBVersionAttribute
    private Long version;

    @DynamoDBAttribute(attributeName = "name")
    private String name;

    public Product(){}

    public Product(String userName, Long range, Long version, String name) {
        this.userName = userName;
        this.range = range;
        this.version = version;
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getRange() {
        return range;
    }

    public void setRange(Long range) {
        this.range = range;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (userName != null ? !userName.equals(product.userName) : product.userName != null)
            return false;
        return range != null ? range.equals(product.range) : product.range == null;

    }

    @Override
    public int hashCode() {
        int result = userName != null ? userName.hashCode() : 0;
        result = 31 * result + (range != null ? range.hashCode() : 0);
        return result;
    }
}
