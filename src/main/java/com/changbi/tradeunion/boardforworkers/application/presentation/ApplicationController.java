package com.changbi.tradeunion.boardforworkers.application.presentation;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.changbi.tradeunion.boardforworkers.common.dto.ResultDto;
import com.changbi.tradeunion.boardforworkers.common.utility.ObjectStorageUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/api/common")
@RequiredArgsConstructor
public class ApplicationController {

    private final ObjectStorageUtility objectStorageUtility;

    @PostMapping("/upload/{fileType}/{accessControl}")
    public ResponseEntity<ResultDto> fileUpload(
            @PathVariable(name = "fileType", required = false) String fileType,
            @PathVariable(name = "accessControl", required = false) String accessControl,
            @RequestParam MultipartFile file){
        ResultDto resultDto = new ResultDto();

        if(accessControl == null){
            accessControl = "private";
        }

        if(file != null){
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(file.getContentType());
            objectMetadata.setContentLength(file.getSize());

            resultDto = objectStorageUtility.fileUpload(objectMetadata, file, fileType, accessControl);
        }

        return ResponseEntity.ok(resultDto);

    }

}
