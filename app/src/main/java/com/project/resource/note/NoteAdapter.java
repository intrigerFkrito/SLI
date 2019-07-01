package com.project.resource.note;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.project.internet.NewsWebView;
import com.project.menu.AddNotes;
import com.project.menu.Note;
import com.project.resource.englishnew.EnglishNewAdapter;
import com.project.resource.englishnew.EnglishNews;
import com.project.sli.MainActivity;
import com.project.sli.R;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * @author
 * @date 2019/06/03
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    private List<Notes> noteList;
    private Context context;
    private int tag = 0;

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView title;
        TextView content;
        LinearLayout menu;
        LinearLayout king;
        TextView delete;
        View line;
        private ImageView btBlue;
        private ImageView btGreen;
        private ImageView btRed;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            title = view.findViewById(R.id.menu_note_title);
            content = view.findViewById(R.id.menu_note_content);
            menu = view.findViewById(R.id.menu_note_menu);
            delete = view.findViewById(R.id.menu_note_delete);
            line = view.findViewById(R.id.divider2);
            btBlue = view.findViewById(R.id.menu_note_blue);
            btGreen = view.findViewById(R.id.menu_note_green);
            btRed = view.findViewById(R.id.menu_note_red);
            king = view.findViewById(R.id.menu_note_king);
        }
    }

    public NoteAdapter(List<Notes> noteList) {
        this.noteList = noteList;
    }

    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if (context == null) {
            context = viewGroup.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.menu_note_recycle_item, viewGroup, false);

        return new NoteAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final NoteAdapter.ViewHolder holder, final int position) {
        final Notes notes = noteList.get(position);
        holder.title.setText(notes.getHead());
        holder.content.setText(notes.getBody());
        switch (notes.getPriority()){
            case 4:
                holder.king.setBackgroundResource(R.drawable.menu_note_red);
                break;
            case 3:
                holder.king.setBackgroundResource(R.drawable.menu_note_yellow);
                break;
            case 2:
                holder.king.setBackgroundResource(R.drawable.menu_note_blue);
                break;
            default:
                break;
        }


        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                tag = 1;
                int position = holder.getAdapterPosition();
                final Notes kickNote = noteList.get(position);
                holder.menu.setVisibility(View.VISIBLE);
                holder.line.setVisibility(View.VISIBLE);


                holder.delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        kickNote.delete();
                        Note.note.initView();
                        holder.menu.setVisibility(View.GONE);
                        holder.line.setVisibility(View.GONE);
                    }
                });

                holder.btRed.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        kickNote.setPriority(4);
                        kickNote.save();
                        Note.note.initView();
                    }
                });

                holder.btGreen.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        kickNote.setPriority(3);
                        kickNote.save();
                        Note.note.initView();
                    }
                });

                holder.btBlue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        kickNote.setPriority(2);
                        kickNote.save();
                        Note.note.initView();
                    }
                });

                return true;
            }
        });
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tag == 1){
                    tag = 0;
                    holder.line.setVisibility(View.GONE);
                    holder.menu.setVisibility(View.GONE);
                }
                else{
                    int position = holder.getAdapterPosition();
                    Intent intent = new Intent(Note.note, AddNotes.class);
                    intent.putExtra("position",position);
                    context.startActivity(intent);
                    Note.note.finish();
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }
}
