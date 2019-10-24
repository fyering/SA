package com.fy.soap;

import com.aliyuncs.dm.model.v20151123.*;
import org.springframework.stereotype.Component;

import javax.jws.WebService;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 由于实现类和接口不在同一个包中。所以要加上targetNamespace属性。
 * 另外，这里的endpointInterface是实现类对应接口的全路径
 * @author Administrator
 */
@WebService(targetNamespace="com.serviceTargetName",endpointInterface= "com.fy.soap.Emailservice")
@Component("Email")//spring注入用
public class Email implements Emailservice {
    public String sendEmail(String _url,String _payload) throws ClientException {
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
                return "N";
            }



        return "Y";
    }

    public String sendEmailBatch(List _url, String _payload){

       // String[]_name={"1073771390@qq.com","17754070839@163.com","17754070839@cug.edu.com"};
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
            return "N";
        }
        catch (ClientException e) {
            e.printStackTrace();
            return "N";
        }

        return "Y";
    }

    public boolean validateEmailAddress(String _utl) {
        Pattern p = Pattern.compile("[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+");  //编译"a*b"为Pattern类的实例  //编译"a*b"为Pattern类的实例
        Matcher m = p.matcher(_utl);     //该实例与"aaaaab"匹配
        boolean b = m.matches();
       return b;
    }
}
