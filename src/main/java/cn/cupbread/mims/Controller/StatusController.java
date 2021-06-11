package cn.cupbread.mims.Controller;

import cn.cupbread.mims.Component.RetResponse;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Cup Of Bread
 * @version : 1.0.0
 * @date : 2020/11/24
 * @description :
 */

@Api(tags = {"响应代码控制类"})
@RestController
@RequestMapping("/status")
public class StatusController {

    @GetMapping("/100")
    public RetResponse Status100(){
        return new RetResponse().makeErrRsp(100,"请求method不支持");
    }

    @GetMapping("/403")
    public RetResponse Status403(){
        return new RetResponse().makeErrRsp(403,"未登录");
    }

    @GetMapping("/404")
    public RetResponse Status404(){
        return new RetResponse().makeErrRsp(404,"Not Found");
    }

}
