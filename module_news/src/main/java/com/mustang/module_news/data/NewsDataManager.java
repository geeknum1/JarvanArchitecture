package com.mustang.module_news.data;

import android.support.annotation.NonNull;

import com.mustang.lib_common.LoginBean;
import com.mustang.lib_common.base.BaseDataManager;
import com.mustang.lib_common.utils.ApiEnum;
import com.mustang.lib_common.utils.ApiUtil;
import com.mustang.module_news.bean.GirlsData;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Flowable;
import okhttp3.RequestBody;


/**
 * Created by Mustang on 2018/8/15.
 */

public class NewsDataManager extends BaseDataManager implements NewsDataSource {
    private NewsApi mNewsApi = new ApiUtil().getApi(NewsApi.class, ApiEnum.MemberCenter);

    public Flowable<LoginBean> login(@NonNull final String name, @NonNull String pwd) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", name);
            jsonObject.put("pwd", pwd);
            RequestBody requestBody = ApiUtil.Companion.createRequestBody(jsonObject);
            return ApiUtil.Companion.getComposeFlowable(mNewsApi.login(requestBody));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Flowable<LoginBean> loadData() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", "dd");
            RequestBody requestBody = ApiUtil.Companion.createRequestBody(jsonObject);
            return ApiUtil.Companion.getComposeFlowable(mNewsApi.login(requestBody));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Flowable<GirlsData> getFuliData(String size, String page) {
        try {
            return ApiUtil.Companion.getComposeFlowable(mNewsApi.getFuliData(size, page));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}