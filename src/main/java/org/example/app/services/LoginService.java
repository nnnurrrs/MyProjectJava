package org.example.app.services;
import org.example.web.dto.LoginForm;
import org.springframework.stereotype.Service;

import org.apache.log4j.*;

@Service
public class LoginService {

    private final Logger logger = Logger.getLogger(LoginService.class);

    public boolean authenticate(LoginForm loginFrom) {
        logger.info("try auth with user-form: " + loginFrom);
        return loginFrom.getUsername().equals("root") && loginFrom.getPassword().equals("123");

    }
}
