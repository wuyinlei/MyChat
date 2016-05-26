package yinlei.com.mychat;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by wuyin on 2016/5/26.
 */
public class MainTabFragmentAdapter extends FragmentStatePagerAdapter {

    //fragment集合
    private List<Fragment> mFragments;
    //上下文
    private Context mContext;

    public MainTabFragmentAdapter(Context context, FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        mContext = context;
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        //return mTitles.get(position);
        return null;
    }


    public View getTabView(String title, int iconId) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.footer_main, null);
        TextView tv = (TextView) view.findViewById(R.id.textView);
        tv.setText(title);
        tv.setTag(title + "txt");
        ImageView img = (ImageView) view.findViewById(R.id.imageView);
        img.setTag(title + "img");
        img.setImageResource(iconId);
        return view;
    }


}
