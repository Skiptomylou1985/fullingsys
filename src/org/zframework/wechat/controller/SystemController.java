package org.zframework.wechat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zframework.web.service.admin.system.LogService;

/**
 * Created by hyf on 2017/9/8.
 */
@Controller
@RequestMapping("/wechat/system")
public class SystemController {
    @Autowired
    private LogService logService;


}