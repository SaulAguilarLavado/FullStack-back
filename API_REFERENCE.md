# API Endpoints Reference

Base URL: `http://localhost:8080/api`

## Role Endpoints

### List Roles (Paginated)
```http
GET /api/roles?page=0&size=10&sort=name,asc
```
**Response**:
```json
{
  "success": true,
  "message": "Roles encontrados",
  "data": {
    "content": [
      {
        "id": 1,
        "name": "ADMIN",
        "description": "Administrator role"
      }
    ],
    "totalElements": 3,
    "totalPages": 1,
    "size": 10,
    "number": 0
  }
}
```

### Get Role by ID
```http
GET /api/roles/1
```

### Create Role
```http
POST /api/roles
Content-Type: application/json

{
  "name": "OWNER",
  "description": "Event owner role"
}
```
**Response** (201 Created):
```json
{
  "success": true,
  "message": "Rol creado",
  "data": {
    "id": 4,
    "name": "OWNER",
    "description": "Event owner role"
  }
}
```

### Update Role
```http
PUT /api/roles/1
Content-Type: application/json

{
  "name": "SUPER_ADMIN",
  "description": "Super administrator role"
}
```

### Delete Role
```http
DELETE /api/roles/1
```

---

## User Endpoints

### List Users (Paginated)
```http
GET /api/users?page=0&size=10
```

### Get User by ID
```http
GET /api/users/550e8400-e29b-41d4-a716-446655440000
```

### Create User
```http
POST /api/users
Content-Type: application/json

{
  "firstName": "Juan",
  "lastName": "Pérez",
  "email": "juan@example.com",
  "phone": "+34612345678",
  "password": "SecurePass123!",
  "role": "USER"
}
```
**Response** (201 Created):
```json
{
  "success": true,
  "message": "Usuario creado",
  "data": {
    "id": "550e8400-e29b-41d4-a716-446655440000",
    "firstName": "Juan",
    "lastName": "Pérez",
    "email": "juan@example.com",
    "phone": "+34612345678",
    "role": "USER"
  }
}
```

### Update User
```http
PUT /api/users/550e8400-e29b-41d4-a716-446655440000
Content-Type: application/json

{
  "firstName": "Juan",
  "lastName": "García",
  "email": "juan.garcia@example.com",
  "phone": "+34612345679",
  "active": true,
  "role": "MODERATOR"
}
```

### Change Password
```http
PATCH /api/users/550e8400-e29b-41d4-a716-446655440000/password
Content-Type: application/json

{
  "currentPassword": "SecurePass123!",
  "newPassword": "NewSecurePass456!"
}
```

### Deactivate User
```http
PATCH /api/users/550e8400-e29b-41d4-a716-446655440000/deactivate
```

---

## Error Responses

### 400 Bad Request (Validation Error)
```json
{
  "success": false,
  "message": "El email no es válido",
  "data": null
}
```

### 404 Not Found
```json
{
  "success": false,
  "message": "Usuario no encontrado",
  "data": null
}
```

### 409 Conflict (Duplicate Email)
```json
{
  "success": false,
  "message": "El email ya está registrado",
  "data": null
}
```

### 500 Internal Server Error
```json
{
  "success": false,
  "message": "Ocurrió un error interno",
  "data": null
}
```

---

## Validation Rules

### Role
- **name**: Required, unique, must not be blank
- **description**: Required, must not be blank

### User
- **firstName**: Required, must not be blank
- **lastName**: Required, must not be blank
- **email**: Required, valid email format, unique
- **phone**: Required, 7-20 characters
- **password**: Required (on create), minimum 8 characters
- **active**: Optional (default true)
- **role**: Optional (default "USER")

### Password Change
- **currentPassword**: Required, must be correct
- **newPassword**: Required, minimum 8 characters

---

## Pagination Parameters

- `page`: Page number (0-indexed, default 0)
- `size`: Items per page (default 10)
- `sort`: Field name and direction (e.g., `sort=name,asc`)

Example:
```
GET /api/users?page=0&size=20&sort=email,asc
```

---

## cURL Examples

### Create Role
```bash
curl -X POST http://localhost:8080/api/roles \
  -H "Content-Type: application/json" \
  -d '{"name":"VENDOR","description":"Vendor role"}'
```

### Create User
```bash
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{
    "firstName":"María",
    "lastName":"López",
    "email":"maria@example.com",
    "phone":"+34687654321",
    "password":"SecurePass123!"
  }'
```

### List Users (Page 1, 20 items)
```bash
curl "http://localhost:8080/api/users?page=1&size=20"
```

### Change Password
```bash
curl -X PATCH http://localhost:8080/api/users/{userId}/password \
  -H "Content-Type: application/json" \
  -d '{
    "currentPassword":"OldPass123!",
    "newPassword":"NewPass456!"
  }'
```

### Delete User
```bash
curl -X DELETE http://localhost:8080/api/users/{userId}
```

---

## HTTP Methods Summary

| Method | Endpoint | Action |
|--------|----------|--------|
| GET | /api/roles | List all roles (paginated) |
| POST | /api/roles | Create new role |
| GET | /api/roles/{id} | Get specific role |
| PUT | /api/roles/{id} | Update role |
| DELETE | /api/roles/{id} | Delete role |
| GET | /api/users | List all users (paginated) |
| POST | /api/users | Create new user |
| GET | /api/users/{id} | Get specific user |
| PUT | /api/users/{id} | Update user |
| PATCH | /api/users/{id}/password | Change password |
| PATCH | /api/users/{id}/deactivate | Deactivate user |

---

**Last Updated**: May 26, 2026
**API Version**: 1.0
**Status**: Active
