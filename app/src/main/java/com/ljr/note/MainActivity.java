package com.ljr.note;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ljr.note.activity.AddNoteActivity;
import com.ljr.note.adapter.NoteAdapter;
import com.ljr.note.bean.Note;
import com.ljr.note.utils.GetDate;


import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";
    @Bind(R.id.common_tv_title)
    TextView mCommonTvTitle;
    @Bind(R.id.common_iv_back)
    ImageView mCommonIvBack;
    @Bind(R.id.common_iv_test)
    ImageView mCommonIvTest;
    @Bind(R.id.common_title_ll)
    LinearLayout mCommonTitleLl;
    @Bind(R.id.main_iv_circle)
    ImageView mMainIvCircle;
    @Bind(R.id.main_tv_date)
    TextView mMainTvDate;
    @Bind(R.id.main_tv_content)
    TextView mMainTvContent;
    @Bind(R.id.item_ll_control)
    LinearLayout mItemLlControl;
    @Bind(R.id.item_first)
    LinearLayout mItemFirst;
    @Bind(R.id.main_rv_show_diary)
    RecyclerView mMainRvShowDiary;
    @Bind(R.id.main_ll_main)
    LinearLayout mMainLlMain;
    @Bind(R.id.main_fab_enter_edit)
    FloatingActionButton mMainFabEnterEdit;
    @Bind(R.id.main_fl_main)
    RelativeLayout mMainFlMain;
    private List<Note> mNoteBeanList;
    public static void startActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //创建数据库
        LitePal.getDatabase();
        getNoteBeanList();
        initTitle();
        initData();
    }

    private void initData() {
        mMainRvShowDiary.setLayoutManager(new LinearLayoutManager(this));
        mMainRvShowDiary.setAdapter(new NoteAdapter(this,mNoteBeanList));
    }

    private void initTitle() {
        mMainTvDate.setText("今天，" + GetDate.getDate());
        mCommonTvTitle.setText("日记");
        mCommonIvBack.setVisibility(View.INVISIBLE);
        mCommonIvTest.setVisibility(View.INVISIBLE);
    }

    private List<Note> getNoteBeanList() {
        mNoteBeanList = new ArrayList<>();
        List<Note> noteList = new ArrayList<>();
        //查询表中的第一行数据
        Note firstNote = DataSupport.findFirst(Note.class);
        String date = null;
        if (firstNote == null) {
date = "";
        } else {
            date = firstNote.getDate();
            Log.e(TAG, "getNoteBeanList: "+firstNote.toString() );
        }

        String dateSystem = GetDate.getDate().toString();
        Log.e(TAG, "getNoteBeanList:dateSystem: "+dateSystem);
        Log.e(TAG, "getNoteBeanList: date: "+date);
        if (dateSystem.equals(date)) {
            mMainLlMain.removeView(mItemFirst);
        }
        List<Note> all = DataSupport.findAll(Note.class);
        for (Note note : all) {
            mNoteBeanList.add(new Note(note.getDate(), note.getTitle(), note.getContent()));
            Log.e(TAG, "getNoteBeanList:mNoteBeanList: "+mNoteBeanList.toString() );
        }
        for (int i = mNoteBeanList.size() - 1; i >= 0; i--) {
            noteList.add(mNoteBeanList.get(i));
            Log.e(TAG, "getNoteBeanList:noteList: "+noteList.toString() );
        }
        mNoteBeanList = noteList;
        return mNoteBeanList;
    }

    @OnClick(R.id.main_fab_enter_edit)
    public void onClick() {
        AddNoteActivity.startActivity(this);

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
