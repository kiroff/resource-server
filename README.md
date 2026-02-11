# Resource Server (`resource-server`)

Backend service that exposes protected HTTP endpoints as an **OAuth2 Resource Server** (JWT-based) and registers itself with **Eureka** for service discovery. In a typical setup, requests reach this service via the API Gateway, and authentication is handled via an external Identity Provider (e.g., Keycloak).

## What this project is for

- Serve application/business APIs (the “resource” in OAuth terms)
- Protect endpoints using **JWT** validation (OAuth2 Resource Server)
- Register with **Eureka** so other components can discover it by service name

## Tech stack

- **Java:** 25
- **Build:** Maven (requires **3.9+**)
- **Framework:** Spring Boot **4.0.2**
- **Spring Cloud:** **2025.1.1**
- **Web:** Spring MVC (`spring-boot-starter-web`)
- **Security:** OAuth2 Resource Server (`spring-boot-starter-security-oauth2-resource-server`)
- **Discovery:** Eureka Client (`spring-cloud-starter-netflix-eureka-client`)

## Prerequisites

- JDK **25**
- Maven **3.9+** (or use the Maven Wrapper: `./mvnw`)
- A running **Eureka server** (see `discovery-service`)
- A running **OIDC provider** that issues JWTs (commonly Keycloak)

