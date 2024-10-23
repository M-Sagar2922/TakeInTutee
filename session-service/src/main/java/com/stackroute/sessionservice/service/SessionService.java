package com.stackroute.sessionservice.service;


import com.stackroute.sessionservice.model.Session;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface SessionService {


    Session getSessionById(String sid);

    Session createSession(Session newSession);

    Session updateSession(Session newSession);

    String deleteSession(String sessionSid);

    List<Session> getAllSessions();



}
