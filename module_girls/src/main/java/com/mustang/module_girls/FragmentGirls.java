package com.mustang.module_girls;



import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.mustang.lib_common.base.ARouterPath;
import com.mustang.lib_common.base.BaseFragment;
import com.mustang.lib_common.base.SimpleFragment;


/**
 * * Created by Mustang on 2018/8/15.
 * @Desc FragmentNews
 */
@Route(path = ARouterPath.GirlsFgt)
public class FragmentGirls extends SimpleFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentGirls() {
        // Required empty public constructor///
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
    public static FragmentGirls newInstance(String param1, String param2) {
        FragmentGirls fragment = new FragmentGirls();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_girls, container, false);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_girls;
    }

    @Override
    protected void initEventAndData() {

    }


}
