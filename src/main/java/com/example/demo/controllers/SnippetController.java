package com.example.demo.controllers;

import com.example.demo.Service.SnippetService;
import com.example.demo.models.Snippet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

@RestController
public class SnippetController {


    @Autowired
   private SnippetService _snipptService;



    @RequestMapping(value="/snippets",method = RequestMethod.POST)
    public ResponseEntity createSnippet(@RequestHeader String host ,@RequestBody Snippet snippet) {

        snippet.setUrl(host+"/snippets/"+snippet.getName());

       Snippet responseObj =_snipptService.save(snippet);
        if(responseObj == null){

            return new ResponseEntity(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(responseObj, HttpStatus.CREATED);


    }


    @RequestMapping(value = "/snippets/{name:[A-Za-z]+}",method = RequestMethod.GET)
    public ResponseEntity<Snippet> getSnippet(@PathVariable("name") String name){
         try {
             Snippet snippet = _snipptService.get(name);
             if (snippet == null)
                 return new ResponseEntity(HttpStatus.NOT_FOUND);
             else
                 return new ResponseEntity<>(snippet, HttpStatus.OK);
         }
         catch (Exception ex){
             return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
         }
      }


}
