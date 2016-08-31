package com.example.yao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.yao.android_question.R;
import com.example.yao.pojo.question;

import org.json.JSONObject;
import org.xutils.common.Callback;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by 89551 on 2016-08-31.
 */
public class adapter_sou extends BaseAdapter{

    private List<question> list;

    LayoutInflater inflater;

    public adapter_sou(Context context, List<question> list) {

        inflater = LayoutInflater.from(context);

        this.list=list;

    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        viewHolder holder = null;

        if (view==null){

            view=inflater.inflate(R.layout.sousuo_adapter,null);

            holder=new viewHolder();

            holder.tv_q= (TextView) view.findViewById(R.id.question_s);

            holder.tv_t=(TextView)view.findViewById(R.id.leixing_s);

            holder.tv_y= (TextView) view.findViewById(R.id.time_s);

            view.setTag(holder);

        }else {

            holder= (viewHolder) view.getTag();

        }


        holder.tv_q.setText(list.get(i).getContent());

        int t=list.get(i).getType();

        if (t==1){

            holder.tv_t.setText("单选题");

        }else if (t==2){

            holder.tv_t.setText("多选题");

        }else if (t==3){
            holder.tv_t.setText("判断题");

        }else if (t==4){

            holder.tv_t.setText("简答题");

        }

        long l =list.get(i).getTime();

        Date data = new Date(l);
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        format.format(data);
        holder.tv_y.setText(format.format(data));

        return view;
    }

    public class viewHolder{

        TextView tv_q;

        TextView tv_y;

        TextView tv_t;

    }

}
