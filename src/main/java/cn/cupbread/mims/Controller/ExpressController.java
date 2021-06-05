package cn.cupbread.mims.Controller;

import cn.cupbread.mims.Component.RetResponse;
import cn.cupbread.mims.Entity.Product;
import cn.cupbread.mims.Entity.Express;
import cn.cupbread.mims.Service.ProductService;
import cn.cupbread.mims.Service.ExpressService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

@RequestMapping("/express")
@RestController
public class ExpressController {

    @Autowired
    private ExpressService expressService;
    @Autowired
    private ProductService productService;


    @ApiOperation("获取快递-通过id")
    @GetMapping("/id")
    public RetResponse getExpressById(Long id) {
        return new RetResponse().makeOKRsp(200, "SUCCESS", expressService.getById(id));
    }

    @ApiOperation("获取所有快递")
    @GetMapping("/page")
    public RetResponse getExpressPage(@ApiParam(value = "当前页", example = "1", required = true) Long current,
                                   @ApiParam(value = "每页大小", example = "10", required = true) Long size) {
        Page<Express> page = new Page<>(current, size);
        return new RetResponse().makeOKRsp(200, "SUCCESS", expressService.page(page));
    }

    @ApiOperation("更新快递")
    @PostMapping("/update")
    public RetResponse updateExpress(Express express) {
        if (expressService.updateById(express)) {
            return new RetResponse().makeOKRsp(200, "SUCCESS");
        } else {
            return new RetResponse().makeErrRsp(400, "FAIL");
        }
    }

    @ApiOperation("新增快递")
    @PostMapping("/add")
    public RetResponse addExpress(Express express) {
        if (expressService.save(express)) {
            return new RetResponse().makeOKRsp(200, "SUCCESS");
        } else {
            return new RetResponse().makeErrRsp(400, "FAIL");
        }
    }

    @ApiOperation("删除快递")
    @PostMapping("/del")
    public RetResponse delExpress(Long id) {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("express", id);
        List<Product> productList = productService.list(queryWrapper);
        if (productList.size() > 0) {
            return new RetResponse().makeErrRsp(400, "已有产品使用该快递，无法删除！");
        } else {
            if (expressService.removeById(id)) {
                return new RetResponse().makeOKRsp(200, "SUCCESS");
            } else {
                return new RetResponse().makeErrRsp(400, "FAIL");
            }
        }
    }
}
