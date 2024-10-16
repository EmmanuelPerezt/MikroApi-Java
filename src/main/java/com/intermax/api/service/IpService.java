package com.intermax.api.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.intermax.api.Model.Ipmodel;
import com.intermax.api.Model.Ipsmodel;

import io.github.cdimascio.dotenv.Dotenv;
import me.legrange.mikrotik.ApiConnection;
import me.legrange.mikrotik.MikrotikApiException;

@Component
public class IpService {

    Dotenv dotenv = Dotenv.load();
    String user = dotenv.get("MIKROTIK_USUARIO");
    String password = dotenv.get("MIKROTIK_PASSWORD");

    public String cortar(Ipmodel ipmodel) throws MikrotikApiException {
        String cliente = ipmodel.getIp_cliente();
        ApiConnection con = ApiConnection.connect(ipmodel.getIp_router());
        con.login(user, password);
        try {
            List<Map<String, String>> result = con
                    .execute(String.format("/ip/firewall/address-list/print where address=%s", cliente));
            if (!result.isEmpty()) {
                String id = result.get(0).get(".id");
                con.execute(String.format("/ip/firewall/address-list/set .id=%s disabled=no", id));
                return "cortado correcto";
            } else {
                return "No se encontró ninguna entrada con la IP especificada: " + cliente;

            }
        } catch (MikrotikApiException e) {
            return e.getMessage();
        } finally {
            con.close();
        }
    }

    public String activar(Ipmodel ipmodel) throws MikrotikApiException {
        String cliente = ipmodel.getIp_cliente();
        ApiConnection con = ApiConnection.connect(ipmodel.getIp_router());
        con.login(user, password);
        try {
            List<Map<String, String>> result = con
                    .execute(String.format("/ip/firewall/address-list/print where address=%s", cliente));
            if (!result.isEmpty()) {
                String id = result.get(0).get(".id");
                con.execute(String.format("/ip/firewall/address-list/set .id=%s disabled=yes", id));
                return "activado correcto";
            } else {
                return "No se encontró ninguna entrada con la IP especificada: " + cliente;

            }
        } catch (MikrotikApiException e) {
            return e.getMessage();
        } finally {
            con.close();
        }

    }
    public String localidad(Ipsmodel ipsmodel) throws MikrotikApiException{

        List<String>ips=ipsmodel.getIps_cliente();
        ApiConnection con = ApiConnection.connect(ipsmodel.getIp_router());
        con.login(user, password);
        try  {
            for(String ipsArray:ips){
                List<Map<String, String>> result=con.execute(String.format("/ip/firewall/address-list/print where address=%s", ipsArray));
                
                if (!result.isEmpty()) {
                    String id = result.get(0).get(".id");
                    con.execute(String.format("/ip/firewall/address-list/set .id=%s disabled=no", id));
                } else {
                    return "No se encontró ninguna entrada con la IPs especificada: " + ips;
    
                }
            }
            return "activado correcto";
        } catch (MikrotikApiException e) {
            return e.getMessage();
        }finally{
            con.close();
        }
    }
}
