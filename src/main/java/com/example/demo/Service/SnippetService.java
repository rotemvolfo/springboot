package com.example.demo.Service;

import com.example.demo.Dao.IDao;
import com.example.demo.models.Snippet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
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

    private String add30SecoundToExpiretionDate(Snippet elem) {


       return Instant.now().plusSeconds( TimeUnit.SECONDS.toSeconds( 30)).toString();

    }
    public Snippet get(String key) throws ParseException {

        Snippet elem=_snippetDao.get(key);
        if(elem == null )
            return null;


         SimpleDateFormat localDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date timeSnippet=localDateFormat.parse(elem.getExpiresIn());
         Date timenow= new Date();


            if(timeSnippet.before(timenow)){

               elem.setExpiresin(add30SecoundToExpiretionDate(elem));
               _snippetDao.update(elem);
               return elem;

          }
           else{
               _snippetDao.delete(elem);

          }
        return null;
    }



}
