package com.meowu.account.portal.service.commons.utils;

import com.meowu.account.portal.client.account.entity.response.AccountVO;
import com.meowu.account.portal.service.core.account.entity.*;
import org.apache.commons.lang3.StringUtils;

public class AccountUtils{

    public static AccountVO toView(Account account){
        return toView(account, null, null, null);
    }

    public static AccountVO toView(Account account, User user){
        return toView(account, user, null, null);
    }

    public static AccountVO toView(Account account, User user, Email email, Phone phone){
        AccountVO view = new AccountVO();

        // account
        if(account != null){
            view.setAccountId(account.getId());
            view.setUsername(account.getUsername());
            view.setState(account.getState() == null ? AccountState.ABNORMAL.getId() : account.getState().getId());
            view.setDeleted(account.getDeleted());
            view.setCreateTime(account.getCreateTime());
            view.setUpdateTime(account.getUpdateTime());
            view.setDeleteTime(account.getDeleteTime());
        }

        // user
        if(user != null){
            view.setUserId(user.getId());
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
