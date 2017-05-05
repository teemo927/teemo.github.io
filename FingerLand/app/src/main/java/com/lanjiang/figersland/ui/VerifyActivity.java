package com.lanjiang.figersland.ui;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lanjiang.figersland.BaseToolbarActivity;
import com.lanjiang.figersland.Constant;
import com.lanjiang.figersland.R;
import com.lanjiang.figersland.utils.CameraUtil;
import com.lanjiang.figersland.utils.Utils;
import com.lanjiang.figersland.widget.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;

/**
 * 企业认证页面
 */
public class VerifyActivity extends BaseToolbarActivity {


    private static final int PICK_AVATAR_REQUEST = 999;

    @BindView(R.id.iv_icon)
    CircleImageView ivIcon;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.et_tel)
    EditText etTel;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.activity_verify)
    LinearLayout activityVerify;
    @BindView(R.id.iv_user1)
    CircleImageView ivUser1;
    @BindView(R.id.iv_user2)
    CircleImageView ivUser2;
    @BindView(R.id.iv_user3)
    CircleImageView ivUser3;
    @BindView(R.id.iv_user4)
    CircleImageView ivUser4;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.tv_defined)
    TextView tvDefined;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @OnClick({R.id.tv_defined, R.id.btn_next, R.id.iv_icon,R.id.iv_user1, R.id.iv_user2, R.id.iv_user3, R.id.iv_user4})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.tv_defined:
                PermissionGen.with(VerifyActivity.this)
                        .addRequestCode(100)
                        .permissions(
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.CAMERA,
                                Manifest.permission.READ_PHONE_STATE,
                                Manifest.permission.RECORD_AUDIO,
                                Manifest.permission.ACCESS_COARSE_LOCATION,
                                Manifest.permission.ACCESS_FINE_LOCATION)
                        .request();
                break;
            case R.id.btn_next:
                startActivity(new Intent(mContext, VerityNextActivity.class));
                break;
            case R.id.iv_icon:
            case R.id.iv_user1:
            case R.id.iv_user2:
            case R.id.iv_user3:
            case R.id.iv_user4:
                chooseImg((CircleImageView) v);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);
        unbinder = ButterKnife.bind(this);

        initView();
    }

    @Override
    public void setActionBar() {
        setActivityTitle(R.string.personal_info);
    }

    protected void initView() {
        chooseImg(ivIcon);

    }

    private void chooseImg(CircleImageView v) {
        int press = getResources().getColor(R.color.color_main);
        int normal = getResources().getColor(R.color.translate);
        ivIcon.setBorderColor(normal);
        ivUser1.setBorderColor(normal);
        ivUser2.setBorderColor(normal);
        ivUser3.setBorderColor(normal);
        ivUser4.setBorderColor(normal);

        CircleImageView circleImageView = v;
        circleImageView.setBorderColor(press);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

//    When it succeeded in obtaining permission
    @PermissionSuccess(requestCode = 100)
    public void doSomething(){
        Toast.makeText(this, "Contact permission is granted", Toast.LENGTH_SHORT).show();
        Utils.takePhoto(VerifyActivity.this);
    }

//    When it failed in obtaining permission
    @PermissionFail(requestCode = 100)
    public void doFailSomething(){
        Toast.makeText(this, "Contact permission is not granted", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case Constant.CMD: {
                final String path = CameraUtil.getResultPhotoPath(this, data, Constant.PIC_DIR);

                ivIcon.setImageBitmap(CameraUtil.extractThumbNail(path, 1500, 1500, true));
                break;
            }
            default:
                break;
        }
    }

}
