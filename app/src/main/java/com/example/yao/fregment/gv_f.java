package com.example.yao.fregment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.yao.adapter.adapter_leibie;
import com.example.yao.android_question.R;
import com.example.yao.android_question.fenlei;
import com.example.yao.android_question.question_list;
import com.example.yao.dialog.MyDialog;
import com.example.yao.pojo.leibie;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * Created by 89551 on 2016-09-04.
 */
public class gv_f extends Fragment {

    int user_id;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_gv, null);

        final GridView gv = (GridView) view.findViewById(R.id.gv);

        final MyDialog dialog = new MyDialog(getActivity());

        dialog.show();

        Bundle bundle = getArguments();

        String url = bundle.getString("url");
        user_id = bundle.getInt("userid");

        RequestParams params = new RequestParams(url);

        x.http().get(params, new Callback.CommonCallback<JSONArray>() {
            @Override
            public void onSuccess(JSONArray result) {

                dialog.dismiss();

                Gson gson=new Gson();

                final List<leibie> list = gson.fromJson(result.toString(),new TypeToken<List<leibie>>(){}.getType());

                final adapter_leibie adapter = new adapter_leibie(getActivity(),list);

                gv.setAdapter(adapter);

                gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        Intent it = new Intent(getActivity(),question_list.class);
                        it.putExtra("leixing",list.get(i));
                        it.putExtra("userid",user_id);
                        startActivity(it);
                        //overridePendingTransition(R.anim.welcome_in,R.anim.welcome_out);
                    }
                });

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

                dialog.dismiss();

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });



        return view;
    }
}
