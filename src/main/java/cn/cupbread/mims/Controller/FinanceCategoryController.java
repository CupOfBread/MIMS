package cn.cupbread.mims.Controller;

import cn.cupbread.mims.Component.RetResponse;
import cn.cupbread.mims.Entity.FinanceCategory;
import cn.cupbread.mims.Entity.FinanceRecord;
import cn.cupbread.mims.Service.FinanceCategoryService;
import cn.cupbread.mims.Service.FinanceRecordService;
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

/**
 * @author : Cup Of Bread
 * @version : 1.0.0
 * @date : 2021/6/5
 * @description :
 */

@RequestMapping("/bank/c")
@Api(tags = {"财务分类控制类"})
@RestController
public class FinanceCategoryController {

    @Autowired
    private FinanceCategoryService financeCategoryService;

    @Autowired
    private FinanceRecordService financeRecordService;


    @ApiOperation("获取财务分类-通过id")
    @GetMapping("/id")
    public RetResponse getFinanceCategoryById(Long id) {
        return new RetResponse().makeOKRsp(200, "SUCCESS", financeCategoryService.getById(id));
    }

    @ApiOperation("获取所有财务分类")
    @GetMapping("/page")
    public RetResponse getFinanceCategoryPage(@ApiParam(value = "当前页", example = "1", required = true) Long current,
                                   @ApiParam(value = "每页大小", example = "10", required = true) Long size) {
        Page<FinanceCategory> page = new Page<>(current, size);
        return new RetResponse().makeOKRsp(200, "SUCCESS", financeCategoryService.page(page));
    }

    @ApiOperation("更新财务分类")
    @PostMapping("/update")
    public RetResponse updateFinanceCategory(FinanceCategory financeCategory) {
        if (financeCategoryService.updateById(financeCategory)) {
            return new RetResponse().makeOKRsp(200, "SUCCESS");
        } else {
            return new RetResponse().makeErrRsp(400, "FAIL");
        }
    }

    @ApiOperation("新增财务分类")
    @PostMapping("/add")
    public RetResponse addFinanceCategory(FinanceCategory financeCategory) {
        if (financeCategoryService.save(financeCategory)) {
            return new RetResponse().makeOKRsp(200, "SUCCESS");
        } else {
            return new RetResponse().makeErrRsp(400, "FAIL");
        }
    }

    @ApiOperation("删除财务分类")
    @PostMapping("/del")
    public RetResponse delFinanceCategory(Long id) {
        QueryWrapper<FinanceRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("c_id", id);
        if (financeRecordService.count(queryWrapper) > 0) {
            return new RetResponse().makeErrRsp(400, "已有财务记录使用该财务分类，无法删除！");
        } else {
            if (financeCategoryService.removeById(id)) {
                return new RetResponse().makeOKRsp(200, "SUCCESS");
            } else {
                return new RetResponse().makeErrRsp(400, "FAIL");
            }
        }
    }
}
