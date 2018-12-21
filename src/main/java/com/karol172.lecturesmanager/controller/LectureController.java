package com.karol172.lecturesmanager.controller;

import com.karol172.lecturesmanager.dto.Conventer.Conventer;
import com.karol172.lecturesmanager.dto.LectureDto;
import com.karol172.lecturesmanager.service.LectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class LectureController {

    private LectureService lectureService;

    @Autowired
    public LectureController(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    //TODO: Only for admin
    @PostMapping("/lecture")
    public Long create (@RequestBody @Valid LectureDto dto) {
        return lectureService.create(Conventer.fromLectureDto(dto), dto.getSpeakers());
    }

    //TODO: Only for admin and speakers
    @PutMapping("/lecture/{id}")
    public Boolean update (@RequestBody @Valid LectureDto dto, @PathVariable("id") Long idLecture) {
        return lectureService.update(Conventer.fromLectureDto(dto), idLecture);
    }

    //TODO: Only for admin and speakers and listeners
    @GetMapping("/lecture/{id}")
    public LectureDto get (@PathVariable("id") Long id) {
        return Conventer.fromLecture(lectureService.get(id));
    }

    //TODO: Only for admin
    @GetMapping("/lecture")
    public List<LectureDto> getAll () {
        return lectureService.getAll().map(l -> Conventer.fromLecture(l)).collect(Collectors.toList());
    }

    //TODO: Only for admin
    @DeleteMapping("/lecture/{id}")
    public void delete (@PathVariable("id") Long id) {
        lectureService.delete(id);
    }

    //TODO: Only for admin and speaker
    @GetMapping("/lecture/{lectureId}/user/{userId}")
    public Boolean addSpeaker (@PathVariable("lectureId") Long idLecture, @PathVariable("userId") Long idUser) {
        return lectureService.addSpeaker(idLecture, idUser);
    }

    //TODO: Only for admin and speaker
    @DeleteMapping("/lecture/{lectureId}/user/{userId}")
    public Boolean removeSpeaker (@PathVariable("lectureId") Long idLecture, @PathVariable("userId") Long idUser) {
        return lectureService.removeSpeaker(idLecture, idUser);
    }

    //TODO: Only for speaker
    @GetMapping("/lecture/{lectureId}/user/{userId}/presence")
    public Boolean confirmPresence (@PathVariable("lectureId") Long idLecture, @PathVariable("userId") Long idUser) {
        return lectureService.confirmUserPresence(idLecture, idUser);
    }

    //TODO: Only for admin
    @GetMapping("/lecture/{lectureId}/user/{userId}/presence/{status}")
    public Boolean setPresence (@PathVariable("lectureId") Long idLecture, @PathVariable("userId") Long idUser,
                                @PathVariable("status") Boolean status) {
        return lectureService.setPresenceForUser(idLecture, idUser,status);
    }

    @GetMapping("/lecture/incoming")
    public List<LectureDto> getIncomingLectures () {
        return lectureService.getIncomingLectures().stream().map(l -> Conventer.fromLecture(l))
                .collect(Collectors.toList());
    }

}