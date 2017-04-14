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


    private static String USER = "Tom";


    private ReadingListRepository readingListRepository;


    @RequestMapping( method = RequestMethod.GET)
    public String findByReader( Model model) {

        List<Book> readingList = readingListRepository.findByReader(USER);
        if (readingList != null) {
            model.addAttribute("books", readingList);
        }
        return "readingList";
    }


    @RequestMapping(method = RequestMethod.POST)
    public String addToReadingList( Book book) {
        book.setReader(USER);
        readingListRepository.save(book);
        return "redirect:/readingList";
    }


    @Autowired
    public void setReadingListRepository(ReadingListRepository readingListRepository) {
        this.readingListRepository = readingListRepository;
    }
}
