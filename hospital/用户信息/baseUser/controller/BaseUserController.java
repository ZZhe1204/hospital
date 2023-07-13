package com.qhit.baseUser.controller;

import com.alibaba.fastjson.JSON;
import com.qhit.baseRole.pojo.BaseRole;
import com.qhit.baseUser.pojo.BaseUser;
import com.qhit.baseUser.service.IBaseUserService;
import com.qhit.baseUser.service.impl.BaseUserServiceImpl;
import com.qhit.utils.MD5;
import org.aspectj.weaver.ast.Var;
import org.omg.CORBA.Request;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2018/11/26 0026.
 */
@Controller
@RequestMapping("/baseUser")
public class BaseUserController {
    private IBaseUserService iBaseUserService = new BaseUserServiceImpl();
    //查询所有
    @RequestMapping("/list")
    public String list(HttpServletRequest request) {
        List<BaseUser> list = iBaseUserService.findAll();
        request.setAttribute("list", list);
        return "baseUser/list";
    }

    //添加
    @RequestMapping("/insert")
    public String insert(BaseUser baseUser) {
        //加密
        MD5 md5 = new MD5();
        baseUser.setPassword(md5.getMD5ofStr(baseUser.getPassword()));
        iBaseUserService.insert(baseUser);
        return "forward:list.action";
    }

    //删除
    @RequestMapping("/delete")
    public String delete(BaseUser baseUser) {
        iBaseUserService.delete(baseUser.getUserId());
        return "forward:list.action";
    }

    //先查找id，通过id查到更改信息
    @RequestMapping("/load")
    public String load(BaseUser baseUsers, Model model, HttpServletRequest request) {
        BaseUser baseUser = iBaseUserService.findById(baseUsers.getUserId());
        //回显
        model.addAttribute("baseUser", baseUser);
        return "baseUser/edit";
    }

    //修改
    @RequestMapping("/set")
    public String set(BaseUser baseUser) {
        iBaseUserService.update(baseUser);
        return "forward:list.action";
    }
    //查找

    @RequestMapping("/find")
    public String find(BaseUser searchObject, HttpServletRequest request) {
        List<BaseUser> list = iBaseUserService.find(searchObject.getCname(), searchObject.getSex(), searchObject.getUserName());
        request.setAttribute("list", list);
        request.setAttribute("searchObject", searchObject);
        return "baseUser/list";
    }

    @RequestMapping("/login")
    public String login(BaseUser baseUser, HttpSession session, Model model) {
        baseUser = iBaseUserService.login(baseUser);
        if (baseUser != null) {
            session.setAttribute("sessionuser", baseUser);
            return "index/home";
        } else {
            model.addAttribute("error", "用户名或密码错误请重新输入！");
            return "index/login";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("sessionuser");
        return "index/login";
    }

    @RequestMapping("/oldPassword")
    public void oldPassword(BaseUser baseUser, HttpServletResponse response) throws IOException {
        boolean flag = iBaseUserService.findoldPassword(baseUser);
        response.getWriter().write(flag ? "Y" : "N");
    }

    @RequestMapping("/updatePassword")
    public void updatePassword(BaseUser baseUser, HttpServletResponse response) throws IOException {
        MD5 md5 = new MD5();
        baseUser.setPassword(md5.getMD5ofStr(baseUser.getPassword()));
        boolean flag = iBaseUserService.updateSelective(baseUser);
        response.getWriter().write(flag ? "Y" : "N");
    }

    @RequestMapping("/distributeLoad")
    public String distributeLoad(BaseUser baseUser, Model model) {
        List<BaseRole> Leftlist = iBaseUserService.findLeftRole(baseUser.getUserId());
        List<BaseRole> rightlist = iBaseUserService.findrightRole(baseUser.getUserId());
        model.addAttribute("userId", baseUser.getUserId());
        model.addAttribute("Leftlist", Leftlist);
        model.addAttribute("rightlist", rightlist);
        return "baseUser/distribute";
    }

    @RequestMapping("/distributeUpdate")
    public String distributeUpdate(Integer userId, String rid, Model model) throws IOException {
        iBaseUserService.distributeUpdate(userId, rid);
        return "forward:list.action";
    }

    @RequestMapping("/$keshi")
    public void $keshi(HttpServletResponse response, Integer keshi) throws IOException {
        BaseUser baseUser = iBaseUserService.findkeshi(keshi);
        String s = JSON.toJSONString(baseUser);
        response.getWriter().write(s);
    }

}
