package com.project.menu;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.project.resource.englishnew.EnglishNewAdapter;
import com.project.resource.note.NoteAdapter;
import com.project.resource.note.Notes;
import com.project.sli.MainActivity;
import com.project.sli.R;

import org.litepal.crud.DataSupport;

import java.security.cert.LDAPCertStoreParameters;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dmt
 * @date 2019/06/02
 */
public class Note extends AppCompatActivity {

    private NoteAdapter adapter;
    public static Note note;
    public List<Notes> notesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        note = this;

        initViews();

        initView();
    }

    private void initViews(){
        //设置toolbar
        Toolbar toolbar = findViewById(R.id.toolbar_menu_note);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //悬浮按钮
        FloatingActionButton add = findViewById(R.id.menu_note_add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Note.this,AddNotes.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public void initView(){
        notesList.clear();
        notesList = DataSupport.findAll(Notes.class);
        for(Notes note:notesList){
            Log.d("MX",note.getHead());
            Log.d("MX",note.getBody());
        }
        RecyclerView recyclerView = findViewById(R.id.menu_note_recycle);
        GridLayoutManager layoutManager = new GridLayoutManager(MainActivity.mainActivity,2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new NoteAdapter(notesList);
        recyclerView.setAdapter(adapter);
    }
}
