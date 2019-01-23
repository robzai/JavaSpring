package study.JPAWithREST;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// @RepositoryRestResource annotation to direct Spring MVC to create RESTful endpoints at /people
// @RepositoryRestResource is not required for a repository to be exported. It is only used to change the export details, 
// such as using /people instead of the default value of /persons
@RepositoryRestResource(collectionResourceRel = "people", path = "people")
public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {

	List<Person> findByLastName(@Param("name") String name);

}
