# spring-security
Demo project to learn Spring Security using Spring Boot

**Course reference:**  
https://www.udemy.com/course/spring-security-zero-to-master/  
https://github.com/eazybytes/spring-security

**Sections:**

Section 1:
- Adding spring security dependency. By default it enables login page with username and password functionality 
- Enabled authentication for a hardcoded username "lokesh" and password "mittal"

Section 2:
- Added controllers AccountController, BalanceController, CardsController, ContactController, LoansController and NoticesController
- Added security configuration permitAll() on "/contact", "/notices", "/error"
- Added security configuration authenticated() on "/myAccount", "/myBalance", "/myCards", "/myLoans"
- For secured APIs, accepts credentials send into login form AND also accepts header --> key="Authorization" value="Basic base64encoded(username:password)"

Section 3:
- Using InMemoryUserDetailsManager to store user credentials and using PasswordEncoder to hash the in-memory password

Section 4:
- Saving user in custom database table and fetch using our own custom implementation of UserDetailsService

Section 6:
- Add custom AuthenticationProvider
- Exception handling for Basic Auth
  - For 401 Authentication Exception - Added AuthenticationEntryPoint
  - For 403 Authorization Exception - Added AccessDeniedHandler

Section 7:
- Listen to Authentication success and failure events
- Retrieve logged-in user's Authentication from SecurityContext

