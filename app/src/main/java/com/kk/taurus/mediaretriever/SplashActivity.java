package com.kk.taurus.mediaretriever;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.kk.taurus.uiframe.a.ToolsActivity;

import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;

/**
 * Created by Taurus on 2017/12/7.
 */

public class SplashActivity extends ToolsActivity {
    @Override
    protected void onLoadState() {

    }

    @Override
    protected void onInit(Bundle savedInstanceState) {
        super.onInit(savedInstanceState);
        fullScreen();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                PermissionGen.with(SplashActivity.this)
                        .addRequestCode(100)
                        .permissions(
                                Manifest.permission.READ_EXTERNAL_STORAGE
                        )
                        .request();
            }
        }, 2000);

    }



    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @PermissionSuccess(requestCode = 100)
    public void permissionSuccess() {
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();
    }

    @PermissionFail(requestCode = 100)
    public void permissionFailure(){
        Toast.makeText(this, "权限拒绝,无法正常使用", Toast.LENGTH_LONG).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 1000);
    }

    @Override
    public View getContentView() {
        return View.inflate(this, R.layout.activity_splash,null);
    }
}