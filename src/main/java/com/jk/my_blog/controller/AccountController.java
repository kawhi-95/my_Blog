package com.jk.my_blog.controller;

import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jk.my_blog.common.dto.LoginDto;
import com.jk.my_blog.common.lang.Result;
import com.jk.my_blog.entity.User;
import com.jk.my_blog.service.UserService;
import com.jk.my_blog.util.JwtUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class AccountController {

    @Autowired
    UserService userService;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse httpServletResponse){

        User user = userService.getOne(new QueryWrapper<User>().eq("username", loginDto.getUsername()));

        Assert.notNull(user, "用户不存在");

        if(!user.getPassword().equals(SecureUtil.md5(loginDto.getPassword()))){
            return Result.fail("密码错误！");
        }

        String jwt = jwtUtils.generateToken(user.getId());
        httpServletResponse.setHeader("Authorization", jwt);
        httpServletResponse.setHeader("Access-control-Epose-Headers", "Authorization");
        return Result.success(MapUtil.builder()
                .put("id", user.getId())
                .put("username", user.getUsername())
                .put("avatar", user.getAvatar())
                .put("email", user.getEmail())
                .map()
        );
    }

    @RequiresAuthentication
    @GetMapping("logout")
    public Result logout(){
        SecurityUtils.getSubject().logout();
        return Result.success(null);
    }
}
