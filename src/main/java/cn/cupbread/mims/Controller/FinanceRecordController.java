package cn.cupbread.mims.Controller;

import cn.cupbread.mims.Component.RetResponse;
import cn.cupbread.mims.Entity.*;
import cn.cupbread.mims.Entity.FinanceRecord;
import cn.cupbread.mims.Service.*;
import cn.cupbread.mims.Service.FinanceRecordService;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @author : Cup Of Bread
 * @version : 1.0.0
 * @date : 2021/6/8
 * @description :
 */

@RequestMapping("/finance/record")
@Api(tags = {"财务记录控制类"})
@RestController
public class FinanceRecordController {

    @Autowired
    private FinanceRecordService financeRecordService;
    @Autowired
    private FinanceBankService financeBankService;
    @Autowired
    private FinanceCategoryService financeCategoryService;
    @Autowired
    private UserService userService;


    @ApiOperation("获取财务记录-通过id")
    @GetMapping("/id")
    public RetResponse getFinanceRecordById(Long id) {
        return new RetResponse().makeOKRsp(200, "SUCCESS", financeRecordService.getById(id));
    }

    @ApiOperation("获取所有财务记录")
    @GetMapping("/page")
    public RetResponse getFinanceRecordPage(@ApiParam(value = "当前页", example = "1", required = true) Long current,
                                            @ApiParam(value = "每页大小", example = "10", required = true) Long size,
                                            @ApiParam(value = "开始时间", example = "2021-6-4", required = true) Date startTime,
                                            @ApiParam(value = "结束时间", example = "2021-6-4", required = true) Date endTime,
                                            @ApiParam(value = "银行id", example = "1") Long bankId,
                                            @ApiParam(value = "用户id", example = "1") Long uId,
                                            @ApiParam(value = "分类id", example = "1") Long cId,
                                            @ApiParam(value = "经办人id", example = "1") Long attnId) {
        Page<FinanceRecord> page = new Page<>(current, size);
        //将要查询的字段放入Map
        Map<String, Object> map = new HashMap<>();
        map.put("c_id", cId);
        map.put("bank_id", bankId);
        map.put("attn_id", attnId);
        map.put("u_id", uId);
        QueryWrapper<FinanceRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.allEq(map, false).between("create_time", startTime, endTime).orderByDesc("id");
        IPage<FinanceRecord> iPage = financeRecordService.page(page, queryWrapper);
        List<FinanceRecord> recordList = iPage.getRecords();
        Set<Long> uIdSet = new HashSet<>();
        Set<Long> bIdSet = new HashSet<>();
        Set<Long> aIdSet = new HashSet<>();
        Set<Long> cIdSet = new HashSet<>();
        for (FinanceRecord record : recordList) {
            uIdSet.add(record.getUId());
            bIdSet.add(record.getBankId());
            aIdSet.add(record.getAttnId());
            cIdSet.add(record.getCId());
        }
        List<User> userList = userService.listByIds(uIdSet);
        List<FinanceBank> bankList = financeBankService.listByIds(bIdSet);
        List<User> attendList = userService.listByIds(aIdSet);
        List<FinanceCategory> categoryList = financeCategoryService.listByIds(cIdSet);
        JSONObject res = JSONUtil.createObj();
        res.putOpt("recordList", recordList);
        res.putOpt("userList", userList);
        res.putOpt("bankList", bankList);
        res.putOpt("attendList", attendList);
        res.putOpt("categoryList", categoryList);
        return new RetResponse().makeOKRsp(200, "SUCCESS", res);
    }

    @ApiOperation("新增财务记录")
    @PostMapping("/add")
    public RetResponse addFinanceRecord(FinanceRecord financeRecord) {
        FinanceBank financeBank = financeBankService.getById(financeRecord.getBankId());
        FinanceCategory financeCategory = financeCategoryService.getById(financeRecord.getCId());
        User user = userService.getById(financeRecord.getUId());
        User attend = userService.getById(financeRecord.getAttnId());
        if (financeBank == null || financeCategory == null || user == null || attend == null)
            return new RetResponse().makeErrRsp(400, "信息错误");
        financeRecord.setCreateTime(new Date());
        if (financeRecordService.save(financeRecord)) {
            return new RetResponse().makeOKRsp(200, "SUCCESS");
        } else {
            return new RetResponse().makeErrRsp(400, "FAIL");
        }
    }

    @ApiOperation("删除财务记录")
    @PostMapping("/del")
    public RetResponse delFinanceRecord(Long id) {
        if (financeRecordService.removeById(id)) {
            return new RetResponse().makeOKRsp(200, "SUCCESS");
        } else {
            return new RetResponse().makeErrRsp(400, "FAIL");
        }

    }
}
