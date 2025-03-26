project3

1. create user table in database, columns are user_id, username, user_password, user_role
2. add dependency jjwt from io.jsonwebtoken
3. authentication:
   1. UserDetailsServiceImpl: retrieve data from database using UserRepository to create UserDetails object
   2. AuthenticationController: define Post mapping endpoint /auth/login, AuthenticationManager validate if input user info matches database info
   3. create Authentication and save in SecurityContextHolder
   4. JwtTokenUtil: generate token
4. authorization
   1. create Filter and override doFilter
   2. get Authorization header and extract token
   3. valid token and create Authentication and save in SecurityContextHolder
   4. config SecurityFilterChain, define global path role restriction
   5. add method level role restriction using @PreAuthorize for Delete mapping in StudentController