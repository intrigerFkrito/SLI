package com.project.internet;

import android.os.Handler;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.project.menu.Note;
import com.project.menu.TransLongText;
import com.project.resource.note.Notes;
import com.project.sli.MainActivity;
import com.project.sli.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.LogRecord;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author
 * @date 2019/5/28
 */
public class TranslateService {

    public static final int UPDATE_TEXT = 1;
    public static final int UPDATE_LONG_TEXT = 2;
    public static final int ERROR = 0;
    OkHttpClient client = new OkHttpClient();
    String word = "";
    String mode = "";
    String transText = "";
    int tag = 0;
    View view;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    Request request;

    public TranslateService(String word, String mode, int tag){
        this.word = word;
        this.mode = mode;
        this.tag = tag;
        initRequest();
    }

    public void setView(View view) {
        this.view = view;
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_TEXT:
                    Snackbar.make(view,transText,Snackbar.LENGTH_LONG)
                            .setAction("加入单词本", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Notes notes = new Notes(word,transText);
                                    notes.save();
                                    Toast.makeText(MainActivity.mainActivity,"已添加",Toast.LENGTH_SHORT).show();
                                }
                            }).show();

                    break;
                case UPDATE_LONG_TEXT:
                    TextView textAnswer = TransLongText.transLongText.findViewById(R.id.menu_trans_answer);
                    textAnswer.setText(transText);
                    break;
                case ERROR:
                    Toast.makeText(MainActivity.mainActivity,"网络错误，请检查您的设备连接",Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };

    private void initRequest(){

        String fromData_str = "{\"cached\":\"true\"," +
                "\"dict\":\"true\"," +
                "\"media\":\"text\"," +
                "\"os_type\":\"web\"," +
                "\"replaced\":\"true\"," +
                "\"request_id\":\"web_fanyi\"," +
                "\"source\":"+"\""+word+"\","+
                "\"trans_type\":"+"\""+mode+"\"}";
        RequestBody fromData = RequestBody.create(JSON,fromData_str);

//        RequestBody fromData = new FormBody.Builder()
//                .add("cached", "true")
//                .add("dict", "true")
//                .add("media", "text")
//                .add("os_type","web")
//                .add("replaced", "true")
//                .add("request_id", "web_fanyi")
//                .add("source", word)
//                .add("trans_type", mode)
//                .build();

        request = new Request.Builder()
                .url("https://api.interpreter.caiyunai.com/v1/translator")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/5" +
                        "37.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36")
                .addHeader("X-Authorization", "token:cy4fgbil24jucmh8jfr5")
                .post(fromData)
                .build();
    }


    public void getResponse(){
        final Message msg = new Message();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = null;
                    response = client.newCall(request).execute();

                    if (response.isSuccessful()) {
                        try {
                            //这里坑的不得了，response.body().string()只能使用一次，在日志里打印这里会闪退
                            String s = response.body().string();
                            JSONObject obj = new JSONObject(s);
                            transText = obj.optString("target");
                            if (tag == 0) {
                                msg.what = UPDATE_TEXT;
                            }
                            else{
                                msg.what = UPDATE_LONG_TEXT;
                            }
                            handler.sendMessage(msg);
                            Log.i("WY","打印POST响应的数据：" + transText);

                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    } else {
                        msg.what = ERROR;
                        handler.sendMessage(msg);
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


    /**
     * 将Unicode中文转化为可见*/
    public static String unicodeDecode(String theString) {
        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len;) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed   \\uxxxx   encoding.");
                        }
                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';
                    else if (aChar == 'n')
                        aChar = '\n';
                    else if (aChar == 'f')
                        aChar = '\f';
                    outBuffer.append(aChar);
                }
            } else
                outBuffer.append(aChar);
        }
        return outBuffer.toString();
    }
}
