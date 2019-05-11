package edu.xpu.tim.myfaceapplication.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.util.Base64;


import com.alibaba.fastjson.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImgSaveUtils {
    private static String accessToken;
    private static boolean flag = false;
    //保存文件到指定路径
    public static void saveImageToGallery(Context context, Bitmap bmp,  String id, String pwd) {
        // 首先保存图片
        String storePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "dearxy";
        File appDir = new File(storePath);
        if (!appDir.exists()) {
            if(appDir.mkdir()) throw new RuntimeException("新建文件夹失败");
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            //通过io流的方式来压缩保存图片
            boolean isSuccess = bmp.compress(Bitmap.CompressFormat.JPEG, 60, fos);
            fos.flush();
            fos.close();

            //保存图片后发送广播通知更新数据库
            Uri uri = Uri.fromFile(file);

            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
            if (isSuccess) {

                new Thread(()->{
                    String base64;
                    base64 = bitmapToBase64(bmp);
                    if(!flag){
                        new Thread(()-> accessToken = AuthService.getAuth()).start();
                        flag = true;
                    }

                    String add = FaceAdd.add(base64, accessToken, "wisdom_class", id, pwd);
                    Integer error_code = JSONObject.parseObject(add).getInteger("error_code");
                    System.out.println("error_code:"+error_code);
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String bitmapToBase64(Bitmap bitmap){
        String result = null;
        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

                baos.flush();
                baos.close();

                byte[] bitmapBytes = baos.toByteArray();
                result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}