package com.qingcheng.controller;


import com.qingcheng.pojo.goods.Brand;
import com.qingcheng.service.goods.BrandService;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {


    //注入远程的业务接口，因为是dubbo
    @Reference
    private BrandService brandService;

    @RequestMapping("/findAll")
    public List<Brand> findAll(){

        return brandService.findAll();
    }
}
