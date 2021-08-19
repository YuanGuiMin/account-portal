package com.meowu.account.portal.client.account.entity;

import com.google.gson.annotations.SerializedName;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Gender{

    @SerializedName("-1")
    UNKNOWN(-1),

    @SerializedName("0")
    FEMALE(0),

    @SerializedName("1")
    MALE(1),

    ;

    private int id;

    public static Gender getById(Integer id){
        if(id != null){
            for(Gender gender : Gender.values()){
                if(gender.getId() == id){
                    return gender;
                }
            }
        }

        return UNKNOWN;
    }
}
