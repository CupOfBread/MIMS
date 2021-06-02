package cn.cupbread.mims.Service;

import cn.cupbread.mims.Entity.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author : Cup Of Bread
 * @version : 1.0.0
 * @date : 2021/6/1
 * @description :
 */

public interface UserService extends IService<User> {

    User getById(Long id);

    User getByEmail(String email);

    User getByName(String name);

    User getByPhone(String phone);

    IPage<User> get(QueryWrapper<User> queryWrapper);

    IPage<User> get(QueryWrapper<User> queryWrapper, Page<User> userPage);

    Integer add(User user);

    Integer update(User user);

    Integer del(Long id);

}