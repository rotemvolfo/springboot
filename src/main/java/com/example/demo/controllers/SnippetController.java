package com.example.demo.controllers;

import com.example.demo.Service.SnippetService;
import com.example.demo.models.Snippet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.text.ParseException;

@RestController
public class SnippetController {


    @Autowired
   private SnippetService _snipptService;



    @RequestMapping(value="/snippets",method = RequestMethod.POST)
    public ResponseEntity createSnippet(@RequestBody Snippet snippet, UriComponentsBuilder usBuilder) throws ParseException {

        snippet.setUrl("https://example.com/snippets/"+snippet.getName());
        if(_snipptService.save(snippet) == null){

            return new ResponseEntity(HttpStatus.CONFLICT);
        }



        return new ResponseEntity( _snipptService.get(snippet.getName()), HttpStatus.CREATED);


    }


    @RequestMapping(value = "/snippets/{name:[A-Za-z]+}",method = RequestMethod.GET)
    public ResponseEntity<Snippet> getSnippet(@PathVariable("name") String name) throws ParseException {
        Snippet snippet=_snipptService.get(name);
        if(snippet == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        else
            return new  ResponseEntity<Snippet>(snippet,HttpStatus.OK);
    }
}
