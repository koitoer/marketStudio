package com.koitoer.android.market.marketapp.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.koitoer.android.market.marketapp.MarketApplication;
import com.koitoer.android.market.marketapp.R;
import com.koitoer.android.market.marketapp.domain.Product;
import com.koitoer.android.market.marketapp.repository.ProductRepository;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by mmena on 4/21/18.
 */

public class AddItemFragment extends Fragment{

    @Inject
    ProductRepository productRepository;

    @BindView(R.id.description)
    TextView descriptionTextView;

    private Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.add_item_fragment, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        MarketApplication.applicationComponent.inject(this);
        return rootView;

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        descriptionTextView.setText("My text");

        productRepository.getProduct("koitoer", 1l)
                .onErrorReturn(throwable -> {
                    Product product = new Product();
                    product.setUserName("NO_ITEM");
                    return product;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(product -> descriptionTextView.setText(product.getName()));

    }

    @OnClick(R.id.saveSecretButton)
    public void sayHi(Button button) {
        descriptionTextView.setText("Hello!");
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
