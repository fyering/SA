package com.amazonaws.samples;
import java.util.HashMap;
import java.util.Map;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.DeleteQueueRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.MessageAttributeValue;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;

public class MyQsender {
	String t="";
	String Msg="null";
	String myurl="";
	 AmazonSQS sqs;
	void setMsg(String msg) {
		if(msg=="") {Msg="null";}
		else
		{		Msg=msg;
		
		}
		
	}
void setOnj(String listener) {
	t=listener;
	
	
}
void sendmsg(String url, AmazonSQS Sqs) {
	
	myurl=url;
	sqs=Sqs;

   
	 // Send a message
    System.out.println("Sending a message to MyQueue"+'\n');
    final Map<String, MessageAttributeValue> messageAttributes = new HashMap<>();
    messageAttributes.put("email", new MessageAttributeValue()
            .withDataType("String")
            .withStringValue(t));
   
    
    
    
    final SendMessageRequest sendMessageRequest = new SendMessageRequest();
    sendMessageRequest.withMessageBody(Msg);
    sendMessageRequest.withQueueUrl(myurl);
    sendMessageRequest.withMessageAttributes(messageAttributes);
    sqs.sendMessage(sendMessageRequest);
   
	
}


	
}
