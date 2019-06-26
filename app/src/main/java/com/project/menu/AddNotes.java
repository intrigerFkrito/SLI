package com.project.menu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.project.resource.note.Notes;
import com.project.sli.R;

/**
 * @author
 * @date 2019/06/03
 */
public class AddNotes extends AppCompatActivity {

    private EditText head;
    private EditText body;
    private ImageView ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);

        initViews();
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
                notes.save();

                Intent intent = new Intent(AddNotes.this,Note.class);
                startActivity(intent);
                finish();
            }
        });

        Toolbar toolbar = findViewById(R.id.menu_note_add_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }
}
