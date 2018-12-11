package com.adminportal.admin.controller;

import com.adminportal.admin.api.WordApi;
import com.adminportal.admin.entity.Word;
import com.adminportal.admin.respository.WordRepository;
import com.adminportal.admin.service.WordService;
import com.sun.org.apache.xpath.internal.operations.Mult;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/word")
public class WordController {

    @Autowired
    private WordService wordService;
    @Autowired
    private WordApi wordApi;
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addWord(Model model) {
        Word word = new Word();
        model.addAttribute("word", word);
        return "addWord";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addWordPost(@ModelAttribute("word") Word word, HttpServletRequest request) {
        wordService.insertWord(word);
        MultipartFile wordImage = word.getImage();
        String name = "";
        try {
            byte[] bytes;
            bytes = wordImage.getBytes();

            name = word.getId() + "_" + wordImage.getOriginalFilename();
            FileCopyUtils.copy(bytes,new File("src/main/resources/static/image/word/" + name));
//            wordApi.getImage();
            //            BufferedOutputStream stream = new BufferedOutputStream(
//                    new FileOutputStream(new File("src/main/resources/static/image/word/" + name)));
//            stream.write(bytes);
//            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        word.setImageWord(name);
        wordService.insertWord(word);
        return "redirect:wordList";
    }

    @RequestMapping("/wordInfo")
    public String wordInfo(@RequestParam("id") Long id, Model model) {
        Word word = wordService.selectWordById(id);
        model.addAttribute("word", word);
        return "wordInfo";
    }

//
//    @GetMapping("/image/{id}")
//    public ResponseEntity<byte[]> getWordImage(@RequestParam("id") Long id, Model model)
//    {
//
//        Word word = wordService.selectWordById(id);
//        MultipartFile file=word.getImage();
//        byte[] bytes={};
//        try{
//            bytes=file.getBytes();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).contentType(MediaType.IMAGE_PNG).body(bytes);
//    }


    @RequestMapping("/updateWord")
    public String updateWord(@RequestParam("id") Long id, Model model) {
        Word word = wordService.selectWordById(id);
        model.addAttribute("word", word);

        return "updateWord";
    }

    @RequestMapping(value = "/updateWord", method = RequestMethod.POST)
    public String updateWordPost(@ModelAttribute("word") Word word, HttpServletRequest request) {
//        wordService.insertWord(word);
        File  file= new File(request.getSession().getServletContext().getRealPath("resources/static/image/word/")+word.getImageWord());
        file.delete();
        MultipartFile wordImage = word.getImage();

        String name = "";
        try {
            byte[] bytes;
            bytes = wordImage.getBytes();
            name = word.getId() + "_" + wordImage.getOriginalFilename();

            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(new File("src/main/resources/static/image/word/" + name)));
            stream.write(bytes);
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        word.setImageWord(name);
        wordService.updateWord(word.getId(),word);
        return "redirect:/word/wordInfo?id=" + word.getId();
    }

    @RequestMapping("/wordList")
    public String wordList(Model model) {
        List<Word> wordsList = wordService.selectAllWord();
        model.addAttribute("wordList", wordsList);
        return "list";

    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String removeWord(
            @ModelAttribute("id") String id, Model model
    ) {
        wordService.deleteWord(Long.parseLong(id.substring(8)));
        List<Word> wordsList = wordService.selectAllWord();
        model.addAttribute("wordList", wordsList);

        return "redirect:wordList";
    }
    @RequestMapping(value="/removeList", method=RequestMethod.POST)
    public String removeList(
            @RequestBody ArrayList<String> wordIdList, Model model
    ){

        for (String id : wordIdList) {
//            String bookId =id.substring(8);
            wordService.deleteWord(Long.parseLong(id));
        }

        return "delete success";
    }
}
