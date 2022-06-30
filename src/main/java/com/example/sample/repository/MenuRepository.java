package com.example.sample.repository;

import com.example.sample.model.MenuVO;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

public interface MenuRepository extends CrudRepository<MenuVO,Integer> {

    Iterable<MenuVO> findAll(Sort sort);
}

