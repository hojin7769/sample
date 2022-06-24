package com.example.sample.repository;

import com.example.sample.model.BoardVO;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends CrudRepository<BoardVO, Integer> {
    Iterable<BoardVO> findAll (Sort sort);
}
