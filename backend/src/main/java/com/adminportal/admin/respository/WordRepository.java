package com.adminportal.admin.respository;

import com.adminportal.admin.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


public interface WordRepository extends JpaRepository<Word,Long> {
//    Word findByVocabulary(String name);

}
