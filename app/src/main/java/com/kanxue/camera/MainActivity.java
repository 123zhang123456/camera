package com.kanxue.camera;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView mResult_container;
    private ImageView take_photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init_function();
        initListener();
    }

    public void initListener(){
        take_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent();
                intent.setAction("android.media.action.IMAGE_CAPTURE");
                intent.addCategory(Intent.CATEGORY_DEFAULT);

                startActivityForResult(intent,1);
            }
        });
    }

    public  void init_function(){
        take_photo = findViewById(R.id.take_photo);
        mResult_container = findViewById(R.id.photo_result_container);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            if(resultCode== Activity.RESULT_OK&&data!=null){
                Bitmap result =data.getParcelableExtra("data");
                if(result!=null){
                    mResult_container.setImageBitmap(result);
                }
            }else if(resultCode== Activity.RESULT_CANCELED){
                Toast.makeText(MainActivity.this,"您取消了拍照",Toast.LENGTH_LONG).show();
            }
        }
    }
}