package com.example.demo.model.dao.impl;

import com.example.demo.model.dao.DataDao;
import com.example.demo.model.dvo.Data;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class DataHashMapDao implements DataDao {

    private Map<Long, Data> map=new HashMap<>();

    @Override
//    persist newly created & existing data
    public Data save(Data data) {
        Long id=data.getId();
//        when we newly create a data
        if(id==null||!map.containsKey(id)){
            id=map.size()+1L;
            data.setId(id);
        }
        map.put(id,data);
        return data;
    }

    @Override
    public Data findById(Long id) {
        return map.get(id);
    }

    @Override
    public List<Data> findAll(String limit) {
        return null;
    }

    @Override
    public List<Data> findByClientId(Long clientId, String field, String sort, String start, String end) {
        return null;
    }
}
