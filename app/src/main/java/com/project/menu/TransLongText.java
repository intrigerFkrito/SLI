package com.project.menu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.internet.TranslateService;
import com.project.sli.R;

/**
 * @author amt
 * @date 2019/05/30
 * 长文本翻译
 */
public class TransLongText extends AppCompatActivity {

    public static TransLongText transLongText;
    private final String ZHTOEN = "zh2en";
    private final String ENTOZH = "en2zh";
    private int mode = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trans_long_text);
        transLongText = this;
        initView();
    }

    private void initView() {
        final EditText search = findViewById(R.id.menu_trans_text);
        final ImageView go = findViewById(R.id.menu_trans_go);
        final ImageView clear = findViewById(R.id.menu_trans_clear);
        final TextView ansText = findViewById(R.id.menu_trans_answer);
        ImageView change = findViewById(R.id.menu_trans_change);
        final TextView from = findViewById(R.id.menu_trans_from);
        final TextView to = findViewById(R.id.menu_trans_to);

        search.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    clear.setVisibility(View.VISIBLE);
                }
                else{
                    clear.setVisibility(View.GONE);
                }
            }
        });
        //clear按键
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search.setText("");
                ansText.setText("");
            }
        });
        //监听编辑框变化
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String transText = search.getText().toString();
                if (transText.length() == 0) {
                    go.setVisibility(View.GONE);
                }
                else{
                    go.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputMethodManager =(InputMethodManager) getSystemService(TransLongText.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(),0);
                String text = search.getText().toString();
                translate(text);
            }
        });

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //增加控件流畅度
                Animation aniFrom = AnimationUtils.loadAnimation(TransLongText.this,R.anim.munu_trans_from);
                Animation aniTo = AnimationUtils.loadAnimation(TransLongText.this,R.anim.munu_trans_to);
                from.startAnimation(aniFrom);
                to.startAnimation(aniTo);
                if (0 == mode) {
                    mode = 1;
                    from.setText("English");
                    to.setText("Chinese");
                }
                else{
                    mode = 0;
                    from.setText("Chinese");
                    to.setText("English");
                }
                String text = search.getText().toString();
                if (text.length() > 0) {
                    translate(text);
                }
            }
        });
    }

    private void translate(String text){
        if (0 == mode) {
            TranslateService translateService = new TranslateService(text, ZHTOEN, 1);
            translateService.getResponse();
        }
        else{
            TranslateService translateService = new TranslateService(text, ENTOZH, 1);
            translateService.getResponse();
        }
    }
}
