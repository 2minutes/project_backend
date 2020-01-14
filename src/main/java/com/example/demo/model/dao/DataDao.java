package com.example.demo.model.dao;

import com.example.demo.model.dvo.Data;

import java.util.List;

public interface DataDao {
    Data save(Data data);
    Data findById(Long id);

    List<Data> findAll(String limit);

    List<Data> findByClientId(Long clientId, String field, String sort, String start, String end);
}
