package com.meowu.account.portal.service.core.account.httpservice;

import com.meowu.account.portal.client.account.entity.view.AccountVO;
import com.meowu.account.portal.service.core.account.service.AccountService;
import com.meowu.account.portal.service.core.account.service.TokenService;
import com.meowu.commons.utils.security.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/1.0/token")
public class TokenRestController{

    @Autowired
    private AccountService accountService;

    @Autowired
    private TokenService tokenService;

    @PostMapping(value = "/refresh/{token}")
    public Response<Long> refreshExpireTime(@PathVariable("token") String token){
        Long expire = tokenService.refreshExpireTime(token);

        return new Response<Long>("refresh token successfully", expire);
    }

    @GetMapping(value = "/expire/{token}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<Long> expireTime(@PathVariable("token") String token){
        Long expire = tokenService.expireTime(token);

        return new Response<Long>("get token expire time successfully", expire);
    }

    @GetMapping(value = "/{token}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<AccountVO> getByToken(@PathVariable("token") String token){
        AccountVO view = accountService.getByToken(token);

        return new Response<AccountVO>("get account successfully", view);
    }
}
