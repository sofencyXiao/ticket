package com.sofency.ticket.utils;

import com.alibaba.fastjson.JSONObject;
import com.sofency.ticket.dto.AuthorizeCode;
import com.sofency.ticket.dto.SessionCode;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author sofency
 * @date 2020/4/19 7:19
 * @package IntelliJ IDEA
 * @description
 */
public class SessionCodeUtils {
    //根据code 到微信后台拿取数据
    /**
     * 如果初次登录就插入用户的openId
     * 如果二次登录
     * @param code
     * @return  返回sessionKey  openid token
     */
    public static SessionCode getSessionCode(String code){
        AuthorizeCode authorizeCode = new AuthorizeCode();
        authorizeCode.setJs_code(code);
        System.out.println(authorizeCode.toString());
        OkHttpClient client = new OkHttpClient();
        //构建请求体
        Request request = new Request.Builder()
                .url("https://api.weixin.qq.com/sns/jscode2session?js_code="
                        +authorizeCode.getJs_code()+"&appid="
                        +authorizeCode.getAppid()+"&secret="
                        +authorizeCode.getSecret()+"&grant_type="
                        +authorizeCode.getGrant_type())
                .get()
                .header("content-type","application/x-www-form-urlencoded")
                .build();
        //进行响应
        try(Response response = client.newCall(request).execute()){
            String str = response.body().string();
            boolean flag =false;//默认没有发生变化
            JSONObject jsonObject =JSONObject.parseObject(str);
            String openId = (String) jsonObject.get("openid");
            String session_key = (String) jsonObject.get("session_key");
            //直接将openId先存储到数据库




        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("出现异常");
        }
        return null;
    }
}
