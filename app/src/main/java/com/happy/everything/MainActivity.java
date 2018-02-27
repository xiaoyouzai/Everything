package com.happy.everything;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;

import com.happy.everything.base.BaseActivity;
import com.happy.everything.tool.FragmentSwitcher;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements FragmentSwitcher.CreateFragmentByTag, View.OnClickListener {

    private FragmentSwitcher switcher;
    private List<String> tags;
    private static final String RED = "RED";
    private static final String YELLOW = "YELLOW";
    private static final String BLUE = "BLUE";
    private ImageView ivRed;
    private ImageView ivYellow;
    private ImageView ivBlue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initFragmentSwitcher();
        switcher.switchToFragmentByTag(RED);
    }

    private void initView() {
        ivRed = findViewById(R.id.iv_red);
        ivYellow = findViewById(R.id.iv_yellow);
        ivBlue = findViewById(R.id.iv_blue);
        ivRed.setOnClickListener(this);
        ivYellow.setOnClickListener(this);
        ivBlue.setOnClickListener(this);
    }

    private void initFragmentSwitcher() {
        tags = new ArrayList<>();
        tags.add(RED);
        tags.add(YELLOW);
        tags.add(BLUE);
        switcher = new FragmentSwitcher(getSupportFragmentManager(), R.id.fl_page_container, tags, this);
    }


    @Override
    public Fragment createFragment(String tag) {
        switch (tag) {
            case RED:
                return new RedFragment();
            case YELLOW:
                return new YellowFragment();
            case BLUE:
                return new BlueFragment();
            default:
                return null;
        }

    }

    @Override
    public void onClick(View v) {
        String tag = null;
        switch (v.getId()) {
            case R.id.iv_red:
                tag = RED;
                break;
            case R.id.iv_blue:
                tag = BLUE;
                break;
            case R.id.iv_yellow:
                tag = YELLOW;
                break;
            default:
        }
        switcher.switchToFragmentByTag(tag);
    }
}
