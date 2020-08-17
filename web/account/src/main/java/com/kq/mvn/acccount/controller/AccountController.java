package com.kq.mvn.acccount.controller;

import com.kq.dto.DtoResult;
import com.kq.entity.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author kq
 * @date 2020-08-17 14:17
 * @since 2020-0630
 */

@RestController
@Slf4j
public class AccountController {

    @RequestMapping("/account/view/{id}")
    public DtoResult view(@PathVariable("id") String id){

        log.info("查看员工ID :{}", id);

        Account account = new Account();
        account.setId(Long.valueOf(id));
        account.setCreateTime(new Date());

        log.info("员工加载 id={}, data={}",id,account);

        DtoResult result = new DtoResult();
        result.setCode("18800000");
        result.setResult(account);

        return result;

    }

}
