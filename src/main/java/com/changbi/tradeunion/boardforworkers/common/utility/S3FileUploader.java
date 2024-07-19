package com.changbi.tradeunion.boardforworkers.common.utility;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.changbi.tradeunion.boardforworkers.common.dto.ResultDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class S3FileUploader {

    private final AmazonS3 s3Client;
    private final Logger logger = LoggerFactory.getLogger(S3FileUploader.class);

    public ResultDto s3FileUpload(
            ObjectMetadata objectMetadata,
            MultipartFile multipartFile,
            String fileType,
            String accessControl) {

        ResultDto resultDto = new ResultDto();
        Map<String , String > uploadResult = new HashMap<>();

        try {
            String uploadFilePath = "temp/upload/file";

            PutObjectRequest putObjectRequest = new PutObjectRequest("dev-bootshop", uploadFilePath, multipartFile.getInputStream(), objectMetadata);

            putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead);

            s3Client.putObject(putObjectRequest);


        }catch (Exception e){
            logger.error("file upload error", e);
        }

        return resultDto;
    }

}
