package com.adminportal.admin.service;

import com.adminportal.admin.entity.Word;
import com.adminportal.admin.respository.WordRepository;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class WordService {
    @Autowired
    private WordRepository wordRepository;
    // get all article
    public List<Word> selectAllWord() {
        return wordRepository.findAll();
    }

    // get a article
    public Word selectWordById(Long id) throws ObjectNotFoundException {
        return wordRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, Word.class.getSimpleName()));
    }

    // add a new article
    public Word insertWord(Word article){
        if (article == null) throw new TypeMismatchException("Object is null");
        return wordRepository.save(article);
    }

    // edit a article
    public Word updateWord(Long id, Word data) throws ObjectNotFoundException{
        Word article = selectWordById(id);
        article.setTitle(data.getTitle());
        article.setImageWord(data.getImageWord());
        article.setVocabulary(data.getVocabulary());
        article.setPhonetic(data.getPhonetic());
        article.setNote(data.getNote());
        article.setDefinition(data.getDefinition());
        article.setTypeword(data.getTypeword());
        return wordRepository.save(article);
    }

    // delete a article
    public boolean deleteWord(Long id) throws ObjectNotFoundException {
        Word article = selectWordById(id);
        wordRepository.delete(article);
        return (wordRepository.existsById(id) == false);
    }
}
