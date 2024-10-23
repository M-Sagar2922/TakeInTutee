package com.stackroute.sessionservice.controller;

import com.stackroute.sessionservice.exceptions.SessionAlreadyExistsException;
import com.stackroute.sessionservice.model.Session;
import com.stackroute.sessionservice.service.SessionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/Session-service")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @PostMapping("session")
    public Session addSession(@RequestBody Session newSession) throws SessionAlreadyExistsException {

        return this.sessionService.createSession(newSession);
    }

    @GetMapping("session/{sid}")
    public Session findSessionById(@PathVariable("sid") String sessionId) {
        return this.sessionService.getSessionById(sessionId);
    }

    @PutMapping("updateSession/{id}")
    public Session updateSession(@RequestBody Session session) {

        return this.sessionService.updateSession(session);
    }

    @DeleteMapping("session/{sid}")
    public String deleteSessionById(@PathVariable String sid) {

        return this.sessionService.deleteSession(sid);
    }


    @GetMapping("sessions")
    public List<Session> findAllSessions() {
        return this.sessionService.getAllSessions();
    }


}
