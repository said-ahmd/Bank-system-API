package org.fawry.bankapisystem.service;

import org.fawry.bankapisystem.dto.AtuthenticationRequest;
import org.fawry.bankapisystem.dto.AuthenticationResponse;
import org.fawry.bankapisystem.dto.RegisterRequest;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AtuthenticationRequest atuthenticationRequest);

    AuthenticationResponse register(RegisterRequest registerRequest);
}
