package com.chsj.smallhabit;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.chsj.smallhabit.utils.EventUtils;

public class UserFeedBackActivity extends Activity implements View.OnClickListener {

    @Bind(R.id.activity_user_feed_back_back)
    ImageView Back;
    @Bind(R.id.activity_user_feed_back_ok)
    Button Ok;
    @Bind(R.id.activity_user_feed_back_yijian)
    EditText Yijian;
    @Bind(R.id.activity_user_feed_back_address)
    EditText Address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_user_feed_back);
        ButterKnife.bind(this);
        EventUtils.setEvent(this, Back, Ok);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_user_feed_back_back:
                finish();
                break;
            case R.id.activity_user_feed_back_ok:
                String trim = Yijian.getText().toString().trim();
                if (trim != null && trim.length() > 0)
                    Toast.makeText(UserFeedBackActivity.this, "成功提交意见", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(UserFeedBackActivity.this, "意见不能为空", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
