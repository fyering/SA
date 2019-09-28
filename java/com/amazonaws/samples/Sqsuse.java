package com.amazonaws.samples;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.CreateQueueRequest;

public class Sqsuse {

	 String myurl;
	 AmazonSQS Sqs;
	 void start() {
		 ProfileCredentialsProvider credentialsProvider = new ProfileCredentialsProvider();
	        try {
	            credentialsProvider.getCredentials();
	        } catch (Exception e) {
	            throw new AmazonClientException(
	                    "Cannot load the credentials from the credential profiles file. " +
	                    "Please make sure that your credentials file is at the correct " +
	                    "location (C:\\Users\\fyrhythm\\.aws\\credentials), and is in valid format.",
	                    e);
	        }

	        AmazonSQS sqs = AmazonSQSClientBuilder.standard()
	                               .withCredentials(credentialsProvider)
	                               .withRegion(Regions.US_EAST_1)
	                               .build();

	        System.out.println("===========================================");
	        System.out.println("Getting Started with Amazon SQS");
	        System.out.println("===========================================\n");

	   
	            // Create a queue
	            System.out.println("Creating a new SQS queue called MyQueue.\n");
	            CreateQueueRequest createQueueRequest = new CreateQueueRequest("MyQueue");
	           myurl= sqs.createQueue(createQueueRequest).getQueueUrl();
	           Sqs=sqs;

	}
}
