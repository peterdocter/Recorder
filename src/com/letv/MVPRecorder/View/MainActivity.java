package com.letv.MVPRecorder.View;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.letv.MVPRecorder.R;


public class MainActivity extends BaseActivity implements IView, View.OnClickListener {

    private TextView tv;
    private Button btn;

    @Override
    protected IView onCreatePresenter() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        findView();
        setListener();
    }

    private void findView() {
        tv = (TextView) findViewById(R.id.tv);
        btn = (Button) findViewById(R.id.btn);
    }

    private void setListener() {
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                presenter.onStartRecord();
                break;
        }
    }

    @Override
    public void showView(String str) {
        tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
        tv.setText(str);
    }
}
