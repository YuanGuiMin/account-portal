package com.meowu.account.portal.client.account.entity;

import com.meowu.commons.utils.domain.Creatable;
import com.meowu.commons.utils.domain.Entity;
import com.meowu.commons.utils.domain.Updatable;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Phone extends Entity implements Creatable, Updatable{

    private String accountId;
    private String code;
    private String number;
    private Date   createTime;
    private Date   updateTime;
}
