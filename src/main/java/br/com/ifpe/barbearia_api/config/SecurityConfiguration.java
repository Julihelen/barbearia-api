// package br.com.ifpe.barbearia_api.config;

// import java.util.Arrays;
// import java.util.List;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.http.HttpMethod;
// import org.springframework.security.authentication.AuthenticationProvider;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
// import org.springframework.web.cors.CorsConfiguration;
// import org.springframework.web.cors.CorsConfigurationSource;
// import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

// import br.com.ifpe.barbearia_api.modelo.acesso.Perfil;
// import br.com.ifpe.barbearia_api.modelo.seguranca.JwtAuthenticationFilter;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfiguration {

//     private final AuthenticationProvider authenticationProvider;
//     private final JwtAuthenticationFilter jwtAuthenticationFilter;

//     public SecurityConfiguration(JwtAuthenticationFilter jwtAuthenticationFilter,
//                                  AuthenticationProvider authenticationProvider) {
//         this.authenticationProvider = authenticationProvider;
//         this.jwtAuthenticationFilter = jwtAuthenticationFilter;
//     }

//     @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

//         http
//             .cors(cors -> cors.configurationSource(corsConfigurationSource()))
//             .csrf(c -> c.disable())
//             .authorizeHttpRequests(authorize -> authorize

//                 // Rotas públicas
//                 .requestMatchers(HttpMethod.POST, "/api/cliente").permitAll()
//                 .requestMatchers(HttpMethod.POST, "/api/auth").permitAll()

//                 // Swagger (documentação)
//                 .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()

//                 // Outras rotas protegidas por perfil
//                 .requestMatchers("/api/agendamento/**").hasAnyAuthority(
//                     Perfil.ROLE_CLIENTE,
//                     Perfil.ROLE_FUNCIONARIO_USER,
//                     Perfil.ROLE_FUNCIONARIO_ADMIN)

//                 .requestMatchers("/api/barbeiro/**").hasAnyAuthority(
//                     Perfil.ROLE_FUNCIONARIO_ADMIN, Perfil.ROLE_FUNCIONARIO_USER)

//                 .requestMatchers("/api/servicos/**").hasAnyAuthority(
//                     Perfil.ROLE_CLIENTE, Perfil.ROLE_FUNCIONARIO_USER, Perfil.ROLE_FUNCIONARIO_ADMIN)

//                 // Qualquer outra rota exige autenticação
//                 .anyRequest().authenticated()
//             )
//             .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//             .authenticationProvider(authenticationProvider)
//             .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

//         return http.build();
//     }

//     public CorsConfigurationSource corsConfigurationSource() {
//         CorsConfiguration configuration = new CorsConfiguration();
        
//         configuration.setAllowedOrigins(List.of("http://localhost:3000")); // origem correta
//         configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); // inclui OPTIONS
//         configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
//         configuration.setAllowCredentials(true); // permite enviar o JWT

//         UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//         source.registerCorsConfiguration("/**", configuration);
//         return source;
//     }
// }
