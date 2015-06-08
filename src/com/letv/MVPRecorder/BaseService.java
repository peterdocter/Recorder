package com.letv.MVPRecorder;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import com.letv.MVPRecorder.presenter.RecorderPresenter;

public class BaseService extends Service {

    private static final String TAG = "BaseService";
    private RecorderPresenter mRecorderPresenter;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate :" + this.hashCode());
        if (mRecorderPresenter == null) {
            mRecorderPresenter = RecorderPresenter.getInstance();
            Log.i(TAG, "create a presenter :" + mRecorderPresenter.hashCode());
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return Service.START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onbind");
        return mRecorderPresenter.getBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind :" + mRecorderPresenter.hashCode());
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }
}
