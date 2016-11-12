package com.dexample.myapplication.controllers;

import com.dexample.myapplication.models.Room;
import com.dexample.myapplication.models.User;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by NE_LEADER on 16. 11. 13..
 */
public class RoomController { //유저 객체 관리 클라스



    public String roomName;
    public Boolean isHide;
    public String roomPassword;
    public String roomNumber;
    public String roominfo;     //방 정보
    public String roomdate;     //방 생성시기
    public Boolean isDeleted;

    //방 생성
    public static void createRoom(String roomName, String roomPassword, String roomNumber,String roominfo, String roomsize, Boolean isHide) {
        String key = FirebaseDatabase.getInstance().getReference().child("rooms").push().getKey();

        Room room = new Room(roomName, roomPassword, roomNumber, roominfo ,roomsize, isHide);

        Map<String, Object> roomValues = room.toMap_R();                 //toMap 미리만든 함수

        Map<String, Object> childUpates = new HashMap<>();             //해쉬맵으로 전달
        childUpates.put("/rooms/" + key, roomValues);                  //users 테이블의 key값에 userValues값을 추가

        FirebaseDatabase.getInstance().getReference().updateChildren(childUpates);      //파이어베이스 인스턴스의 레퍼런스의 개체들 업데이트.
    }

    //방정보 수정
    public static void editRoom(Room room, String key) {
        Map<String, Object> roomValues = room.toMap_R();
        Map<String, Object> childUpates = new HashMap<>();
        childUpates.put("/rooms/" + key, roomValues);       //바뀐 정보를 넣어줌

        FirebaseDatabase.getInstance().getReference().updateChildren(childUpates);
    }

    public static void editRoom(String key, Map<String, Object> childUpates) {  //오버로딩// 3가지 조건 중 하나.
        FirebaseDatabase.getInstance().getReference().child("rooms").child(key).updateChildren(childUpates); // 키값과 칠드런 갑으로 바로 수정.
    }


    //방 삭제 - 임의 삭제 똔느 거래완료
    public static void deleteRoom(String key) {
        Map<String, Object> childUpates = new HashMap<>();
        childUpates.put("isDeleted", true); //삭제됨을 알림.
        editRoom(key, childUpates); //유저정보 갱신
    }

    //방 상태 변화 - 입장 또는 퇴장
    public static void RoomEvent(Room room,String key) {

        Map<String, Object> childUpates = new HashMap<>();




     //   //입장이면
     //   if(room.roomsize==room.roomsize_now)
     //       childUpates.put("isHide", true); //방 자동 비공개
     //   else if(room.roomsize>room.roomsize_now)
     //       childUpates.put("isHide", false); //방 자동 비공개
     //   //미완성


        editRoom(key, childUpates); //유저정보 갱신
    }
    ////유저 등급 상승
    //public static void User(String key) {
    //    Map<String, Object> childUpates = new HashMap<>();
    //    childUpates.put("isDeleted", true); //삭제됨을 알림.
    //    editUser(key, childUpates); //유저정보 갱신
    //}

}
