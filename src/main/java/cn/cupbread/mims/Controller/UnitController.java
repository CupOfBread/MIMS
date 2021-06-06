package cn.cupbread.mims.Controller;

import cn.cupbread.mims.Component.RetResponse;
import cn.cupbread.mims.Entity.Inventory;
import cn.cupbread.mims.Entity.Product;
import cn.cupbread.mims.Entity.Unit;
import cn.cupbread.mims.Service.*;
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

@RequestMapping("/unit")
@Api(tags = {"计量单位控制类"})
@RestController
public class UnitController {

    @Autowired
    private UnitService unitService;
    @Autowired
    private ProductService productService;


    @ApiOperation("获取单位-通过id")
    @GetMapping("/id")
    public RetResponse getUnitById(Long id) {
        return new RetResponse().makeOKRsp(200, "SUCCESS", unitService.getById(id));
    }

    @ApiOperation("获取所有单位")
    @GetMapping("/page")
    public RetResponse getUnitPage(@ApiParam(value = "当前页", example = "1", required = true) Long current,
                                   @ApiParam(value = "每页大小", example = "10", required = true) Long size) {
        Page<Unit> page = new Page<>(current, size);
        return new RetResponse().makeOKRsp(200, "SUCCESS", unitService.page(page));
    }

    @ApiOperation("更新单位")
    @PostMapping("/update")
    public RetResponse updateUnit(Unit unit) {
        if (unitService.updateById(unit)) {
            return new RetResponse().makeOKRsp(200, "SUCCESS");
        } else {
            return new RetResponse().makeErrRsp(400, "FAIL");
        }
    }

    @ApiOperation("新增单位")
    @PostMapping("/add")
    public RetResponse addUnit(Unit unit) {
        if (unitService.save(unit)) {
            return new RetResponse().makeOKRsp(200, "SUCCESS");
        } else {
            return new RetResponse().makeErrRsp(400, "FAIL");
        }
    }

    @ApiOperation("删除单位")
    @PostMapping("/del")
    public RetResponse delUnit(Long id) {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("unit", id);
        List<Product> productList = productService.list(queryWrapper);
        if (productList.size() > 0) {
            return new RetResponse().makeErrRsp(400, "已有产品使用该计量单位，无法删除！");
        } else {
            if (unitService.removeById(id)) {
                return new RetResponse().makeOKRsp(200, "SUCCESS");
            } else {
                return new RetResponse().makeErrRsp(400, "FAIL");
            }
        }
    }
}
