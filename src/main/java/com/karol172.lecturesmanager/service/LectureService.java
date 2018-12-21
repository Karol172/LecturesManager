package com.karol172.lecturesmanager.service;

import com.karol172.lecturesmanager.model.Lecture;
import com.karol172.lecturesmanager.model.User;
import com.karol172.lecturesmanager.repository.LectureRepository;
import com.karol172.lecturesmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class LectureService {

    private LectureRepository lectureRepository;

    private UserRepository userRepository;

    @Autowired
    public LectureService(LectureRepository lectureRepository, UserRepository userRepository) {
        this.lectureRepository = lectureRepository;
        this.userRepository = userRepository;
    }

    public Long create (Lecture lecture, Collection<Long> speakersId) {
        Set<User> speakers = speakersId.stream()
                .map(id -> userRepository.findById(id).get()).collect(Collectors.toSet());
        if (speakers.size() == speakersId.size()) {
            lecture.setSpeakers(new HashSet<>());
            lecture.setId(null);
            speakers.forEach(u -> u.addConductedLecture(lecture));
            lectureRepository.save(lecture);
            return lecture.getId();
        }
        return null;
    }

    public Boolean update (Lecture lecture, Long lectureId) {
        final Optional<Lecture> entity = lectureRepository.findById(lectureId);
        if (entity.isPresent()) {
            entity.get().setDescription(lecture.getDescription());
            entity.get().setTitle(lecture.getTitle());
            lectureRepository.save(entity.get());
        }
        return entity.isPresent();
    }

    public Lecture get (Long id) {
        return lectureRepository.findById(id).get();
    }


    public Stream<Lecture> getAll () {
        return lectureRepository.findAll().stream().sorted(Comparator.comparing(Lecture::getDate));
    }

    //TODO: removing files from server
    public void delete (Long id) {
        final Optional<Lecture> entity = lectureRepository.findById(id);
        if (entity.isPresent()) {
            entity.get().getSpeakers().forEach(s -> s.removeConductedLecture(entity.get()));
            entity.get().getPresentLiseners().forEach(l -> l.removeListenedLecture(entity.get()));
            lectureRepository.save(entity.get());
            lectureRepository.deleteById(id);
        }
    }

    public Boolean addSpeaker (Long idLecture, Long idUser) {
        final Optional<Lecture> lecture = lectureRepository.findById(idLecture);
        final Optional<User> user = userRepository.findById(idUser);

        if (lecture.isPresent() && user.isPresent()) {
            user.get().addConductedLecture(lecture.get());
            lectureRepository.save(lecture.get());
        }
        return lecture.isPresent() && user.isPresent();
    }

    public Boolean removeSpeaker (Long idLecture, Long idUser) {
        final Optional<Lecture> lecture = lectureRepository.findById(idLecture);
        final Optional<User> user = userRepository.findById(idUser);

        if (lecture.isPresent() && user.isPresent()) {
            user.get().removeConductedLecture(lecture.get());
            lectureRepository.save(lecture.get());
        }
        return lecture.isPresent() && user.isPresent();
    }

    public Boolean confirmUserPresence (Long idLecture, Long idUser) {
        final Optional<Lecture> lecture = lectureRepository.findById(idLecture);
        final Optional<User> user = userRepository.findById(idUser);

        if (lecture.isPresent() && user.isPresent()) {
            user.get().addListenedLecture(lecture.get());
            lectureRepository.save(lecture.get());
        }
        return lecture.isPresent() && user.isPresent();
    }

    public Boolean setPresenceForUser (Long idLecture, Long idUser, Boolean status) {
        final Optional<Lecture> lecture = lectureRepository.findById(idLecture);
        final Optional<User> user = userRepository.findById(idUser);

        if (lecture.isPresent() && user.isPresent() &&
                !(status & user.get().getListenedLectures().contains(lecture.get()))) {
            if (status)
                user.get().addListenedLecture(lecture.get());
            else
                user.get().removeListenedLecture(lecture.get());
            lectureRepository.save(lecture.get());
        }
        return lecture.isPresent() && user.isPresent();
    }

    public List<Lecture> getIncomingLectures () {
        return lectureRepository.findByDateAfterOrderByDateDesc(LocalDateTime.now());
    }

}