### Note:
Kalau pakai spring data rest plugin. 
kita tidak perlu membuat Services dan Controller. kita hanay perlu buat DAO/Repository untuk memakai JpaRepository sehingga 
by default spring data rest plugin akan membuat Rest API untuk kita.


Jadi cukup buat Entity dan JPA Repository. Hanya 2 file saja.

```java
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path="employees") //change path to anything you want
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    
}
```

contoh diatas adalah untuk path employees, kalau di controller kita biasanya buat /employees/ `@RestController("/api")` karena kita pakai rest jadi tiak ada controller
maka kita setup di repository saja yang ada `implements JpaRepository<Employee, Long>`