package org.springframework.samples.petclinic.service;

import org.springframework.samples.petclinic.model.User;

public interface UserService {

    void saveUser(User user) ;

    boolean signIn(String username, String password);

    User findByUsername(String username);
}
