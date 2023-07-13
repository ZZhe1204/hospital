package com.qhit.basePatientInfo.controller; 

import com.qhit.basePatientInfo.pojo.BasePatientInfo; 
import com.qhit.basePatientInfo.service.IBasePatientInfoService; 
import com.qhit.basePatientInfo.service.impl.BasePatientInfoServiceImpl;
import com.qhit.baseUserRole.pojo.BaseUserRole;
import com.qhit.baseUserRole.service.IBaseUserRoleService;
import com.qhit.baseUserRole.service.impl.BaseUserRoleServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.RequestMapping; 
import javax.annotation.Resource; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 
import com.alibaba.fastjson.JSON; 
import java.io.IOException; 
import java.util.List; 

/**
* Created by GeneratorCode on 2018/12/21
*/
@Controller 
@RequestMapping("/basePatientInfo") 
public class BasePatientInfoController { 

    IBasePatientInfoService basePatientInfoService = new BasePatientInfoServiceImpl();
    IBaseUserRoleService baseUserRoleService=new BaseUserRoleServiceImpl();

    @RequestMapping("/insert") 
    public String insert(BasePatientInfo basePatientInfo) { 
        basePatientInfoService.insert(basePatientInfo); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/delete") 
    public String delete(Integer patientId, HttpServletResponse response) throws IOException { 
        basePatientInfoService.delete(patientId); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/update") 
    public String update(BasePatientInfo basePatientInfo) { 
        basePatientInfoService.update(basePatientInfo); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/updateSelective") 
    public String updateSelective(BasePatientInfo basePatientInfo) { 
        basePatientInfoService.updateSelective(basePatientInfo); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/load") 
    public String load(Integer patientId, Model model) { 
        BasePatientInfo basePatientInfo = basePatientInfoService.findById(patientId); 
        model.addAttribute("basePatientInfo",basePatientInfo); 
        return "basePatientInfo/edit"; 
    } 
 
    @RequestMapping("/list") 
    public String list(Model model) throws IOException { 
        List<BasePatientInfo> list = basePatientInfoService.findAll(); 
        model.addAttribute("list",list); 
        return "basePatientInfo/list"; 
    }
    //查询挂号信息
    @RequestMapping("/listguahaoinfo")
    public String guahaoinfo(Model model) throws IOException {
        List<BasePatientInfo> list = basePatientInfoService.findAll();
        model.addAttribute("list",list);
        return "basePatientInfo/guahaolist";
    }

    @RequestMapping("/ajaxList") 
    public void ajaxList(HttpServletResponse response) throws IOException { 
        List<BasePatientInfo> list = basePatientInfoService.findAll(); 
        String s = JSON.toJSONString(list); 
        response.getWriter().write(s); 
    }
    @RequestMapping("/ajaxdoctorinfoList")
    public void doctorinfoList(HttpServletResponse response) throws IOException {
        List<BaseUserRole> list = baseUserRoleService.findsAll();
        String s = JSON.toJSONString(list);
        response.getWriter().write(s);
    }

    @RequestMapping("/search") 
    public String search(BasePatientInfo basePatientInfo,Model model) { 
        List<BasePatientInfo> list = basePatientInfoService.search(basePatientInfo); 
        model.addAttribute("list",list); 
        model.addAttribute("searchObject",basePatientInfo); 
        return "basePatientInfo/list"; 
    } 
 
} 
