package cn.cupbread.mims.Service.Impl;

import cn.cupbread.mims.DAO.FinanceBankDAO;
import cn.cupbread.mims.Entity.FinanceBank;
import cn.cupbread.mims.Service.FinanceBankService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author : Cup Of Bread
 * @version : 1.0.0
 * @date : 2021/6/4
 * @description :
 */

@Service
public class FinanceBankServiceImpl extends ServiceImpl<FinanceBankDAO, FinanceBank> implements FinanceBankService {
}
