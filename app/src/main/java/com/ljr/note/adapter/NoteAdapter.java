package com.ljr.note.adapter;

import android.content.Context;
import android.support.v4.app.FragmentManagerNonConfig;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ljr.note.MainActivity;
import com.ljr.note.R;
import com.ljr.note.activity.UpdateNoteActivity;
import com.ljr.note.bean.Note;
import com.ljr.note.utils.GetDate;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by LinJiaRong on 2017/6/16.
 * TODO：
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<Note> mNoteDatas;

    public NoteAdapter(MainActivity context, List<Note> noteBeanList) {
        mContext = context;
        this.mLayoutInflater = LayoutInflater.from(mContext);
        this.mNoteDatas = noteBeanList;
    }


    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NoteViewHolder(mLayoutInflater.inflate(R.layout.item_diary, parent, false));
    }

    @Override
    public void onBindViewHolder(final NoteViewHolder holder, final int position) {
        String dateSystem = GetDate.getDate().toString();
        if(mNoteDatas.get(position).getDate().equals(dateSystem)){
            holder.mMainIvCircle.setImageResource(R.drawable.circle_orange);
        }
        holder.mMainTvDate.setText(mNoteDatas.get(position).getDate());
        holder.mMainTvTitle.setText(mNoteDatas.get(position).getTitle());
        holder.mMainTvContent.setText("       " + mNoteDatas.get(position).getContent());
        holder.mMainIvEdit.setVisibility(View.INVISIBLE);
        holder.mItemLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.mMainIvEdit.getVisibility() == View.INVISIBLE) {
                    holder.mMainIvEdit.setVisibility(View.VISIBLE);
                }else {
                    holder.mMainIvEdit.setVisibility(View.INVISIBLE);

                }
            }
        });

        holder.mMainIvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateNoteActivity.startActivity(mContext,mNoteDatas.get(position).getDate(),mNoteDatas.get(position).getTitle(),
                        "       " + mNoteDatas.get(position).getContent());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNoteDatas.size();
    }

    public static class NoteViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.main_iv_circle)
        ImageView mMainIvCircle;
        @Bind(R.id.main_tv_date)
        TextView mMainTvDate;
        @Bind(R.id.main_tv_title)
        TextView mMainTvTitle;
        @Bind(R.id.item_ll_title)
        LinearLayout mItemLlTitle;
        @Bind(R.id.main_tv_content)
        TextView mMainTvContent;
        @Bind(R.id.main_iv_edit)
        ImageView mMainIvEdit;
        @Bind(R.id.item_rl_edit)
        RelativeLayout mItemRlEdit;
        @Bind(R.id.item_ll_control)
        LinearLayout mItemLlControl;
        @Bind(R.id.item_ll)
        LinearLayout mItemLl;

        public NoteViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
