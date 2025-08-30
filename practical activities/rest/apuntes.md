## Flujo de Spring Security

1. **El usuario envía credenciales a '/api/auth/login'.**
2. **El controlador autentica usando 'AuthenticationManager' y, si es correcto, genera un JWT.**
3. **El cliente usa el JWT en el header 'Authorization: Bearer <token>' para acceder a endpoints protegidos.**
4. **Un filtro ('JwtAuthenticationFilter') valida el token en cada request y coloca el usuario en el contexto de seguridad.**
5. **Las reglas de acceso (por rol) se definen en 'SecurityConfig'.**
6. **Si el token es inválido o el usuario no tiene permisos, se responde con error JSON estándar.**

##
  - access-ttl: 15m 
  - refresh-ttl: 7d 
  (El usuario podrá hacer peticiones a recursos protegidos por 15 minutos. Si caducan se requiere un refresh token. El proceso de refrescar el token se podrá llevar a cabo máximo por 7 días, obligando al usuario a iniciar sesión nuevamente.)

  Los DTO no hacen lógica, solo transportan datos