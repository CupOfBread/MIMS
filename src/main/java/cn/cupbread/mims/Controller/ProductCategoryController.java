package cn.cupbread.mims.Controller;

import cn.cupbread.mims.Component.RetResponse;
import cn.cupbread.mims.Entity.Product;
import cn.cupbread.mims.Entity.ProductCategory;
import cn.cupbread.mims.Service.ProductService;
import cn.cupbread.mims.Service.ProductCategoryService;
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

@RequestMapping("/product/category")
@Api(tags = {"产品分类控制类"})
@RestController
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private ProductService productService;


    @ApiOperation("获取产品分类-通过id")
    @GetMapping("/id")
    public RetResponse getProductCategoryById(Long id) {
        return new RetResponse().makeOKRsp(200, "SUCCESS", productCategoryService.getById(id));
    }

    @ApiOperation("获取所有产品分类")
    @GetMapping("/page")
    public RetResponse getProductCategoryPage(@ApiParam(value = "当前页", example = "1", required = true) Long current,
                                   @ApiParam(value = "每页大小", example = "10", required = true) Long size) {
        Page<ProductCategory> page = new Page<>(current, size);
        return new RetResponse().makeOKRsp(200, "SUCCESS", productCategoryService.page(page));
    }

    @ApiOperation("更新产品分类")
    @PostMapping("/update")
    public RetResponse updateProductCategory(ProductCategory productCategory) {
        if (productCategoryService.updateById(productCategory)) {
            return new RetResponse().makeOKRsp(200, "SUCCESS");
        } else {
            return new RetResponse().makeErrRsp(400, "FAIL");
        }
    }

    @ApiOperation("新增产品分类")
    @PostMapping("/add")
    public RetResponse addProductCategory(ProductCategory productCategory) {
        if (productCategoryService.save(productCategory)) {
            return new RetResponse().makeOKRsp(200, "SUCCESS");
        } else {
            return new RetResponse().makeErrRsp(400, "FAIL");
        }
    }

    @ApiOperation("删除产品分类")
    @PostMapping("/del")
    public RetResponse delProductCategory(Long id) {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cid", id);
        List<Product> productList = productService.list(queryWrapper);
        if (productList.size() > 0) {
            return new RetResponse().makeErrRsp(400, "已有产品使用该产品分类，无法删除！");
        } else {
            if (productCategoryService.removeById(id)) {
                return new RetResponse().makeOKRsp(200, "SUCCESS");
            } else {
                return new RetResponse().makeErrRsp(400, "FAIL");
            }
        }
    }
}
