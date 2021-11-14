package io.gig.cathreview.web.controller;

import io.gig.catchreview.core.domain.attach.AttachmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author : Jake
 * @date : 2021-11-14
 */
@Slf4j
@Controller
@RequestMapping("attachment")
@RequiredArgsConstructor
public class AttachmentController {

    private final AttachmentService attachmentService;

    @PostMapping("upload")
    @ResponseBody
    public ResponseEntity upload(
            @RequestParam(value = "file") MultipartFile multipartFile) throws IOException {

        String fileFullPath = attachmentService.upload(multipartFile);

        log.info("===== S3 multipartFileList Successfully Uploaded. =====");

        return ResponseEntity.ok().body(fileFullPath);
    }
}
