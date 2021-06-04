package cn.cupbread.mims.Service.Impl;

import cn.cupbread.mims.DAO.ExpressDAO;
import cn.cupbread.mims.Entity.Express;
import cn.cupbread.mims.Service.ExpressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author : Cup Of Bread
 * @version : 1.0.0
 * @date : 2021/6/4
 * @description :
 */

@Service
public class ExpressServiceImpl extends ServiceImpl<ExpressDAO, Express> implements ExpressService {
}
