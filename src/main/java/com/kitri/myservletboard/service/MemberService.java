package com.kitri.myservletboard.service;

import com.kitri.myservletboard.dao.MemberDao;
import com.kitri.myservletboard.dao.MemberJdbcDao;

import java.util.ArrayList;

public class MemberService {
    MemberDao memberDao = MemberJdbcDao.getInstance();
    private MemberService(){};
    private static final MemberService instance = new MemberService();

    public static MemberService getInstance() {
        return instance;
    }

    public ArrayList<String> getUserIds() { return memberDao.getUserIds();}
}
