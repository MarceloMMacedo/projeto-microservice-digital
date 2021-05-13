package br.com.digital.dgestoque.faingbases;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

@Component
@FeignClient(name = "dg-movimento", path = "/movimentos")
public interface MovimentoFeingClient {

}
