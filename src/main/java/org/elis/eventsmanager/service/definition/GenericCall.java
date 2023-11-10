package org.elis.eventsmanager.service.definition;

import org.elis.eventsmanager.util.Utilities;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public interface GenericCall {

    private <P>HttpEntity<String> createHttpEntity(P parameter){
        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String json="";
        if(parameter!=null)json= Utilities.convertToJson(parameter);
        return new HttpEntity<>(json,headers);
    }

    private HttpEntity<String> createHttpEntity(){
        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(headers);
    }

    private <P>HttpEntity<String> createHttpEntity(String authToken,P parameter){
        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization","Bearer "+authToken);
        String json="";
        if(parameter!=null)json= Utilities.convertToJson(parameter);
        return new HttpEntity<>(json,headers);
    }

    default <P,R>R callGet(String path,P parameter,Class<R> classeDiRisposta){
        //questo oggetto sarà il "body" della request
        HttpEntity<String> requestEntity=createHttpEntity(parameter);
        //creo l'oggetto che mi permetterà di effettuare le chiamate
        //per quanto riguarda lo standard boot si usa il RestTemplate
        RestTemplate restTemplate=new RestTemplate();
        try{
            //il metodo del restTemplate per chiamare un servizio è il metodo exchange
            //e ritornerà una ResponseEntity di String
            ResponseEntity<String>  response=restTemplate.exchange(path, HttpMethod.GET,requestEntity,String.class);
            //ora posso riconvertire la mia responseEntity in ogetto
            //il metodo getBody prenderà il Json contenuto nel body e tramite il metodo scritto da noi
            //lo ricorvertirà in oggetto
            return Utilities.convertToObject(response.getBody(),classeDiRisposta);
            //se la chiamata ritorna un codice diverso da 2** andrà in exception
        }catch (HttpClientErrorException ex){
            System.out.println(ex.getStatusCode().value());
            System.out.println(ex.getMessage());
            return null;
        }
    }

    default <P> void callPost(String path,P parameter){
        HttpEntity<String> requestEntity=createHttpEntity(parameter);
        RestTemplate restTemplate=new RestTemplate();
        try {
            restTemplate.exchange(path,HttpMethod.POST,requestEntity,String.class);
        }catch (HttpClientErrorException ex){
            System.out.println(ex.getStatusCode().value());
            System.out.println(ex.getMessage());
        }
    }

    default <P> void callPut(String path,P parameter){
        HttpEntity<String> requestEntity=createHttpEntity(parameter);
        RestTemplate restTemplate=new RestTemplate();
        try {
            restTemplate.exchange(path,HttpMethod.PUT,requestEntity,String.class);
        }catch (HttpClientErrorException ex){
            System.out.println(ex.getStatusCode().value());
            System.out.println(ex.getMessage());
        }
    }




}
