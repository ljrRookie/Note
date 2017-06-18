package com.ljr.note.activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ljr.note.MainActivity;
import com.ljr.note.R;
import com.ljr.note.bean.Note;
import com.ljr.note.utils.GetDate;
import com.ljr.note.view.LinedEditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cc.trity.floatingactionbutton.FloatingActionButton;
import cc.trity.floatingactionbutton.FloatingActionsMenu;

public class AddNoteActivity extends Activity {

    @Bind(R.id.common_iv_back)
    ImageView mCommonIvBack;
    @Bind(R.id.common_tv_title)
    TextView mCommonTvTitle;
    @Bind(R.id.common_title_ll)
    LinearLayout mCommonTitleLl;
    @Bind(R.id.add_diary_tv_date)
    TextView mAddDiaryTvDate;
    @Bind(R.id.add_diary_et_title)
    EditText mAddDiaryEtTitle;
    @Bind(R.id.add_diary_et_content)
    LinedEditText mAddDiaryEtContent;
    @Bind(R.id.save)
    FloatingActionButton mSave;
    @Bind(R.id.goback)
    FloatingActionButton mGoback;
    @Bind(R.id.btn_menus)
    FloatingActionsMenu mBtnMenus;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, AddNoteActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        ButterKnife.bind(this);
        initTitle();
    }

    private void initTitle() {
        mCommonTvTitle.setText("添加日记");
        mAddDiaryTvDate.setText("今天，" + GetDate.getDate());
        mAddDiaryEtContent.setText("");
        mAddDiaryEtTitle.setText("");
    }

    @OnClick({R.id.common_iv_back, R.id.save, R.id.goback})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.common_iv_back:
                MainActivity.startActivity(this);
                finish();
                break;
            case R.id.save:
                final String dateBack = GetDate.getDate().toString();
                final String titleBack = mAddDiaryEtTitle.getText().toString();
                final String contentBack = mAddDiaryEtContent.getText().toString();
                if(!titleBack.isEmpty()||!contentBack.isEmpty()){
                    Note note = new Note();
                    note.setDate(dateBack);
                    note.setTitle(titleBack);
                    note.setContent(contentBack);
                    note.save();
                    MainActivity.startActivity(this);
                    finish();
                }else{
                    Toast.makeText(this, "输入的信息不能为空！！", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.goback:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("退出编辑日记")
                        .setMessage("是否取消保存日记？")
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                MainActivity.startActivity(AddNoteActivity.this);
                                finish();
                            }
                        }).setCancelable(true)
                        .show();
                break;

        }
    }
}
