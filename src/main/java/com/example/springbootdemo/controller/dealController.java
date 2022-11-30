package com.example.springbootdemo.controller;

import com.example.springbootdemo.bean.pet;
import com.example.springbootdemo.service.PetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;

@Controller
public class dealController {
    @Resource
    PetService petService;
    @RequestMapping("/add")
    public String add(){
        return "add";
    }

    @RequestMapping(value = "/add1",method = RequestMethod.POST)
    public String addMethod(String name, String other, String type, String symptom, String result, String date, Model model){
        String msg = null;
        if (name.isEmpty() || result.isEmpty() || type.isEmpty() || symptom.isEmpty() || date.isEmpty()){
            if (name.isEmpty()) {
                msg = "姓名不能为空";
                model.addAttribute("msg",msg);
                return "add";
            }
            if (type.isEmpty()) {
                msg = "类型不能为空";
                model.addAttribute("msg",msg);
                return "add";
            }
            if (result.isEmpty()) {
                msg = "就诊结果不能为空";
                model.addAttribute("msg",msg);
                return "add";
            }
            if (symptom.isEmpty()) {
                msg = "症状不能为空";
                model.addAttribute("msg",msg);
                return "add";
            }
            if (date.isEmpty())
            {
                msg="时间不能为空";
                model.addAttribute("msg",msg);
                return "add";
            }
        }

        petService.add(name,other,type,symptom,result,date);
        msg="添加成功";
        model.addAttribute("msg",msg);
        return "add";
    }

    @RequestMapping("/delete")
    public String delete(){
        return "delete";
    }

    @RequestMapping(value = "/delete1",method = RequestMethod.POST)
    public String deleteMethod(String name,String type,Model model){
        petService.delete(name,type);
        String msg=null;
        msg="删除成功";
        model.addAttribute("msg",msg);
        return "delete";
    }

    @RequestMapping("/update")
    public String update(){
        return "update";
    }

    @RequestMapping(value = "/update1",method = RequestMethod.POST)
    public String updateMethod(String name,String other,String type,String symptom,String result,Model model){
        petService.update(name,other,type,symptom,result);
        String msg=null;
        msg="更新成功";
        model.addAttribute("msg",msg);
        return "update";
    }

    @RequestMapping("/select")
    public String select(){
        return "select";
    }

    @RequestMapping(value = "/select1",method = RequestMethod.POST)
    public String select1Method(Model model){
        List<pet> petList=petService.select();
        String tableHeaders="<tr><th>宠物姓名</th><th>宠物类型</th><th>宠物症状</th><th>就诊结果</th><th>备注</th><th>就诊时间</th></tr>";
        String petsHTML = "";
        petsHTML += tableHeaders;
        Iterator<pet> iterator = petList.iterator();
        String pHTML;
        while (iterator.hasNext()) {
            pet a = iterator.next();
            pHTML="<tr><td>"+a.getPname()+"</td><td>"+a.getPtype()+"</td><td>"+a.getPsymptom()+"</td><td>"+a.getPvresult()+"</td><td>"+a.getPother()+"</td><td>"+a.getPdate()+"</td></tr>";
            petsHTML += pHTML;
        }
        String msg="查询结果如下";
        model.addAttribute("msg",msg);
        model.addAttribute("info1","<table id='table'>"+petsHTML+"</table>");
        return "select";
    }

    @RequestMapping(value = "/select2",method = RequestMethod.POST)
    public String select2Method(String name,String type,Model model){
        String msg;
        if (name.isEmpty() && type.isEmpty()){
            msg="宠物姓名和类型不能都为空！";
            model.addAttribute("msg",msg);
            return "select";
        }
        List<pet> petList;
        if (name.isEmpty()){
            petList=petService.selectType(type);
        } else if (type.isEmpty()) {
            petList=petService.selectName(name);
        }else {
            petList=petService.selectNT(name,type);
        }
        String tableHeaders="<tr><th>宠物姓名</th><th>宠物类型</th><th>宠物症状</th><th>就诊结果</th><th>备注</th><th>就诊时间</th></tr>";
        String petsHTML = "";
        petsHTML += tableHeaders;
        Iterator<pet> iterator;
        iterator = petList.iterator();
        String pHTML=null;
        while (iterator.hasNext()) {
            pet a = iterator.next();
            pHTML="<tr><td>"+a.getPname()+"</td><td>"+a.getPtype()+"</td><td>"+a.getPsymptom()+"</td><td>"+a.getPvresult()+"</td><td>"+a.getPother()+"</td><td>"+a.getPdate()+"</td></tr>";
            petsHTML += pHTML;
//            System.out.println(a.getPname());
        }
        msg="查询结果如下";
        model.addAttribute("msg",msg);
        model.addAttribute("info1","<table id='table'>"+petsHTML+"</table>");
        return "select";
    }
}