package br.com.gsw.atm.withdrawmoney.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 
 * Pedro Luiz da Silva Pereira
 * 
 * Esta classe eh o Modelo utilizado da abstracao do objeto cliente
 * Para ser utilizado para consulta e armazenamento de dados no banco de dados MongoDB.
 *
 */

/**
 * 
 * Associando a colecao de dados utilizada no MongoDB
 *
 */
@Document(collection="customer")
public class Customer {

  @Id
  private ObjectId _id;

  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private double accountBalance;

  public Customer() {}
  
  public Customer(ObjectId _id, 
		  			String firstName, 
		  			String lastName, 
		  			String email, 
		  			String password, 
		  			double accountBalance) {
	this._id = _id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.accountBalance = accountBalance;
  }
  
  public Customer(String firstName, 
		  		  String lastName, 
		  		  String email, 
		  		  String password, 
		  		  double accountBalance) {
	ObjectId _id = new ObjectId();
	this._id = _id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.accountBalance = accountBalance;    
  }

  public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public double getAccountBalance() {
	return accountBalance;
}

public void setAccountBalance(double accountBalance) {
	this.accountBalance = accountBalance;
}

public String get_id() {
	return _id.toHexString();
}

public void set_id(ObjectId _id) {
	this._id = _id;
}

public String getFirstName() {
	return firstName;
}

public void setFirstName(String firstName) {
	this.firstName = firstName;
}

public String getLastName() {
	return lastName;
}

public void setLastName(String lastName) {
	this.lastName = lastName;
}

@Override
  public String toString() {
    return String.format(
        "Customer[id=%s, firstName='%s', lastName='%s']",
        _id, firstName, lastName);
  }

}