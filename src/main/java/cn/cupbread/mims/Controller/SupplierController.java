package cn.cupbread.mims.Controller;

import cn.cupbread.mims.Component.RetResponse;
import cn.cupbread.mims.Entity.Product;
import cn.cupbread.mims.Entity.ProductInRecord;
import cn.cupbread.mims.Entity.Supplier;
import cn.cupbread.mims.Service.ProductInRecordService;
import cn.cupbread.mims.Service.ProductService;
import cn.cupbread.mims.Service.SupplierService;
import cn.hutool.core.date.DateUtil;
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

@RequestMapping("/supplier")
@RestController
public class SupplierController {

    @Autowired
    private SupplierService supplierService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductInRecordService productInRecordService;


    @ApiOperation("获取供应商-通过id")
    @GetMapping("/id")
    public RetResponse getSupplierById(Long id) {
        return new RetResponse().makeOKRsp(200, "SUCCESS", supplierService.getById(id));
    }

    @ApiOperation("获取所有供应商")
    @GetMapping("/page")
    public RetResponse getSupplierPage(@ApiParam(value = "当前页", example = "1", required = true) Long current,
                                   @ApiParam(value = "每页大小", example = "10", required = true) Long size) {
        Page<Supplier> page = new Page<>(current, size);
        return new RetResponse().makeOKRsp(200, "SUCCESS", supplierService.page(page));
    }

    @ApiOperation("更新供应商")
    @PostMapping("/update")
    public RetResponse updateSupplier(Supplier supplier) {
        supplier.setUpdateTime(DateUtil.date());
        if (supplierService.updateById(supplier)) {
            return new RetResponse().makeOKRsp(200, "SUCCESS");
        } else {
            return new RetResponse().makeErrRsp(400, "FAIL");
        }
    }

    @ApiOperation("新增供应商")
    @PostMapping("/add")
    public RetResponse addSupplier(Supplier supplier) {
        supplier.setCreateTime(DateUtil.date());
        supplier.setUpdateTime(DateUtil.date());
        if (supplierService.save(supplier)) {
            return new RetResponse().makeOKRsp(200, "SUCCESS");
        } else {
            return new RetResponse().makeErrRsp(400, "FAIL");
        }
    }

    @ApiOperation("删除供应商")
    @PostMapping("/del")
    public RetResponse delSupplier(Long id) {
        QueryWrapper<ProductInRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("s_id", id);
        if (productInRecordService.count(queryWrapper) > 0) {
            return new RetResponse().makeErrRsp(400, "已有入库记录使用该供应商，无法删除！");
        } else {
            if (supplierService.removeById(id)) {
                return new RetResponse().makeOKRsp(200, "SUCCESS");
            } else {
                return new RetResponse().makeErrRsp(400, "FAIL");
            }
        }
    }
}
