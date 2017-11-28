package cn.abtion.neuqercc.mine.activities;

import android.app.AlertDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.activities.ToolBarActivity;

/**
 * @author fhyPayaso
 * @since 2017/11/28 13:25
 * email fhyPayaso@qq.com
 */

public class HonorInformationActivity extends ToolBarActivity {
    @BindView(R.id.img_honor)
    ImageView imgHonor;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_honor_information;
    }

    @Override
    protected void initVariable() {

        setActivityTitle(getString(R.string.title_honor_information));
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void loadData() {

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @OnClick(R.id.img_honor)
    public void onImgHonorClicked() {

        showFullImg();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void showFullImg() {

        View view = View.inflate(this, R.layout.item_dialog_full_img, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);
        ImageView imageView = (ImageView) view.findViewById(R.id.img_full_dialog);
        imageView.setImageResource(R.drawable.bg_account_title);

        builder.show();

    }




}
