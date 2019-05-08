package com.example.dell.big_wanandroid.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.example.dell.big_wanandroid.R;
import com.example.dell.big_wanandroid.api.MyServer;
import com.example.dell.big_wanandroid.base.BaseFragment;
import com.example.dell.big_wanandroid.httpUtils.BaseObserver;
import com.example.dell.big_wanandroid.httpUtils.HttpUtils;
import com.example.dell.big_wanandroid.httpUtils.RxUtils;
import com.example.dell.big_wanandroid.move.MoveP;
import com.example.dell.big_wanandroid.move.flow.MoveV;
import com.example.dell.big_wanandroid.move.vpFragmentTeam.MoveBean;
import com.example.dell.big_wanandroid.move.flow.NavigationAdapter;
import com.example.dell.big_wanandroid.utils.RlvHideUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.Disposable;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.TabView;

/**
 * Created by Dell on 2019/4/26.
 */
public class MoveFragment extends BaseFragment<MoveP,MoveV> implements MoveV {
    private static final String TAG = "MoveFragment";
    @BindView(R.id.tab)
    VerticalTabLayout tab;
    @BindView(R.id.rlv)
    RecyclerView rlv;
    private View view;
    private LinearLayoutManager mLayoutManager;
    private NavigationAdapter mRecyclerAdapter;
    private RadioGroup rgtbn;


    protected void initData() {
        presenter.getdata();

    }

    @Override
    protected MoveP initpresenter() {
        return new MoveP();
    }

    @Override
    protected int getlayout() {
        return R.layout.move;
    }




    @Override
    public void show(MoveBean bean) {
        final List<MoveBean.DataBean> list = bean.getData();
        mLayoutManager = new LinearLayoutManager(getContext());
        rlv.setLayoutManager(mLayoutManager);
        mRecyclerAdapter = new NavigationAdapter(list, getContext());
        rlv.setAdapter(mRecyclerAdapter);

        tab.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabView tab, int position) {
                LinearLayoutManager layoutManager = (LinearLayoutManager) rlv.getLayoutManager();
                layoutManager.scrollToPositionWithOffset(position, 0);
            }

            @Override
            public void onTabReselected(TabView tab, int position) {

            }
        });

        rgtbn = getActivity().findViewById(R.id.rg);
        final Toolbar Toolbar = getActivity().findViewById(R.id.toolbar);
        final FloatingActionButton flbtn = getActivity().findViewById(R.id.flbtn);
        RlvHideUtils.MoveHide(tab,rgtbn,rlv,flbtn);
        RlvHideUtils.OnClicks(flbtn,rlv,Toolbar);
        RlvHideUtils.initRecy(rlv,Toolbar);


        mRecyclerAdapter.addData(list);
        tab.setTabAdapter(new TabAdapter() {
            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public ITabView.TabBadge getBadge(int position) {
                return null;
            }

            @Override
            public ITabView.TabIcon getIcon(int position) {
                return null;
            }

            @Override
            public ITabView.TabTitle getTitle(int position) {
                return new TabView.TabTitle.Builder()
                        .setContent(list.get(position).getName())
                        .build();
            }

            @Override
            public int getBackground(int position) {
                return 0;
            }
        });
    }
}
