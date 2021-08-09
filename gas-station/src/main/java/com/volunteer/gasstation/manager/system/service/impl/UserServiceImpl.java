package com.volunteer.gasstation.manager.system.service.impl;

import com.volunteer.gasstation.manager.system.entity.User;
import com.volunteer.gasstation.manager.system.mapper.UserMapper;
import com.volunteer.gasstation.manager.system.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author huoyao
 * @since 2021-08-08
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
