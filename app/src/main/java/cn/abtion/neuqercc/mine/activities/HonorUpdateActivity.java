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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.OnClick;
import cn.abtion.neuqercc.R;
import cn.abtion.neuqercc.base.activities.ToolBarActivity;
import cn.abtion.neuqercc.common.Config;
import cn.abtion.neuqercc.utils.ToastUtil;

/**
 * @author fhyPayaso
 * @since 2017/11/20 19:57
 * email fhyPayaso@qq.com
 */

public class HonorUpdateActivity extends ToolBarActivity {




    /**
     * 动态申请权限
     */
    private static final String[] PERMISSION_EXTERNAL_STORAGE = new String[]{ Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
    private static final int REQUEST_EXTERNAL_STORAGE = 100;
    public final int TAKE_PHOTO_FLAG = 1;
    public final int SET_IMG_FLAG = 100;
    private boolean flagUpLoad =false;

    Button btnTakePhoto;
    Button btnFromAlbum;
    Button btnCancel;

    @BindView(R.id.img_add_honor)
    ImageView imgAddHonor;
    @BindView(R.id.btn_honor_confirm_edit)
    Button btnHonorConfirmEdit;
    @BindView(R.id.edit_event_name)
    EditText editEventName;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_honor_update;
    }

    @Override
    protected void initVariable() {


        setActivityTitle(getString(R.string.title_certificate_editing));
        verifyStoragePermissions(HonorUpdateActivity.this);
    }

    @Override
    protected void initView() {



    }

    @Override
    protected void loadData() {

    }

    @OnClick(R.id.img_add_honor)
    public void onImgAddHonorClicked() {

        showHonorDialog();
    }


    public void showHonorDialog() {


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

            imgAddHonor.setImageBitmap(BitmapFactory.decodeFile(picturePath));

            flagUpLoad = true;

        } else if(requestCode == TAKE_PHOTO_FLAG && resultCode == RESULT_OK && null != data) {

            Bundle bundle = data.getExtras();
            Bitmap bitmap = (Bitmap) bundle.get("data");
            imgAddHonor.setImageBitmap(bitmap);

            flagUpLoad = true;
        }

    }


    /**
     * 确认修改点击事件
     */
    @OnClick(R.id.btn_honor_confirm_edit)
    public void onBtnConfirmClicked() {

        if(isDataTrue()) {
            ToastUtil.showToast(getString(R.string.toast_edit_successful));
            finish();
        } else {
            ToastUtil.showToast(getString(R.string.toast_lack_information));
        }

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
     * 判断信息是否完整
     * @return
     */
    private boolean isDataTrue() {
        boolean flag = true;

        if (editEventName.getText().toString().trim().equals(Config.EMPTY_FIELD)) {
            flag=false;
        } else if(!flagUpLoad) {
            flag=false;
        }

        return flag;
    }

}
