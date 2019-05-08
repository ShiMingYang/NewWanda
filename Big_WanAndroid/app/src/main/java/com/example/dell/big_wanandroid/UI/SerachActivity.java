package com.example.dell.big_wanandroid.UI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.big_wanandroid.R;

public class SerachActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mBacks;
    /**
     * 搜索历史
     */
    private TextView mLishi;
    /**
     * 清空
     */
    private TextView mClear;
    private RecyclerView mRlv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serach);
        initView();
    }

    private void initView() {
        mBacks = (ImageView) findViewById(R.id.backs);
        mLishi = (TextView) findViewById(R.id.lishi);
        mClear = (TextView) findViewById(R.id.clear);
        mRlv = (RecyclerView) findViewById(R.id.rlv);
        mBacks.setOnClickListener(this);
        mClear.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.backs:
                finish();
                break;
            case R.id.clear:
                mClear.setText("");
                break;
        }
    }
}
