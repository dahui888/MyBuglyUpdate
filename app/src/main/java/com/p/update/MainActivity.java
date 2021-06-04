package com.p.update;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.UpgradeInfo;

/**
 * Author：i小灰
 * Date：2021/6/4 06:15
 * desc：主页
 */
public class MainActivity extends AppCompatActivity {

    private Activity activity;
    private Button checkUpgradeBtn;
    private Button refreshBtn;
    private TextView upgradeInfoTv;
    private TextView appInfoTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity = this;
        //初始化ID
        initView();
        //初始化监听事件
        initListener();
        //最上方显示的信息
        loadAppInfo();
    }

    private void initView() {
        checkUpgradeBtn = $(R.id.check_upgrade);
        refreshBtn = $(R.id.refresh_info);
        upgradeInfoTv = $(R.id.upgrade_info);
        appInfoTv = $(R.id.app_info);
    }


    private void initListener() {

        /***** 检查更新 *****/
        checkUpgradeBtn.setOnClickListener(v -> {
            BugLyHelper.getInstance().doUpdateNow(activity);
        });

        /***** 刷新 *****/
        refreshBtn.setOnClickListener(v -> {
            loadUpgradeInfo();
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        //加载升级信息
        loadUpgradeInfo();
    }

    private void loadUpgradeInfo() {
        if (upgradeInfoTv == null)
            return;

        /***** 获取升级信息 *****/
        UpgradeInfo upgradeInfo = Beta.getUpgradeInfo();

        if (upgradeInfo == null) {
            upgradeInfoTv.setText("无升级信息");
            return;
        }

        StringBuilder info = new StringBuilder();
        info.append("id: ").append(upgradeInfo.id).append("\n");
        info.append("标题: ").append(upgradeInfo.title).append("\n");
        info.append("升级说明: ").append(upgradeInfo.newFeature).append("\n");
        info.append("versionCode: ").append(upgradeInfo.versionCode).append("\n");
        info.append("versionName: ").append(upgradeInfo.versionName).append("\n");
        info.append("发布时间: ").append(upgradeInfo.publishTime).append("\n");
        info.append("安装包Md5: ").append(upgradeInfo.apkMd5).append("\n");
        info.append("安装包下载地址: ").append(upgradeInfo.apkUrl).append("\n");
        info.append("安装包大小: ").append(upgradeInfo.fileSize).append("\n");
        info.append("弹窗间隔（ms）: ").append(upgradeInfo.popInterval).append("\n");
        info.append("弹窗次数: ").append(upgradeInfo.popTimes).append("\n");
        info.append("发布类型（0:测试 1:正式）: ").append(upgradeInfo.publishType).append("\n");
        info.append("弹窗类型（1:建议 2:强制 3:手工）: ").append(upgradeInfo.upgradeType);

        upgradeInfoTv.setText(info);
    }

    /**
     * UpgradeInfo内容如下
     * public String id = "";//唯一标识
     * public String title = "";//升级提示标题
     * public String newFeature = "";//升级特性描述
     * public long publishTime = 0;//升级发布时间,ms
     * public int publishType = 0;//升级类型 0测试 1正式
     * public int upgradeType = 1;//升级策略 1建议 2强制 3手工
     * public int popTimes = 0;//提醒次数
     * public long popInterval = 0;//提醒间隔
     * public int versionCode;
     * public String versionName = "";
     * public String apkMd5;//包md5值
     * public String apkUrl;//APK的CDN外网下载地址
     * public long fileSize;//APK文件的大小
     * pubilc String imageUrl; // 图片url
     */


    private void loadAppInfo() {
        try {
            StringBuilder info = new StringBuilder();
            PackageInfo packageInfo = this.getPackageManager().getPackageInfo(this.getPackageName(), PackageManager.GET_CONFIGURATIONS);
            int versionCode = packageInfo.versionCode;
            String versionName = packageInfo.versionName;
            info.append("appid: ").append(BugLyHelper.APP_ID).append(" ")
                    .append("channel: ").append(BugLyHelper.APP_CHANNEL).append(" ")
                    .append("version: ").append(versionName).append(".").append(versionCode);
            appInfoTv.setText(info);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 代替findViewById
     */
    @SuppressWarnings("unchecked")
    public <T> T $(int resId) {
        return (T) findViewById(resId);
    }
}