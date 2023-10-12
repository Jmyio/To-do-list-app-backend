package com.example.demo.repos;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.dto.Lists;

@Repository
public interface ListsRepo extends JpaRepository<Lists, Long> {
    List<Lists> findByUserid(Long userId);
    Lists findByListid(Long listid);
    void deleteByListid(Long listId);
}
