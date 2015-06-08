package com.letv.MVPRecorder.presenter;


/**
 * Created by zhangjiahao on 2015/6/7.
 */
public class RecorderPresenter extends BasePresenter implements IPresenter {

    private int count;

    @Override
    public void onStartRecord() {
        view.showView("current count = " + count++);
    }
}
