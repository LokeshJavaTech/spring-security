package com.lokesh.springsecurity.controller;

import com.lokesh.springsecurity.model.Notice;
import com.lokesh.springsecurity.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class NoticesController {

    private final NoticeRepository noticeRepository;

//    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/notices")
    public ResponseEntity<List<Notice>> getNotices() {
        List<Notice> noticeList = noticeRepository.findAllActiveNotices();
        if (noticeList != null) {
            return ResponseEntity.ok()
                    .cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS))
                    .body(noticeList);
        } else {
            return null;
        }
    }
}
