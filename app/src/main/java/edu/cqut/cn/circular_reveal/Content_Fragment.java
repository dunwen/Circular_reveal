package edu.cqut.cn.circular_reveal;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by dun on 2015/11/21.
 */
public class Content_Fragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_main,container,false);

        return view;

    }

    public static Content_Fragment getContent_Fragment(){
        return new Content_Fragment();
    }

}
