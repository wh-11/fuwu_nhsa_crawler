package com.example.gov.nhsa.fuwu.crawler.demo.utils;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import com.example.gov.nhsa.fuwu.crawler.demo.entity.AdmdvsDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.script.Invocable;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * fuwu.nhsa.gov.cn 请求工具类
 *
 * @date: 2024/3/6
 */
@Slf4j
@Component
@AllArgsConstructor
public class FuwuNhsaHelper {


    private final JavaCallJS javaCallJS;

    private final RestTemplate restTemplate;

    /**
     * @param requestObject 请求对象
     * @param url           请求url
     * @return 解密后的json字符串
     */
    public String httpRequestHelper(Object requestObject, String url) {
        try {
            return doRequest(JSONObject.toJSONString(requestObject), url);
        } catch (FileNotFoundException | ScriptException | NoSuchMethodException e) {
            log.error("执行请求时异常，接口地址：{}，，入参：{}，，，异常信息，{}", url, JSONObject.toJSONString(requestObject), e.getMessage(), e);
        }
        return "";
    }


    /**
     * 执行请求
     *
     * @param jsonStr json body
     * @param url     请求url，，/nthl/api/***  开头的半路径
     * @return 解密后的json response
     * @throws FileNotFoundException 提供的脚本不存在，则触发此异常
     * @throws ScriptException       在调用js方法时发生错误，则触发此异常
     * @throws NoSuchMethodException 找不到给定的js 方法，则触发此异常
     */
    public String doRequest(String jsonStr, String url) throws FileNotFoundException, ScriptException, NoSuchMethodException {
        Invocable invocable = javaCallJS.getJSInvocable();

        // 通过js 方法，加密body
        Object str = invocable.invokeFunction("EncryptedDataFromJsonStr", jsonStr, url);
        JSONObject jo = JSONObject.parseObject((String) str);

        // http 请求，获取 response 的字符串
        String response = httpRequest(jo);
        // 通过js 方法解密
        Object a = invocable.invokeFunction("DecryptedDataFromString", response);
        return (String) a;
    }

    private String httpRequest(JSONObject jo) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("channel", "web");
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.set("contentType", "application/x-www-form-urlencoded");
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        headers.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/121.0.0.0 Safari/537.36 Edg/121.0.0.0");
        headers.set("Referer", "https://fuwu.nhsa.gov.cn/nationalHallSt/");
        headers.set("X-Tif-Paasid", "");
        JSONObject joHeader = jo.getJSONObject("headers");
        headers.set("x-tif-signature", joHeader.getString("x-tif-signature"));
        headers.set("x-tif-timestamp", joHeader.getString("x-tif-timestamp"));
        headers.set("x-tif-nonce", joHeader.getString("x-tif-nonce"));

        JSONObject data = jo.getJSONObject("data");
        String fullUrl = "https://fuwu.nhsa.gov.cn/ebus/fuwu/api" + jo.getString("url");

        JSONObject body = new JSONObject();
        body.put("data", data);

        HttpEntity<String> request = new HttpEntity<>(body.toString(), headers);

        return restTemplate.postForObject(fullUrl, request, String.class);
    }


    /**
     * 查询区域列表
     * 这里获取到的是三级区域，但不是标准的三级区域
     * 存在一些：
     * 河北省 -> 华油。
     * 河北省 -> 铁路。
     * 河北省 -> 电力。
     * 山东省 -> 胜利油田。
     *
     * @return 区域列表
     */
    public List<AdmdvsDTO> queryAdmdvsTree() {
        String fullUrl = "https://fuwu.nhsa.gov.cn/ebus/fuwu/api/nthl/api/dic/queryAdmdvsTree";
        String body = "{\"data\": {\"transferFlag\": \"\"}}";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.set("channel", "web");
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        headers.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/121.0.0.0 Safari/537.36 Edg/121.0.0.0");

        HttpEntity<String> request = new HttpEntity<>(body, headers);
        String response = restTemplate.postForObject(fullUrl, request, String.class);

        JSONObject jo = JSONObject.parseObject(response);
        if (jo == null) {
            log.error("查询地址信息接口返回null，接口地址：{}，，入参：{}", fullUrl, body);
            return new ArrayList<>();
        }
        if ("success".equals(jo.getString("type")) && jo.getInteger("code") == 0) {
            return JSONObject.parseObject(jo.getString("data"), new TypeReference<List<AdmdvsDTO>>() {
            });

        }
        log.error("查询地址信息接口返回内容异常，接口地址：{}，，入参：{}，，返回值;{}", fullUrl, body, response);
        return new ArrayList<>();
    }
}
