package com.example.yao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yao.android_question.R;
import com.example.yao.android_question.fenlei;
import com.example.yao.pojo.leibie;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.io.File;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by 89551 on 2016-09-01.
 */
public class adapter_leibie extends BaseAdapter{

    LayoutInflater inflater;
    private List<leibie> list;

    public adapter_leibie(Context context, List<leibie> list) {

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

        viewHolder holder=null;

        if (view==null){

            holder=new viewHolder();
            view=inflater.inflate(R.layout.fenlei_item,null);
            holder.imageView= (ImageView) view.findViewById(R.id.imag_f_i);
            holder.textView= (TextView) view.findViewById(R.id.tv_f_i);

            view.setTag(holder);

        }else {
            holder= (viewHolder) view.getTag();
        }

        ImageOptions options = new ImageOptions.Builder()
                .setIgnoreGif(false)
                .setFailureDrawableId(R.mipmap.ic_launcher)
                .setLoadingDrawableId(R.mipmap.ic_launcher)
                .setImageScaleType(ImageView.ScaleType.CENTER)
                .build()
                ;

        x.image().bind(holder.imageView,"http://115.29.136.118:8080/web-question/"+list.get(i).getIcon(),options);

        holder.textView.setText(list.get(i).getName());

        return view;
    }

    public class viewHolder{

        ImageView imageView;
        TextView textView;

    }

}
