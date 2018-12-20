package com.karol172.lecturesmanager.service;

import com.karol172.lecturesmanager.model.Attachment;
import com.karol172.lecturesmanager.model.Lecture;
import com.karol172.lecturesmanager.repository.AttachmentRepository;
import com.karol172.lecturesmanager.repository.LectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AttachmentService {

    private AttachmentRepository attachmentRepository;

    private LectureRepository lectureRepository;

    @Autowired
    public AttachmentService(AttachmentRepository attachmentRepository, LectureRepository lectureRepository) {
        this.attachmentRepository = attachmentRepository;
        this.lectureRepository = lectureRepository;
    }


    public Long create (Attachment attachment, Long lectureId) {
        final Optional<Lecture> lecture = lectureRepository.findById(lectureId);
        if (lecture.isPresent()) {
            attachment.setId(null);
            attachment.setLecture(lecture.get());
            attachmentRepository.save(attachment);
            return attachment.getId();
        }
        return null;
    }

    public Boolean update (Attachment attachment, Long attachmentId) {
        final Optional<Attachment> entity = attachmentRepository.findById(attachmentId);
        if (entity.isPresent()) {
            entity.get().setName(attachment.getName());
            entity.get().setDescription(attachment.getDescription());
            attachmentRepository.save(entity.get());
            return true;
        }
        return false;
    }

    public Attachment get (Long idAttachment) {
        return attachmentRepository.findById(idAttachment).get();
    }

    public void delete (Long idAttachment) {
        attachmentRepository.deleteById(idAttachment);
    }
}
