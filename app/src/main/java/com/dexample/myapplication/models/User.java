package com.dexample.myapplication.models;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.Exclude;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by NE_LEADER on 16. 11. 13..
 */
public class User { //유저 클라스

    public String userName;         //기본 레퍼런스
    public String userPassword;
    public String phoneNumber;
    public long level;       //권한의 정도 ban = 1, master = 5
    public Boolean isDoing; //심부름꾼 구했나?
    public Boolean isDeleted; //지워진 정보인가?
    public String userDate; //가입시기

    public User() {}






    public User(String userName, String userPassword, String phoneNumber) {

        //long now = System.currentTimeMillis();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", java.util.Locale.getDefault());
        Date date = new Date();
        String strDate = dateFormat.format(date);
        //시간계산

        this.userName       = userName;
        this.userPassword   = userPassword;
        this.phoneNumber    = phoneNumber;
        this.level          = 2;
        if(userName.equals("KL"))
            this.level = 5;

        this.isDeleted      = false;
        this.isDoing        = false;
        this.userDate       = strDate;
    }



    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("userName", userName);
        result.put("userPassword", userPassword);
        result.put("phoneNumber", phoneNumber);
        result.put("level", level);
        result.put("isDeleted", isDeleted);
        result.put("isDoing", isDoing);
        return result;
    }

    @Exclude
    public static User parseSnapshot(DataSnapshot snapshot) {
        User user = new User();
        user.userName       = (String) snapshot.child("userName").getValue();
        user.userPassword   = (String) snapshot.child("userPassword").getValue();
        user.phoneNumber    = (String) snapshot.child("phoneNumber").getValue();
        user.level    =  (long) snapshot.child("level").getValue();
        user.isDeleted      = (Boolean) snapshot.child("isDeleted").getValue();
        user.isDoing      = (Boolean) snapshot.child("isDoing").getValue();

        return user;
    }
}
