package cn.cupbread.mims.Controller;

import cn.cupbread.mims.Component.RetResponse;
import cn.cupbread.mims.Entity.Product;
import cn.cupbread.mims.Entity.WarehouseTransfer;
import cn.cupbread.mims.Service.ProductService;
import cn.cupbread.mims.Service.WarehouseTransferService;
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

@RequestMapping("/warehouse_t")
@Api(tags = {"计量单位控制类"})
@RestController
public class WarehouseTransferController {

    @Autowired
    private WarehouseTransferService warehouseTransferService;
    @Autowired
    private ProductService productService;


    @ApiOperation("获取单位-通过id")
    @GetMapping("/id")
    public RetResponse getWarehouseTransferById(Long id) {
        return new RetResponse().makeOKRsp(200, "SUCCESS", warehouseTransferService.getById(id));
    }

    @ApiOperation("获取所有单位")
    @GetMapping("/page")
    public RetResponse getWarehouseTransferPage(@ApiParam(value = "当前页", example = "1", required = true) Long current,
                                   @ApiParam(value = "每页大小", example = "10", required = true) Long size) {
        Page<WarehouseTransfer> page = new Page<>(current, size);
        return new RetResponse().makeOKRsp(200, "SUCCESS", warehouseTransferService.page(page));
    }

    @ApiOperation("更新单位")
    @PostMapping("/update")
    public RetResponse updateWarehouseTransfer(WarehouseTransfer warehouseTransfer) {
        if (warehouseTransferService.updateById(warehouseTransfer)) {
            return new RetResponse().makeOKRsp(200, "SUCCESS");
        } else {
            return new RetResponse().makeErrRsp(400, "FAIL");
        }
    }

    @ApiOperation("新增单位")
    @PostMapping("/add")
    public RetResponse addWarehouseTransfer(WarehouseTransfer warehouseTransfer) {
        if (warehouseTransferService.save(warehouseTransfer)) {
            return new RetResponse().makeOKRsp(200, "SUCCESS");
        } else {
            return new RetResponse().makeErrRsp(400, "FAIL");
        }
    }

    @ApiOperation("删除单位")
    @PostMapping("/del")
    public RetResponse delWarehouseTransfer(Long id) {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("warehouseTransfer", id);
        List<Product> productList = productService.list(queryWrapper);
        if (productList.size() > 0) {
            return new RetResponse().makeErrRsp(400, "已有产品使用该计量单位，无法删除！");
        } else {
            if (warehouseTransferService.removeById(id)) {
                return new RetResponse().makeOKRsp(200, "SUCCESS");
            } else {
                return new RetResponse().makeErrRsp(400, "FAIL");
            }
        }
    }
}
