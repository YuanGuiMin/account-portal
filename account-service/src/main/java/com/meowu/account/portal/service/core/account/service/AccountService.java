package com.meowu.account.portal.service.core.account.service;

import com.meowu.account.portal.client.account.entity.response.AccountVO;

public interface AccountService{

    void register(String username, String password);

    AccountVO login(String username, String password);
}
