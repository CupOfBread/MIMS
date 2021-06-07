package cn.cupbread.mims.Service.Impl;

import cn.cupbread.mims.DAO.FinanceCategoryDAO;
import cn.cupbread.mims.Entity.FinanceCategory;
import cn.cupbread.mims.Service.FinanceCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author : Cup Of Bread
 * @version : 1.0.0
 * @date : 2021/6/7
 * @description :
 */

@Service
public class FinanceCategoryServiceImpl extends ServiceImpl<FinanceCategoryDAO, FinanceCategory> implements FinanceCategoryService {
}
