package com.changbi.tradeunion.boardforworkers.common.utility;

import com.changbi.tradeunion.boardforworkers.board.exception.FileUploadException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class FileUtility {

    private static final Logger logger = LoggerFactory.getLogger(FileUtility.class);

    private static String fileUploadDirectory;

    @Value("${server.file.upload-directory.path}")
    private void setFileUploadDirectory(String fileUploadDirectory) {
        FileUtility.fileUploadDirectory = fileUploadDirectory;
    }

    public static Map<String, String> uploadMultipartFile(MultipartFile multipartFile) {
        Map<String, String> uploadResult = new HashMap<>();

        try {
            String fileDirectory = FileUtility.generateFileDirectory(LocalDate.now());
            String originalFilename = multipartFile.getOriginalFilename();
            String renameFileName = FileUtility.generateRenameFileName(originalFilename);
            String ext = originalFilename.substring(originalFilename.lastIndexOf("."));

            Path filePath = Paths.get(fileDirectory+"/"+renameFileName + ext);
            Files.copy(multipartFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            uploadResult.put("originalFilename", originalFilename);
            uploadResult.put("renameFileName", renameFileName);
            uploadResult.put("fileSize", String.valueOf(multipartFile.getSize()));
            uploadResult.put("filePath",filePath.toString());
        }catch (IOException e){
            logger.error("파일 업로드 오류", e);
            throw new FileUploadException("파일 업로드 오류 발생", multipartFile.getOriginalFilename());
        }catch (NullPointerException e){
            logger.error("파일 업로드 오류", e);
            throw new FileUploadException("파일이 존재하지 않습니다.");
        }

        return uploadResult;
    }

    private static String  generateFileDirectory(LocalDate nowDate){
        int nowYear = nowDate.getYear();
        int nowMonth = nowDate.getMonthValue();

        File uploadDirectory = new File(FileUtility.fileUploadDirectory+"/"+nowYear+"/"+nowMonth);

        if(!uploadDirectory.exists()){
            logger.info("File Directory is not exist");
            uploadDirectory.mkdirs();
        }

        return uploadDirectory.toString();
    }

    private static String generateRenameFileName(String originalFileName) {
        String year = LocalDate.now().getYear()+"";
        String month = LocalDate.now().getMonthValue()+"";
        String base64FileName = Base64.getEncoder().encodeToString(originalFileName.getBytes());
        return year+month+"_"+base64FileName.trim().substring(0, 5);
    }
}
