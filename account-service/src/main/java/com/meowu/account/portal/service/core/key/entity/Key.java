package com.meowu.account.portal.service.core.key.entity;

import com.meowu.commons.utils.domain.Creatable;
import com.meowu.commons.utils.domain.Entity;
import com.meowu.commons.utils.domain.Updatable;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Key extends Entity implements Creatable, Updatable{

    private String name;
    private String content;
    private Date   createTime;
    private Date   updateTime;
}
