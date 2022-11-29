package com.example.springbootdemo.controller;

import com.example.springbootdemo.service.PetService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.annotation.Resource;

@Controller
public class dealController {
    @Resource
    PetService petService;
    @RequestMapping("/add")
    public String add(){
        return "add";
    }
    //,Model model
    @RequestMapping(value = "/add1",method = RequestMethod.POST)
    public String addMethod(String name,String other,String type,String symptom,String result,String date){
        if (name.isEmpty() || result.isEmpty() || type.isEmpty() || symptom.isEmpty() || date.isEmpty()){
            String msg = null;
            if (name.isEmpty())
                msg="姓名不能为空";
            if (type.isEmpty())
                msg="类型不能为空";
            if (result.isEmpty())
                msg="就诊结果不能为空";
            if (symptom.isEmpty())
                msg="症状不能为空";
            if (date.isEmpty())
                msg="时间不能为空";
//            model.addAttribute("SelectResult","<table id='table'>"+msg+"</table>");
        }

        petService.add(name,other,type,symptom,result,date);
//        model.addAttribute("SelectResult","<table id='table'>"+petsHTML+"</table>");
        return "add";
    }

    @RequestMapping("/delete")
    public String delete(){
        return "delete";
    }

    @RequestMapping(value = "/delete1",method = RequestMethod.POST)
    public String deleteMethod(String name,String type){
        petService.delete(name,type);
//        model.addAttribute("SelectResult",
//                "<table id='table'>"+petsHTML+"</table>");
        return "delete";
    }

    @RequestMapping("/update")
    public String update(){
        return "update";
    }

    @RequestMapping(value = "/update1",method = RequestMethod.POST)
    public String updateMethod(String name,String other,String type,String symptom,String result,String date){
        petService.update(name,other,type,symptom,result);
//        model.addAttribute("SelectResult","<table id='table'>"+petsHTML+"</table>");
        return "update";
    }

    @RequestMapping("/select")
    public String select(){
        return "select";
    }

    @RequestMapping(value = "/select1",method = RequestMethod.POST)
    public String updateMethod(){
        petService.select();
//        model.addAttribute("SelectResult","<table id='table'>"+petsHTML+"</table>");
        return "update";
    }
}