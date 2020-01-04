package br.com.gsw.atm.withdrawmoney.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.gsw.atm.withdrawmoney.model.Customer;

/**
 * 
 * Pedro Luiz da Silva Pereira
 * Esta interface tem o objetivo de ligar as funcionalidades do Controller
 * e serem processadas no respositorio do MongoDB, com varios metodos disponiveis.
 * Algumas customizacoes estao publicadas dentro da interface.
 */

public interface CustomerRepository extends MongoRepository<Customer, String> {

  public List<Customer> findByFirstName(String firstName);
  public List<Customer> findByLastName(String lastName);
  public Customer findBy_id(ObjectId _id);
  public Customer findByEmail(String email);

}