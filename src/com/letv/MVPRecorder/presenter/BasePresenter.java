package com.letv.MVPRecorder.presenter;

import android.os.Binder;
import android.os.IBinder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by zhangjiahao on 2015/6/7.
 */
public abstract class BasePresenter<T, K> {

    private Binder mPresenterBinder;

    protected K view;

    public BasePresenter() {
        mPresenterBinder = new PresenterBinder();
    }

    private InvocationHandler handler = new InvocationHandler() {
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Object result = null;
            synchronized (this) {
                result = method.invoke(BasePresenter.this, args);
            }
            return result;
        }
    };

    private T getPresenterProxy() {
        T instance = (T) Proxy.newProxyInstance(getClass().getClassLoader(), getClass().getInterfaces(), handler);
        return instance;
    }

    public IBinder getBinder() {
        return mPresenterBinder;
    }

    public class PresenterBinder extends Binder {

        public T newPresenter(K view) {
            BasePresenter.this.view = view;
            return getPresenterProxy();
        }
    }

}
