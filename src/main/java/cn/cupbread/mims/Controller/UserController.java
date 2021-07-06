package cn.cupbread.mims.Controller;

import cn.cupbread.mims.Component.RetResponse;
import cn.cupbread.mims.Entity.FinanceRecord;
import cn.cupbread.mims.Entity.User;
import cn.cupbread.mims.Service.UserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("获取所有用户")
    @GetMapping("/page")
    public RetResponse getUserPage(@ApiParam(value = "当前页", example = "1", required = true) Long current,
                                   @ApiParam(value = "每页大小", example = "10", required = true) Long size) {
        Page<User> page = new Page<>(current, size);
        IPage<User> iPage =userService.page(page);
        return new RetResponse().makeOKRsp(200,"SUCCESS",iPage);
    }


}
