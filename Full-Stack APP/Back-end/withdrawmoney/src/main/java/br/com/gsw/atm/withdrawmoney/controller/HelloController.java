package br.com.gsw.atm.withdrawmoney.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * Pedro Luiz da Silva Pereira
 *
 * Esta Classe retorna a mensagem de que o Spring Boot esta disponivel ou nao.
 * 
 * para acessar localhost:8080/
 */

@RestController
public class HelloController {

  @RequestMapping("/")
  public String index() {
	  try{
		    return "Greetings from Spring Boot!";
	  }catch(Exception ex)
	  {
		    return "Error: "+ex.toString();
	  }
  }
}
