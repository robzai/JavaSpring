package study.JPA;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity    //@Entity, indicating that it is a JPA entity. For lack of a @Table annotation, it is assumed that this entity will be mapped to a table named Customer.
public class Customer {

    @Id    // with @Id so that JPA will recognize it as the object’s ID
    @GeneratedValue(strategy=GenerationType.AUTO)    // with @GeneratedValue to indicate that the ID should be generated automatically
    private Long id;
    
    // Unannotated. It is assumed that they’ll be mapped to columns that share the same name as the properties themselves.
    private String firstName;
    
    private String lastName;

    //The default constructor only exists for the sake of JPA. You won’t use it directly, so it is designated as protected
    protected Customer() {}

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
                "Customer[id=%d, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }

}
