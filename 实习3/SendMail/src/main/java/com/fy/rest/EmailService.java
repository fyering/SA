package com.fy.rest;

import com.aliyuncs.exceptions.ClientException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.List;

@Path("/email")
public interface EmailService {
    @GET
    @Path("/sendemail")
    String sendEmail(@QueryParam("_url")String _url, @QueryParam("_payload")String _payload) throws ClientException;

    @GET
    @Path("/sendemailbatch")
    String sendEmailBatch(@QueryParam("_url") List<String> _url, @QueryParam("_payload") String _payload);

    @GET
    @Path("/validateemailaddress")
    boolean validateEmailAddress(@QueryParam("name") String _url);


}
