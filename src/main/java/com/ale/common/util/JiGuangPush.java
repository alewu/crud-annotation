package com.ale.common.util;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import cn.jpush.api.push.model.notification.WinphoneNotification;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.util.Map;

/**
 * 标题：极光推送（使用Java SDK）
 * 功能描述：向某单个设备或者某设备列表推送一条通知、或者消息。推送的内容只能是 JSON 表示的一个推送对象。
 * 推送的主要有两个核心。
 * 一是创建一个链接对象，即 PushClient; 二是创建推送对象，即 PushPayload（关键）
 *
 * @author alewu
 * @date 2017/11/14 11:49
 * @description JiGuangPush
 */
public class JiGuangPush {
    private static final Logger LOGGER = LoggerFactory.getLogger(JiGuangPush.class);
    private static final boolean APNS_PRODUCTION = true;
    private static final int TIME_TO_LIVE = 86400;
    private static final String MASTER_SECRET = ConfigUtil.getParameter("MASTER_SECRET");
    private static final String APP_KEY = ConfigUtil.getParameter("APP_KEY");


    public static JPushClient getJPushClient() {
        ClientConfig clientConfig = ClientConfig.getInstance();
        return new JPushClient(MASTER_SECRET, APP_KEY, null, clientConfig);
    }


    /**
     * 根据别名推送
     *
     * @param alias 别名
     * @return PushResult
     */
    public static PushResult pushByAlias(String alias, Map<String, String> map) {
        JPushClient jpushClient = getJPushClient();
        // 推送对象可以设置的属性比较多，可以决定推送的信息内容，平台，平台下的设备等等
        // PushPayload payload = buildPushObjectAllAliasAlert(alias, pushMsg);
        PushPayload pushPayload = buildPushObjectAndroidAliasSilenceAlert(alias, map);
        try {
            return jpushClient.sendPush(pushPayload);
        } catch (APIConnectionException e) {
            LOGGER.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOGGER.error("Error response from JPush server. Should review and fix it. ", e);
            LOGGER.info("HTTP Status: " + e.getStatus());
            LOGGER.info("Error Code: " + e.getErrorCode());
            LOGGER.info("Error Message: " + e.getErrorMessage());
            LOGGER.info("Msg ID: " + e.getMsgId());
            LOGGER.error("Sendno: " + pushPayload.getSendno());
        }
        return null;
    }

    /**
     * 推送对象：所有平台，所有设备，内容为 ALERT 的通知
     *
     * @return PushResult
     */
    public static PushResult pushToAll(String alert) {
        JPushClient jpushClient = getJPushClient();
        PushPayload payload = buildPushObjectAllAllAlert(alert);
        try {
            return jpushClient.sendPush(payload);
        } catch (APIConnectionException e) {
            LOGGER.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOGGER.error("Error response from JPush server. Should review and fix it. ", e);
            LOGGER.info("HTTP Status: " + e.getStatus());
            LOGGER.info("Error Code: " + e.getErrorCode());
            LOGGER.info("Error Message: " + e.getErrorMessage());
            LOGGER.info("Msg ID: " + e.getMsgId());
        }
        return null;
    }
    /**
     * 快捷地构建推送对象：所有平台，所有设备，内容为 ALERT 的通知。
     */
    public static PushPayload buildPushObjectAllAllAlert(String alert) {
        return PushPayload.alertAll(alert);
    }

    /**
     * 静默推送
     * 构建推送对象：平台为android，推送目标是别名为 "alias"，推送内容是 - 内容为 MSG_CONTENT 的消息。
     *
     * @param deviceAlias 设备别名
     * @param map         业务的key/value
     * @return 推送载体
     */
    public static PushPayload buildPushObjectAndroidAliasSilenceAlert(String deviceAlias, Map<String, String> map) {
        // 推送平台android
        Platform platform = Platform.android();
        // 推送设备对象，表示一条推送可以被推送到哪些设备列表。确认推送设备对象，JPush 提供了多种方式，比如：别名、标签、注册ID、分群、广播等。
        Audience alias = Audience.alias(deviceAlias);
        // 可选 消息内容体。是被推送到客户端的内容。应用内消息。与 notification 一起二者必须有其一，可以二者并存
        Message message = Message.newBuilder().setMsgContent("").addExtras(map).build();
        // 可选	推送参数
        Options options = Options.newBuilder().setApnsProduction(true).build();
        return PushPayload.newBuilder().setPlatform(platform).setAudience(alias)
                          .setMessage(message)
                          .setOptions(options)
                          .build();
    }

    /**
     * 构建推送对象：所有平台
     *
     * @param alias   别名
     * @param content 通知内容
     * @return 推送载体
     */
    public static PushPayload buildPushObjectAllAliasAlert(String alias, String content) {
        // 所有平台
        Platform all = Platform.all();
        // 设置推送目标
        Audience target = Audience.alias(alias);
        // 设置通知 安卓、iOS、winPhone
        AndroidNotification androidNotification = AndroidNotification.newBuilder().build();
        IosNotification iosNotification = IosNotification.newBuilder().build();
        WinphoneNotification winphoneNotification = WinphoneNotification.newBuilder().build();
        // 可选	推送参数
        Options options = Options.newBuilder()
                                 //true-推送生产环境 false-推送开发环境（测试使用参数）
                                 .setApnsProduction(false)
                                 //消息在JPush服务器的失效时间（测试使用参数）
                                 .setTimeToLive(TIME_TO_LIVE)
                                 .build();
        return PushPayload.newBuilder().setPlatform(all).setAudience(target)
                          .setNotification(Notification.newBuilder()
                                                       .setAlert(content)
                                                       .addPlatformNotification(androidNotification)
                                                       .addPlatformNotification(iosNotification)
                                                       .build())
                          .setOptions(options)
                          .build();
    }



//    public static void pushJson(String pushUrl, String alias, String alert,
//                                String appKey, String MASTER_SECRET, PushMsg pushMsg) {
//        try {
//            String result = push(pushUrl, alias, alert, appKey, MASTER_SECRET,
//                    APNS_PRODUCTION, TIME_TO_LIVE, pushMsg);
//            JSONObject resData = JSONObject.parseObject(result);
//            if (!resData.containsKey("error")) {
//                LOGGER.info("针对别名为" + alias + "的信息推送成功！");
//            } else {
//                LOGGER.error("针对别名为" + alias + "的信息推送失败！");
//            }
//        } catch (Exception e) {
//            LOGGER.error("针对别名为" + alias + "的信息推送失败！", e);
//        }
//    }

    /**
     * 推送方法-调用极光API
     *
     * @param reqUrl 请求url
     * @param alias  别名
     * @param alert  提示
     * @return result
     */
    public static String push(String reqUrl, String alias, String alert, String appKey, String MASTER_SECRET,
                              boolean apnsProduction, int timeToLive, PushMsg pushMsg) {
        String base64AuthString = encryptBASE64(appKey + ":" + MASTER_SECRET);
        String authorization = "Basic " + base64AuthString;
        return sendPostRequest(reqUrl, generateJson(alias, alert, apnsProduction, timeToLive, pushMsg).toString(),
                "UTF-8", authorization);
    }

    /**
     * 发送Post请求（json格式）
     *
     * @param reqURL        请求url
     * @param data          数据
     * @param encodeCharset 编码
     * @param authorization 授权
     * @return result
     */
    public static String sendPostRequest(String reqURL, String data, String encodeCharset, String authorization) {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(reqURL);
        HttpResponse response;
        String result = "";
        try {
            StringEntity entity = new StringEntity(data, encodeCharset);
            entity.setContentType("application/json");
            httpPost.setEntity(entity);
            httpPost.setHeader("Authorization", authorization.trim());
            response = client.execute(httpPost);
            result = EntityUtils.toString(response.getEntity(), encodeCharset);
        } catch (Exception e) {
            LOGGER.error("请求通信[" + reqURL + "]时偶遇异常,堆栈轨迹如下", e);
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 组装极光推送专用json串
     *
     * @param alias 别名
     * @param alert 提示
     * @return json
     */
    public static JSONObject generateJson(String alias, String alert, boolean apnsProduction, int timeToLive, PushMsg pushMsg) {
        JSONObject json = new JSONObject();
        // 平台
        JSONArray platform = new JSONArray();
        platform.add("android");
        platform.add("ios");
        // 推送目标
        JSONObject audience = new JSONObject();
        JSONArray alias1 = new JSONArray();
        alias1.add(alias);
        audience.put("alias", alias1);
        // 通知内容
        JSONObject notification = new JSONObject();
        // android通知内容
        JSONObject android = new JSONObject();
        android.put("alert", alert);
        android.put("builder_id", 1);
        android.put("extras", pushMsg);
        // ios通知内容
        JSONObject ios = new JSONObject();
        ios.put("alert", alert);
        ios.put("sound", "default");
        ios.put("badge", "+1");
        ios.put("extras", pushMsg);
        notification.put("android", android);
        notification.put("ios", ios);
        // 设置参数
        JSONObject options = new JSONObject();
        options.put("timeToLive", timeToLive);
        options.put("apnsProduction", apnsProduction);

        json.put("platform", platform);
        json.put("audience", audience);
        json.put("notification", notification);
        json.put("options", options);
        return json;

    }

    /**
     * BASE64加密工具
     */
    public static String encryptBASE64(String str) {
        byte[] key = str.getBytes();
        BASE64Encoder base64Encoder = new BASE64Encoder();
        return base64Encoder.encodeBuffer(key);
    }


}