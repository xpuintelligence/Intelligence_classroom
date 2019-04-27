package edu.xpu.tim.myfaceapplication.util;


import java.util.*;

/**
* 人脸注册
*/
public class FaceAdd {

    public static String add(String img, String accessToken,
                             String group_id, String user_id, String user_info) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/add";
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("image", img);
            map.put("group_id", group_id);
            map.put("user_id", user_id);
            map.put("user_info", user_info);
            map.put("image_type", "BASE64");
            map.put("quality_control", "LOW");

            String param = GsonUtils.toJson(map);

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            //String accessToken = "[调用鉴权接口获取的token]";

            String result = HttpUtil.post(url, accessToken, "application/json", param);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}