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