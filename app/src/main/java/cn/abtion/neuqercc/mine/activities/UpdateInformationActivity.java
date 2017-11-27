package cn.abtion.neuqercc.mine.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.activities.ToolBarActivity;
import cn.abtion.neuqercc.mine.adapters.GridHonorAdapter;
import cn.abtion.neuqercc.mine.models.HonorCertificateModel;
import cn.abtion.neuqercc.widget.HonorGridView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author fhyPayaso
 * @since 2017/11/15 21:30
 * email fhyPayaso@qq.com
 */

public class UpdateInformationActivity extends ToolBarActivity {

    public static final int FLAG_EYE_OPEN = 1;
    public static final int FLAG_EYE_CLOSE = 0;

    public static int flagNameEye = 1;
    public static int flagPhoneEye = 1;

    Button btnAddHonor;
    Button btnTakePhoto;
    Button btnFromAlbum;
    Button btnCancel;

    @BindView(R.id.img_mine_name_eye)
    ImageView imgNameEye;
    @BindView(R.id.img_mine_phone_eye)
    ImageView imgPhoneEye;
    @BindView(R.id.cb_edit_girl)
    CheckBox checkBoxGirl;
    @BindView(R.id.cb_edit_boy)
    CheckBox checkBoxBoy;
    @BindView(R.id.mine_update_avatar)
    CircleImageView imgUpdateAvatar;
    @BindView(R.id.rlayout_mine_wrap)
    RelativeLayout rlayoutMineWrap;

    private GridHonorAdapter gridHonorAdapter;
    private boolean isShowDelete;
    private List<HonorCertificateModel> honorCertificateModelList = new ArrayList<HonorCertificateModel>();


    @Override
    protected int getLayoutId() {
        return R.layout.activity_update_imformation;
    }

    @Override
    protected void initVariable() {


    }

    @Override
    protected void initView() {

        initCheckbox();
        initGrid();
        initTitle();

    }

    @Override
    protected void loadData() {

    }


    protected  void initTitle() {

        setActivityTitle(getString(R.string.title_update_information));
        setTextOver(getString(R.string.title_over));

        TextView txtTitleOver=(TextView)getToolbar().findViewById(R.id.txt_toolbar_over);

        txtTitleOver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    public void initGrid() {


        HonorGridView gridView = (HonorGridView) findViewById(R.id.mine_grid_honor);
        gridHonorAdapter = new GridHonorAdapter(this, honorCertificateModelList);
        gridView.setAdapter(gridHonorAdapter);

        //如果点击添加图片部分
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == parent.getChildCount() - 1) {

                    initHonorDialog();
                    addDatas();
                }
                isShowDelete=false;
            }
        });

        //如果长按图片
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {


            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                if (position < honorCertificateModelList.size()) {

                    if (isShowDelete) {
                        isShowDelete = false;
                        gridHonorAdapter.setIsShowDelete(isShowDelete);
                    } else {
                        isShowDelete = true;
                        gridHonorAdapter.setIsShowDelete(isShowDelete);
                    }
                }
                return false;
            }
        });


    }

    public void addDatas() {

        HonorCertificateModel honorCertificateAdd = new HonorCertificateModel(R.drawable.bg_account_title);
        honorCertificateModelList.add(honorCertificateAdd);
        gridHonorAdapter.notifyDataSetChanged();

    }


    /**
     * 初始化CheckBox
     */
    public void initCheckbox() {

        checkBoxGirl.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && checkBoxBoy.isChecked()) {
                    checkBoxBoy.setChecked(false);
                }
            }
        });

        checkBoxBoy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked && checkBoxGirl.isChecked()) {
                    checkBoxGirl.setChecked(false);
                }
            }
        });

    }


    @OnClick(R.id.img_mine_name_eye)
    public void onImgNameEyeClicked() {

        if (flagNameEye == FLAG_EYE_OPEN) {
            imgNameEye.setSelected(true);
            flagNameEye = FLAG_EYE_CLOSE;
        } else {
            imgNameEye.setSelected(false);
            flagNameEye = FLAG_EYE_OPEN;
        }

    }

    @OnClick(R.id.img_mine_phone_eye)
    public void onImgPhoneEyeClicked() {

        if (flagPhoneEye == FLAG_EYE_OPEN) {
            imgPhoneEye.setSelected(true);
            flagPhoneEye = FLAG_EYE_CLOSE;
        } else {
            imgPhoneEye.setSelected(false);
            flagPhoneEye = FLAG_EYE_OPEN;
        }
    }




    public void initHonorDialog() {


        View view = LayoutInflater.from(this).inflate(R.layout.dialog_mine_honor, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.dialog_bottom);
        builder.setView(view);
        final AlertDialog dialogAddHonor = builder.create();
        Window window = dialogAddHonor.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
        dialogAddHonor.show();

        btnAddHonor = (Button) view.findViewById(R.id.btn_add_honor);
        btnCancel = (Button) view.findViewById(R.id.btn_cancel);


        btnAddHonor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (dialogAddHonor.isShowing()) {
                    dialogAddHonor.dismiss();
                }
                Intent intent = new Intent(UpdateInformationActivity.this, HonorUpdateActivity.class);
                startActivity(intent);

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (dialogAddHonor != null && dialogAddHonor.isShowing()) {
                    dialogAddHonor.dismiss();
                }
            }
        });

    }





    public void initAvatarDialog() {


        View view = LayoutInflater.from(this).inflate(R.layout.dialog_mine_avatar, null);


        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.dialog_bottom);
        builder.setView(view);
        final AlertDialog dialogAddHonor = builder.create();
        Window window = dialogAddHonor.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
        dialogAddHonor.show();

        btnTakePhoto = (Button) view.findViewById(R.id.btn_take_photo);
        btnFromAlbum = (Button) view.findViewById(R.id.btn_from_album);
        btnCancel = (Button) view.findViewById(R.id.btn_cancel);

        btnTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (dialogAddHonor.isShowing()) {
                    dialogAddHonor.dismiss();
                }

                Intent takeIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takeIntent, 1);


            }
        });


        btnFromAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (dialogAddHonor.isShowing()) {
                    dialogAddHonor.dismiss();
                }

                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 100);

            }
        });


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (dialogAddHonor != null && dialogAddHonor.isShowing()) {
                    dialogAddHonor.dismiss();
                }
            }
        });
    }

    @OnClick(R.id.mine_update_avatar)
    public void onImgUpdateAvatarClicked() {

        initAvatarDialog();
    }



    @OnClick(R.id.rlayout_mine_wrap)
    public void onViewClicked() {

        if(isShowDelete) {

            isShowDelete=false;
            gridHonorAdapter.setIsShowDelete(isShowDelete);
        }else {
            gridHonorAdapter.setIsShowDelete(isShowDelete);
        }
    }
}
