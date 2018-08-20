package com.mustang.lib_common.base;


import com.mustang.lib_common.LoginBean;
import com.mustang.lib_common.MemberInfoApi;
import com.mustang.lib_common.utils.ApiEnum;
import com.mustang.lib_common.utils.ApiUtil;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Flowable;
import okhttp3.RequestBody;

/**
 * Created by Mustang on 2018/8/15.
 */
public abstract class BaseDataManager {
    private MemberInfoApi mMemberInfoApi = new ApiUtil().getApi(MemberInfoApi.class, ApiEnum.MemberCenter);
    public Flowable<LoginBean> getMemberInfo(){
        try {

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("login","Account");
            RequestBody requestBody = ApiUtil.Companion.createRequestBody(jsonObject);
            return ApiUtil.Companion.getComposeFlowable(mMemberInfoApi.login(requestBody));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}