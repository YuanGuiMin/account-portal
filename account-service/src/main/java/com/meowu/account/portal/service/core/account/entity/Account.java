package com.meowu.account.portal.service.core.account.entity;

import com.meowu.commons.utils.domain.Creatable;
import com.meowu.commons.utils.domain.Deletable;
import com.meowu.commons.utils.domain.Entity;
import com.meowu.commons.utils.domain.Updatable;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Account extends Entity implements Creatable, Updatable, Deletable{

    private String       username;
    private String       password;
    private AccountState state;
    private Boolean      deleted;
    private Date         createTime;
    private Date         updateTime;
    private Date         deleteTime;
}
