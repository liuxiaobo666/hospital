package com.sxxy.hospital.equipment.controller;

import com.sxxy.hospital.equipment.entity.Equipment;
import com.sxxy.hospital.equipment.mapper.EquipmentMapper;
import com.sxxy.hospital.equipment.service.EquipmentService;
import com.sxxy.hospital.personnel.entity.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/equipment")
public class EquipmentController {
    @Autowired
    EquipmentService equipmentService;
    @Autowired
    EquipmentMapper equipmentMapper;

    //查看所有器材
    @RequestMapping("/equipments")
    public String allEquipment(Model model) {
        List<Equipment> equipments = new ArrayList<>();
        equipments = equipmentMapper.findAll();
        model.addAttribute("equipments", equipments);
        return "equipment/equipment/showequipments";
    }

    //修改器材现状
    @RequestMapping("/editEquipmentNowState")
    public String editEquipmentNowState(String equipmentState,String tag) {
        String[] strs = tag.split(",");
        System.out.println(equipmentState);
        for (int i = 0; i < strs.length; i++) {
            try {
                equipmentService.editequipmentNowState(equipmentState, Collections.singletonList(strs[i]));
            } catch (Exception e) {
                return "personnel/error";
            }
        }
        return "equipment/equipment/showequipments";
    }

    //增加器材
    @RequestMapping("/addEquipment")
    public String addEquipment(String equipmentNum, String equipmentName, String equipmentState, Double equipmentPrice, String equipmentSite,Date equipmentTime) {
        Equipment addEquipment = new Equipment(equipmentNum,equipmentName,equipmentState,equipmentPrice,equipmentSite,equipmentTime);
        if (addEquipment != null) {
            equipmentService.addEquipment(addEquipment);
            return "equipment/equipment/addequipments";
        } else {
            return "equipment/equipment/addequipments";
        }
    }
}
