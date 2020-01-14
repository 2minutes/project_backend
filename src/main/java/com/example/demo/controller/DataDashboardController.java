package com.example.demo.controller;

import com.example.demo.cache.Cache;
import com.example.demo.model.dao.DataDao;
import com.example.demo.model.dvo.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class DataDashboardController {

    @Qualifier("dataJpaDao")
    @Autowired
    private DataDao dataDao;

    @PostMapping("/data")
    public Data createData(@RequestBody Data data){
        return dataDao.save(data);
    }

    @Cacheable(value="dataHanpuCache", key = "#id")
    @GetMapping("/data/{id}")
    public Data findDataById(@PathVariable Long id){
        System.out.println("\nCalling getDataById from DAO for data:"+ id+"\n");
        return dataDao.findById(id);
    }

    @GetMapping("/data")
    public List<Data> getAllData(@RequestParam(name="limit", required = false) String limit){
        return dataDao.findAll(limit);
    }

    @GetMapping("/data/client/{clientId}")
    public List<Data> findDataByClientId(
            @PathVariable Long clientId,
            @RequestParam(name="field", required=false) String field,
            @RequestParam(name="sort", required =false) String sort,
            @RequestParam(name="start", required=false) String start,
            @RequestParam(name="end", required=false) String end){
        return dataDao.findByClientId(clientId, field, sort, start, end);
    }

    @CachePut(value="dataHanpuCache", key="#id")
    @PutMapping("/data/{id}")
    public Data updateDataById(@PathVariable Long id, @RequestBody Data data){
        Data res= dataDao.findById(id);
        if(res==null){
//            throw new RuntimeException("id not exist");
            return null;
        }else{
            res.setClientId(data.getClientId());
            res.setStepCount(data.getStepCount());
            res.setTemperature(data.getTemperature());
            dataDao.save(res);
            return res;
        }
    }

}
