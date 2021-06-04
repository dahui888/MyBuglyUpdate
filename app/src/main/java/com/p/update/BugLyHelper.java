package com.p.update;

import android.content.Context;
import android.os.Environment;

import com.tencent.bugly.Bugly;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.beta.Beta;

/**
 * Author：i小灰
 * Date：2021/6/4 06:08
 * desc：BugLy的帮助工具类
 */
public class BugLyHelper {

    public static final String APP_ID = "c756867ac2"; // BugLy上注册的AppId
    public static final String APP_CHANNEL = "DEBUG"; // 自定义渠道

    private static  class BugLyHelperHolder{
        private static final BugLyHelper Instance = new BugLyHelper();
    }

    private BugLyHelper(){ }

    public static  BugLyHelper getInstance(){
        return BugLyHelperHolder.Instance;
    }

    /**
     * app中进行初始化以及一些配置
     * @param context
     */
    public void initBugLy(Context context){
        /***** Beta高级设置 *****/
        /**
         * true 表示app启动自动初始化升级模块;
         * false 不会自动初始化;
         * 开发者如果担心sdk初始化影响app启动速度，可以设置为false，
         * 在后面某个时刻手动调用Beta.init(getApplicationContext(),false);
         */
        Beta.autoInit = true;

        /**
         * true 表示初始化时自动检查升级;
         * false 表示不会自动检查升级,需要手动调用Beta.checkUpgrade()方法;
         */
        Beta.autoCheckUpgrade = true;

        /**
         * 设置升级检查周期为60s(默认检查周期为0s)，60s内SDK不重复向后台请求策略);
         */
        Beta.upgradeCheckPeriod = 60 * 1000;

        /**
         * 设置启动延时为1s（默认延时3s），APP启动1s后初始化SDK，避免影响APP启动速度;
         */
        Beta.initDelay = 1 * 1000;

        /**
         * 设置通知栏大图标，largeIconId为项目中的图片资源;
         */
        Beta.largeIconId = R.mipmap.icon;

        /**
         * 设置状态栏小图标，smallIconId为项目中的图片资源Id;
         */
        Beta.smallIconId = R.mipmap.icon;

        /**
         * 设置更新弹窗默认展示的banner，defaultBannerId为项目中的图片资源Id;
         * 当后台配置的banner拉取失败时显示此banner，默认不设置则展示“loading“;
         */
        Beta.defaultBannerId = R.mipmap.icon;

        /**
         * 设置sd卡的Download为更新资源保存目录;
         * 后续更新资源会保存在此目录，需要在manifest中添加WRITE_EXTERNAL_STORAGE权限;
         */
        Beta.storageDir = Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);


        /**
         * 设置自定义升级对话框UI布局
         */
        Beta.upgradeDialogLayoutId = R.layout.upgrade_dialog;

        /**
         * 点击过确认的弹窗在APP下次启动自动检查更新时会再次显示;
         */
        Beta.showInterruptedStrategy = true;

        /**
         * 设置是否显示消息通知;
         * 如果你不想在通知栏显示下载进度，你可以将这个接口设置为false，默认值为true。
         */
        Beta.enableNotification = true;

        /**
         * 设置Wifi下自动下载;
         * 如果你想在Wifi网络下自动下载，可以将这个接口设置为true，默认值为false。
         */
        Beta.autoDownloadOnWifi  = true;

        /**
         * 设置是否显示弹窗中的apk信息 true显示全部信息 为false 只显示更新说明;
         * 如果你使用我们默认弹窗是会显示apk信息的，如果你不想显示可以将这个接口设置为false。
         */
        Beta.canShowApkInfo  = true;

        /**
         * 用于去除弹出的tips这里不是很需要 看你们具体的需求了啊 不需要就注释掉
         */
//        Beta.strToastYourAreTheLatestVersion = "";
//        Beta.strToastCheckingUpgrade = "";

        /**
         * 只允许在MainActivity上显示更新弹窗，其他activity上不显示弹窗;
         * 不设置会默认所有activity都可以显示弹窗;
         */
        Beta.canShowUpgradeActs.add(MainActivity.class);

        /***** BugLy高级设置 *****/
        BuglyStrategy strategy = new BuglyStrategy();

        /**
         * 设置app渠道号
         */
        strategy.setAppChannel(APP_CHANNEL);

        /***** 统一初始化BugLy产品true表示打开debug模式，false表示关闭调试模式，包含Beta *****/
        Bugly.init(context, APP_ID, false, strategy);
    }

    /**
     * 进行更新
     * @param context
     */
    public void doUpdateNow(Context context){
        Beta.init(context,true);
        Beta.checkUpgrade();
    }




    /**
     * 示例在 {@link MainActivity}中
     * 手动检查更新（用于设置页面中检测更新按钮的点击事件）
     */
//    public static synchronized void checkUpgrade()

    /**
     * 示例在 {@link MainActivity}中
     * 获取本地已有升级策略（非实时，可用于界面红点展示）
     *
     * @return
     */
//    public static synchronized UpgradeInfo getUpgradeInfo()

    /**
     * 示例在 {@link MainActivity}中
     * @param isManual  用户手动点击检查，非用户点击操作请传false
     * @param isSilence 是否显示弹窗等交互，[true:没有弹窗和toast] [false:有弹窗或toast]
     */
//    public static synchronized void checkUpgrade(boolean isManual, boolean isSilence)


}