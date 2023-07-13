package com.qhit.baseFunction.controller;

import com.alibaba.fastjson.JSON;
import com.qhit.baseFunction.pojo.BaseFunction;
import com.qhit.baseFunction.service.IBaseFunctionService;
import com.qhit.baseFunction.service.impl.BaseFunctionServiceImpl;
import com.qhit.baseModule.pojo.BaseModule;
import com.qhit.baseModule.service.IBaseModuleService;
import com.qhit.baseModule.service.impl.BaseModuleServiceImpl;
import com.qhit.baseUser.pojo.BaseUser;
import com.qhit.utils.CommonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

/**
 * Created by Administrator on 2018/11/28 0028.
 */
@Controller
@RequestMapping("/baseFunction")
public class baseFunctionController {
    private IBaseFunctionService iBaseFunctionService=new BaseFunctionServiceImpl();
    private IBaseModuleService iBaseModuleService=new BaseModuleServiceImpl();
   //遍历所有
    @RequestMapping("/list")
    public String list(HttpServletRequest request){
        List<BaseFunction> list = iBaseFunctionService.findsAll();
        request.setAttribute("list",list);
        return "baseFunction/list";
    }
    @RequestMapping("/lists")
    public String lists(HttpServletRequest request){
        List<BaseModule> list = iBaseModuleService.findAll();
        request.setAttribute("list",list);
        return "baseFunction/add";
    }
    //添加
    @RequestMapping("/insert")
    public String insert(BaseModule baseModules,BaseFunction baseFunction){
        BaseModule baseModule = iBaseModuleService.finds(baseModules.getMname());
        if(baseFunction.getMid()!=null){
            baseFunction.setMid(baseModule.getMid());
        }

        iBaseFunctionService.insert(baseFunction);
        return "forward:list.action";
    }
    //删除
    @RequestMapping("/delete")
    public  String delete(BaseFunction baseFunction){
        iBaseFunctionService.delete(baseFunction.getFid());
        return "forward:list.action";
    }
    //先查找id，通过id修改信息
    @RequestMapping("/load")
    public String load(BaseFunction baseFunction, Model model,HttpServletRequest request){
        BaseFunction baseFunction1 = iBaseFunctionService.findById(baseFunction.getFid());
        List<BaseModule> list = iBaseModuleService.findAll();
        //回显
        model.addAttribute("baseFunction1",baseFunction1);
        request.setAttribute("list",list);

        return "baseFunction/edit";
    }
    //修改
    @RequestMapping("/set")
    public String set(BaseFunction baseFunction,BaseModule baseModule){
        baseFunction.setMid(baseModule.getMid());
        iBaseFunctionService.update(baseFunction);
        return "forward:list.action";
    }
//查找
@RequestMapping("/find")
public String find(BaseFunction searchObject,HttpServletRequest request,BaseModule baseModule,Model model){
    List<BaseFunction> list= iBaseFunctionService.find(searchObject.getFname(),baseModule.getMname(),searchObject.getUrl());
    request.setAttribute("list",list);
    model.addAttribute("searchObject",searchObject);
    model.addAttribute("baseModule",baseModule);
    return "baseFunction/list";
}
    @RequestMapping("/findbymid")
    public void findbymid(HttpServletResponse response, Integer mid, HttpSession session) throws IOException {
        BaseUser baseUser = (BaseUser) session.getAttribute("sessionuser");
        List<BaseFunction> list = iBaseFunctionService.findbymid(mid,baseUser.getUserId());
        String s= JSON.toJSONString(list);
        response.getWriter().write(s);
    }
    @RequestMapping("/exportToExcel")
    public void exportToExcel()throws Exception{
        String title = "功能信息表";
        String[] name = {"序号","功能名称 ","模板id","路径"};
        //获取数据
        Connection connection = CommonUtil.getConnection();
        //创建数据库连接
        Statement statement = connection.createStatement();
        String sql = "SELECT * FROM base_function";
        ResultSet rs = statement.executeQuery(sql);
        //获取标题，名称，SQL
        CommonUtil.exportExcel(title,name,rs);
    }
}
