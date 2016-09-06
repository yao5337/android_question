package com.example.yao.dialog;

import android.app.Dialog;
import android.content.Context;

import com.example.yao.android_question.R;

/**
 * Created by 89551 on 2016-08-30.
 */
public class MyDialog extends Dialog{

        public MyDialog(Context context) {
            super(context);
            setContentView(R.layout.dialog_l);
        }


        @Override
        public void show() {
            super.show();
        }

        @Override
        public void dismiss() {
            super.dismiss();
        }



}
