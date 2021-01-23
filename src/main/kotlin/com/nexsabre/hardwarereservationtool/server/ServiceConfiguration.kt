package com.nexsabre.hardwarereservationtool.server

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter
import java.util.*


@Configuration
open class ServiceConfiguration {
    @Bean
    open fun corsFilter(): CorsFilter {
        val source = UrlBasedCorsConfigurationSource()
        val config = CorsConfiguration()
        config.allowCredentials = true
        // Don't do this in production, use a proper list  of allowed origins
        config.allowedOrigins = listOf("http://localhost:8081")
        config.allowedHeaders = Arrays.asList("Origin", "Content-Type", "Accept")
        config.allowedMethods = Arrays.asList("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH")
        source.registerCorsConfiguration("/**", config)
        return CorsFilter(source)
    }
}
