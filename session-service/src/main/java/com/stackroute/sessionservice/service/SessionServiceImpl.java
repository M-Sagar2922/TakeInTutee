package com.stackroute.sessionservice.service;


import com.stackroute.sessionservice.model.Session;
import org.springframework.beans.factory.annotation.Autowired;
import com.stackroute.sessionservice.repository.SessionRepository;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SessionServiceImpl implements SessionService {


    @Autowired
    private SessionRepository sessionRepo;


    @Override
    public Session getSessionById(String sid) {
        try {
            Optional<Session> optSession = this.sessionRepo.findById(sid);
            return optSession.get();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Session createSession(Session newSession) {
        try {
            return this.sessionRepo.save(newSession);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Session updateSession(Session session) {
        try {
            return this.sessionRepo.save(session);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public String deleteSession(String deleteSid) {
        try {
            this.sessionRepo.deleteById(deleteSid);
            return "Session deleted";
        } catch (Exception e) {
            throw e;
        }
    }


    @Override
    public List<Session> getAllSessions() {
        try {
            return this.sessionRepo.findAll();
        } catch (Exception e) {
            throw e;
        }
    }
}
