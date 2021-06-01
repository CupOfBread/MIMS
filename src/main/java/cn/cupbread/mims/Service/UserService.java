package cn.cupbread.mims.Service;

import cn.cupbread.mims.Entity.User;

/**
 * @author : Cup Of Bread
 * @version : 1.0.0
 * @date : 2021/6/1
 * @description :
 */

public interface UserService {

    User getUserById(Long id);

    User getUserByEmail(String email);

    User getUserByName(String name);

    User getUserByPhone(String phone);

    Integer addUser(User user);

    Integer updateUser(User user);

    Integer delUser(Long id);




}
