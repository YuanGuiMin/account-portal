package com.meowu.account.portal.service.core.account.httpservice;

import com.meowu.account.portal.client.account.entity.request.AccountRequest;
import com.meowu.account.portal.client.account.entity.response.AccountVO;
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

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response register(@RequestBody AccountRequest request){
        accountService.register(request.getUsername(), request.getPassword());

        return new Response(MessageFormat.format("account[{0}] register successfully", request.getUsername()));
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<AccountVO> login(@RequestBody AccountRequest request){
        AccountVO view = accountService.login(request.getUsername(), request.getPassword());

        return new Response<AccountVO>("account login successfully", view);
    }

    @PostMapping(value = "/logout", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response logout(@RequestHeader("token") String token){
        accountService.logout(token);

        return new Response("account logout successfully");
    }
}
