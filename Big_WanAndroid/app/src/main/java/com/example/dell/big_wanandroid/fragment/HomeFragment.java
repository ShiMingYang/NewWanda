package com.example.dell.big_wanandroid.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RadioGroup;

import com.example.dell.big_wanandroid.R;
import com.example.dell.big_wanandroid.base.BaseFragment;
import com.example.dell.big_wanandroid.home.HomeP;
import com.example.dell.big_wanandroid.home.HomeView;
import com.example.dell.big_wanandroid.home.adapter.HomeXlvAdapter;
import com.example.dell.big_wanandroid.home.bean.BannerBean;
import com.example.dell.big_wanandroid.home.bean.ListBean;
import com.example.dell.big_wanandroid.home.ui.HomeBanWebActivity;
import com.example.dell.big_wanandroid.home.ui.HomeWebActivity;
import com.example.dell.big_wanandroid.utils.RlvHideUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Dell on 2019/4/26.
 */

public class HomeFragment extends BaseFragment<HomeP, HomeView> implements HomeView {


    @BindView(R.id.rlv)
    RecyclerView rlv;
    @BindView(R.id.smr)
    SmartRefreshLayout smr;
    @BindView(R.id.ll)
    CoordinatorLayout ll;


    private int page = 0;
    private ArrayList<BannerBean.DataBean> banbeans;
    private ArrayList<ListBean.DataBean.DatasBean> list;
    private HomeXlvAdapter adapter;
    private boolean isScroll;


    @Override
    protected HomeP initpresenter() {
        return new HomeP();
    }

    @Override
    protected int getlayout() {
        return R.layout.home;
    }

    @Override
    protected void initData() {
        presenter.getdata(page);
        presenter.getbanData();
    }

    @Override
    protected void initView() {
        rlv.setLayoutManager(new LinearLayoutManager(getContext()));
        banbeans = new ArrayList<>();
        list = new ArrayList<>();
        adapter = new HomeXlvAdapter(getContext(), list, banbeans);
        rlv.setAdapter(adapter);

        final RadioGroup rgbtn = getActivity().findViewById(R.id.rg);
        final Toolbar Toolbar = getActivity().findViewById(R.id.toolbar);
        final FloatingActionButton flbtn = getActivity().findViewById(R.id.flbtn);
          RlvHideUtils.Hide(rgbtn,rlv,flbtn);
         RlvHideUtils.OnClicks(flbtn,rlv,Toolbar);
          RlvHideUtils.initRecy(rlv,Toolbar);

        smr.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                adapter.banbeans.clear();
                adapter.notifyDataSetChanged();
                initData();
                smr.finishLoadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                adapter.list.clear();
                adapter.banbeans.clear();
                initData();
                adapter.notifyDataSetChanged();
                smr.finishRefresh();

            }
        });

        adapter.SetOnItemBanClickLisener(new HomeXlvAdapter.OnItemBanClickLisener() {
            @Override
            public void OnItemBanClickLisener(int position) {
                Intent intent = new Intent(getContext(), HomeBanWebActivity.class);
                intent.putExtra("title",banbeans.get(position).getTitle());
                intent.putExtra("link",banbeans.get(position).getUrl());
                startActivity(intent);
            }
        });

        adapter.SetOnItemClickLisener(new HomeXlvAdapter.OnItemClickLisener() {
            @Override
            public void OnItemClickLisener(int position) {
                Intent intent = new Intent(getContext(), HomeBanWebActivity.class);
                intent.putExtra("link", list.get(position).getLink());
                intent.putExtra("titles", list.get(position).getTitle());
                startActivity(intent);
            }
        });


    }




    @Override
    public void setData(ListBean cc) {
        adapter.setlistData(cc);


    }

    @Override
    public void setBandata(BannerBean cc) {
        adapter.setbanData(cc);

    }



}
