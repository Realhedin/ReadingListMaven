package com.example.controllers;


import java.util.List;

import com.example.domain.Book;
import com.example.jpa.ReadingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/readingList")
public class ReadingListController {


    private ReadingListRepository readingListRepository;


    @RequestMapping(value =  "/{reader}", method = RequestMethod.GET)
    public String findByReader(@PathVariable("reader") String reader, Model model) {

        List<Book> readingList = readingListRepository.findByReader(reader);
        if (readingList != null) {
            model.addAttribute("books", readingList);
        }
        return "readingList";
    }


    @RequestMapping(value = "/{reader}",method = RequestMethod.POST)
    public String addToReadingList(@PathVariable("reader") String reader, Book book) {
        book.setReader(reader);
        readingListRepository.save(book);
        return "redirect:/readingList/{reader}";
    }


    @Autowired
    public void setReadingListRepository(ReadingListRepository readingListRepository) {
        this.readingListRepository = readingListRepository;
    }
}
