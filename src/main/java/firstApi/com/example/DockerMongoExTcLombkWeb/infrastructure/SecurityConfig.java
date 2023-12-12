package firstApi.com.example.DockerMongoExTcLombkWeb.infrastructure;

public class SecurityConfig {


  /*  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests((authorize) -> authorize
                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults())
                .formLogin(withDefaults()).csrf().disable();;

        return http.build();
    }*/



  /*  public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.builder()
                .username("user")
                .password(encoder().encode("password"))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }*/
}
