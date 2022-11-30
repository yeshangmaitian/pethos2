package com.example.springbootdemo.serviceImpl;
import com.example.springbootdemo.bean.pet;
import com.example.springbootdemo.mapper.PetMapper;
import com.example.springbootdemo.service.PetService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class PetServiceImpl implements PetService{
    @Resource
    PetMapper petMapper;

    @Override
    public void add(String name, String other, String type, String symptom, String result, String date) {
        petMapper.add(name,other,type,symptom,result,date);
    }

    @Override
    public void delete(String name, String type) {
        petMapper.delete(name,type);
    }

    @Override
    public void update(String name, String other, String type, String symptom, String result) {
        petMapper.update(name,other,type,symptom,result);
    }

    @Override
    public List<pet> select(){
        return petMapper.select();
    }

    @Override
    public List<pet> selectType(String type) {
        return petMapper.selectType(type);
    }

    @Override
    public List<pet> selectName(String name) {
        return petMapper.selectName(name);
    }

    @Override
    public List<pet> selectNT(String name, String type) {
        return petMapper.selectNT(name,type);
    }
}
