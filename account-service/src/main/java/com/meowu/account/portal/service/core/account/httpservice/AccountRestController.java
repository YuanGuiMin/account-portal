package com.meowu.account.portal.service.core.account.httpservice;

import com.meowu.account.portal.client.account.entity.view.AccountVO;
import com.meowu.account.portal.service.core.account.service.AccountService;
import com.meowu.commons.utils.security.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;

@RestController
@RequestMapping(value = "/api/1.0/account")
public class AccountRestController{

    @Autowired
    private AccountService accountService;

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response register(String username, String password){
        accountService.register(username, password);

        return new Response(MessageFormat.format("account[{0}] register successfully", username));
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<AccountVO> login(String username, String password){
        AccountVO view = accountService.login(username, password);

        return new Response<AccountVO>("account login successfully", view);
    }

    @PostMapping(value = "/logout", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response logout(@RequestHeader("token") String token){
        accountService.logout(token);

        return new Response("account logout successfully");
    }
}
