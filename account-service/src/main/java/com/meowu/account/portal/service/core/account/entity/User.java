package com.meowu.account.portal.service.core.account.entity;

import com.meowu.commons.utils.domain.Creatable;
import com.meowu.commons.utils.domain.Entity;
import com.meowu.commons.utils.domain.Updatable;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class User extends Entity implements Creatable, Updatable{

    private String accountId;
    private String nickname;
    private String headImg;
    private String backgroundImg;
    private String description;
    private Gender gender;
    private Date   createTime;
    private Date   updateTime;
}
