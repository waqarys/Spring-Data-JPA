# Spring-Data-JPA
https://github.com/dlbunker/ps-guitar-db

# Installing Spring-Data-JPA
```
<!-- https://mvnrepository.com/artifact/org.springframework.data/spring-data-jpa -->
<dependency>
    <groupId>org.springframework.data</groupId>
    <artifactId>spring-data-jpa</artifactId>
    <version>1.7.1.RELEASE</version>
</dependency>
```

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

# JPA Repository Features
1. Functionality
	- Query DSL
	- CRUD Operations
	- Paging and Sorting
	- Helpers
		- count()
		- exists(Long id)
		- flush
		- deleteInBatch(Iterable entities)
		
# Query DSL (Domain Specific Language)

# Query Methods Syntax Basics
**1. Query Methods**
	- Query parser will match the following:
		- find..By, query..By, read..By, count..By, get..By
	- Criteria uses JPA entity attribute names
	- Multiple criteria combined with ["And","Or"]
	
**2. Query Methods Return Types**
e.g.,
```
public interface LocationJpaRepository extends JpaRepository<Location, Long> {
	List<Location> findByStateLike(String stateName);
	Location findFirstByState(String stateName);
	Long countByStateLike(String stateName);
}
```

**3. Keyword: AND and Or**
e.g., 
- `findByStateAndCountry("CA", "USA");`
- `findByStateOrState("CA", "AZ");`

**4. Keyword: Equals, Is and Not**
e.g.,
- `findByState("CA")`
- `findByStateIs("CA")`
- `findByStateEquals("CA")`
- `findByStateNot("CA")`

**5. Keyword: Like and NotLike**
e.g., 
- `findByStateLike("Cali%")`
- `findByStateNotLike("Al%")`

**6. Keyword: StartingWith, endingWith and Containing**
Similar to the "Like" keyword except the % is automatically added to the filter value.
e.g.,
- `findByStateStartingWith("Al")`   -> 	Al%
- `findByStateEndingWith("ia")`   -> 	%ia
- `findByStateContainingWith("in")`   -> 	%in%

**7. Keyword: LessThan(Equal) and GreaterThan(Equal)**
e.g.,
- `findByPriceLessThan(20)`
- `findByPriceLessThanEqual(20)`

**8. Keyword: Before, After and Between**
e.g.,
- `findByFoundedDateBefore(dateObj)`
- `findByFoundedDateAfter(dateObj)`
- `findByFoundedDateBetween(startDate, endDate)`

**9. Keyword: True and False**
e.g., 
- `findByActiveTrue()`
- `findByActiveFalse()`

**10. Keyword: IsNull, IsNotnull and NotNull**
e.g.,
- `findByStateIsNull`
- `findByStateIsNotNull`
- `findByStateNotNull`

**11. Keyword: IsNull, IsNotnull and NotNull**
e.g.,
- `findByStateIn(Collection<String> states)`
- `findByStateNotIn(Collection<String> states)`

**12. Keyword: IgnoreCase**
When you need to perform a case insensitive comparison
e.g.,
- `findByStateIgnoreCase("ca")`
- `findByStateStartingWithIgnoreCase("c")`

**13. Keyword: OrderBy**
Used to set order by clause on your query
e.g.,
- `findByStateOrderByCountryAsc()`
- `findByStateOrderByCountryDesc()`

**14. Keyword: First, Top and Distinct**
Used to limit the results returned by the query
e.g.,
- `findFirstByStateLike("Al")`
- `findTop5ByStateLike("A")`
- `findDistinctManufacturerByStateLike("A")`

# Query Annotations
- Reuse existing JPQL
- Advanced query functionality
- Eager loading control("fetch")
e.g.,
`@Query("select m from Model m where m.name = ?1")
List<Model> queryByName(String name)`

# Query Options
**1. Named Parameters**
```
@Query("select m from Model m where m.name = :modelname")
List<Model> queryByName(@Param("modelname") String name)
```

**2. Enhanced JPQL Syntax**
```
@Query("select m from Model m where m.name like %?1")
List<Model> queryByName(String name)
```

**3. Native Queries**
```
@Query(value = "select * from Model where name = ?0", nativeQuery = true)
List<Model> queryByName(String name)
```

**4. Modifiable Queries**
```
@Modifying
@Query("update Model m set m.name = ?1")
int updateByName(String name)
```


**5. Named Queries**

# Query Precedence
- Methods with @Query annotation take highest precedence
- Methods that match a named or native named query "name"
- Methods that follow the query DSL keyword naming structure

# Paging and Sorting

# Custom Repository

# Auditing Support
- @CreatedBy
- @CreatedDate
- LastModifiedBy
- @LastModifiedDate 
e.g.,
```
@Entity
public class Model{
	@CreatedBy
	private USer user;
	
	@CreatedDate
	private DateTime createdDate;
}
```

We can use Spring's Auditor framework.

public class SecurityAuditorAware implements AuditorAware<User>

**XML Configuration**
`<jpa:auditing auditor-aware-ref="securityAuditorAware" />`

**Annotation Config**
```
@Configuration
@EnableJpaAuditing
public class Config {
	@Bean
	public AuditorAware<User> auditorProvider() {
		`return new SecurityAuditorAware();
	}
}
```

# Locking
```
@Entity
public class Model{
	@Version
	private int version;
	
}
```

**Optimistic Locking**
- If the version number doesn't match, throws `OptimisticLockException`
- Rolls back the transaction

**Pessimistic Locking**
- Long term locks the data for the transaction duration, preventing others from
accessing the data until the transaction commits.
```
@Lock(LockModeType.PESSIMISTIC_WRITE)
List<Model> findByModelTypeNameIn(List<String> types)
```

- while using `@Lock` annotation queries should run in a transaction else you will get Exception when using Data access layer.
