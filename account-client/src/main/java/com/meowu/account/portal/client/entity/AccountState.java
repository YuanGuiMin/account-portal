package com.meowu.account.portal.client.entity;

import com.google.gson.annotations.SerializedName;
import com.meowu.account.portal.client.security.exception.InvalidAccountStateException;
import com.meowu.commons.utils.utils.AssertUtils;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum AccountState{

    @SerializedName("0")
    NORMAL(0),

    @SerializedName("1")
    LOCKED(1),

    @SerializedName("2")
    FROZEN(2),

    ;

    private int id;

    public static AccountState getById(Integer id){
        AssertUtils.notNull(id, "account state id must not be null");

        for(AccountState state : AccountState.values()){
            if(state.getId() == id){
                return state;
            }
        }

        throw new InvalidAccountStateException("invalid state id: [{0}]", id);
    }
}
