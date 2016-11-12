package com.dexample.myapplication.controllers;

import com.dexample.myapplication.models.User;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by NE_LEADER on 16. 11. 13..
 */
public class UserController { //유저 객체 관리 클라스
    //유저 객체 생성 - 회원가입
    public static void createUser(String userName, String userPassword, String phoneNumber) {
        String key = FirebaseDatabase.getInstance().getReference().child("users").push().getKey();

        User user = new User(userName, userPassword, phoneNumber);

        Map<String, Object> userValues = user.toMap();                 //toMap 미리만든 함수

        Map<String, Object> childUpates = new HashMap<>();             //해쉬맵으로 전달
        childUpates.put("/users/" + key, userValues);                  //users 테이블의 key값에 userValues값을 추가

        FirebaseDatabase.getInstance().getReference().updateChildren(childUpates);      //파이어베이스 인스턴스의 레퍼런스의 개체들 업데이트.
    }

    //유저 정보 수정 - 개인정보
    public static void editUser(User user, String key) {
        Map<String, Object> userValues = user.toMap();
        Map<String, Object> childUpates = new HashMap<>();
        childUpates.put("/users/" + key, userValues);       //바뀐 정보를 넣어줌

        FirebaseDatabase.getInstance().getReference().updateChildren(childUpates);
    }

    public static void editUser(String key, Map<String, Object> childUpates) {  //오버로딩// 3가지 조건 중 하나.
        FirebaseDatabase.getInstance().getReference().child("users").child(key).updateChildren(childUpates); // 키값과 칠드런 갑으로 바로 수정.
    }


    //유저 정보 삭제
    public static void deleteUser(String key) {
        Map<String, Object> childUpates = new HashMap<>();
        childUpates.put("isDeleted", true); //삭제됨을 알림.
        editUser(key, childUpates); //유저정보 갱신
    }

    //유저 상태변화
    public static void UserEvent(String key) {
        Map<String, Object> childUpates = new HashMap<>();
        childUpates.put("isDoing", true); //일할 사람을 구함..
        editUser(key, childUpates); //유저정보 갱신
    }
    ////유저 등급 상승
    //public static void User(String key) {
    //    Map<String, Object> childUpates = new HashMap<>();
    //    childUpates.put("isDeleted", true); //삭제됨을 알림.
    //    editUser(key, childUpates); //유저정보 갱신
    //}

}
