package com.changbi.tradeunion.boardforworkers.application.presentation;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.changbi.tradeunion.boardforworkers.application.domain.MetaType;
import com.changbi.tradeunion.boardforworkers.application.service.ApplicationService;
import com.changbi.tradeunion.boardforworkers.common.CommonValues;
import com.changbi.tradeunion.boardforworkers.common.dto.ResultDto;
import com.changbi.tradeunion.boardforworkers.common.utility.ObjectStorageUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/api/common")
@RequiredArgsConstructor
public class ApplicationController {

    private final ObjectStorageUtility objectStorageUtility;
    private final ApplicationService applicationService;

    @GetMapping("/meta/{type}")
    public ResponseEntity<?> findMetaValue(
            @PathVariable(name = "type") String type){
        return ResponseEntity.ok(applicationService.findApplicationMetaByType(MetaType.valueOf(type)));
    }

    @PutMapping("/meta/{type}")
    public ResponseEntity<?> updateMetaValue(
            @PathVariable(name = "type") String type,
            @RequestBody String metaValue){
        return ResponseEntity.ok(
                    ResultDto.builder()
                        .resultCode(CommonValues.RESULT_CODE_SUCCESS_DEFAULT)
                        .data(applicationService.updateApplicationMeta(MetaType.valueOf(type), metaValue))
                    .build());
    }

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
