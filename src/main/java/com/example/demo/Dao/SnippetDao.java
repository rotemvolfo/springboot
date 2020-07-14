package com.example.demo.Dao;

import com.example.demo.models.Snippet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class SnippetDao implements IDao<Snippet> {


    private HashMap<String,Snippet> _map = new HashMap<>();


    @Override
    public Snippet get(String id) {
        if(_map.containsKey(id))
            return _map.get(id);
        else
            return null;
    }




    @Override
    public Snippet save(Snippet snippet) {

        if(!_map.containsKey(snippet.getName()))
        {
            _map.put(snippet.getName(),snippet);
            return snippet;

        }

        return null;
    }

    @Override
    public void update(Snippet snippet) {
        _map.put(snippet.getName(),snippet);
    }

    @Override
    public void delete(Snippet snippet) {
        if(_map.containsKey(snippet.getName()))
              _map.remove(snippet.getName());


    }
}
