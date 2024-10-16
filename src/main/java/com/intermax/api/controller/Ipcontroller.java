package com.intermax.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.intermax.api.Model.Ipmodel;
import com.intermax.api.Model.Ipsmodel;
import com.intermax.api.service.IpService;

import me.legrange.mikrotik.MikrotikApiException;

@RestController
@RequestMapping("/")
public class Ipcontroller {
    @GetMapping("/")
    public String helloworld(){
        return"hello world";
    }
    @PostMapping("/cortar")
    public ResponseEntity<Ipmodel> corte(@RequestBody Ipmodel ipmodel) throws MikrotikApiException{
        String result = new IpService().cortar(ipmodel);
        if(result=="cortado correcto"){
            return ResponseEntity.ok(ipmodel);
        }else{
            return ResponseEntity.status(500).body(ipmodel);
        }

        
    }
    @PostMapping("/activar")
    public ResponseEntity<Ipmodel> activar(@RequestBody Ipmodel ipmodel) throws MikrotikApiException{

       String result =new IpService().activar(ipmodel);
        
       if(result=="activado correcto"){
        return ResponseEntity.ok(ipmodel);
    }else{
        return ResponseEntity.status(500).body(ipmodel);
    } 
    }
    @PostMapping("/localidad")
    public ResponseEntity<String> localidad(@RequestBody Ipsmodel ipsmodel) throws MikrotikApiException{

        String result =new IpService().localidad(ipsmodel);

        if(result=="activado correcto"){
            return ResponseEntity.ok(result);
        }else{
            return ResponseEntity.status(500).body(result);
        }
    }
}
