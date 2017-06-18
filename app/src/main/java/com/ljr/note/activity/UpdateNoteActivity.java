package com.ljr.note.activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ljr.note.MainActivity;
import com.ljr.note.R;
import com.ljr.note.bean.Note;
import com.ljr.note.utils.GetDate;
import com.ljr.note.view.LinedEditText;

import org.litepal.crud.DataSupport;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cc.trity.floatingactionbutton.FloatingActionButton;
import cc.trity.floatingactionbutton.FloatingActionsMenu;

public class UpdateNoteActivity extends Activity {

    @Bind(R.id.common_iv_back)
    ImageView mCommonIvBack;
    @Bind(R.id.common_tv_title)
    TextView mCommonTvTitle;
    @Bind(R.id.common_iv_test)
    ImageView mCommonIvTest;
    @Bind(R.id.common_title_ll)
    LinearLayout mCommonTitleLl;
    @Bind(R.id.update_date)
    TextView mUpdateDate;
    @Bind(R.id.update_et_title)
    EditText mUpdateEtTitle;
    @Bind(R.id.update_et_content)
    LinedEditText mUpdateEtContent;
    @Bind(R.id.update_delete)
    FloatingActionButton mUpdateDelete;
    @Bind(R.id.update_save)
    FloatingActionButton mUpdateSave;
    @Bind(R.id.update_back)
    FloatingActionButton mUpdateBack;
    @Bind(R.id.btn_menu)
    FloatingActionsMenu mBtnMenu;
    private Intent mIntent;

    public static void startActivity(Context context,String date, String title, String content) {
        Intent intent = new Intent(context, UpdateNoteActivity.class);
        intent.putExtra("date",date);
        intent.putExtra("title", title);
        intent.putExtra("content", content);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_note);
        ButterKnife.bind(this);
        initTitle();
        initData();
    }

    private void initData() {
        mIntent = getIntent();
        mUpdateDate.setText( mIntent.getStringExtra("date"));
        mUpdateEtTitle.setText(mIntent.getStringExtra("title"));
        mUpdateEtContent.setText(mIntent.getStringExtra("content"));

    }

    private void initTitle() {
        mCommonTvTitle.setText("修改日记");
    }

    @OnClick({R.id.common_iv_back, R.id.update_delete, R.id.update_save, R.id.update_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.common_iv_back:
                finish();
                break;
            case R.id.update_delete:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("删除日记？")
                        .setMessage("确定哟啊删除日记吗？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DataSupport.deleteAll(Note.class,"title = ?",mIntent.getStringExtra("title"));
                                MainActivity.startActivity(UpdateNoteActivity.this);
                                finish();
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
                break;
            case R.id.update_save:
                Note note = new Note();
                note.setDate(mUpdateDate.getText().toString());
                note.setTitle(mUpdateEtTitle.getText().toString());
                note.setContent(mUpdateEtContent.getText().toString());
                note.updateAll("date = ? and title = ?", mIntent.getStringExtra("date"),mIntent.getStringExtra("title"));
                MainActivity.startActivity(this);
                finish();
                break;
            case R.id.update_back:
                finish();
                break;
        }
    }
}
