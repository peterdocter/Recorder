package com.letv.MVPRecorder.presenter;


import com.letv.MVPRecorder.View.IView;

/**
 * Created by zhangjiahao on 2015/6/7.
 */
public class RecorderPresenter extends BasePresenter<IPresenter,IView> implements IPresenter {

    private int count;

    @Override
    protected IPresenter onCreatePresenter() {
        return this;
    }

    @Override
    public void onStartRecord() {
        view.showView("current count = " + count++);
    }
}
