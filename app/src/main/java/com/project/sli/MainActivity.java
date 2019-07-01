package com.project.sli;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.project.internet.EnglishNew;
import com.project.internet.TranslateService;
import com.project.menu.Note;
import com.project.menu.TransLongText;
import com.project.resource.country.Countries;
import com.project.resource.country.CountryAdapter;

import java.util.List;

/**
 *@author dmt
 * @date 2019/5/28
 */
public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    public static MainActivity mainActivity;
    public SwipeRefreshLayout swipeRefresh;
    public RecyclerView recycleCountry;
    public EnglishNew englishNew = new EnglishNew();
    public TextView check;
    private int tagCountryRecycleView = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //给非Activity类的Toast使用
        mainActivity = this;
        initViews();
    }


    @SuppressLint("ClickableViewAccessibility")
    private void initViews(){
        //默认关闭软键盘
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        Toolbar toolbar =  findViewById(R.id.toolbar_main);
        final EditText search = findViewById(R.id.main_search);
        Button menu = findViewById(R.id.main_menu);
        Button go = findViewById(R.id.main_go);
        SearchView newSearch = findViewById(R.id.main_new_search);
        check = findViewById(R.id.main_new_check);
        //侧滑菜单
        final NavigationView navigationView = findViewById(R.id.nav_view);
        //下拉刷新
        swipeRefresh = findViewById(R.id.main_refresh);


        swipeRefresh.setColorSchemeColors(getColor(R.color.colorPrimary));
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //轻微震动
                Vibrator vib = (Vibrator) MainActivity.this.getSystemService(Service.VIBRATOR_SERVICE);
                VibrationEffect vibrationEffect = VibrationEffect.createOneShot(70,20);
                if(vib.hasVibrator()){
                    vib.vibrate(vibrationEffect);
                }

                englishNew.getResponse();
            }
        });

        //单词搜索
        search.setCursorVisible(false);
        search.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                search.setCursorVisible(true);
                return false;
            }
        });

        //设置toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        drawerLayout = findViewById(R.id.draw_layout);

        //打开侧滑菜单
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        //侧滑菜单
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.text_translate:
                        Intent transActivity = new Intent(MainActivity.this, TransLongText.class);
                        startActivity(transActivity);
                        break;
                    case R.id.note:
                        Intent note = new Intent(MainActivity.this, Note.class);
                        startActivity(note);
                        break;
                    default:
                        break;
                }
                drawerLayout.closeDrawers();
                return true;
            }
        });

        //search按钮
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputMethodManager =(InputMethodManager) getSystemService(MainActivity.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(),0);
                String trans_text = search.getText().toString();
                String mode = "";
                if(trans_text.equals("")) {
                }
                else if (trans_text.length() >= 25 ) {
                    Toast.makeText(MainActivity.this,"文本过长，请在左侧滑动菜单选择长文本翻译以获得卓越翻译体验",Toast.LENGTH_LONG).show();
                }
                else{
                    if (trans_text.charAt(0) >= 122){
                        mode = "zh2en";
                    }
                    else {
                        mode = "en2zh";
                    }
                    TranslateService translateService = new TranslateService(trans_text, mode,0);
                    translateService.setView(v);
                    translateService.getResponse();
                }
            }
        });

        //新闻搜索
        newSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                searchNew(s);
                swipeRefresh.setRefreshing(true);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        //检索按键
        check.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                tagCountryRecycleView = 1;
                recycleCountry = findViewById(R.id.main_country_recycleview);
                recycleCountry.setVisibility(View.VISIBLE);
                LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
                layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recycleCountry.setLayoutManager(layoutManager);
                Countries countries = new Countries();
                CountryAdapter adapter = new CountryAdapter(countries.getCountries());
                recycleCountry.setAdapter(adapter);
                return true;
            }
        });
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.shake);
                check.startAnimation(animation);
                if (1 == tagCountryRecycleView) {
                    tagCountryRecycleView = 0;
                    recycleCountry.setVisibility(View.GONE);
                }
            }
        });
    }

    //搜索新闻方法
    private void searchNew(String text){
        EnglishNew englishNew = new EnglishNew(text);
        englishNew.getResponse();
    }

}
