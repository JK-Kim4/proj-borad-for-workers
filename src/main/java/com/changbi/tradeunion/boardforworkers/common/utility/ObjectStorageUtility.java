package com.changbi.tradeunion.boardforworkers.common.utility;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.changbi.tradeunion.boardforworkers.common.CommonValues;
import com.changbi.tradeunion.boardforworkers.common.dto.ResultDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ObjectStorageUtility {

    private final AmazonS3 amazonS3;
    private final Logger logger = LoggerFactory.getLogger(ObjectStorageUtility.class);

    public ResultDto fileUpload(
            ObjectMetadata objectMetadata,
            MultipartFile file,
            String fileType,
            String accessControl){

        ResultDto resultDto = new ResultDto();
        Map<String, String> uploadResult = new HashMap<>();

        try {
            String uploadFilePath = getFilePathWithType(fileType.toLowerCase(), file.getOriginalFilename());

            PutObjectRequest putRequest
                    = new PutObjectRequest
                    (
                            CommonValues.NCP_OBJECT_STORAGE_BUCKET_NAME,
                            uploadFilePath,
                            file.getInputStream(),
                            objectMetadata
                    );

            if(accessControl != null
                    && CommonValues.NCP_OBJECT_STORAGE_AUTHORITY_PUBLIC.equals(accessControl)){
                putRequest.setCannedAcl(CannedAccessControlList.PublicRead);
            }

            amazonS3.putObject(putRequest);
            uploadResult.put("fileName", file.getOriginalFilename());
            uploadResult.put("filePath", CommonValues.NCP_OBJECT_STORAGE_BUCKET_NAME+ "/" +uploadFilePath);

            resultDto.setResultCode(CommonValues.RESULT_CODE_SUCCESS_DEFAULT);
            resultDto.setResultMessage(CommonValues.RESULT_MESSAGE_SUCCESS_DEFAULT);
            resultDto.setData(uploadResult);
        }catch (Exception e){
            logger.error("첨부파일 업로드 오류 발생", e);
            resultDto.setResultCode(CommonValues.RESULT_CODE_FAIL_SYSTEM);
        }

        return resultDto;
    }

    public ResultDto fileUpload(
            ObjectMetadata objectMetadata,
            String originalFileName,
            InputStream fileInputStream,
            String fileType,
            String accessControl){

        ResultDto resultDto = new ResultDto();
        Map<String, String> uploadResult = new HashMap<>();

        try {
            String uploadFilePath = getFilePathWithType(fileType.toLowerCase(), originalFileName);

            PutObjectRequest putRequest
                    = new PutObjectRequest
                    (
                            CommonValues.NCP_OBJECT_STORAGE_BUCKET_NAME,
                            uploadFilePath,
                            fileInputStream,
                            objectMetadata
                    );

            if(accessControl != null
                    && CommonValues.NCP_OBJECT_STORAGE_AUTHORITY_PUBLIC.equals(accessControl)){
                putRequest.setCannedAcl(CannedAccessControlList.PublicRead);
            }

            amazonS3.putObject(putRequest);
            uploadResult.put("fileName", originalFileName);
            uploadResult.put("filePath", CommonValues.NCP_OBJECT_STORAGE_BUCKET_NAME+"/"+uploadFilePath);

            resultDto.setResultCode(CommonValues.RESULT_CODE_SUCCESS_DEFAULT);
            resultDto.setResultMessage(CommonValues.RESULT_MESSAGE_SUCCESS_DEFAULT);
            resultDto.setData(uploadResult);
        }catch (Exception e){
            logger.error("첨부파일 업로드 오류 발생", e);
            resultDto.setResultCode(CommonValues.RESULT_CODE_FAIL_SYSTEM);
        }

        return resultDto;
    }

    private String getFilePathWithType(String fileType, String orgFileName){
        switch (fileType){
            case CommonValues.NCP_OBJECT_STORAGE_FILE_TYPE_COVER:
                return "cover/" + orgFileName;
            case CommonValues.NCP_OBJECT_STORAGE_FILE_TYPE_THUMBNAIL:
                return "cover/thumbnail/" + orgFileName;
            case CommonValues.NCP_OBJECT_STORAGE_FILE_TYPE_AUDIO:
                return "audio/" + orgFileName;
            case CommonValues.NCP_OBJECT_STORAGE_FILE_TYPE_VIDEO:
                return "video/" + orgFileName;
            case CommonValues.NCP_OBJECT_STORAGE_FILE_TYPE_ETC:
                return "etc/" + orgFileName;
            case CommonValues.NCP_OBJECT_STORAGE_FILE_TYPE_PRE_AUDIO:
                return "pre-audio/" + orgFileName;
            case CommonValues.NCP_OBJECT_STORAGE_FILE_TYPE_ICON:
                return "icon/" + orgFileName;
            default:
                return orgFileName;
        }
    }
}
