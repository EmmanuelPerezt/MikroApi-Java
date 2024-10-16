package com.intermax.api.Model;

import java.util.List;

public class Ipsmodel {
    private String ip_router;

    private List<String> ips_cliente;
    


    public String getIp_router() {
        return ip_router;
    }
    public void setIp_router(String ip_router) {
        this.ip_router = ip_router;
    }
    public List<String> getIps_cliente() {
        return ips_cliente;
    }
    public void setIps_cliente(List<String> ips_cliente) {
        this.ips_cliente = ips_cliente;
    }
}
