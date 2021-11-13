package com.meowu.account.portal.client.account.entity.response;

import com.meowu.commons.utils.domain.Creatable;
import com.meowu.commons.utils.domain.Deletable;
import com.meowu.commons.utils.domain.Updatable;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AccountVO implements Creatable, Updatable, Deletable{

    private String  token;
    private String  username;
    private String  nickname;
    private String  headImg;
    private String  backgroundImg;
    private String  description;
    private String  email;
    private String  phoneCode;
    private String  phone;
    private Integer gender;
    private Integer state;
    private Boolean deleted;
    private Date    createTime;
    private Date    updateTime;
    private Date    deleteTime;
}
