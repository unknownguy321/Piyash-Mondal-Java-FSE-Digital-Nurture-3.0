import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Bean
public CorsFilter corsFilter() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowCredentials(true);
    config.addAllowedOriginPattern("*"); // Allow all origins or specify allowed origins
    config.addAllowedHeader("*"); // Allow all headers
    config.addAllowedMethod("*"); // Allow all HTTP methods
    source.registerCorsConfiguration("/**", config);
    return new CorsFilter(source);
}
