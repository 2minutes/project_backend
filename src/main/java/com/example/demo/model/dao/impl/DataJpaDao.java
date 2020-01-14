package com.example.demo.model.dao.impl;

import com.example.demo.model.dao.DataDao;
import com.example.demo.model.dao.repository.DataJpaRepository;
import com.example.demo.model.dvo.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataJpaDao implements DataDao {

    @Autowired
    public DataJpaRepository repository;

    @Override
    public Data save(Data data) {
        return repository.save(data);
    }

    @Override
    public Data findById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public List<Data> findAll(String limit) {
        if(limit==null){
            return repository.findAll();
        }else{
            return repository.findAllByLimit(Integer.valueOf(limit));
        }
    }

    @Override
    public List<Data> findByClientId(Long clientId, String field, String sort, String start, String end) {
        if(field!=null&&field.equals("step_count")){
            if(sort!=null&&sort.equals("desc")){
                return repository.findByClientIdOrderByStepCountDesc(clientId);
            }else if(start!=null&&!start.isEmpty()&&end!=null&&!end.isEmpty()){
                return repository.findByClientIdAndStepCountIsBetween(
                        clientId, Integer.valueOf(start), Integer.valueOf(end)
                );
            }else{
                return repository.findByClientIdOrderByStepCount(clientId);
            }
        }else{
            return repository.findByClientId(clientId);
        }
    }
}
