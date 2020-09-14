package com.sxxy.hospital.personnel.controller;

import com.sxxy.hospital.personnel.entity.Doctor;
import com.sxxy.hospital.personnel.entity.Other;
import com.sxxy.hospital.personnel.mapper.DoctorMapper;
import com.sxxy.hospital.personnel.mapper.OtherMapper;
import com.sxxy.hospital.personnel.service.DoctorService;
import com.sxxy.hospital.personnel.service.OtherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/other")
public class OtherController {
    @Autowired
    DoctorService doctorService;
    @Autowired
    DoctorMapper doctorMapper;
    @Autowired
    OtherService otherService;
    @Autowired
    OtherMapper otherMapper;

    //增加医生
    @RequestMapping("/addOther")
    public String addOther(String doctorNum, String doctorName, String doctorPhone, String doctorBirthday, String doctorAddress, String doctorEmail, int doctorAge, String doctorGender, String doctorPosition, String doctorWorkspace, String doctorWorkDate, String doctorAbout) {
        Doctor addDoctor = new Doctor(doctorNum, doctorName, doctorPhone, doctorBirthday, doctorAddress, doctorEmail, doctorAge, doctorGender, doctorPosition, doctorWorkspace, doctorWorkDate, doctorAbout, "123", "可出诊",8000);
        if (addDoctor != null) {
            doctorService.addDoctor(addDoctor);
            return "personnel/success";
        } else {
            return "personnel/error";
        }
    }

    //编辑医生信息
    @RequestMapping("/updateOther")
    public String updateOther(String doctorPhone, String doctorAddress, String doctorEmail, String doctorPosition, String doctorWorkspace, String doctorWorkDate, String doctorAbout,String doctorPassword,double doctorMoney,String doctorNum)  {
        int updateDoctor = doctorService.updateDoctor(doctorPhone, doctorAddress, doctorEmail, doctorPosition, doctorWorkspace, doctorWorkDate, doctorAbout, doctorPassword, doctorMoney,doctorNum);
        if (updateDoctor > 0) {
            return "personnel/success";
        } else {
            return "personnel/error";
        }
    }

    //查看所有员工
    @RequestMapping("/others")
    public String allOther(Model model) {
        List<Other> others = new ArrayList<>();
        try {
            others = otherMapper.findAll();
        }catch (Exception e){
            return "personnel/error";
        }
        model.addAttribute("others", others);
        return "personnel/other/others";
    }

    //查看所有员工详细信息
    @RequestMapping("/otherInfo")
    public String otherInfo(Model model) {
        List<Other> others = new ArrayList<>();
        others = otherMapper.findAll();
        model.addAttribute("others", others);
        return "personnel/other/aboutOther";
    }

    //删除医生信息
    @RequestMapping("/deleteOther")
    public String deleteOther(String tag) {
        String[] strs = tag.split(",");
        for (int i = 0; i < strs.length; i++) {
            try {
                otherMapper.deleteOtherByOtherNum(Collections.singletonList(strs[i]));
            } catch (Exception e) {
                return "personnel/error";
            }
        }
        return "personnel/other/others";
    }

    //修改医生现状
    @RequestMapping("/editOtherNowState")
    public String editOtherNowState(String otherState,String tag) {
        String[] strs = tag.split(",");
        for (int i = 0; i < strs.length; i++) {
            try {
                otherService.editOtherNowState(otherState,Collections.singletonList(strs[i]));
            } catch (Exception e) {
                return "personnel/error";
            }
        }
        return "personnel/other/others";
    }

}