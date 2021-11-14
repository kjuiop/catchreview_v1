package io.gig.catchreview.core.domain.attach;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import io.gig.catchreview.core.utils.AmazonS3Utils;
import io.gig.catchreview.core.utils.S3Properties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.security.InvalidParameterException;

/**
 * @author : Jake
 * @date : 2021-09-22
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AttachmentService {

    private final S3Properties s3Properties;

    public String upload(MultipartFile multipartFile) throws IOException {

        String originalFileName = multipartFile.getOriginalFilename();
        validFileUpload(originalFileName);

        int startIndex = originalFileName.replaceAll("\\\\", "/").lastIndexOf("/");
        originalFileName = originalFileName.substring(startIndex + 1);
        String fileExtension = FilenameUtils.getExtension(originalFileName);
        String randomFileName =  RandomStringUtils.randomAlphanumeric(128);
        String s3FileName = randomFileName + "." + fileExtension;
        FileType fileType = FileUtils.defineMediaType(fileExtension);

        AmazonS3 s3Client = AmazonS3Utils.getS3Client(s3Properties);

        String bucketName = s3Properties.getBucketName();

        ObjectMetadata meta = new ObjectMetadata();
        meta.setContentType(multipartFile.getContentType());
        meta.setContentLength(multipartFile.getBytes().length);
        meta.setHeader("filename", originalFileName);

        try {
            s3Client.putObject(new PutObjectRequest(bucketName, s3FileName, multipartFile.getInputStream(), meta));
            log.info("============= Upload custom file - Done!! ===============");
            URL url = s3Client.getUrl(bucketName, s3FileName);
            return url.toString();

        } catch (AmazonServiceException ase) {
            log.error("Caught an AmazonServiceException from PUT requests, rejected reasons:");
            log.error("Error Message:    " + ase.getMessage());
            log.error("HTTP Status Code: " + ase.getStatusCode());
            log.error("AWS Error Code:   " + ase.getErrorCode());
            log.error("Error Type:       " + ase.getErrorType());
            log.error("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            log.error("Caught an AmazonClientException: ");
            log.error("Error Message: " + ace.getMessage());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private void validFileUpload(String originalFileName) {

        boolean isPermit = FileUtils.permitExtensionCheck(originalFileName);

        if (!isPermit) {
            throw new InvalidParameterException("해당 파일은 업로드하실 수 없습니다.<br />확인 후 다시 업로드해주시기 바랍니다.");
        }
    }

}
