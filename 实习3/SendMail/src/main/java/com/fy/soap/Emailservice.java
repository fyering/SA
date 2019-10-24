package com.fy.soap;
import com.aliyuncs.exceptions.ClientException;

import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService(targetNamespace = "com.serviceTargetName")
public interface Emailservice {

    //如果接口没有用@WebParam(name = "parameterName")来指明方法入参的参数名称-这里时'parameterName',则在客户端你传入参数时：
    //客户端设置入参：call.addParameter("parameterName", XMLType.XSD_STRING, ParameterMode.IN);
    //会报错：Unmarshalling Error: 意外的元素 (uri:"", local:"parameterName")。所需元素为<{}arg0>
    public String sendEmail(@WebParam(name="parameterName") String _url,String _payload) throws ClientException;
    public String sendEmailBatch(@WebParam(name="parameterName") List _url, String _payload) ;
    public boolean validateEmailAddress(@WebParam(name="parameterName") String _utl);


}
