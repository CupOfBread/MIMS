package cn.cupbread.mims.Service.Impl;

import cn.cupbread.mims.DAO.UnitDAO;
import cn.cupbread.mims.Entity.Unit;
import cn.cupbread.mims.Service.UnitService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author : Cup Of Bread
 * @version : 1.0.0
 * @date : 2021/6/4
 * @description :
 */

@Service
public class UnitServiceImpl extends ServiceImpl<UnitDAO, Unit> implements UnitService {
}
