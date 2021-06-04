大家好我是i小灰 今天给大家封装了一个BugLy自动升级与bug收集的模块 直接导入即可使用

百度搜索 i小灰 或下面进入学习更多内容
github: https://github.com/dahui888
博客: https://www.jianshu.com/u/37d88b909f3b


BugLy官网: https://bugly.qq.com/
暂时已经支持了BugLy更新以及BugLy日志提交
支持了自定义弹出框:
这里面有不少坑需要后面去注意:
自定义的5个tag都是需要保留的,
自定义时需要后台策略选择自定义策略且由于随机选择策略所以要针对当前的策略进行选择
弹出框也需要自己去设定选中的背景 不然看不到

upgrade_dialog为项目的布局资源。
注意：因为要保持接口统一，需要用户在指定控件按照以下方式设置tag，否则会影响您的正常使用：(MainActivity 中有升级信息返回的详情数据)

特性图片：beta_upgrade_banner，如：android:tag="beta_upgrade_banner"

标题：beta_title，如：android:tag="beta_title"

升级信息：beta_upgrade_info 如： android:tag="beta_upgrade_info"

更新属性：beta_upgrade_feature 如： android:tag="beta_upgrade_feature"

取消按钮：beta_cancel_button 如：android:tag="beta_cancel_button"

确定按钮：beta_confirm_button 如：android:tag="beta_confirm_button"



BugLy Android 应用升级 SDK 高级配置请看:
https://bugly.qq.com/docs/user-guide/advance-features-android-beta/?v=20200622202242#1id



设置升级对话框生命周期回调接口:
Beta.upgradeDialogLifecycleListener = new UILifecycleListener<UpgradeInfo>() {
            @Override
            public void onCreate(Context context, View view, UpgradeInfo upgradeInfo) {
                Log.d(TAG, "onCreate");

            }

            @Override
            public void onStart(Context context, View view, UpgradeInfo upgradeInfo) {
                Log.d(TAG, "onStart");
            }

            @Override
            public void onResume(Context context, View view, UpgradeInfo upgradeInfo) {
                Log.d(TAG, "onResume");
                // 注：可通过这个回调方式获取布局的控件，如果设置了id，可通过findViewById方式获取，如果设置了tag，可以通过findViewWithTag，具体参考下面例子:

                // 通过id方式获取控件，并更改imageview图片
                ImageView imageView = (ImageView) view.findViewById(R.id.icon);
                imageView.setImageResource(R.mipmap.ic_launcher);

                // 通过tag方式获取控件，并更改布局内容
                TextView textView = (TextView) view.findViewWithTag("textview");
                textView.setText("my custom text");

                // 更多的操作：比如设置控件的点击事件
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), OtherActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onPause(Context context, View view, UpgradeInfo upgradeInfo) {
                Log.d(TAG, "onPause");
            }

            @Override
            public void onStop(Context context, View view, UpgradeInfo upgradeInfo) {
                Log.d(TAG, "onStop");
            }

            @Override
            public void onDestroy(Context context, View view, UpgradeInfo upgradeInfo) {
                Log.d(TAG, "onDestory");
            }

        };
