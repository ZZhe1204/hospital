package com.qhit.medicinePurchaseInfo.controller; 

import com.qhit.medicinePurchaseInfo.pojo.MedicinePurchaseInfo; 
import com.qhit.medicinePurchaseInfo.service.IMedicinePurchaseInfoService; 
import com.qhit.medicinePurchaseInfo.service.impl.MedicinePurchaseInfoServiceImpl;
import com.qhit.medicineReqPlan.pojo.MedicineReqPlan;
import org.omg.CORBA.Request;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.RequestMapping; 
import javax.annotation.Resource; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON; 
import java.io.IOException; 
import java.util.List; 

/**
* Created by GeneratorCode on 2018/12/05
*/
@Controller 
@RequestMapping("/medicinePurchaseInfo") 
public class MedicinePurchaseInfoController { 

    IMedicinePurchaseInfoService medicinePurchaseInfoService = new MedicinePurchaseInfoServiceImpl();; 

    @RequestMapping("/insert") 
    public String insert(MedicinePurchaseInfo medicinePurchaseInfo) { 
        medicinePurchaseInfoService.insert(medicinePurchaseInfo); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/delete")
    public String delete(Integer pchId, HttpServletResponse response) throws IOException { 
        medicinePurchaseInfoService.delete(pchId); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/update") 
    public String update(MedicinePurchaseInfo medicinePurchaseInfo) { 
        medicinePurchaseInfoService.update(medicinePurchaseInfo); 
        return "forward:list.action";
    }
    //审批购买
    @RequestMapping("/updateBantch")
    public String updateBantch(HttpServletRequest Request) {
        String[] pchIds = Request.getParameterValues("pchId");
        MedicinePurchaseInfo medicinePurchaseInfo=new MedicinePurchaseInfo();
        for (String pchId:pchIds){
            medicinePurchaseInfo.setPchId(Integer.parseInt(pchId));
            medicinePurchaseInfo.setStatus(2);
            medicinePurchaseInfoService.updateSelective(medicinePurchaseInfo);

        }
        return "forward:apprvlist.action";
    }

    @RequestMapping("/updateSelective") 
    public String updateSelective(MedicinePurchaseInfo medicinePurchaseInfo) {
        //计算总价=价格*数量。
        medicinePurchaseInfo.setPchTotal(medicinePurchaseInfo.getPchPrice()*medicinePurchaseInfo.getPchAmt());
        medicinePurchaseInfoService.updateSelective(medicinePurchaseInfo);
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/load") 
    public String load(Integer pchId, Model model) { 
        MedicinePurchaseInfo medicinePurchaseInfo = medicinePurchaseInfoService.findById(pchId); 
        model.addAttribute("medicinePurchaseInfo",medicinePurchaseInfo);
        return "medicinePurchaseInfo/edit";
    }

    //采购员
    @RequestMapping("/list")
    public String list(Model model) throws IOException {
        List<MedicinePurchaseInfo> list =medicinePurchaseInfoService.findsall();
        MedicinePurchaseInfo medicinePurchaseInfo=new MedicinePurchaseInfo();
      MedicinePurchaseInfo medicinePurchaseInfo1=  medicinePurchaseInfoService.findbyname(medicinePurchaseInfo.getPchUserid());
        model.addAttribute("list",list);
        model.addAttribute("medicinePurchaseInfo1",medicinePurchaseInfo1);
        return "medicinePurchaseInfo/list";
    }
    //采购主管
    @RequestMapping("/apprvlist")
    public String apprvlist(Model model) throws IOException {
        List<MedicinePurchaseInfo> list =medicinePurchaseInfoService.findsall();
        MedicinePurchaseInfo medicinePurchaseInfo=new MedicinePurchaseInfo();
        MedicinePurchaseInfo medicinePurchaseInfo1=  medicinePurchaseInfoService.findbyname(medicinePurchaseInfo.getPchUserid());
        model.addAttribute("list",list);
        model.addAttribute("medicinePurchaseInfo1",medicinePurchaseInfo1);
        return "medicinePurchaseInfo/apprvlist";
    }
    //汇总
    @RequestMapping("/collection")
    public String collection(HttpSession session) throws IOException {
       medicinePurchaseInfoService.collection(session);
        return "forward:list.action";
    }
    //遍历所有
    @RequestMapping("/ajaxList") 
    public void ajaxList(HttpServletResponse response) throws IOException { 
       // List<MedicinePurchaseInfo> list = medicinePurchaseInfoService.findAll();
        List<MedicineReqPlan> list = medicinePurchaseInfoService.findlist();
        String s = JSON.toJSONString(list); 
        response.getWriter().write(s); 
    } 
 
    @RequestMapping("/search") 
    public String search(MedicinePurchaseInfo medicinePurchaseInfo,Model model) { 
        List<MedicinePurchaseInfo> list = medicinePurchaseInfoService.search(medicinePurchaseInfo); 
        model.addAttribute("list",list); 
        model.addAttribute("searchObject",medicinePurchaseInfo); 
        return "medicinePurchaseInfo/list"; 
    } 
 
} 
