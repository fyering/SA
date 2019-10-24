package com.fy.soap;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import java.util.Arrays;
import java.util.List;

public class JaxWsDynamicClient {

    public static void main(String[] args) {
        JaxWsDynamicClientFactory factory = JaxWsDynamicClientFactory.newInstance();
        Client client = factory.createClient("http://localhost:8080/ws/soap/Email?wsdl");
        String[]_name={"1073771390@qq.com","17754070839@163.com","17754070839@cug.edu.cn"};

        List list = Arrays.asList(_name);
        try {
            Object[] results = client.invoke("sendEmailBatch",list,"hello");
            System.out.println(results[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
