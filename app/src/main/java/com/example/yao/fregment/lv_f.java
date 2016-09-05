package com.example.yao.fregment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.yao.adapter.adapter_sou;
import com.example.yao.android_question.R;
import com.example.yao.android_question.question_activity;
import com.example.yao.dialog.MyDialog;
import com.example.yao.pojo.question;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * Created by 89551 on 2016-09-04.
 */
public class lv_f extends Fragment {


    ListView lv_l;
    int userid;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lv, null);

        lv_l= (ListView) view.findViewById(R.id.lv_shoucang);

        final MyDialog dialog = new MyDialog(getActivity());
        dialog.show();

        Bundle bundle = getArguments();
        userid = bundle.getInt("userid");
        RequestParams params = new RequestParams("http://115.29.136.118:8080/web-question/app/mng/store?method=list");

        params.addBodyParameter("userId",userid+"");

        x.http().post(params, new Callback.CommonCallback<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {

                dialog.dismiss();

                try {

                    JSONArray content = result.getJSONArray("content");

                    Gson gson = new Gson();
                    final List<question> list =gson.fromJson(content.toString(),new TypeToken<List<question>>(){}.getType());

                    for (question q :
                            list) {
                        Log.i("question_list",q.toString());
                    }

                    adapter_sou adapter = new adapter_sou(getActivity().getApplicationContext(),list);

                    lv_l.setAdapter(adapter);

                    lv_l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            Intent it = new Intent(getActivity(),question_activity.class);
                            it.putExtra("all",list.size());
                            it.putExtra("i",list.get(i));
                            it.putExtra("userid",userid);
                            it.putExtra("a",i+1);
                            startActivity(it);

                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

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
