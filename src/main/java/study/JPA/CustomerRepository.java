package study.JPA;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


/**
 * 
 * Spring Data JPA focuses on using JPA to store data in a relational database.
 * Its most compelling feature is the ability to create repository implementations automatically,
 * at runtime, from a repository interface.
 * 
 * The type of entity and ID that CustomerRepository works with, Customer and Long in this case,
 * are specified in the generic parameters on CrudRepository. 
 * 
 * By extending CrudRepository, CustomerRepository inherits several methods for working with Customer persistence, 
 * including methods for saving, deleting, and finding Customer entities.
 * 
 * You don’t have to write an implementation of the repository interface. Spring Data JPA creates an implementation on the fly when you run the application.
 */

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);
    
}