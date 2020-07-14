package com.example.demo.Service;

import com.example.demo.Dao.IDao;
import com.example.demo.models.Snippet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

@Component
public class SnippetService {

    private IDao<Snippet> _snippetDao;

    @Autowired
    public SnippetService(IDao<Snippet> snippetDao){
        _snippetDao=snippetDao;

    }
    public Snippet save(Snippet newSnippet){

        String newTime=Instant.now().plusSeconds( TimeUnit.SECONDS.toSeconds( Integer.parseInt(newSnippet.getExpiresIn()))).toString();
        newSnippet.setExpiresin(newTime);
        return _snippetDao.save(newSnippet);

    }

    private String add30SecondToExpirationDate(Snippet elem) {

       return Instant.now().plusSeconds( TimeUnit.SECONDS.toSeconds( 30)).toString();
    }
    public Snippet get(String key) throws ParseException {

        Snippet elem=_snippetDao.get(key);
        if(elem == null )
            return null;


        Instant elemExpirationTime= Instant.parse(elem.getExpiresIn());
        Instant currentTime= Instant.now();

        if (elemExpirationTime.compareTo(currentTime)>0) {

            elem.setExpiresin(add30SecondToExpirationDate(elem));
            _snippetDao.update(elem);
            return elem;

        } else {
            _snippetDao.delete(elem);

        }


        return null;
    }



}
