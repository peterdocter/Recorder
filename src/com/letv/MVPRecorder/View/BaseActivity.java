package com.letv.MVPRecorder.View;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import com.letv.MVPRecorder.BaseService;
import com.letv.MVPRecorder.presenter.BasePresenter;

/**
 * Created by zhangjiahao on 2015/6/7.
 */
public abstract class BaseActivity<T,K> extends Activity {

    private Intent intent;
    protected T presenter;

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            presenter = (T) ((BasePresenter.PresenterBinder) service).newPresenter(onCreatePresenter());
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };

    protected abstract K onCreatePresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initRecorderService();
        bindRecorderService();
    }

    private void initRecorderService() {
        intent = new Intent(this, BaseService.class);
        startService(intent);
    }

    private void bindRecorderService() {
        bindService(intent, conn, BIND_AUTO_CREATE);
    }

    private void unbindRecorderService() {
        unbindService(conn);
        conn = null;
    }

    private void stopRecorderService() {
        stopService(intent);
        intent = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (conn != null) {
            unbindRecorderService();
        }
    }

}
