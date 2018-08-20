package com.mustang.lib_common.webview;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.mustang.lib_common.R;
import com.mustang.lib_common.base.SimpleActivity;

import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Mustang on 2018/8/15.
 */

public class WebActivity extends SimpleActivity {


    private ImageView mIvBack;
    /**
     * 关闭
     */
    private TextView mTvClose;
    private TextView mTvtitle;
    private ProgressBar mProgressBar;
    private WebView mWebview;


    @Override
    protected int getLayout() {
        return R.layout.web_view;
    }

    @Override
    protected void initEventAndData() {
        initViewId();
        initView();
    }

    @Override
    protected void initListener() {

    }

    private void initViewId() {
        mIvBack = (ImageView) findViewById(R.id.iv_back);
        mTvClose = (TextView) findViewById(R.id.tv_close);
        mTvtitle = (TextView) findViewById(R.id.tv_title);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        mWebview = (WebView) findViewById(R.id.webview);
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mWebview.canGoBack()) {
                    mWebview.goBack(); //goBack()表示返回WebView的上一页面

                } else {
                    if (mWebview != null) {
                        mWebview.stopLoading();
                        mWebview.destroy();
                        mWebview = null;
                    }
                    onBackPressedSupport();
                }
            }
        });
        mTvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mWebview != null) {
                    mWebview.stopLoading();
                    mWebview.destroy();
                    mWebview = null;
                }
                onBackPressedSupport();
            }
        });
    }

    private void initView() {
        mTvtitle.setText("标题");
        mTvClose.setVisibility(View.VISIBLE);
        // 添加头部功能选择，这里用一张图片模拟实现。
        mWebview.getSettings().setJavaScriptEnabled(true);
        mWebview.getSettings().setSupportMultipleWindows(true);
        //webView.getSettings().setRenderPriority(RenderPriority.HIGH);
        mWebview.getSettings().setSupportZoom(true);
        mWebview.getSettings().setAppCacheEnabled(false);
        mWebview.getSettings().setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);
        mWebview.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        mWebview.getSettings().setUserAgentString(mWebview.getSettings().getUserAgentString() + "jarvan");
        mWebview.setClipToPadding(true);
        mWebview.setLayerType(View.LAYER_TYPE_NONE, null);
        mWebview.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    mProgressBar.setVisibility(View.GONE);
                } else {
                    if (View.INVISIBLE == mProgressBar.getVisibility()) {
                        mProgressBar.setVisibility(View.VISIBLE);
                    }
                    mProgressBar.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);

            }

            public void onReceivedIcon(WebView view, Bitmap icon) {
            }

            public void onReceivedTitle(WebView view, String title) {

            }
        });

        mWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {


                return true;
            }

        });

    }


    public class JsInteration {

       /* @JavascriptInterface
        public void goods_id(String id) {
            if (TextUtil.isEmpty(id)) {
                return;
            }

        }*/


    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            if (mWebview.canGoBack()) {
                mWebview.goBack(); //goBack()表示返回WebView的上一页面
                return true;
            } else {
                if (mWebview != null) {
                    mWebview.stopLoading();
                    mWebview.destroy();
                    mWebview = null;
                }
                finish();
                return true;
            }

        }
        return false;
    }

}
