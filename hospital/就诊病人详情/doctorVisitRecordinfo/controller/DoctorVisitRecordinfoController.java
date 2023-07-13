package com.qhit.doctorVisitRecordinfo.controller; 

import com.qhit.doctorVisitRecordinfo.pojo.DoctorVisitRecordinfo; 
import com.qhit.doctorVisitRecordinfo.service.IDoctorVisitRecordinfoService; 
import com.qhit.doctorVisitRecordinfo.service.impl.DoctorVisitRecordinfoServiceImpl; 
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
* Created by GeneratorCode on 2018/12/28
*/ 

@Controller 
@RequestMapping("/doctorVisitRecordinfo") 
public class DoctorVisitRecordinfoController { 

    @Resource 
    IDoctorVisitRecordinfoService doctorVisitRecordinfoService; 

    @RequestMapping("/insert") 
    public String insert(DoctorVisitRecordinfo doctorVisitRecordinfo) { 
        doctorVisitRecordinfoService.insert(doctorVisitRecordinfo); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/delete") 
    public String delete(Integer infoId, HttpServletResponse response) throws IOException { 
        doctorVisitRecordinfoService.delete(infoId); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/update") 
    public String update(DoctorVisitRecordinfo doctorVisitRecordinfo) { 
        doctorVisitRecordinfoService.update(doctorVisitRecordinfo); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/updateSelective") 
    public String updateSelective(DoctorVisitRecordinfo doctorVisitRecordinfo) { 
        doctorVisitRecordinfoService.updateSelective(doctorVisitRecordinfo); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/load") 
    public String load(Integer infoId, Model model) { 
        DoctorVisitRecordinfo doctorVisitRecordinfo = doctorVisitRecordinfoService.findById(infoId); 
        model.addAttribute("doctorVisitRecordinfo",doctorVisitRecordinfo); 
        return "doctorVisitRecordinfo/edit"; 
    } 
 
    @RequestMapping("/list") 
    public String list(Model model) throws IOException { 
        List<DoctorVisitRecordinfo> list = doctorVisitRecordinfoService.findAll(); 
        model.addAttribute("list",list); 
        return "doctorVisitRecordinfo/list"; 
    } 
 
    @RequestMapping("/ajaxList") 
    public void ajaxList(HttpServletResponse response) throws IOException { 
        List<DoctorVisitRecordinfo> list = doctorVisitRecordinfoService.findAll(); 
        String s = JSON.toJSONString(list); 
        response.getWriter().write(s); 
    } 
 
    @RequestMapping("/search") 
    public String search(DoctorVisitRecordinfo doctorVisitRecordinfo,Model model) { 
        List<DoctorVisitRecordinfo> list = doctorVisitRecordinfoService.search(doctorVisitRecordinfo); 
        model.addAttribute("list",list); 
        model.addAttribute("searchObject",doctorVisitRecordinfo); 
        return "doctorVisitRecordinfo/list"; 
    } 
 
} 
