package com.meowu.account.portal.service.core.service;

import com.meowu.account.portal.service.AccountServiceApplication;
import com.meowu.account.portal.service.commons.utils.PasswordUtils;
import com.meowu.account.portal.service.core.account.service.AccountService;
import com.meowu.account.portal.service.core.key.service.KeyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AccountServiceApplication.class)
@ActiveProfiles(value = "development")
public class AccountServiceTest{

    @Autowired
    private KeyService keyService;

    @Autowired
    private AccountService accountService;

    @Test
    public void register(){
        String username = "LaiHoiWah";
        String password = PasswordUtils.encode("0829.RM%", keyService.getPasswordRSAPublicKey());

        accountService.register(username, password);
    }

    @Test
    public void encodePassword(){
        String password = PasswordUtils.encode("0829.RM%", keyService.getPasswordRSAPublicKey());

        System.out.println(password);
    }
}
