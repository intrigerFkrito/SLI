package com.project.internet;

import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.project.resource.englishnew.EnglishNewAdapter;
import com.project.resource.englishnew.EnglishNews;
import com.project.sli.MainActivity;
import com.project.sli.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.LogRecord;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author
 * @date 2019/05/30
 */
public class EnglishNew {
    private final int OK = 1;
    private final String URLHEAD = "https://newsapi.org/v2/top-headlines?";
    private final String KEY = "apiKey=722a9567956f4defad52adc8d4217eb7";
    private String country = "jp";
    private String source = "";
    String URL;
    Request request;
    OkHttpClient client = new OkHttpClient();

    public List<EnglishNews> englishNews = new ArrayList<>();
    private EnglishNewAdapter adapter;

    private View view;

    public EnglishNew (){
        initRequest();
    }

    public EnglishNew(String search){
        initRequest(search);
    }
    public void setCountry(String country){
        this.country = country;
        initRequest();
    }
    private void initRequest(){
        URL = URLHEAD + "country=" + country + "&"+ KEY;
        request = new Request.Builder()
                .url(URL)
                .build();
    }

    private void initRequest(String search){
        URL = URLHEAD + "q=" + search + "&"+ KEY;
        request = new Request.Builder()
                .url(URL)
                .build();
    }

    public void getResponse(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = null;
                    response = client.newCall(request).execute();

                    if (response.isSuccessful()) {
                        String s = response.body().string();
                        Log.i("WY","打印POST响应的数据：" + s);
                        dealJson(s);
                    } else {
                        throw new IOException("Unexpected code " + response);
                    }
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        };
        ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
        singleThreadPool.execute(runnable);
    }

    private void dealJson(String responseData){
        Message msg = new Message();
        englishNews.clear();
        try{
            JSONObject obj = new JSONObject(responseData);
            int dataLength = obj.optInt("totalResults");
            JSONArray newArray = new JSONArray(obj.getString("articles"));
            for(int i = 0;i < newArray.length(); i++){
                JSONObject oneNew = newArray.getJSONObject(i);
                String title = oneNew.getString("title");
                String url = oneNew.getString("url");
                String imageUrl = oneNew.getString("urlToImage");
                String publishedAt = oneNew.getString("publishedAt");
                String author = oneNew.getString("author");
                JSONObject src = new JSONObject(oneNew.getString("source"));
                String source = src.getString("name");

                EnglishNews oneEngNew = new EnglishNews(title, imageUrl, url, publishedAt, source, author);

                englishNews.add(oneEngNew);
            }
            msg.what = OK;
            handler.sendMessage(msg);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case OK:
                    RecyclerView recyclerView = MainActivity.mainActivity.findViewById(R.id.main_recycle);
                    GridLayoutManager layoutManager = new GridLayoutManager(MainActivity.mainActivity,1);
                    recyclerView.setLayoutManager(layoutManager);
                    adapter = new EnglishNewAdapter(englishNews);
                    recyclerView.setAdapter(adapter);
                    MainActivity.mainActivity.swipeRefresh.setRefreshing(false);
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };
}
