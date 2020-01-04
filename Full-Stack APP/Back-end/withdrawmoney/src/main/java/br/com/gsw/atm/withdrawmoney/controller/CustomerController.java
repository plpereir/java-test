package br.com.gsw.atm.withdrawmoney.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ConvertOperators;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.gsw.atm.withdrawmoney.repository.CustomerRepository;
import br.com.gsw.atm.withdralmoney.exception.ResourceNotFoundException;
import br.com.gsw.atm.withdrawmoney.model.Customer;

/**
 * 
 * Pedro Luiz da Silva Pereira
 * 
 * Esta classe eh responsavel por receber as requisicoes e realizar 
 * a devolucao das respostas para o solicitante da requisicao
 *
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
/**
 * 
 * mapeando que todas as requisicoes relacionadas ao cliente deve iniciar /customer
 *
 */
@RequestMapping("/api/v1")
public class CustomerController{
	  @Autowired
	  private CustomerRepository repository;
	  
	  /**
	   * 
	   * Este metodo retorna o JSON de todos os clientes cadastrados no MongoDB, 
	   * para realizar a chamada /customer/
	   */
	    @GetMapping("/customer")
	    public List<Customer> getAllCustomer() {
	        return repository.findAll();
	    }
	    
	  /**
	   * 
	   * Este metodo retorna o JSON de um cliente especifico cadastrado no MongoDB, 
	   * para realizar a chamada /customer/[id do usuario no banco MongoDB].
	   */	  
	    @GetMapping("/customer/{id}")
	    public ResponseEntity<Customer> getCustomerById(@PathVariable(value = "id") String customerId)
	        throws ResourceNotFoundException {
	    	ObjectId id = new ObjectId(customerId);
			  if (repository.existsById(id.toHexString()))
			  {
			      Customer customer = repository.findBy_id(id);
			      return ResponseEntity.ok().body(customer);
			  }
			  else
			  {
				  return ResponseEntity.ok().body(null);	  
			  }
	    }
	  
	  /**
	   * 
	   * Este metodo retorna o JSON uma lista de clientes com resultado na pesquisa pelo campo first Name. 
	   * para realizar a chamada /customer/[primeiro nome]
	   */
	  @RequestMapping(value = "/customer/firstName/{firstName}", method = RequestMethod.GET)
	  public List<Customer> getCustomerByfirstName(@PathVariable("firstName") String firstName) {
	    return repository.findByFirstName(firstName);
	  }

	  /**
	   * 
	   * Este metodo retorna o JSON uma lista de clientes com resultado na pesquisa pelo campo last Name. 
	   * para realizar a chamada /customer/[ultimo nome]
	   */	  
	  @RequestMapping(value = "/customer/lastName/{lastName}", method = RequestMethod.GET)
	  public List<Customer> getCustomerBylastName(@PathVariable("lastName") String lastName) {
	    return repository.findByLastName(lastName);
	  }

	  /**
	   * 
	   * Este metodo retorna o numero de requistros na base MongoDB. 
	   * para realizar a chamada /customer/count
	   */	  
	  @RequestMapping(value = "/customer/count", method = RequestMethod.GET)
	  public Long getCustomerByCount() {
	    return repository.count();
	  }

	  /**
	   * 
	   * Este metodo retorna se teve sucesso ou nao o login do usuario no sistema, 
	   * consistindo os dados no MongoDB. 
	   * para realizar a chamada /customer/[email do usuario]/[senha do usuario]
	   */		  
	  @RequestMapping(value = "/customer/login/{email}/{password}", method = RequestMethod.GET)
	  public String checkCustomerLogin(
			  @PathVariable("email") String email, 
			  @PathVariable("password") String password) {
		  Customer customer = repository.findByEmail(email);
		  if (customer.getEmail().toString().equals(email.toString()) && 
			  customer.getPassword().toString().equals(password.toString())){
			  return "true";
		  }else
		  {
			  return "false";
		  }
	  }

	  /**
	   * 
	   * Este metodo retorna o JSON um cliente especifico cadastrado no MongoDB, e faz a delecao, 
	   * para realizar a chamada /customer/delete/[id do usuario no banco MongoDB].
	   */	  
	  
	    @DeleteMapping("/customer/{id}")
	    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") String customerId)
	         throws ResourceNotFoundException {
	    	ObjectId objectId = new ObjectId(customerId);
	        Customer customer = repository.findBy_id(objectId);	
	        Map<String, Boolean> response = new HashMap<>();
			  if (repository.existsById(objectId.toHexString()))
			  {
				  repository.delete(customer);
			      response.put("deleted", Boolean.TRUE);
			  }
			  else
			  {
			      response.put("ID not found, not deleted", Boolean.FALSE);			  
			  }
	        return response;
	    }
	  
	  /**
	   * 
	   * Este metodo insere um novo registro no banco MongoDB. 
	   * para realizar a chamada /customer/new/[primero nome]/[ultimo nome]/[email]/[senha]/[saldo bancario]
	   */		  
	    @PostMapping("/customer")
	    public Customer createEmployee(@Valid @RequestBody Customer customer) {
	    	if (!customer.getFirstName().isEmpty() && 
	    		!customer.getLastName().isEmpty() && 
	    		!customer.getEmail().isEmpty() && 
	    		!customer.getPassword().isEmpty() && 
	    		 customer.getAccountBalance()>0)
	    	{
		        return repository.save(customer);
	    	}else
	    	{
	    		return null;
	    	}
	    }
	    
	  /**
	   * 
	   * Este metodo atualiza um novo registro no banco MongoDB. 
	   * para realizar a chamada /customer/update/[id do usuario no MongoDB]/[primero nome]/[ultimo nome]/[email]/[senha]/[saldo bancario]
	   */		  
	  
	    
	    @PutMapping("/customer/{id}")
	    public ResponseEntity<Boolean> updateCustomer(@PathVariable(value = "id") String customerId,
	         @Valid @RequestBody Customer customerDetails) throws ResourceNotFoundException {
	    	ObjectId objectId = new ObjectId(customerId);
	        Customer customer = repository.findBy_id(objectId);
	        Boolean submitted = true;
	        if (!customerDetails.getFirstName().isEmpty()){
		        customer.setFirstName(customerDetails.getFirstName());
	        }else{submitted = false;}
	        if (!customerDetails.getLastName().isEmpty()){
		        customer.setLastName(customerDetails.getLastName());
	        }else{submitted = false;}
	        if (!customerDetails.getEmail().isEmpty())
	        {
		        customer.setEmail(customerDetails.getEmail());
	        }else{submitted = false;}
	        if (!customerDetails.getPassword().isEmpty())
	        {
		        customer.setPassword(customerDetails.getPassword());
	        }else{submitted = false;}
	        if (customerDetails.getAccountBalance()>0)
	        {
		        customer.setAccountBalance(customerDetails.getAccountBalance());
	        }else{submitted = false;}

	        if (submitted)
	        {
		        repository.save(customer);
	        } 
	        return ResponseEntity.ok(submitted);
	    } 
	    

	  /**
	   * 
	   * Este metodo realiza o saque na conta do cliente, respeitando as notas disponiveis. 
	   * para realizar a chamada /customer/cash/[id do usuario no banco MongoDB]/[saldo bancario]
	   */		  
	  
	  @PutMapping(value = "/customer/cash/{id}/{value}")
	  public ResponseEntity<String> getCashValue(
			  @PathVariable("id") String id, 
			  @PathVariable("value") double value) {
		  
		  int[] bills =  {100, 50, 20, 10};
		  double tmpValue =  value;
		  String response = "";
		  
		  if (repository.existsById(id))
		  {
		    	ObjectId objectId = new ObjectId(id);
		        Customer customer = repository.findBy_id(objectId);;
			  
			  if (tmpValue>0 && ((customer.getAccountBalance()-value)>0))
			  {
					for(int i = 0; i < bills.length; i++){
						if( tmpValue >= bills[i] ){
							response += (int)tmpValue/bills[i] + " bills of the " + bills[i]+", ";
							tmpValue = tmpValue % bills[i];
						}
					}
					if (tmpValue>0)
					{
						return ResponseEntity.ok("[{\"alert\":\"warning\"},{\"message\":\""+"Account not updated, is required multiple values 100, 50, 20 and 10."+"\"}]");
					}else
					{
						customer.setAccountBalance(customer.getAccountBalance()-value);
						repository.save(customer);
						return ResponseEntity.ok("[{\"alert\":\"success\"},{\"message\":\""+"Account update, new account value: "+customer.getAccountBalance()+" bills:  "+response+"\"}]");
					}
			  }else
			  {
				  return ResponseEntity.ok("[{\"alert\":\"warning\"},{\"message\":\""+"Account not Update, please fill value greater 0.00 or insufficient bank balance."+"\"}]");
			  }
		  }
		  else
		  {
			  return ResponseEntity.ok("[{\"alert\":\"danger\"},{\"message\":\""+"Document not found."+"\"}]");
		  }
	  }
	  
}
