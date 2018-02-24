package com.happy.everything;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenyizhang on 24/02/2018.
 */

public class FragmentSwitcher {
    private Map<String, Fragment> fragmentMap = new HashMap<>();
    private FragmentManager manager;
    private List<String> tags;
    private int resId;
    private CreateFragmentByTag createFragmentByTag;
    private String currentTag;

    public FragmentSwitcher(FragmentManager manager, int resId, List<String> tags, CreateFragmentByTag createFragmentByTag) {
        this.manager = manager;
        this.tags = tags;
        this.createFragmentByTag = createFragmentByTag;
        this.resId = resId;
    }

    public void switchToFragmentByTag(String tag) {
        if (!tags.contains(tag)) {
            return;
        }
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment fragment = manager.findFragmentByTag(tag);
        if (fragment == null) {
            fragment = getByCatchOrCreate(tag);
            transaction.add(resId, fragment, tag);
        }
        if (!tag.equals(currentTag)) {
            for (String t : tags) {
                if (!t.equals(tag)) {
                    Fragment f = manager.findFragmentByTag(t);
                    if (f != null) {
                        transaction.hide(f);
                    }
                }
            }
        }
        transaction.show(fragment);
        transaction.commitAllowingStateLoss();
        manager.executePendingTransactions();
        currentTag = tag;
    }

    private Fragment getByCatchOrCreate(String tag) {
        Fragment fragment = fragmentMap.get(tag);
        if (fragment == null) {
            fragment = createFragmentByTag.createFragment(tag);
            fragmentMap.put(tag, fragment);
        }
        return fragment;
    }

    public interface CreateFragmentByTag {
        Fragment createFragment(String tag);
    }

}
