package com.mustang.lib_common.utils;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.TextView;

/**
 * Created by Mustang on 2018/8/15.
 */

public class AllInputUtil {
    private AllInputUtil(){}
    static boolean isAllInput(TextView... textViews) {
        boolean b = true;
        for (TextView tv :
                textViews) {
            if (TextUtils.isEmpty(tv.getText().toString())) {
                b = false;
                break;
            }
        }
        return b;
    }

    public interface HaveInfo {
        boolean haveInfo();

        void addTextChangedListener(TextChangeListener textChangeListener);
    }

    public interface TextChangeListener {
        void textChange(CharSequence s);
    }

    public static void setListener(final TextView button, final TextView... textViews) {
        for (TextView tv :
                textViews) {
            tv.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    boolean allInput = isAllInput(textViews);
                    button.setEnabled(allInput);
                }
            });
        }
    }

    public static void setListener(final TextView button, final HaveInfo... haveInfos) {
        for (HaveInfo haveInfo :
                haveInfos) {
            haveInfo.addTextChangedListener(new TextChangeListener() {
                @Override
                public void textChange(CharSequence s) {
                    boolean allInput = isAllInput(haveInfos);
                    button.setEnabled(allInput);
                }
            });
        }
    }

    private static boolean isAllInput(HaveInfo[] haveInfos) {
        boolean b = true;
        for (HaveInfo haveInfo :
                haveInfos) {
            if (!haveInfo.haveInfo()) {
                b = false;
                break;
            }
        }
        return b;
    }


}

