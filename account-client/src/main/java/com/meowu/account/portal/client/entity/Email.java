package com.meowu.account.portal.client.entity;

import com.meowu.commons.utils.domain.Creatable;
import com.meowu.commons.utils.domain.Entity;
import com.meowu.commons.utils.domain.Updatable;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Email extends Entity implements Creatable, Updatable{

    private String accountId;
    private String address;
    private Date   createTime;
    private Date   updateTime;
}
