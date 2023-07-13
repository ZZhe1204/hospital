package com.qhit.doctorMenuMedicine.controller; 

import com.qhit.doctorMenuMedicine.pojo.DoctorMenuMedicine; 
import com.qhit.doctorMenuMedicine.service.IDoctorMenuMedicineService; 
import com.qhit.doctorMenuMedicine.service.impl.DoctorMenuMedicineServiceImpl; 
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
* Created by GeneratorCode on 2018/12/25
*/ 

@Controller 
@RequestMapping("/doctorMenuMedicine") 
public class DoctorMenuMedicineController { 

    @Resource 
    IDoctorMenuMedicineService doctorMenuMedicineService; 

    @RequestMapping("/insert") 
    public String insert(DoctorMenuMedicine doctorMenuMedicine) { 
        doctorMenuMedicineService.insert(doctorMenuMedicine); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/delete") 
    public String delete(Integer mdId, HttpServletResponse response) throws IOException { 
        doctorMenuMedicineService.delete(mdId); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/update") 
    public String update(DoctorMenuMedicine doctorMenuMedicine) { 
        doctorMenuMedicineService.update(doctorMenuMedicine); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/updateSelective") 
    public String updateSelective(DoctorMenuMedicine doctorMenuMedicine) { 
        doctorMenuMedicineService.updateSelective(doctorMenuMedicine); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/load") 
    public String load(Integer mdId, Model model) { 
        DoctorMenuMedicine doctorMenuMedicine = doctorMenuMedicineService.findById(mdId); 
        model.addAttribute("doctorMenuMedicine",doctorMenuMedicine); 
        return "doctorMenuMedicine/edit"; 
    } 
 
    @RequestMapping("/list") 
    public String list(Model model) throws IOException { 
        List<DoctorMenuMedicine> list = doctorMenuMedicineService.findAll(); 
        model.addAttribute("list",list); 
        return "doctorMenuMedicine/list"; 
    } 
 
    @RequestMapping("/ajaxList") 
    public void ajaxList(HttpServletResponse response) throws IOException { 
        List<DoctorMenuMedicine> list = doctorMenuMedicineService.findAll(); 
        String s = JSON.toJSONString(list); 
        response.getWriter().write(s); 
    }
    //通过套餐id查找药品
    @RequestMapping("/ajaxtaocan")
    public void ajaxtaocan(HttpServletResponse response,DoctorMenuMedicine doctorMenuMedicine) throws IOException {
        List<DoctorMenuMedicine> list = doctorMenuMedicineService.findmedicinetaocanid(doctorMenuMedicine);
        String s = JSON.toJSONString(list);
        response.getWriter().write(s);
    }
    @RequestMapping("/search") 
    public String search(DoctorMenuMedicine doctorMenuMedicine,Model model) { 
        List<DoctorMenuMedicine> list = doctorMenuMedicineService.search(doctorMenuMedicine); 
        model.addAttribute("list",list); 
        model.addAttribute("searchObject",doctorMenuMedicine); 
        return "doctorMenuMedicine/list"; 
    } 
 
} 
