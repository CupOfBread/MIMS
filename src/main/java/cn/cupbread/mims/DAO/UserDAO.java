package cn.cupbread.mims.DAO;

import cn.cupbread.mims.Entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @author : CupOfBread
 * @version : 0.1.0
 * @date : 2021/05/24
 * @description :
 */

@Repository
public interface UserDAO extends BaseMapper<User> {

}
