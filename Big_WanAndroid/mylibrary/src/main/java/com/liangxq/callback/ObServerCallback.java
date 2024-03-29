package com.liangxq.callback;

import com.liangxq.ApiException;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;


public abstract class ObServerCallback<T> implements Observer<T>{
    private CompositeDisposable compositeDisposable=new CompositeDisposable();
    @Override
    public void onSubscribe(Disposable d) {
        compositeDisposable.add(d);
    }

    @Override
    public void onError(Throwable e) {
        String error="";
        if(e instanceof ApiException){
            ApiException apiException= (ApiException) e;
            error=apiException.getMessage();
        }else if(e instanceof HttpException){
            HttpException httpException= (HttpException) e;
            error=httpException.message();
        }
        onError(error);
        if(compositeDisposable!=null){
            compositeDisposable.clear();
        }
    }

    public abstract void onError(String message);

    @Override
    public void onComplete() {
        if(compositeDisposable!=null){
            compositeDisposable.clear();
        }
    }
}
