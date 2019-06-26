package com.project.sli;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import org.litepal.tablemanager.Connector;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * @date 2019/05/23
 * @author
 * 启动界面*/
public class LauncherActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Connector.getDatabase();
        //setContentView(R.layout.launcher);
        Runnable runnable  = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LauncherActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(0,0);
            }
        };
        Handler handler = new Handler();
        handler.postDelayed(runnable,2000);



        //在新线程中处理耗时任务，完成后启动主界面()

       // ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
        //singleThreadPool.execute(runnable);
    }
}
