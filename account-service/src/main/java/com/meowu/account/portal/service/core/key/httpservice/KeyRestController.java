package com.meowu.account.portal.service.core.key.httpservice;

import com.meowu.account.portal.service.core.key.service.KeyService;
import com.meowu.commons.utils.security.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/1.0/key")
public class KeyRestController{

    @Autowired
    private KeyService keyService;

    @GetMapping(value = "/rsa/public", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<String> getRSAPublicKey(){
        String key = keyService.getRSAPublicKey();

        return new Response<String>("get RSA public key successfully", key);
    }
}
