package cn.abtion.neuqercc.mine.activities;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.activities.ToolBarActivity;
import cn.abtion.neuqercc.common.Config;
import cn.abtion.neuqercc.mine.adapters.GridHonorAdapter;
import cn.abtion.neuqercc.mine.models.HonorCertificateModel;
import cn.abtion.neuqercc.utils.RegexUtil;
import cn.abtion.neuqercc.utils.ToastUtil;
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
    public static final int FLAG_CORRECT = 0;
    public static final int FLAG_LACK_ERROR = 1;
    public static final int FLAG_PHONE_ERROR = 2;
    public static int flagNameEye = 1;
    public static int flagPhoneEye = 1;


    /**
     * 动态申请权限
     */
    private static final String[] PERMISSION_EXTERNAL_STORAGE = new String[]{ Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
    private static final int REQUEST_EXTERNAL_STORAGE = 100;
    public final int TAKE_PHOTO_FLAG = 1;
    public final int SET_IMG_FLAG = 100;
    private boolean flagUpLoad =false;


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
    @BindView(R.id.edit_nick_name)
    EditText editNickName;
    @BindView(R.id.edit_real_name)
    EditText editRealName;
    @BindView(R.id.edit_phone_number)
    EditText editPhoneNumber;
    @BindView(R.id.edit_update_profession)
    EditText editUpdateProfession;
    @BindView(R.id.edit_update_stu_id)
    EditText editUpdateStuId;

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


    protected void initTitle() {

        setActivityTitle(getString(R.string.title_update_information));
        setTextOver(getString(R.string.title_over));

        TextView txtTitleOver = (TextView) getToolbar().findViewById(R.id.txt_toolbar_over);

        txtTitleOver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (isDataTrue()) {

                    case FLAG_LACK_ERROR:
                        ToastUtil.showToast(getString(R.string.toast_lack_information));
                        break;
                    case FLAG_PHONE_ERROR:
                        ToastUtil.showToast(getString(R.string.error_phone_number_illegal));
                        break;
                    default:
                        ToastUtil.showToast(getString(R.string.toast_edit_successful));
                        finish();
                        break;

                }

            }
        });
    }


    /**
     * 初始化荣誉墙
     */
    public void initGrid() {


        HonorGridView gridView = (HonorGridView) findViewById(R.id.mine_grid_honor);
        gridHonorAdapter = new GridHonorAdapter(this, honorCertificateModelList,true);
        gridView.setAdapter(gridHonorAdapter);

        //如果点击添加图片部分
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == parent.getChildCount() - 1) {

                    initHonorDialog();
                    addDatas();
                } else  {
                    Intent intent = new Intent(UpdateInformationActivity.this, HonorUpdateActivity.class);
                    startActivity(intent);
                }
                isShowDelete = false;
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
                //返回true只处理长按事件
                return true;
            }
        });
    }

    /**
     * 荣誉墙添加item
     */
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


    /**
     * 控制姓名显示
     */
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

    /**
     * 控制手机号码显示
     */
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


    /**
     * 初始化添加荣誉底部弹窗
     */
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


    /**
     * 初始化更换头像底部弹窗
     */
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
                startActivityForResult(takeIntent, TAKE_PHOTO_FLAG);


            }
        });


        btnFromAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (dialogAddHonor.isShowing()) {
                    dialogAddHonor.dismiss();
                }

                Intent intent = new Intent(Intent. ACTION_PICK,
                        android.provider.MediaStore.Images.Media. EXTERNAL_CONTENT_URI);
                intent.setType( "image/*");
                startActivityForResult(intent, SET_IMG_FLAG);

            }
        });


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ( dialogAddHonor.isShowing()) {
                    dialogAddHonor.dismiss();
                }
            }
        });
    }

    /**
     * 更换头像点击事件
     */
    @OnClick(R.id.mine_update_avatar)
    public void onImgUpdateAvatarClicked() {

        initAvatarDialog();
    }


    /**
     * 取消荣誉墙删除按钮点击事件
     */
    @OnClick(R.id.rlayout_mine_wrap)
    public void onViewClicked() {

        if (isShowDelete) {

            isShowDelete = false;
            gridHonorAdapter.setIsShowDelete(isShowDelete);
        } else {
            gridHonorAdapter.setIsShowDelete(isShowDelete);
        }
    }


    /**
     * 验证用户输入是否正确
     *
     * @return 正确为true
     */
    private int isDataTrue() {
        int flag = FLAG_CORRECT;
        if (editNickName.getText().toString().trim().equals(Config.EMPTY_FIELD)) {

            flag = FLAG_LACK_ERROR;
        } else if (editRealName.getText().toString().trim().equals(Config.EMPTY_FIELD)) {

            flag = FLAG_LACK_ERROR;
        } else if (editPhoneNumber.getText().toString().trim().equals(Config.EMPTY_FIELD)) {

            flag = FLAG_LACK_ERROR;
        } else if (editUpdateProfession.getText().toString().trim().equals(Config.EMPTY_FIELD)) {

            flag = FLAG_LACK_ERROR;
        } else if (editUpdateStuId.getText().toString().trim().equals(Config.EMPTY_FIELD)) {

            flag = FLAG_LACK_ERROR;
        } else if (!checkBoxBoy.isChecked()&&!checkBoxGirl.isChecked()) {

            flag = FLAG_LACK_ERROR;
        } else if (!flagUpLoad){

            flag = FLAG_LACK_ERROR;
        }else if(!RegexUtil.checkMobile(editPhoneNumber.getText().toString().trim())) {

            flag = FLAG_PHONE_ERROR;
        }
        return flag;
    }


    /**
     * 动态申请权限
     * @param activity
     */
    private void verifyStoragePermissions(Activity activity) {
        int permissionWrite = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionWrite != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, PERMISSION_EXTERNAL_STORAGE,
                    REQUEST_EXTERNAL_STORAGE);
        }
    }


    /**
     * 返回选择的图片
     * @param requestCode
     * @param resultCode
     * @param data
     */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SET_IMG_FLAG && resultCode == RESULT_OK && null != data) {

            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null , null);

            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);

            String picturePath = cursor.getString(columnIndex);

            cursor.close();

            imgUpdateAvatar.setImageBitmap(BitmapFactory.decodeFile(picturePath));

            flagUpLoad = true;

        } else if(requestCode == TAKE_PHOTO_FLAG && resultCode == RESULT_OK && null != data) {

            Bundle bundle = data.getExtras();
            Bitmap bitmap = (Bitmap) bundle.get("data");
            imgUpdateAvatar.setImageBitmap(bitmap);

            flagUpLoad = true;
        }

    }


}
