package yinlei.com.mychat.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import yinlei.com.mychat.R;
import yinlei.com.mychat.adapter.MainTabFragmentAdapter;
import yinlei.com.mychat.fragment.ContactFragment;
import yinlei.com.mychat.fragment.DiscoveryFragment;
import yinlei.com.mychat.fragment.MessageFragment;
import yinlei.com.mychat.fragment.MineFragment;
import yinlei.com.mychat.ui.base.BaseActivity;
import yinlei.com.mychat.utils.BadgeUtil;
import yinlei.com.mychat.widget.BadgeView;

public class HomeActivity extends BaseActivity {


    //Tab索引
    public static final int TAB_GOODS = 1;
    public static final int TAB_MINE = 4;
    public static final int TAB_TROLLEY = 3;

    private MainTabFragmentAdapter fragmentAdapter;

    //红点相关
    public static final String PARAM_TAB_INDEX = "tabIndex";
    private BadgeView trolleyBadgeView;

    //底部菜单栏的实现
    private TabLayout tabLayout;
    private ViewPager viewPager;
    public List<Fragment> fragments = new ArrayList<>();

    public static List<View> views;
    String[] fragmentTitles;   //底部菜单栏标题
    int[] fragmentNormalIcons;    //正常icon
    int[] fragmentSelectedIcon;   //按下选择后的icon


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ininFragments();
        initTabLayout();
        initListener();
    }

    private void ininFragments() {
        this.fragments.add(new MessageFragment());
        this.fragments.add(new ContactFragment());
        this.fragments.add(new DiscoveryFragment());
        this.fragments.add(new MineFragment());
    }


    /*
     * 初始化tablayout
     */
    private void initTabLayout() {

        fragmentTitles = getResources().getStringArray(R.array.main_footer_title);
        fragmentNormalIcons = new int[]{R.mipmap.tab_icon_chat_normal,
                R.mipmap.tab_icon_contact_normal, R.mipmap.tab_icon_discover_normal,
                R.mipmap.tab_icon_me_normal};
        fragmentSelectedIcon = new int[]{
                R.mipmap.tab_icon_chat_focus, R.mipmap.tab_icon_contact_focus,
                R.mipmap.tab_icon_discover_focus, R.mipmap.tab_icon_me_focus
        };

        viewPager = (ViewPager) findViewById(R.id.viewPager_main);
        viewPager.setOffscreenPageLimit(4);
        //LogUtil.i(TAG, viewPager.getOffscreenPageLimit() + " page");
        tabLayout = (TabLayout) findViewById(R.id.tablayout_main);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式

        tabLayout.addTab(tabLayout.newTab().setText(fragmentTitles[0]));//添加tab选项卡
        tabLayout.addTab(tabLayout.newTab().setText(fragmentTitles[1]));
        tabLayout.addTab(tabLayout.newTab().setText(fragmentTitles[2]));
        tabLayout.addTab(tabLayout.newTab().setText(fragmentTitles[3]));

        fragmentAdapter = new MainTabFragmentAdapter(this, getSupportFragmentManager(), fragments);
        viewPager.setAdapter(fragmentAdapter);//给ViewPager设置适配器
        tabLayout.setupWithViewPager(viewPager);//将TabLayout和ViewPager关联起来。
        tabLayout.setTabsFromPagerAdapter(fragmentAdapter);//给Tabs设置适配器

        views = new ArrayList<>();

        initFooter();
        resetFooter(0);
    }

    /*初始化根部*/
    private void initFooter() {
        views.clear();

        for (int i = 0; i < fragments.size(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            View view = fragmentAdapter.getTabView(fragmentTitles[i], fragmentNormalIcons[i]);
            views.add(view);
            tab.setCustomView(view);
        }
    }


    //重置根部布局
    public void resetFooter(int position) {
        for (int i = 0; i < fragments.size(); i++) {
            View view = views.get(i);
            TextView title = (TextView) view.findViewWithTag(fragmentTitles[i] + "txt");
            ImageView icon = (ImageView) view.findViewWithTag(fragmentTitles[i] + "img");

            if (position != i) {
                title.setTextColor(getResources().getColor(R.color.black));
                icon.setImageResource(fragmentNormalIcons[i]);
            } else {
                title.setTextColor(getResources().getColor(R.color.green));
                icon.setImageResource(fragmentSelectedIcon[i]);
            }

        }
    }


    /*初始化viewPager监听者*/
    private void initListener() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                resetFooter(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    //查询是否有新消息
    private void weatherNewMsg() {
        for (int i = 0 ; i < views.size();i++) {
            trolleyBadgeView = BadgeUtil.addBadge(HomeActivity.this, views.get(i), i*10);
            trolleyBadgeView.show();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        weatherNewMsg();
    }


}
