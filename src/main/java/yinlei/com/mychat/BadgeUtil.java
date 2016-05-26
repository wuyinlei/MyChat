package yinlei.com.mychat;

import android.content.Context;
import android.view.View;

/**
 * Created by wuyin on 2016/5/26.
 */
public class BadgeUtil {
    public static BadgeView addBadge(Context context, View view, int text){
        BadgeView badgeView = new BadgeView(context, view);
        badgeView.setText(text + "");
        badgeView.show();
        return badgeView;
    }

    public static BadgeView addBadge(Context context, View view){
        BadgeView badgeView = new BadgeView(context, view);
        badgeView.setText(" ");
        badgeView.show();
        return badgeView;
    }


    public static BadgeView addBadge(Context context, View view, int text, int margin){
        BadgeView badgeView = new BadgeView(context, view);
        badgeView.setText(text + "");
        badgeView.setBadgePosition(BadgeView.POSITION_CENTER);
        badgeView.setBadgeMargin(margin);
        badgeView.show();
        return badgeView;
    }

    public static void addBadge(BadgeView badgeView){
        badgeView.setText(" ");
        badgeView.show();
    }
}
