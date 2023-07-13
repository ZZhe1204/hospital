package com.qhit.baseRole.controller;

import com.qhit.baseFunction.pojo.BaseFunction;
import com.qhit.baseRole.pojo.BaseRole;
import com.qhit.baseRole.service.IBaseRoleService;
import com.qhit.baseRole.service.impl.BaseRoleServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2018/11/30 0030.
 */
@Controller
@RequestMapping("/baseRole")
public class baseRoleController {
    IBaseRoleService iBaseRoleService=new BaseRoleServiceImpl();

@RequestMapping("/list")
    public String list(HttpServletRequest request){

    List list = iBaseRoleService.findAll();
    request.setAttribute("list",list);
    return "baseRole/list";
}
@RequestMapping("/insert")
    public String insert(BaseRole baseRole){
        iBaseRoleService.insert(baseRole);
    return "forward:list.action";
}
@RequestMapping("/del")
    public String del(BaseRole baseRole,HttpServletRequest request){
    boolean flag = (boolean) request.getAttribute("flag");
    if (!flag){
        return "error/authority";
    }
        iBaseRoleService.delete(baseRole.getRid());
           return "forward:list.action";
}
@RequestMapping("/load")
    public String load(BaseRole baseRoles, Model model,HttpServletRequest request  ){
    boolean flag = (boolean) request.getAttribute("flag");
    if (!flag){
        return "error/authority";
    }
    BaseRole baseRole = iBaseRoleService.findById(baseRoles.getRid());
    //回显
    model.addAttribute("baseRole",baseRole);
    return "baseRole/edit";
}
@RequestMapping("/set")
    public String set(BaseRole baseRole){
        iBaseRoleService.update(baseRole);
    return "forward:list.action";
}
@RequestMapping("/find")
    public String find(BaseRole searchObject,HttpServletRequest request){
    List<BaseRole>list=iBaseRoleService.find(searchObject.getRname());
    request.setAttribute("list",list);
    request.setAttribute("searchObject",searchObject);
    return "baseRole/list";

}
    @RequestMapping("/distributeLoad")
    public String distributeLoad(BaseRole baseRole, Model model){
        List<BaseFunction> leftList = iBaseRoleService.distributeLeft(baseRole);
        List<BaseFunction> rightList = iBaseRoleService.distributeRight(baseRole);
        model.addAttribute("leftList",leftList);
        model.addAttribute("rightList",rightList);
        model.addAttribute("rid",baseRole.getRid());
        return "baseRole/distribute";
    }
    @RequestMapping("/distributeUpdate")
    public String distributeUpdate(BaseRole baseRole,HttpServletRequest request){
        String[] fid = request.getParameterValues("fid");
        iBaseRoleService.distributeUpdate(baseRole.getRid(),fid);
        return "forward:list.action";
    }
}
