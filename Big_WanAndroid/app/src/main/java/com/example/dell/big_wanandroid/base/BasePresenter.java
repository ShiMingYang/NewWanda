package com.example.dell.big_wanandroid.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by Dell on 2019/4/28.
 */

public abstract class BasePresenter<v extends BaseView>{


    protected v mView;

    public BasePresenter() {
        initModel();
    }

    protected abstract void initModel();

    public void bind(v baseview) {
        this.mView = baseview;
    }


}
