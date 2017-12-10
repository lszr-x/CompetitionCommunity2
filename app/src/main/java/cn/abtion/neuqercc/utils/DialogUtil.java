package cn.abtion.neuqercc.utils;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

/**
 * @author lszr
 * @since 2017/12/1 下午7:58
 * email wsyglszr@gmail.com
 */


public final class DialogUtil {

    public class NativeDialog{

        private AlertDialog.Builder builder;

        /**
         * show
         * @return
         */
        public AlertDialog.Builder showNativeDialog(){
            builder.show();
            return builder;
        }


        /**
         * 初始化builder
         * @param context
         * @return
         */
        public NativeDialog singleInit(Context context){
            builder=new AlertDialog.Builder(context);
            return this;
        }

        /**
         * 设置取消按钮
         * @param negativeButton
         * @param onClickButton
         * @return
         */
        public NativeDialog setNegativeButton(String negativeButton,DialogInterface.OnClickListener onClickButton){
            builder.setNegativeButton(negativeButton,onClickButton);

            return this;
        }

        /**
         * 设置取消按钮
         * @param negativeButton
         * @return
         */
        public NativeDialog setNegativeButton(String negativeButton){
            builder.setNegativeButton(negativeButton,null);
            return this;
        }

        /**
         * 设置确定按钮
         * @param positiveButton
         * @param onClickButton
         * @return
         */
        public NativeDialog setPositiveButton(String positiveButton,DialogInterface.OnClickListener onClickButton){
            builder.setPositiveButton(positiveButton,onClickButton);

            return this;
        }

        /**
         * 设置单选框（原生）
         * @param singleItemString
         * @param onClickListener
         * @return
         */
        public NativeDialog setSingleChoice(final String singleItemString[],int position, DialogInterface.OnClickListener onClickListener){
            builder.setSingleChoiceItems(singleItemString, position, onClickListener);
            return this;
        }

        public NativeDialog setTitle(String title){
            builder.setTitle(title);
            return this;
        }
        public NativeDialog setMessage(String message){
            builder.setMessage(message);
            return this;
        }




    }


    static class CustomAlertDialog {

        private static AlertDialog.Builder builder;
        private static View view;

        public CustomAlertDialog showDialog(){
            builder.show();
            return this;
        }

        /**
         * 获取view
         * @return
         */
        public View getView(){
            return view;
        }
        /**
         * 初始化Dialog，设置view
         *
         * @param context
         * @param itemView
         * @return
         */
        public CustomAlertDialog initDialog(Context context, @LayoutRes int itemView, int styleId){
            builder=new AlertDialog.Builder(context,styleId);
            view=View.inflate(context,itemView,null);
            builder.setView(view);

            return this;
        }

        /**
         * 设置gravity
         * @param gravity
         * @return
         */
        public CustomAlertDialog setGravity(int gravity){
            Window window=builder.create().getWindow();
            window.setGravity(gravity);
            return this;
        }

        /**
         * 设置点击外部是否取消
         * @param bool
         * @return
         */
        public CustomAlertDialog setCanceledOntouchOutside(boolean bool){
            builder.create().setCanceledOnTouchOutside(bool);
            return this;
        }

        /**
         * 设置Dialog的宽高
         * @param height
         * @param width
         * @return
         */
        public CustomAlertDialog setHeightAndWidth(int height, int width){
            Window window=builder.create().getWindow();
            WindowManager.LayoutParams lp=window.getAttributes();
            lp.width=width;
            lp.height=height;
            window.setAttributes(lp);

            return this;
        }

        /**
         *
         * 设置padding
         *
         * @param left
         * @param right
         * @param top
         * @param bottom
         * @return
         */
        public CustomAlertDialog setPadding(int left, int right, int top, int bottom){
            Window window=builder.create().getWindow();
            window.getDecorView().setPadding(left,top,right,bottom);

            return this;
        }

        /**
         * 设置位置
         * @param x
         * @param y
         * @return
         */
        public CustomAlertDialog setPosition(int x, int y){
            Window window=builder.create().getWindow();
            WindowManager.LayoutParams lp=window.getAttributes();
            lp.x=x;
            lp.y=y;

            window.setAttributes(lp);


            return this;
        }

        public static AlertDialog.Builder getBuilder() {
            return builder;
        }

        /**
         * 设置背景透明度

         * @param transparency
         * @return
         */
        public CustomAlertDialog setTransparency(int transparency){
            Window window=builder.create().getWindow();
            WindowManager.LayoutParams lp=window.getAttributes();
            lp.alpha=transparency;
            window.setAttributes(lp);

            return this;
        }




    }
    static class CustomProgressDialog {

        private static ProgressDialog customProgressDialog;

        public CustomProgressDialog initDialog(Context context){
            customProgressDialog=new ProgressDialog(context);
            return this;
        }

        public CustomProgressDialog setContent(){


            return this;
        }

        /**
         * 显示Dialog
         * @param context
         * @return
         */
        public CustomProgressDialog showDialog(Context context){
            customProgressDialog.show();

            return this;
        }

        /**
         * 设置标题
         * @param title
         * @return
         */
        public CustomProgressDialog setDialogTitle(String title){
            customProgressDialog.setTitle(title);

            return  this;
        }

        /**
         * 设置外部点击是否可以取消
         * @param bool
         * @return
         */
        public CustomProgressDialog setCanceledOutside(boolean bool){
            customProgressDialog.setCanceledOnTouchOutside(bool);

            return this;
        }

        /**
         * 设置进度条的形式
         * @param style
         * @return
         */
        public CustomProgressDialog setProgressDialogStyle(int style){
            customProgressDialog.setProgressStyle(style);

            return this;
        }

        /**
         * 设置title图标
         * @param titleImage
         * @return
         */
        public CustomProgressDialog setIcon(int titleImage){
            customProgressDialog.setIcon(titleImage);

            return this;
        }

        /**
         * 设置内容
         * @param message
         * @return
         */
        public CustomProgressDialog setDialogMessage(String message){
            customProgressDialog.setMessage(message);

            return this;
        }

    }




}

