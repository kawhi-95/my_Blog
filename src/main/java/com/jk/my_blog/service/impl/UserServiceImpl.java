package com.jk.my_blog.service.impl;

import com.jk.my_blog.entity.User;
import com.jk.my_blog.mapper.UserMapper;
import com.jk.my_blog.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 玖拾伍
 * @since 2023-08-26
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
