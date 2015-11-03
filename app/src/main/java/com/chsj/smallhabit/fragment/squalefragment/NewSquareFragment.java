package com.chsj.smallhabit.fragment.squalefragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.chsj.smallhabit.R;

/**
 * Created
 * Author: jmxgrobby
 * Email: jmxgrobby@163.com
 * Date:  2015/11/3.
 */

/**
 * 这是广场Activity 的最新动态的界面
 */
public class NewSquareFragment extends BaseFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new_square,container,false);
    }
    @Override
    public String getFragmentTitle() {
        return "最新";
    }
}
