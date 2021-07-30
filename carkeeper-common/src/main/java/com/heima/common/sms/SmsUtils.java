package com.heima.common.sms;


import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;


public class SmsUtils {
    public static final String VALIDATE_CODE = "SMS_206880236";//发送短信验证码
    public static final String ORDER_NOTICE = "SMS_206880498";//体检预约成功通知

    /**
     * 发送短信验证码
     * @param phoneNumbers
     * @param param
     */
    public static void validUserTelephone(String phoneNumbers,String param) {
        try {
            DefaultProfile profile = DefaultProfile.getProfile("cn-shanghai", "LTAI4G92Tch3XciwFVTr16JE", "akp9rLHmbNSTc1pBrtyUpoPQMWjV4n");
            IAcsClient client = new DefaultAcsClient(profile);

            CommonRequest request = new CommonRequest();
            request.setSysMethod(MethodType.POST);
            request.setSysDomain("dysmsapi.aliyuncs.com");
            request.setSysVersion("2017-05-25");
            request.setSysAction("SendSms");
            request.putQueryParameter("RegionId", "cn-shanghai");
            request.putQueryParameter("PhoneNumbers", phoneNumbers);
            request.putQueryParameter("SignName", "传智健康");
            request.putQueryParameter("TemplateCode", VALIDATE_CODE);
            request.putQueryParameter("TemplateParam", "{\"code\":\""+param+"\"}");

            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("发送验证码失败");
        }
    }


    public static void orderSuccessMessage(String phoneNumbers,String param) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-shanghai", "LTAI4G92Tch3XciwFVTr16JE", "akp9rLHmbNSTc1pBrtyUpoPQMWjV4n");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-shanghai");
        request.putQueryParameter("PhoneNumbers", phoneNumbers);
        request.putQueryParameter("SignName", "传智健康");
        request.putQueryParameter("TemplateCode", ORDER_NOTICE);
        request.putQueryParameter("TemplateParam", "{\"code\":\""+param+"\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    //1234
    public static void main(String[] args) throws ClientException {
        SmsUtils.orderSuccessMessage("15565656529","9876");
    }
}