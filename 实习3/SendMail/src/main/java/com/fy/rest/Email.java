package com.fy.rest;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.dm.model.v20151123.SingleSendMailResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class Email implements EmailService{
    public String sendEmail(String _url, String _payload) throws ClientException {
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4Fj9eGuYVLZ28BYALyR2", "l0r4XjCArpnKryVqOkQK3B0AwvYp5x");
        IAcsClient client = new DefaultAcsClient(profile);
        SingleSendMailRequest request = new SingleSendMailRequest();
        //使用https加密连接
        request.setProtocol(com.aliyuncs.http.ProtocolType.HTTPS);

        //request.setVersion("2017-06-22");// 如果是除杭州region外的其它region（如新加坡region）,必须指定为2017-06-22
        request.setAccountName("17754070839@fangya.xyz");
        request.setFromAlias("fangya");
        request.setAddressType(1);
        request.setTagName("控制台创建的标签");
        request.setReplyToAddress(true);
        if(validateEmailAddress(_url)) {
            request.setToAddress(_url);
            request.setSubject("验证码");
            request.setHtmlBody(_payload);
            SingleSendMailResponse httpResponse = client.getAcsResponse(request);
        }
        else{
            return "Failed to send, check that the recipient's email address is correct";
        }



        return "Send successfully";

    }

    public String sendEmailBatch(List<String> _url, String _payload) {
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4Fj9eGuYVLZ28BYALyR2", "l0r4XjCArpnKryVqOkQK3B0AwvYp5x");
        IAcsClient client = new DefaultAcsClient(profile);
        SingleSendMailRequest request=new SingleSendMailRequest();
        try {
            request.setAccountName("17754070839@fangya.xyz");
            request.setAddressType(1);
            request.setTagName("控制台创建的标签");
            request.setReplyToAddress(true);
            for(int i=0;i<_url.size();i++){
                if(validateEmailAddress((String) _url.get(i))){
                    request.setToAddress((String) _url.get(i));
                    request.setSubject("验证码");
                    request.setHtmlBody(_payload);
                    SingleSendMailResponse response=client.getAcsResponse(request);
                }
            }

        } catch (ServerException e) {
            e.printStackTrace();
            return "Failed to send";
        }
        catch (ClientException e) {
            e.printStackTrace();
            return "Failed to send";
        }

        return "Send successfully";

    }

    public boolean validateEmailAddress(String _url) {
        Pattern p = Pattern.compile("[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+");  //编译"a*b"为Pattern类的实例  //编译"a*b"为Pattern类的实例
        Matcher m = p.matcher(_url);     //该实例与"aaaaab"匹配
        boolean b = m.matches();
        return b;
    }
}
