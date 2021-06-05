package cn.cupbread.mims.Controller;

import cn.cupbread.mims.Component.RetResponse;
import cn.cupbread.mims.Entity.Inventory;
import cn.cupbread.mims.Entity.Product;
import cn.cupbread.mims.Service.*;
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

@RequestMapping("/product")
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private InventoryService inventoryService;


    @ApiOperation("获取产品-通过id")
    @GetMapping("/id")
    public RetResponse getProductById(Long id) {
        return new RetResponse().makeOKRsp(200, "SUCCESS", productService.getById(id));
    }

    @ApiOperation("获取所有产品")
    @GetMapping("/page")
    public RetResponse getProductPage(@ApiParam(value = "当前页", example = "1", required = true) Long current,
                                      @ApiParam(value = "每页大小", example = "10", required = true) Long size) {
        Page<Product> page = new Page<>(current, size);
        return new RetResponse().makeOKRsp(200, "SUCCESS", productService.page(page));
    }

    @ApiOperation("更新产品")
    @PostMapping("/update")
    public RetResponse updateProduct(Product product) {
        if (productService.updateById(product)) {
            return new RetResponse().makeOKRsp(200, "SUCCESS");
        } else {
            return new RetResponse().makeErrRsp(400, "FAIL");
        }
    }

    @ApiOperation("新增产品")
    @PostMapping("/add")
    public RetResponse addProduct(Product product) {
        if (productService.save(product)) {
            return new RetResponse().makeOKRsp(200, "SUCCESS");
        } else {
            return new RetResponse().makeErrRsp(400, "FAIL");
        }
    }

    @ApiOperation("删除产品")
    @PostMapping("/del")
    public RetResponse delProduct(Long id) {
        QueryWrapper<Inventory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("p_id", id);
        List<Inventory> inventoryList = inventoryService.list(queryWrapper);
        Long quantity = 0L;
        for (Inventory i : inventoryList) {
            quantity += i.getQuantity();
        }
        if (quantity > 0) {
            return new RetResponse().makeErrRsp(400, "已经生产库存，无法删除！");
        } else {
            if (productService.removeById(id)) {
                return new RetResponse().makeOKRsp(200, "SUCCESS");
            } else {
                return new RetResponse().makeErrRsp(400, "FAIL");
            }
        }
    }


}
