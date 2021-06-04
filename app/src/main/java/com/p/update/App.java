package com.p.update;

import android.app.Application;

/**
 * Author：i小灰
 * Date：2021/6/4 06:06
 * desc：App的启动类
 */
public class App extends Application {

    //单例模式
    private static App Instance;

    public App() {
        App.Instance = this;
    }

    public static App getInstance() {
        if (Instance == null) {
            synchronized (App.class){
                if (Instance == null) {
                    Instance = new App();
                }
            }
        }
        return Instance;
    }


    @Override
    public void onCreate() {
        super.onCreate();

        //初始化BugLy
        BugLyHelper.getInstance().initBugLy(this);

    }

}