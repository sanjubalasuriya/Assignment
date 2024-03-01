package com.microservice.authservice.controller;

import com.microservice.authservice.dto.requestDto.ChangeRoleRequestDto;
import com.microservice.authservice.service.UserService;
import com.microservice.authservice.util.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping( "/isExist/{userId}")
    public ResponseEntity<Boolean> isExist(@PathVariable long userId) {

        boolean message = userService.isExistUser(userId);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PutMapping("/changeRole/{userId}")
    public ResponseEntity<StandardResponse> changeRole(@RequestBody ChangeRoleRequestDto changeRoleRequestDto, @PathVariable long userId) {
        String message = userService.changeRole(changeRoleRequestDto,userId);
        return new ResponseEntity<StandardResponse>(new StandardResponse(201, "success", message)
                , HttpStatus.OK);
    }
}
