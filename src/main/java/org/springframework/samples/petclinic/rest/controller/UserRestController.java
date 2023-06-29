/*
 * Copyright 2016-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.samples.petclinic.rest.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.mapper.UserMapper;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.rest.api.UsersApi;
import org.springframework.samples.petclinic.rest.dto.UserDto;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.samples.petclinic.util.CookieUtil;
import org.springframework.samples.petclinic.util.JWTUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("api")
public class UserRestController implements UsersApi {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserRestController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }


    @PreAuthorize( "hasRole(@roles.ADMIN)" )
    @Override
    public ResponseEntity<UserDto> addUser(UserDto userDto) {
        HttpHeaders headers = new HttpHeaders();
        User user = userMapper.toUser(userDto);
        this.userService.saveUser(user);
        return new ResponseEntity<>(userMapper.toUserDto(user), headers, HttpStatus.CREATED);
    }

    @PostMapping("/users/signin")
    public ResponseEntity<UserDto> signIn(@Valid @RequestBody UserDto userDto, HttpServletResponse res) {
        HttpHeaders headers = new HttpHeaders();
        User user = userMapper.toUser(userDto);
        if(this.userService.signIn(user.getUsername(), user.getPassword()))
        {
            String jwtToken = JWTUtil.addJWTToken(res, user);
            CookieUtil.create(res, "JWT-TOKEN", jwtToken, false, -1, "localhost");
            return new ResponseEntity<>(userMapper.toUserDto(user), headers, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(userMapper.toUserDto(user), headers, HttpStatus.UNAUTHORIZED);
        }
    }
}
