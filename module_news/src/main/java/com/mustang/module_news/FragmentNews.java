package com.mustang.module_news;


import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.jakewharton.rxbinding2.view.RxView;
import com.mustang.lib_common.base.ARouterPath;
import com.mustang.lib_common.base.RootFragment;
import com.mustang.lib_common.utils.ToastUtils;
import com.mustang.module_news.adapter.GirlsAdapter;
import com.mustang.module_news.bean.GirlsData;
import com.mustang.module_news.callback.GirlItemClickCallback;
import com.mustang.module_news.contract.NewsContract;
import com.mustang.module_news.presenter.NewsPresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;


@Route(path = ARouterPath.NewsFgt)
public class FragmentNews extends RootFragment<NewsPresenter> implements NewsContract.View {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View view;
    private GirlsAdapter girlsAdapter;

    public FragmentNews() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentNews.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentNews newInstance(String param1, String param2) {
        FragmentNews fragment = new FragmentNews();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        view = View.inflate(getActivity(), R.layout.fragment_news, null);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        setPresenter(new NewsPresenter());
        stateLoading();
        girlsAdapter = new GirlsAdapter(girlItemClickCallback);
        RecyclerView recyclerView = mView.findViewById(R.id.girls_list);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext,2));
        recyclerView.setAdapter(girlsAdapter);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mPresenter.loadData();
            }
        }, 2000);

        addDisposable(RxView.clicks(mView.findViewById(R.id.btn_login)).subscribeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                ARouter.getInstance()
                        .build(ARouterPath.NewsAty)
                        .withString("params", "666")
                        .navigation(mActivity, 3);
            }
        }));



    }

    @Override
    protected void loadErrorData() {
        stateLoading();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mPresenter.loadData();
            }
        }, 2000);
    }

    @Override
    public void setUpView(GirlsData girlsData) {
        girlsAdapter.setGirlsList(girlsData.getResults());
        girlsAdapter.notifyDataSetChanged();
        stateMain();
       // ToastUtils.showLongToast("请求成功");

    }

    GirlItemClickCallback girlItemClickCallback = new GirlItemClickCallback() {

        @Override
        public void onClick(GirlsData.ResultsBean girlsItem) {
            Toast.makeText(getContext(), girlsItem.getDesc(), Toast.LENGTH_SHORT).show();
        }
    };






}
