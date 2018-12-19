package com.karol172.lecturesmanager.dto.Conventer;

import com.karol172.lecturesmanager.dto.AttachmentDto;
import com.karol172.lecturesmanager.dto.LectureDto;
import com.karol172.lecturesmanager.dto.UserDto;
import com.karol172.lecturesmanager.model.Attachment;
import com.karol172.lecturesmanager.model.Lecture;
import com.karol172.lecturesmanager.model.Role;
import com.karol172.lecturesmanager.model.User;

import java.util.HashSet;

public class Conventer {

    public static User fromUserDto (UserDto dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setLogin(dto.getLogin());
        user.setPassword(dto.getPassword());
        user.setName(dto.getName());
        user.setRole(Role.valueOf(dto.getRole().toUpperCase()));
        user.setActive(dto.getActive());
        return user;
    }

    public static UserDto fromUser (User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setLogin(user.getLogin());
        dto.setPassword(user.getPassword());
        dto.setName(user.getName());
        dto.setRole(user.getRole().toString().toUpperCase());
        dto.setActive(user.getActive());
        dto.setConductedLectures(new HashSet<>());
        user.getConductedLectures().forEach(l -> dto.getConductedLectures().add(l.getId()));
        dto.setListenedLectures(new HashSet<>());
        user.getListenedLectures().forEach(l -> dto.getListenedLectures().add(l.getId()));
        return dto;
    }

    public static Lecture fromLectureDto (LectureDto dto) {
        Lecture lecture = new Lecture();
        lecture.setId(dto.getId());
        lecture.setTitle(dto.getTitle());
        lecture.setDescription(dto.getDescription());
        lecture.setDate(dto.getDate());
        return lecture;
    }

    public static LectureDto fromLecture (Lecture lecture) {
        LectureDto dto = new LectureDto();
        dto.setId(lecture.getId());
        dto.setTitle(lecture.getTitle());
        dto.setDescription(lecture.getDescription());
        dto.setDate(lecture.getDate());
        dto.setAttachments(new HashSet<>());
        lecture.getAttachments().forEach(a -> dto.getAttachments().add(a.getId()));
        dto.setPresentLiseners(new HashSet<>());
        lecture.getPresentLiseners().forEach(l -> dto.getPresentLiseners().add(l.getId()));
        dto.setSpeakers(new HashSet<>());
        lecture.getSpeakers().forEach(s -> dto.getSpeakers().add(s.getId()));
        return dto;
    }

    public static Attachment fromAttachmentDto (AttachmentDto dto) {
        Attachment attachment = new Attachment();
        attachment.setId(dto.getId());
        attachment.setName(dto.getName());
        attachment.setDescription(dto.getDescription());
        attachment.setUrlAddress(dto.getUrlAddress());
        return attachment;
    }

    public static AttachmentDto fromAttachment (Attachment attachment) {
        AttachmentDto dto = new AttachmentDto();
        dto.setId(attachment.getId());
        dto.setName(attachment.getName());
        dto.setDescription(attachment.getDescription());
        dto.setUrlAddress(attachment.getUrlAddress());
        dto.setLecture(attachment.getLecture().getId());
        return dto;
    }

}