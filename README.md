# FullStack Backend - TicketFlow

Aplicación backend para el sistema de gestión de entradas (TicketFlow) construida con Spring Boot 4.0.6, Java 21, y arquitectura hexagonal.

## Arquitectura Implementada

### Módulos Completados

#### 1. **Role Module** (Módulo de Roles)
- **Entidad JPA**: `Role` con id (INTEGER, PK), name (VARCHAR, UNIQUE), description
- **DTOs**: `RoleRequest`, `RoleResponse`
- **Repositorio**: `RoleRepository` (Spring Data JPA)
- **Servicio**: `RoleService` (interfaz) y `RoleServiceImpl` (implementación)
- **Mapper**: `RoleMapper` (MapStruct)
- **Controlador**: `RoleController` (REST API)
- **Endpoints**:
  - `GET /api/roles` - Listar roles con paginación
  - `GET /api/roles/{id}` - Obtener un rol
  - `POST /api/roles` - Crear nuevo rol
  - `PUT /api/roles/{id}` - Actualizar rol
  - `DELETE /api/roles/{id}` - Eliminar rol

#### 2. **User Module** (Módulo de Usuarios)
- **Entidad JPA**: `User` con relación `@ManyToOne` a `Role`
  - id (UUID, generado)
  - firstName, lastName (combinados como fullName)
  - email (UNIQUE)
  - phone
  - pwdHash (contraseña hasheada con BCrypt)
  - active (Boolean)
  - role (Foreign Key a roles)
  - createdAt, updatedAt (auditoria temporal)
  
- **DTOs con validación**:
  - `UserRequest` - Crear usuario (firstName, lastName, email, phone, password requeridos)
  - `UserResponse` - Respuesta del usuario (sin pwdHash)
  - `UserUpdateRequest` - Actualizar usuario
  - `PasswordChangeRequest` - Cambiar contraseña

- **Repositorio**: `UserRepository` (Spring Data JPA)
  - `findByEmail(email)` - Buscar por email
  - `existsByEmail(email)` - Verificar existencia
  - `findAll(Pageable)` - Lista paginada

- **Servicio**: `UserService` (interfaz) y `UserServiceImpl` (implementación)
  - CRUD completo con validaciones
  - Hash de contraseña con BCrypt
  - Cambio de contraseña
  - Desactivación de usuarios
  
- **Mapper**: `UserMapper` (MapStruct)
- **Controlador**: `UserController` (REST API)
- **Endpoints**:
  - `GET /api/users` - Listar usuarios con paginación
  - `GET /api/users/{id}` - Obtener un usuario
  - `POST /api/users` - Crear nuevo usuario
  - `PUT /api/users/{id}` - Actualizar usuario
  - `PATCH /api/users/{id}/password` - Cambiar contraseña
  - `PATCH /api/users/{id}/deactivate` - Desactivar usuario

### Infraestructura Compartida

- **Exception Handling**: `GlobalExceptionHandler` (ControllerAdvice)
  - ResourceNotFoundException
  - BusinessException
  - DataIntegrityViolationException
  - MethodArgumentNotValidException (validación)
  - Excepciones genéricas

- **Response Wrapper**: `ApiResponse<T>` (genérico)
  ```json
  {
    "success": true,
    "message": "Descripción",
    "data": {}
  }
  ```

- **CORS Configuration**: `CorsConfig`
  - Permitir orígenes: `http://localhost:4200`, `http://localhost:3000`
  - Métodos: GET, POST, PUT, DELETE, OPTIONS
  - Headers: * (todos)

- **Security Config**: `SecurityConfig`
  - Bean `PasswordEncoder` con BCryptPasswordEncoder

## Requisitos Previos

- **Java 21** o superior
- **Maven 3.8+**
- **MySQL 8.0+**

## Instalación y Configuración

### 1. Preparar Base de Datos

```sql
CREATE DATABASE ticketflow CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE ticketflow;
```

### 2. Clonar el Repositorio

```bash
git clone https://github.com/SaulAguilarLavado/FullStack-back.git
cd FullStack-back
```

### 3. Configurar Variables de Conexión

Editar `src/main/resources/application.properties`:

```properties
# MySQL Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/ticketflow?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=tu_contraseña
```

### 4. Compilar el Proyecto

```bash
./mvnw clean compile
```

O si estás en Windows:
```bash
mvnw.cmd clean compile
```

### 5. Ejecutar la Aplicación

```bash
./mvnw spring-boot:run
```

La aplicación estará disponible en: `http://localhost:8080/api`

## Scripts de Inicialización

El archivo `data.sql` se ejecuta automáticamente con los siguientes roles:

```sql
INSERT INTO roles (id, name, description) VALUES 
(1, 'ADMIN', 'Administrator role with full access'),
(2, 'USER', 'Regular user role'),
(3, 'MODERATOR', 'Moderator role for content management');
```

## Dependencias Principales

```xml
- Spring Boot 4.0.6
- Spring Data JPA (Hibernate)
- Spring Validation (Jakarta Bean Validation)
- Spring Security Crypto (BCrypt)
- MySQL Connector/J 8.x
- MapStruct 1.5.5
- Lombok
- Springdoc OpenAPI 3.0.2
- JUnit 5 (Testing)
```

## Estructura de Carpetas

```
src/main/java/com/ticketflow/FullStack_back/
├── config/
│   ├── CorsConfig.java
│   └── SecurityConfig.java
├── controllers/
│   ├── RoleController.java
│   ├── UserController.java
│   └── AuthController.java (pendiente)
├── dto/
│   ├── role/
│   │   ├── RoleRequest.java
│   │   └── RoleResponse.java
│   └── user/
│       ├── UserRequest.java
│       ├── UserResponse.java
│       ├── UserUpdateRequest.java
│       └── PasswordChangeRequest.java
├── mappers/
│   ├── RoleMapper.java
│   └── UserMapper.java
├── models/
│   ├── Role.java
│   └── User.java
├── repositories/
│   ├── RoleRepository.java
│   └── UserRepository.java
├── services/
│   ├── RoleService.java
│   ├── RoleServiceImpl.java
│   ├── UserService.java
│   └── UserServiceImpl.java
├── shared/
│   ├── exception/
│   │   ├── BusinessException.java
│   │   ├── GlobalExceptionHandler.java
│   │   └── ResourceNotFoundException.java
│   └── response/
│       └── ApiResponse.java
└── FullStackBackApplication.java
```

## Ejemplo de Uso - cURL

### Crear Rol
```bash
curl -X POST http://localhost:8080/api/roles \
  -H "Content-Type: application/json" \
  -d '{
    "name": "OWNER",
    "description": "Event owner role"
  }'
```

### Crear Usuario
```bash
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "Juan",
    "lastName": "Pérez",
    "email": "juan@example.com",
    "phone": "+34612345678",
    "password": "SecurePassword123!"
  }'
```

### Listar Usuarios
```bash
curl http://localhost:8080/api/users?page=0&size=10
```

### Cambiar Contraseña
```bash
curl -X PATCH http://localhost:8080/api/users/{userId}/password \
  -H "Content-Type: application/json" \
  -d '{
    "currentPassword": "SecurePassword123!",
    "newPassword": "NewPassword456!"
  }'
```

## Próximos Pasos

- [ ] Implementar autenticación JWT
- [ ] Agregar autorización basada en roles
- [ ] Crear módulo de eventos (Event Module)
- [ ] Crear módulo de entradas (Ticket Module)
- [ ] Agregar tests unitarios
- [ ] Agregar tests de integración
- [ ] Documentación OpenAPI/Swagger
- [ ] Configuración de CI/CD

## Convenciones de Código

- **Validación**: Usar anotaciones de `jakarta.validation.constraints`
- **Mapeo**: Usar MapStruct para DTO ↔ Entity
- **Excepciones**: Usar excepciones específicas del negocio
- **Transacciones**: Usar `@Transactional` en servicios
- **Logging**: Nivel DEBUG para módulo, INFO para general

## Soporte

Para dudas o reportes de errores, contactar al equipo de desarrollo.
