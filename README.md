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
`public interface LocationJpaRepository extends JpaRepository<Location, Long> {
	List<Location> findByStateLike(String stateName);
	Location findFirstByState(String stateName);
	Long countByStateLike(String stateName);
}`

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

**7. Keyword: Before, After and Between**
e.g.,
- `findByFoundedDateBefore(dateObj)`
- `findByFoundedDateAfter(dateObj)`
- `findByFoundedDateBetween(startDate, endDate)`