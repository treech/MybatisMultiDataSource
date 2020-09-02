package com.netposa.template.controller;

import com.netposa.template.model.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"测试接口"})
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @ApiOperation(value = "")
    @PostMapping("")
    public ResponseResult test() {
        return new ResponseResult();
    }

}
