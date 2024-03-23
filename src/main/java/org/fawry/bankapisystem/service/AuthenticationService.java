package org.fawry.bankapisystem.service;

import org.fawry.bankapisystem.dto.auth.AtuthenticationRequest;
import org.fawry.bankapisystem.dto.auth.AuthenticationResponse;
import org.fawry.bankapisystem.dto.auth.RegisterRequest;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AtuthenticationRequest atuthenticationRequest);

    AuthenticationResponse register(RegisterRequest registerRequest);
}
