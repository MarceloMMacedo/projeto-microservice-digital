package br.com.digital.dgestoque.faingbases;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

@Component
@FeignClient(name = "dg-pessoa", path = "/fornecedores")
public interface FornecedorFeingClient {

}
