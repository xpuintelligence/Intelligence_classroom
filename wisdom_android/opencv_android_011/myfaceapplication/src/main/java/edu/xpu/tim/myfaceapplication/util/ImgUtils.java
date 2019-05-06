package edu.xpu.tim.myfaceapplication.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImgUtils {
    public static Bitmap netUrlPicToBmp(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);
            connection.setDoInput(true);
            connection.setUseCaches(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            if(input == null){
                throw new RuntimeException("图片流为空");
            }
            Bitmap bitmap = null;
            try {
                byte[] data = readStream(input);
                if(data != null){
                    bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            input.close();
            return bitmap;
        } catch (IOException e) {
            Log.d("StudentAty", "Error");
            return null;
        }
    }

    /*
     * 得到图片字节流 数组大小
     * */
    public static byte[] readStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while( (len=inStream.read(buffer)) != -1){
            outStream.write(buffer, 0, len);
        }
        outStream.close();
        inStream.close();
        return outStream.toByteArray();
    }
}
