package com.letv.MVPRecorder.presenter;

import android.os.Binder;
import android.os.IBinder;

/**
 * Created by zhangjiahao on 2015/6/7.
 */
public abstract class BasePresenter<T,K> {

    private Binder mPresenterBinder;

    protected K view;

    public BasePresenter() {
        mPresenterBinder = new PresenterBinder();
    }

    public IBinder getBinder() {
        return mPresenterBinder;
    }

    public class PresenterBinder extends Binder {

        public T newPresenter(K view) {
            BasePresenter.this.view = view;
            return onCreatePresenter();
        }
    }

    protected abstract T onCreatePresenter();
}
