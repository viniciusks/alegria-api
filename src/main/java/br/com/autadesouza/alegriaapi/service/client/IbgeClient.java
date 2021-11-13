package br.com.autadesouza.alegriaapi.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "ibge-client", url = "http://servicodados.ibge.gov.br/api/v1")
public interface IbgeClient {

    @RequestMapping(method = RequestMethod.GET, value = "/localidades/estados/{UF}/municipios")
    String getMunicipiosByUf(@PathVariable("UF") String uf);
}
