package com.happy.everything;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.happy.everything.base.BaseFragment;

/**
 * Created by chenyizhang on 24/02/2018.
 */

public class YellowFragment extends BaseFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.layout_yellow, container, false);
        return view;
    }
}
