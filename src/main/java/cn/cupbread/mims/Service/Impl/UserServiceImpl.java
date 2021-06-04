package cn.cupbread.mims.Service.Impl;

import cn.cupbread.mims.DAO.UserDAO;
import cn.cupbread.mims.Entity.User;
import cn.cupbread.mims.Service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author : Cup Of Bread
 * @version : 1.0.0
 * @date : 2021/6/4
 * @description :
 */

@Service
public class UserServiceImpl extends ServiceImpl<UserDAO, User> implements UserService {
}
