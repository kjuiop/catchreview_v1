package io.gig.catchreview.core.utils;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

/**
 * @author : Jake
 * @date : 2021-09-22
 */
public class AmazonS3Utils {

    public static AmazonS3 getS3Client(S3Properties s3Properties) {

        if (s3Properties == null) return null;

        AWSCredentials credentials = new BasicAWSCredentials(s3Properties.getAccessKey(), s3Properties.getSecretKey());

        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(s3Properties.getRegion())
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withForceGlobalBucketAccessEnabled(true)
                .build();

        return s3Client;
    }

}
