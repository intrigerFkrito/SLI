package com.project.menu;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.project.resource.note.Notes;
import com.project.sli.R;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * @author
 * @date 2019/06/03
 */
public class AddNotes extends AppCompatActivity {

    private EditText head;
    private EditText body;
    private ImageView ok;

    private int position;
    private String defaultHead;
    private String defaultBody;
    private int defaultPriority = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);
        Intent intent = getIntent();
        position = intent.getIntExtra("position",-1);
        initViews();
        if (position != -1){
            showContent();
        }
    }

    private void initViews(){
        head = findViewById(R.id.menu_note_add_head);
        body = findViewById(R.id.menu_note_add_body);
        ok = findViewById(R.id.menu_note_add_ok);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String headText = head.getText().toString();
                String bodyText = body.getText().toString();
                Notes notes  = new Notes(headText,bodyText);
                notes.setPriority(defaultPriority);
                if (position != -1){
                    try {
                        ContentValues values = new ContentValues();
                        values.put("head",headText);
                        values.put("body",bodyText);
                        values.put("priority",defaultPriority);
                        DataSupport.updateAll(Notes.class,values,"head = ?",defaultHead);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                else {
                    notes.save();
                }
                Intent intent = new Intent(AddNotes.this,Note.class);
                startActivity(intent);
                finish();
            }
        });

        Toolbar toolbar = findViewById(R.id.menu_note_add_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    private void showContent(){
        Notes note = Note.note.notesList.get(position);
        defaultHead = note.getHead();
        defaultBody = note.getBody();
        defaultPriority = note.getPriority();
        head.setText(defaultHead);
        body.setText(defaultBody);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Intent intent = new Intent(AddNotes.this,Note.class);
        startActivity(intent);
        finish();
        return super.onKeyDown(keyCode, event);
    }
}

