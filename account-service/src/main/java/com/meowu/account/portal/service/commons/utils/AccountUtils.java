package com.meowu.account.portal.service.commons.utils;

import com.meowu.account.portal.client.account.entity.response.AccountVO;
import com.meowu.account.portal.service.core.account.entity.*;
import org.apache.commons.lang3.StringUtils;

public class AccountUtils{

    public static AccountVO toView(Account account){
        return toView(null, account, null, null, null);
    }

    public static AccountVO toView(String token ,Account account){
        return toView(token, account, null, null, null);
    }

    public static AccountVO toView(String token, Account account, User user){
        return toView(token, account, user, null, null);
    }

    public static AccountVO toView(String token, Account account, User user, Email email, Phone phone){
        AccountVO view = new AccountVO();

        // token
        if(StringUtils.isNotBlank(token)){
            view.setToken(token);
        }

        // account
        if(account != null){
            view.setUsername(account.getUsername());
            view.setState(account.getState().getId());
            view.setDeleted(account.getDeleted());
            view.setCreateTime(account.getCreateTime());
            view.setUpdateTime(account.getUpdateTime());
            view.setDeleteTime(account.getDeleteTime());
        }

        // user
        if(user != null){
            view.setNickname(user.getNickname());
            view.setHeadImg(user.getHeadImg());
            view.setBackgroundImg(user.getBackgroundImg());
            view.setDescription(user.getDescription());
            view.setGender(user.getGender() == null ? Gender.UNKNOWN.getId() : user.getGender().getId());
        }

        // email
        if(email != null){
            view.setEmail(email.getAddress());
        }

        // phone
        if(phone != null){
            view.setPhoneCode(phone.getCode());
            view.setPhone(phone.getNumber());
        }

        return view;
    }
}
