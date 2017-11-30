# Spring-Data-JPA
https://github.com/dlbunker/ps-guitar-db

# Installing Spring-Data-JPA
`<!-- https://mvnrepository.com/artifact/org.springframework.data/spring-data-jpa -->
<dependency>
    <groupId>org.springframework.data</groupId>
    <artifactId>spring-data-jpa</artifactId>
    <version>1.7.1.RELEASE</version>
</dependency>`

`<jpa:repositories base-package="com.guitar.db.repository" />`

Alternatively we can use 
`@EnableJpaRepositories` annotation

- All Spring Data JPA Repositories are interfaces and not classes
- Map 1 to 1 with a JPA entity
- Focus on DAO contract

e.g.,

`public interface MyJpaRepository extends JpaRepository<Entity, Id type>`
- First parameter is the JPa entity
- Second parameter is the primary key data type
