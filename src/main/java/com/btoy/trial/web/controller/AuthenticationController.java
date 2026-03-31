package com.btoy.trial.web.controller;

import com.btoy.trial.constants.Authorities;
import com.btoy.trial.model.security.UserModel;
import com.btoy.trial.service.auth.AuthenticationService;
import com.btoy.trial.web.config.propeties.endpoints.EndpointMapping;
import com.btoy.trial.web.response.AppResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.btoy.trial.constants.ApplicationConstants.*;
import static com.btoy.trial.constants.RequestMappingConstants.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@EndpointMapping(value= BASE_AUTH_ENDPOINT)
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping(value = LOG_IN, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse<String>> logIn(@RequestBody UserModel model) {
        service.logIn(model);
        return ResponseEntity.ok(AppResponse.of(EMPTY_STRING));
    }

    @PostMapping(value = REGISTER, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse<String >> register(@RequestBody UserModel model) {
        service.register(model);
        return ResponseEntity.ok(AppResponse.of(EMPTY_STRING));
    }

    @PostMapping(value = LOG_OUT, produces = APPLICATION_JSON_VALUE)
    @PreAuthorize(value= Authorities.AUTHORITY_LOGOUT)
    public ResponseEntity<AppResponse<String>> logOut() {
        service.logout();
        return ResponseEntity.ok(AppResponse.of(EMPTY_STRING));
    }

}
