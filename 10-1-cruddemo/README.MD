### Project dependency and persistence documentation

Below is a concise, module-by-module overview of the dependencies used in this repository, with a special focus on data/persistence choices (JPA/Hibernate, SQL style, transactions, etc.). I also point to the key files that demonstrate these choices.

---

### Repository overview
- Build system: Maven (each subfolder is a separate Spring Boot Maven module)
- Spring Boot parent: `org.springframework.boot:spring-boot-starter-parent:3.5.7`
- Java version: `25` (as declared in each `pom.xml`)

Modules scanned (paths relative to repo root):
- `10-1-cruddemo` — CRUD demo with JPA and MySQL
- `10-hibernate-jpa-crud` — a web demo scaffold (name suggests JPA but no JPA dependency is present)
- `08-bean-lifecycle-methods`, `09-java-config-bean`, `demo 3 - Qualifier`, `demo 4`, `demo1`, `demo2` — Spring Boot web/demo projects without persistence deps

---

### 10-1-cruddemo
Path: `/10-1-cruddemo`

#### Key dependencies
- `org.springframework.boot:spring-boot-starter-data-jpa`
    - Brings JPA API (`jakarta.persistence`), Spring Data JPA, and Hibernate as the default JPA provider.
- `com.mysql:mysql-connector-j` (scope `runtime`)
    - MySQL JDBC driver.
- `org.springframework.boot:spring-boot-starter-test` (scope `test`)

See: `/10-1-cruddemo/pom.xml`

#### Persistence style and patterns
- JPA provider: Hibernate (implicit via Spring Boot starter)
- Entity mapping with annotations:
    - Example entity: `/10-1-cruddemo/src/main/java/com/bobbyakyong/cruddemo/entity/Student.java`
        - Uses `@Entity`, `@Table`, `@Id`, `@GeneratedValue(strategy = IDENTITY)`, and `@Column`.
- Data access approach: JPA `EntityManager` with manual JPQL (HQL-style) queries
    - DAO interface: `/10-1-cruddemo/src/main/java/com/bobbyakyong/cruddemo/dao/StudentDAO.java`
    - Implementation: `/10-1-cruddemo/src/main/java/com/bobbyakyong/cruddemo/dao/StudentDAOImpl.java`
        - Injects `jakarta.persistence.EntityManager`
        - Examples:
            - Read all ordered by last name:
              ```java
              TypedQuery<Student> query = entityManager.createQuery(
                  "FROM Student s order by lastName desc", Student.class);
              ```
            - Filter by last name:
              ```java
              TypedQuery<Student> query = entityManager.createQuery(
                  "FROM Student s where s.lastName = :lastName", Student.class);
              query.setParameter("lastName", lastName);
              ```
            - Bulk delete:
              ```java
              int rows = entityManager.createQuery("DELETE FROM Student").executeUpdate();
              ```
        - Mutation methods annotated with `@Transactional` at the method level (Spring-managed transactions): `save`, `update`, `delete`, `deleteAll`.
- SQL/DDL and logging configuration: `/10-1-cruddemo/src/main/resources/application.properties`
    - DataSource:
        - `spring.datasource.url=jdbc:mysql://localhost:3306/student_tracker?useSSL=false`
        - `spring.datasource.username=springstudent`
        - `spring.datasource.password=springstudent`
    - Hibernate DDL auto: `spring.jpa.hibernate.ddl-auto=update` (schema auto-update)
    - Hibernate SQL logs:
        - `logging.level.org.hibernate.SQL=DEBUG`
        - `logging.level.org.hibernate.orm.jdbc.bind=TRACE` (shows bound parameters)
    - Boot banner/log verbosity tweaks.

Summary: This module uses Spring Data JPA with Hibernate and the MySQL driver. Data access is implemented manually via `EntityManager` and JPQL (HQL-style) queries inside a `@Repository` DAO class; there is no `JpaRepository`/Spring Data repository interface in use. Transactions are Spring-managed via `@Transactional`.

---

### 10-hibernate-jpa-crud
Path: `/10-hibernate-jpa-crud`

#### Key dependencies
- `org.springframework.boot:spring-boot-starter-web`
- `org.springframework.boot:spring-boot-devtools` (runtime, optional)
- `org.springframework.boot:spring-boot-starter-test` (test)

See: `/10-hibernate-jpa-crud/pom.xml`

Notes: Despite the folder name, this module does not declare JPA/Hibernate/database dependencies at this time. It is a web scaffold only.

---

### Other demo modules (no persistence)
Modules: `/08-bean-lifecycle-methods`, `/09-java-config-bean`, `/demo 3 - Qualifier`, `/demo 4`, `/demo1`, `/demo2`

#### Key dependencies (common across these demos)
- `org.springframework.boot:spring-boot-starter-web`
- `org.springframework.boot:spring-boot-devtools` (runtime, optional)
- `org.springframework.boot:spring-boot-starter-test` (test)

None of these modules include JPA/Hibernate or a database driver.

Example POMs:
- `/08-bean-lifecycle-methods/pom.xml`
- `/09-java-config-bean/pom.xml`
- `/demo1/pom.xml` (has commented examples of optional `actuator` and `security` dependencies)
- `/demo2/pom.xml`
- `/demo 3 - Qualifier/pom.xml`
- `/demo 4/pom.xml`

---

### Versions and plugins
- Spring Boot Maven Plugin: present in all modules (`org.springframework.boot:spring-boot-maven-plugin`)
- Parent manages dependency versions (via `spring-boot-starter-parent:3.5.7`). Individual versions are not pinned in child POMs.

---

### Short “at a glance” summary
- Persistence present: Only in `10-1-cruddemo`
    - Provider: Hibernate (via Spring Data JPA)
    - Access style: JPA `EntityManager` + manual JPQL/HQL
    - Transactions: Spring `@Transactional`
    - DB: MySQL (`mysql-connector-j`), JDBC URL in `application.properties`
    - DDL policy: `spring.jpa.hibernate.ddl-auto=update`
    - SQL logging: enabled (statements + bind params)
- All other modules: Web-only demos (no JPA/DB)

---

### Would you like this saved as a markdown file?
I can add a `DEPENDENCIES.md` at the repository root or update each module’s `README.md` with the relevant section. Tell me your preference:
- Single consolidated `DEPENDENCIES.md` at repo root, or
- Per-module additions (e.g., update `/10-1-cruddemo/README.MD` and create missing READMEs for others).

If you choose one, I’ll prepare and commit the file(s) accordingly.