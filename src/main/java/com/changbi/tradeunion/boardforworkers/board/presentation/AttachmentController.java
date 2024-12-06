package com.changbi.tradeunion.boardforworkers.board.presentation;

import com.changbi.tradeunion.boardforworkers.board.application.BoardServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/attachment")
public class AttachmentController {

    private final Logger logger = LoggerFactory.getLogger(AttachmentController.class);
    private final BoardServiceImpl boardService;

    @GetMapping("/download/{attachmentId}")
    public void downloadAttachment(
            @PathVariable(name = "attachmentId") Long attachmentId,
            HttpServletResponse response){
        logger.info("[ATTACHMENT DOWNLOAD] START attachment id: {}", attachmentId);

        boardService.downloadAttachment(attachmentId, response);
    }
}
