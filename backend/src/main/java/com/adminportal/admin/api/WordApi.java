package com.adminportal.admin.api;

import com.adminportal.admin.entity.Word;
import com.adminportal.admin.service.WordService;
import com.adminportal.admin.util.ApiResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/wordapi")
public class WordApi {
    @Autowired
    private WordService articleService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Map<String, ?> getAllWordAPI() {
        return ApiResponseBuilder.buildContainsData("List all words", articleService.selectAllWord());
    }

    @GetMapping("/{id}")
    public Map<String, ?> getWordAPI(@PathVariable Long id) {
        return ApiResponseBuilder.buildContainsData("Get article#" + id, articleService.selectWordById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, ?> postArticle(@RequestBody Word article) {
        Word insertedArticle = articleService.insertWord(new Word(article.getVocabulary(), article.getPhonetic(), article.getNote(), article.getDefinition(), article.getTypeword(), article.getTitle(), article.getImageWord()));
        return ApiResponseBuilder.buildSuccess(String.format("Insert article#%d success", insertedArticle.getId()), insertedArticle);
    }

    //    @RequestMapping(value = "/Image/{id:.+}", method = RequestMethod.GET)
//    public ResponseEntity<byte[]> getImage(@PathVariable("id") String id) {
//        byte[] image = imageService.getImage(id);
//        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
//    }
    @GetMapping(value = "/image/{id}")
    public ResponseEntity<InputStreamResource> getImage(@PathVariable("id")Long id) throws IOException {
        Word word=articleService.selectWordById(id);
        ClassPathResource imgFile = new ClassPathResource("static/image/word/"+word.getImageWord());
//        File initialFile=new File("static/image/word/"+word.getImageWord());
//        InputStream targetStream = new FileInputStream(file);
//        final InputStream targetStream =
//                new DataInputStream(new FileInputStream(initialFile));
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(new InputStreamResource(imgFile.getInputStream()));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, ?> putArticle(@PathVariable Long id, @RequestBody Word article) {
        Word updatedArticle = articleService.updateWord(id, article);
        return ApiResponseBuilder.buildSuccess(String.format("Update article#%d success", id), updatedArticle);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, ?> deleteArticle(@PathVariable Long id) {
        boolean success = articleService.deleteWord(id);
        if (success)
            return ApiResponseBuilder.buildSuccess(String.format("Delete article#%d success", id));
        else
            return ApiResponseBuilder.buildError(String.format("Delete article#%d fail", id));
    }
}
