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
public class Room { //방 클라스

    public String roomName;
    public Boolean isHide;
    public String roomPassword;
    public String roomNumber;
    public String roominfo;     //방 정보
    public String roomdate;     //방 생성시기
    public String roomsize;        //총 인원수
    public String roomsize_now;    //현 인원수
    public Boolean isDeleted;


    public Room() {}


    public Room(String roomName, String roomPassword, String roomNumber,String roominfo, String roomsize, boolean isHide) {

        //방생성시기
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", java.util.Locale.getDefault());
        Date date = new Date();
        String strDate = dateFormat.format(date);

        this.roomName       = roomName;
        this.roomPassword   = roomPassword;
        this.roomNumber  = "준비중입니다.";
        this.roominfo       = roominfo;
        this.roomsize = roomsize;
        this.isHide   = isHide;
        this.roomdate    = strDate;
        this.isDeleted      = false;

        if(this.roomsize_now!=null &&this.roomsize!=null) {
            int a = Integer.parseInt(this.roomsize_now);
            int b = Integer.parseInt(this.roomsize);
            if (b > a)
                a++;
            this.roomsize_now = a + "";
        }

    }


    @Exclude
    public Map<String, Object> toMap_R() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("roomName", roomName);
        result.put("roomPassword", roomPassword);
        result.put("roomNumber", roomNumber);
        result.put("roominfo", roominfo);
        result.put("roomsize",roomsize);
        result.put("roomsize_now",roomsize_now);
        result.put("isHide", isHide);
        result.put("roomdate", roomdate);
        result.put("isDeleted", isDeleted);
        return result;
    }

    @Exclude
    public static Room parseSnapshot(DataSnapshot snapshot) {
        Room room = new Room();
        room.roomName       = (String) snapshot.child("roomName").getValue();
        room.roomPassword   = (String) snapshot.child("roomPassword").getValue();
        room.roomNumber    = (String) snapshot.child("roomNumber").getValue();
        room.roominfo       = (String) snapshot.child("roominfo").getValue();
        room.roomsize       = (String) snapshot.child("roomsize").getValue();
        room.roomsize_now       =(String) snapshot.child("roomsize_now").getValue();
        room.isHide   = (Boolean) snapshot.child("isHide").getValue();
        room.roomdate    = (String) snapshot.child("roomdate").getValue();
        room.isDeleted      = (Boolean) snapshot.child("isDeleted").getValue();

        return room;
    }
}
