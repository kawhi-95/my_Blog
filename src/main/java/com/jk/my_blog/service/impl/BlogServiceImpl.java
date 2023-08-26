package com.jk.my_blog.service.impl;

import com.jk.my_blog.entity.Blog;
import com.jk.my_blog.mapper.BlogMapper;
import com.jk.my_blog.service.BlogService;
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
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}
