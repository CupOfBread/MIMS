package cn.cupbread.mims.Controller;

import cn.cupbread.mims.Component.RetResponse;
import cn.cupbread.mims.Entity.FinanceRecord;
import cn.cupbread.mims.Entity.FinanceBank;
import cn.cupbread.mims.Service.FinanceRecordService;
import cn.cupbread.mims.Service.ProductService;
import cn.cupbread.mims.Service.FinanceBankService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : Cup Of Bread
 * @version : 1.0.0
 * @date : 2021/6/5
 * @description :
 */

@RequestMapping("/bank")
@Api(tags = {"金融银行控制类"})
@RestController
public class FinanceBankController {

    @Autowired
    private FinanceBankService financeBankService;
    @Autowired
    private ProductService productService;
    @Autowired
    private FinanceRecordService financeRecordService;


    @ApiOperation("获取银行-通过id")
    @GetMapping("/id")
    public RetResponse getFinanceBankById(Long id) {
        return new RetResponse().makeOKRsp(200, "SUCCESS", financeBankService.getById(id));
    }

    @ApiOperation("获取所有银行")
    @GetMapping("/page")
    public RetResponse getFinanceBankPage(@ApiParam(value = "当前页", example = "1", required = true) Long current,
                                   @ApiParam(value = "每页大小", example = "10", required = true) Long size) {
        Page<FinanceBank> page = new Page<>(current, size);
        return new RetResponse().makeOKRsp(200, "SUCCESS", financeBankService.page(page));
    }

    @ApiOperation("更新银行")
    @PostMapping("/update")
    public RetResponse updateFinanceBank(FinanceBank financeBank) {
        if (financeBankService.updateById(financeBank)) {
            return new RetResponse().makeOKRsp(200, "SUCCESS");
        } else {
            return new RetResponse().makeErrRsp(400, "FAIL");
        }
    }

    @ApiOperation("新增银行")
    @PostMapping("/add")
    public RetResponse addFinanceBank(FinanceBank financeBank) {
        if (financeBankService.save(financeBank)) {
            return new RetResponse().makeOKRsp(200, "SUCCESS");
        } else {
            return new RetResponse().makeErrRsp(400, "FAIL");
        }
    }

    @ApiOperation("删除银行")
    @PostMapping("/del")
    public RetResponse delFinanceBank(Long id) {
        QueryWrapper<FinanceRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("bank_id", id);
        if (financeRecordService.count(queryWrapper) > 0) {
            return new RetResponse().makeErrRsp(400, "已有财务记录使用该银行，无法删除！");
        } else {
            if (financeBankService.removeById(id)) {
                return new RetResponse().makeOKRsp(200, "SUCCESS");
            } else {
                return new RetResponse().makeErrRsp(400, "FAIL");
            }
        }
    }
}
