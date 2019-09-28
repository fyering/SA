package com.amazonaws.samples;

import java.util.List;
import java.util.Vector;
import java.util.Map.Entry;

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
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;

public class MyQReceiver {
	
	String msg="";
	Vector<MyQsender>ss=new Vector<MyQsender>();
	String myQueueUrl;
	String token;
	AmazonSQS sqs;

MyQReceiver(String i,String url, AmazonSQS Sqs){
	
	token=i;
	myQueueUrl=url;
    sqs=Sqs;
     
		
		
	}
void setToken(String listener) {
	
	token=listener;
}
void recvmsg() {
	
	
	
	  // Receive messages
    System.out.println("Receiving messages from MyQueue.\n");
   // ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(myQueueUrl);
    //List<Message> messages = sqs.receiveMessage(receiveMessageRequest).getMessages();
    final List<Message> messages = sqs.receiveMessage(
    	    new ReceiveMessageRequest(myQueueUrl)
    	        .withMessageAttributeNames("email")
    	).getMessages();
    for (Message message : messages) {
    	
    	//System.out.println(message);
    	System.out.println(token);
    	final String email =message.getMessageAttributes().get("email").getStringValue();
    	System.out.println("email:"+email);
    	System.out.println(message);
    	
    		if(email.equals(token)) {
    			
    			msg=message.getBody();
    			System.out.println(msg);
    			
    			sqs.deleteMessage(myQueueUrl, message.getReceiptHandle());
    		
    		}
    		
  	
    }

	

   
    
}


}
