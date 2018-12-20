package com.karol172.lecturesmanager.controller;

import com.karol172.lecturesmanager.dto.AttachmentDto;
import com.karol172.lecturesmanager.dto.Conventer.Conventer;
import com.karol172.lecturesmanager.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class AttachmentController {

    private AttachmentService attachmentService;

    @Autowired
    public AttachmentController(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }

    //TODO: For speaker and admin
    @PostMapping("/attachment")
    public Long create (@RequestBody @Valid AttachmentDto dto) {
        return attachmentService.create(Conventer.fromAttachmentDto(dto), dto.getLecture());
    }

    //TODO: For speaker and admin
    @PutMapping("/attachment/{id}")
    public Boolean update (@RequestBody @Valid AttachmentDto dto, @PathVariable("id") Long id) {
        return attachmentService.update(Conventer.fromAttachmentDto(dto), id);
    }

    //TODO: For speaker and admin and listenedUser
    @GetMapping("/attachment/{id}")
    public AttachmentDto get (@PathVariable("id") Long attachmentId) {
        return Conventer.fromAttachment(attachmentService.get(attachmentId));
    }

    //TODO: For speaker and admin
    @DeleteMapping("/attachment/{id}")
    public void delete (@PathVariable("id") Long attachmentId) {
        attachmentService.delete(attachmentId);
    }

    //TODO: add File Method

    //TODO: remove File Method

}