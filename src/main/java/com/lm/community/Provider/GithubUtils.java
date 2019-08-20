package com.lm.community.Provider;

import com.alibaba.fastjson.JSON;
import com.lm.community.Domain.AccessToken;
import com.lm.community.Domain.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GithubUtils {

    /**
     * 将从github接收到的信息再次发送https://github.com/login/oauth/access_token
     * 获取access_token码
     * @param accessToken
     * @return
     */
    public String getAccessToken(AccessToken accessToken){
         MediaType mediaType = MediaType.get("application/json; charset=utf-8");

         OkHttpClient client = new OkHttpClient();


         RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessToken));
         Request request = new Request.Builder()
                 .url("https://github.com/login/oauth/access_token")
                 .post(body)
                 .build();
         try (Response response = client.newCall(request).execute()) {
             String string = response.body().string();
             String s = string.split("&")[0].split("=")[1];
             return s;
         } catch (IOException e) {
             e.printStackTrace();
         }

        return null;
    }

    /**
     * 由token码从github获取用户信息
     * @param accessToken
     * @return
     */
    public GithubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accessToken)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            GithubUser user = JSON.parseObject(string, GithubUser.class);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
