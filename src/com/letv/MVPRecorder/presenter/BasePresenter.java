package com.letv.MVPRecorder.presenter;

import android.os.Binder;
import android.os.IBinder;
import com.letv.MVPRecorder.View.IView;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by zhangjiahao on 2015/6/7.
 */
public abstract class BasePresenter {

    private static final String TAG = "BasePresenter";
    private IPresenter mPresenterProxy;
    private Binder mPresenterBinder;

    protected IView view;

    public BasePresenter() {
        mPresenterBinder = new PresenterBinder();
        mPresenterProxy = getPresenterProxy();
    }

    private InvocationHandler handler = new InvocationHandler() {
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Object result;
            synchronized (this) {
                result = method.invoke(BasePresenter.this, args);
            }
            return result;
        }
    };

    private IPresenter getPresenterProxy() {
        IPresenter instance = (IPresenter) Proxy.newProxyInstance(getClass().getClassLoader(), getClass().getInterfaces(), handler);
        return instance;
    }

    public IBinder getBinder() {
        return mPresenterBinder;
    }

    public class PresenterBinder extends Binder {

        public IPresenter newPresenter(IView view) {
            BasePresenter.this.view = view;
            return mPresenterProxy;
        }
    }
}
