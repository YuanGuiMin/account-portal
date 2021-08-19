package com.meowu.account.portal.client.account.entity.view;

import com.meowu.account.portal.client.account.entity.*;
import com.meowu.commons.utils.domain.Creatable;
import com.meowu.commons.utils.domain.Deletable;
import com.meowu.commons.utils.domain.Entity;
import com.meowu.commons.utils.domain.Updatable;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AccountVO implements Creatable, Updatable, Deletable{

    private String       token;
    private String       username;
    private String       nickname;
    private String       headImg;
    private String       backgroundImg;
    private String       description;
    private String       email;
    private String       phoneCode;
    private String       phone;
    private Gender       gender;
    private AccountState state;
    private Boolean      deleted;
    private Date         createTime;
    private Date         updateTime;
    private Date         deleteTime;

    public AccountVO(){

    }

    public AccountVO(Account account, User user){
        this.setAccount(account);
        this.setUser(user);
    }

    public AccountVO(String token, Account account, User user){
        this.setToken(token);
        this.setAccount(account);
        this.setUser(user);
    }

    public void setAccount(Account account){
        //账户信息
        if(account != null){
            this.setUsername(account.getUsername());
            this.setState(account.getState());
            this.setDeleted(account.getDeleted());
            this.setCreateTime(account.getCreateTime());
            this.setUpdateTime(account.getUpdateTime());
            this.setDeleteTime(account.getDeleteTime());
        }
    }

    public void setUser(User user){
        //用户信息
        if(user != null){
            this.setNickname(user.getNickname());
            this.setHeadImg(user.getHeadImg());
            this.setBackgroundImg(user.getBackgroundImg());
            this.setDescription(user.getDescription());
            this.setGender(user.getGender());
        }
    }

    public void setEmailAddress(Email email){
        //邮箱信息
        if(email != null){
           this.setEmail(email.getAddress());
        }
    }

    public void setPhoneNumber(Phone phone){
        //手机信息
        if(phone != null){
            this.setPhoneCode(phone.getCode());
            this.setPhone(phone.getNumber());
        }
    }
}
