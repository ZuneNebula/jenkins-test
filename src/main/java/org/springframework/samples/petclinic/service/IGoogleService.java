package org.springframework.samples.petclinic.service;

import net.minidev.json.parser.ParseException;
import org.springframework.samples.petclinic.model.User;

public interface IGoogleService {
    String googlelogin(String redirectUrl);

    String getGoogleAccessToken(String code, String redirectUrl);

    User getGoogleUserProfile(String accessToken) throws ParseException;
}
