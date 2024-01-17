package com.kitri.myservletboard.dao;

import com.kitri.myservletboard.data.Member;

import java.util.ArrayList;
import java.util.HashMap;

public interface MemberDao {

    public ArrayList<String> getUserIds();

    public HashMap<String,String> getUserPass();

    public void regist(Member meber);
}
