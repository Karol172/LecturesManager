package com.karol172.lecturesmanager.controller;

import com.karol172.lecturesmanager.dto.Conventer.Conventer;
import com.karol172.lecturesmanager.dto.LectureDto;
import com.karol172.lecturesmanager.dto.PasswordsDto;
import com.karol172.lecturesmanager.dto.UserDto;
import com.karol172.lecturesmanager.model.Lecture;
import com.karol172.lecturesmanager.model.Role;
import com.karol172.lecturesmanager.model.User;
import com.karol172.lecturesmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user/register")
    public Long create (@RequestBody @Valid UserDto userDto) {
        return userService.createUser(Conventer.fromUserDto(userDto));
    }

    //TODO: Only for user with id
    @PutMapping("/user/{id}")
    public Boolean update (@RequestBody @Valid UserDto userDto, @PathVariable("id") Long userId) {
        return userService.updateUser(userId, Conventer.fromUserDto(userDto));
    }

    //TODO: Only for admin and user with id
    @GetMapping("/user/{id}")
    public User get (@PathVariable("id") Long id) {
        return userService.get(id);
    }

    //TODO: Only for admin
    @GetMapping("/user")
    public List<UserDto> getAll () {
        return userService.getAll().stream().map(u -> Conventer.fromUser(u)).collect(Collectors.toList());
    }

    //TODO: Only for admin
    @DeleteMapping("/user/{id}")
    public void delete (@PathVariable("id") Long id) {
        userService.delete(id);
    }

    @GetMapping("/user/exist/{login}")
    public Boolean checkIfExist (@PathVariable("login") String login) {
        return userService.checkIfExist(login);
    }

    //TODO: User can be activated just by ADMIN
    @GetMapping("/user/{id}/activate/{status}")
    public Boolean setActive (@PathVariable("id") Long loginId, @PathVariable("status") Boolean status) {
        return userService.setActiveForUser(loginId, status);
    }

    //TODO: Role can by changed just by ADMIN
    @GetMapping("/user/{id}/role/{role}")
    public Boolean setRole (@PathVariable("id") Long loginId, @PathVariable("role") String role) {
        return userService.setRoleForUser(loginId, Role.valueOf(role));
    }

    //TODO: Only for user with id and admin
    @GetMapping("/user/{id}/lectures/listened")
    public List<LectureDto> getListenedLectures (@PathVariable("id") Long idUser) {
        return userService.getListenedLecturesForUser(idUser).stream().sorted(Comparator.comparing(Lecture::getDate))
                .map(l -> Conventer.fromLecture(l)).collect(Collectors.toList());
    }

    //TODO: Only for user with id and admin
    @GetMapping("/user/{id}/lectures/conducted")
    public List<LectureDto> getConductedLectures (@PathVariable("id") Long idUser) {
        return userService.getConductedLecturesForUser(idUser).stream().sorted(Comparator.comparing(Lecture::getDate))
                .map(l -> Conventer.fromLecture(l)).collect(Collectors.toList());
    }

    //TODO: Only for user with id
    @PutMapping("/user/{id}/password/change")
    public Boolean changePassword (@PathVariable("id") Long idUser, @RequestBody @Valid PasswordsDto passwordsDto) {
        return userService.changePasswordForUser(idUser, passwordsDto.getOldPassword(), passwordsDto.getNewPassword());
    }

}