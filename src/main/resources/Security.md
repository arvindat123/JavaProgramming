---

### **Difference Between Authentication and Authorization**

| Feature                | **Authentication**                                                      | **Authorization**                                                       |
|------------------------|-------------------------------------------------------------------------|-------------------------------------------------------------------------|
| **Definition**         | The process of verifying the identity of a user or system.             | The process of determining what actions or resources a user/system has access to. |
| **Focus**              | Identifies "Who are you?"                                               | Determines "What can you do?" or "What resources can you access?"      |
| **Purpose**            | Confirms the identity of the user.                                      | Validates permissions for specific actions or resources.               |
| **Process**            | Usually involves credentials such as username/password, biometrics, etc. | Involves checking permissions and roles assigned to the authenticated user. |
| **Sequence**           | Always happens before authorization.                                    | Happens only after successful authentication.                          |
| **Outcome**            | Ensures the user/system is genuine.                                     | Ensures the user/system has proper access rights.                      |
| **Implementation Examples** | Login forms, fingerprint scans, OTP verification.                   | Role-based access control (RBAC), file permissions, access tokens.     |
| **Level of Access**    | No access beyond proving identity.                                      | Grants or denies access to resources based on permissions.             |
| **Technology Examples**| OAuth, OpenID, Multi-factor Authentication (MFA).                      | Access Control Lists (ACLs), RBAC, Policies.                           |
| **Example Questions**  | "Who is trying to log in?"                                              | "Is this user allowed to view this page or perform this operation?"    |

---

### **Example: Authentication vs. Authorization in a Web Application**

1. **Authentication**: 
   - A user enters their **username and password** into a login form.
   - The system verifies the credentials by checking them against stored records in the database.

   **Outcome**: The system confirms the user is who they claim to be.

2. **Authorization**:
   - After login, the user tries to access an admin dashboard.
   - The system checks if the user’s role includes "Admin" permissions.

   **Outcome**: 
   - If authorized: Access is granted to the dashboard.
   - If unauthorized: Access is denied, even though the user is authenticated.

---

### **Key Analogy**

Think of authentication as **showing an ID card** to enter a building (proving identity). Authorization is like **having a specific keycard or permissions** to access certain rooms inside the building. 

Both are essential for secure systems but serve distinct purposes.

---
---

Securing API endpoints in microservices is crucial to protect sensitive data, ensure authorized access, and maintain the integrity of the system. Below are the approaches to secure API endpoints in a microservices architecture:

---
- Table which store token (findByFieldNameAndSystemNameAndKmsRegion)
- ![image](https://github.com/user-attachments/assets/0da5cf44-b704-44b0-82b7-b56c809a0dab)
- Get token from database
- if(encryptedSecretOptional.get().getExpiryAt().minusMinutes(5).isAfter(LocalDateTime.now())) -> token is valid
- Token is stored in base64 encrypted format in database based on data_key
- get Base64.getDecoder().decode(cipherText) [Decoder RFC4648]
- pass ciphertext and datakey to AmazonHelper.decrypt() to get decrypted token
-  ```java
   private static SecretKeySpec decryptKey(final EnvelopeEncryptedMessage envelope) {
		ByteBuffer encryptedKey = ByteBuffer.wrap(envelope.getEncryptedKey());
		DecryptRequest decryptRequest = new DecryptRequest().withCiphertextBlob(encryptedKey);
		ByteBuffer plainTextKey = getAmazonKMSClient().decrypt(decryptRequest).getPlaintext();
		SecretKeySpec key = new SecretKeySpec(plainTextKey.array(), "AES");
		return key;

	}
   ```
- After getting access token, send it to partner system
- To generate token
  - get client id and secret key from AWS secret manager and tokenURL from config server and transaction id of request
  -     HttpPost httpPost = new HttpPost(postUri);
        String base64Credentials = Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes());
        httpPost.setHeader("Authorization", "Basic " + base64Credentials);
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Connection", "keep-alive");
        CloseableHttpResponse response = HTTP_CLIENT.execute(httpPost);
- get access token from response and encrypt it using Amazon KMS and generate one data_key and save to database
- If token is not expired and required to send request to partner system and refresh token with current time and extend expiry_at with expiry time given by partner system

### **1. Authentication and Authorization**

#### **Authentication**
Verify the identity of the user or service making the request:
- **OAuth 2.0**: Use standards like OAuth 2.0 and OpenID Connect for user and service authentication.
- **JWT (JSON Web Token)**: Use JWTs for stateless authentication. Tokens can be validated at each microservice without additional lookups to a central database.

#### **Authorization**
Control access to resources:
- Implement **Role-Based Access Control (RBAC)** or **Attribute-Based Access Control (ABAC)**.
- Use scopes or claims in JWT tokens to specify allowed actions for users.

**Example**:
```yaml
Authorization: Bearer <JWT-Token>
```

---

### **2. API Gateway for Centralized Security**
An **API Gateway** acts as a single entry point for all API requests, providing:
- **Request Validation**: Ensures all required parameters and headers are present.
- **Rate Limiting**: Throttles requests to prevent DDoS attacks.
- **Token Validation**: Verifies JWT or OAuth tokens.
- **IP Whitelisting/Blacklisting**: Restricts access based on IP addresses.
  
**Tools**:
- Kong
- Apigee
- AWS API Gateway
- Spring Cloud Gateway

---

### **3. Secure Communication**
Ensure all communication between services and clients is encrypted:
- Use **HTTPS** for all external API communications.
- Implement **mTLS (Mutual TLS)** for secure service-to-service communication.
- Use encryption protocols like **TLS 1.2** or higher.

---

### **4. Secure Data in Transit and at Rest**
- Encrypt sensitive data at rest using AES or similar encryption algorithms.
- Use secure protocols like HTTPS and TLS to encrypt data in transit.

---

### **5. Input Validation and Parameterized Queries**
- Validate all incoming API inputs to prevent injection attacks.
- Use parameterized queries or ORM frameworks (e.g., Hibernate) to prevent SQL injection.

---

### **6. Implement API Throttling and Rate Limiting**
Prevent abuse of APIs by implementing limits on the number of requests allowed per user or client:
- Use tools like **Bucket4j** or configurations in API Gateways to enforce rate limits.

---

### **7. Cross-Origin Resource Sharing (CORS)**
Configure CORS policies to restrict which domains can access your APIs:
- Use `Access-Control-Allow-Origin` header to specify allowed origins.

---

### **8. Logging and Monitoring**
- Log all API requests and responses for auditing.
- Use monitoring tools (e.g., ELK stack, Prometheus, Grafana) to detect anomalies and unauthorized access attempts.

---

### **9. Security Headers**
Include HTTP security headers to protect against common vulnerabilities:
- `Content-Security-Policy`: Prevent XSS attacks.
- `X-Frame-Options`: Prevent clickjacking.
- `Strict-Transport-Security`: Enforce HTTPS.

---

### **10. Secure Sensitive Information**
- Avoid exposing sensitive information like API keys or database credentials in code or API responses.
- Use **environment variables** or secrets management tools (e.g., AWS Secrets Manager, HashiCorp Vault) for storing credentials.

---

### **11. Service-to-Service Authentication**
- Use **Service Identity**: Each microservice should authenticate its identity with others using certificates, tokens, or API keys.
- Implement **Zero Trust Architecture**: Always verify requests, even within the internal network.

---

### **12. Use Security Standards and Frameworks**
- **OpenAPI/Swagger**: Define API contracts and use tools to validate security compliance.
- Implement tools like **Spring Security** for fine-grained control over access.

---

### Example Implementation: Securing APIs with Spring Boot and JWT

#### **Step 1: Add Spring Security**
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

#### **Step 2: JWT Token Authentication**
```java
@Override
protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable()
        .authorizeRequests()
        .antMatchers("/public/**").permitAll()
        .anyRequest().authenticated()
        .and()
        .addFilter(new JwtAuthenticationFilter(authenticationManager()));
}
```

#### **Step 3: Validate Tokens**
Use middleware or filters to validate JWT tokens in each request:
```java
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        // Validate token logic
        filterChain.doFilter(request, response);
    }
}
```

---

### **13. Penetration Testing**
Regularly test APIs for vulnerabilities using tools like OWASP ZAP, Burp Suite, or custom scripts.

---

By combining these techniques, API endpoints in microservices can be effectively secured against unauthorized access and attacks.

---

### **What is JWT?**

JWT (JSON Web Token) is a compact, URL-safe token format used to represent claims between two parties. It's widely used for securely transmitting information between clients and servers in the form of JSON objects, especially in authentication systems.

A JWT consists of **three parts**:
1. **Header**
2. **Payload**
3. **Signature**

These three parts are **Base64 encoded** and joined together with dots (`.`) to form a single token string:
```
<Header>.<Payload>.<Signature>
```

---

### **1. Header**
The header typically contains two pieces of information:
- **Type of the token** (JWT).
- **Signing algorithm** (e.g., HMAC SHA256, RSA).

#### Example Header:
```json
{
  "alg": "HS256",
  "typ": "JWT"
}
```

In this case:
- `"alg": "HS256"` means the algorithm used to sign the token is HMAC using SHA-256.
- `"typ": "JWT"` indicates the type of token.

#### Base64 URL Encoded Header:
After encoding this header, it becomes:
```
eyJhbGciOiAiSFMyNTYiLCAidHlwIjogIkpXVCJ9
```

---

### **2. Payload**
The payload contains **claims**, which are statements about an entity (typically the user) and additional data. Claims are divided into three types:
- **Registered claims**: These are predefined claims, such as `iss` (issuer), `exp` (expiration), `sub` (subject), `aud` (audience), etc.
- **Public claims**: These are custom claims created to share information between parties that can be publicly used (e.g., user roles).
- **Private claims**: These are custom claims created to share information between two parties (e.g., `userId`, `email`).

#### Example Payload:
```json
{
  "sub": "1234567890",
  "name": "John Doe",
  "iat": 1516239022
}
```
In this case:
- `"sub": "1234567890"` is the **subject** (the user ID).
- `"name": "John Doe"` is a custom claim indicating the user's name.
- `"iat": 1516239022` is the **issued at** time (timestamp when the token was issued).

#### Base64 URL Encoded Payload:
After encoding, this payload becomes:
```
eyJzdWIiOiAiMTIzNDU2Nzg5MCIsICJuYW1lIjogIkpvaG4gRG9lIiwgImlhdCI6IDE1MTYyMzkwMjJ9
```

---

### **3. Signature**
The signature is used to verify that the sender of the JWT is who it says it is, and to ensure that the message wasn't changed along the way. 

To create the signature:
- Take the **encoded header** and **encoded payload** and join them with a dot (`.`).
- Apply the **algorithm** specified in the header (e.g., HMAC SHA256).
- Sign the joined string with a **secret key** (in the case of symmetric algorithms like HMAC), or with a **private key** (in the case of asymmetric algorithms like RSA).

#### Example Signature (for HMAC SHA256):
For a secret key `your-256-bit-secret`, the signature is generated by applying the HMAC SHA256 algorithm to the combined string:
```
<encoded header>.<encoded payload>
```
In this case, let's assume the resulting signature is:
```
s5sJfYtiyZ8t0zoV6Jtn3P2Y1Kvpmk6m9rd0T9Tu-K8
```

---

### **Complete JWT Token Example**
After Base64 encoding the header and payload, and applying the signature, the resulting JWT token looks like this:
```
eyJhbGciOiAiSFMyNTYiLCAidHlwIjogIkpXVCJ9.eyJzdWIiOiAiMTIzNDU2Nzg5MCIsICJuYW1lIjogIkpvaG4gRG9lIiwgImlhdCI6IDE1MTYyMzkwMjJ9.s5sJfYtiyZ8t0zoV6Jtn3P2Y1Kvpmk6m9rd0T9Tu-K8
```

This token consists of three parts:
1. **Header** (encoded): `eyJhbGciOiAiSFMyNTYiLCAidHlwIjogIkpXVCJ9`
2. **Payload** (encoded): `eyJzdWIiOiAiMTIzNDU2Nzg5MCIsICJuYW1lIjogIkpvaG4gRG9lIiwgImlhdCI6IDE1MTYyMzkwMjJ9`
3. **Signature** (encoded): `s5sJfYtiyZ8t0zoV6Jtn3P2Y1Kvpmk6m9rd0T9Tu-K8`

---

### **How to Use JWT:**

1. **Authentication**:
   - A client sends credentials (e.g., username and password) to the server.
   - The server validates credentials, creates a JWT token, and sends it back to the client.
   
2. **Authorization**:
   - For subsequent requests, the client sends the JWT token in the **Authorization header** as a bearer token:
     ```
     Authorization: Bearer <JWT>
     ```
   - The server verifies the token, checks its signature, and extracts the user information from the payload to authorize access to the API.

---

### **JWT Token Structure Summary:**

1. **Header**:
   - Contains metadata about the token (type, signing algorithm).
   - Typically: `{ "alg": "HS256", "typ": "JWT" }`

2. **Payload**:
   - Contains claims (statements about the user and other data).
   - Example: `{ "sub": "1234567890", "name": "John Doe", "iat": 1516239022 }`

3. **Signature**:
   - Verifies the authenticity of the token.
   - Created using the encoded header, payload, and a secret key or private key.

---

### **Advantages of Using JWT:**

- **Stateless**: No need for server-side sessions, reducing the load on the server.
- **Scalable**: JWT tokens are self-contained, making it easy to distribute them across different services (ideal for microservices).
- **Compact**: JWTs are URL-safe and compact in size.
- **Secure**: The signature ensures that the token has not been tampered with.
  
---

### **JWT Expiration:**
To ensure security, a JWT can contain an **expiration time (`exp`)** claim, which indicates when the token is no longer valid. For example:
```json
{
  "exp": 1616161616
}
```
The `exp` claim contains a Unix timestamp, and once the current time exceeds this value, the token is considered expired and can no longer be used for authentication.

---

### **Conclusion:**

JWT is a powerful and flexible tool for handling authentication and authorization in a secure, stateless manner. Understanding the components of a JWT (header, payload, signature) is essential for leveraging its full potential in building scalable applications.

---

### **What is OAuth 2.0?**

OAuth 2.0 is an **open authorization framework** that allows third-party applications to obtain limited access to a user’s resources on a web server without exposing the user’s credentials. It is widely used for granting permissions in a secure and standardized way.

---

### **Structure of OAuth 2.0**

OAuth 2.0 operates with the following key **roles**:

1. **Resource Owner**:
   - The entity that owns the resources to be accessed (e.g., a user).
   
2. **Client**:
   - The third-party application requesting access to the resources (e.g., a mobile app, web app).

3. **Resource Server**:
   - The server hosting the user's resources (e.g., Google APIs, GitHub APIs).

4. **Authorization Server**:
   - The server responsible for authenticating the user and issuing tokens (access token and optionally a refresh token).

5. **Access Token**:
   - A token issued by the authorization server that grants the client access to the resource server.

---

### **Key Components of OAuth 2.0 Flow**

1. **Authorization Grant**:
   - A mechanism by which the client obtains the access token. There are several types:
     - **Authorization Code** (most secure, used for server-side apps).
     - **Implicit Grant** (used for client-side apps, less secure).
     - **Resource Owner Password Credentials** (legacy, used when other grants are impractical).
     - **Client Credentials** (used for machine-to-machine communication).

2. **Access Token**:
   - A credential used by the client to access protected resources on behalf of the resource owner.

3. **Refresh Token**:
   - An optional token used to obtain a new access token when the current one expires.

---

### **Working of OAuth 2.0**

#### **Step-by-Step Flow (Authorization Code Grant)**

1. **Client Requests Authorization**:
   - The client redirects the user to the authorization server with an authorization request that includes:
     - **client_id**: Unique identifier for the client.
     - **redirect_uri**: Where the user should be redirected after approval.
     - **response_type**: Specifies the flow (e.g., `code` for authorization code grant).
     - **scope**: Defines the level of access requested.

   Example URL:
   ```
   https://authorization-server.com/auth?
   response_type=code&
   client_id=12345&
   redirect_uri=https://example-app.com/callback&
   scope=read
   ```

2. **User Grants Authorization**:
   - The resource owner (user) logs in and grants permissions to the client.

3. **Authorization Server Issues Authorization Code**:
   - The server redirects the user to the client’s `redirect_uri` with the authorization code:
     ```
     https://example-app.com/callback?code=abc123
     ```

4. **Client Requests Access Token**:
   - The client sends a POST request to the authorization server with:
     - The authorization code.
     - Client ID and secret.
     - Redirect URI.
     - Grant type (`authorization_code`).

   Example:
   ```
   POST /token
   Host: authorization-server.com
   Content-Type: application/x-www-form-urlencoded

   grant_type=authorization_code&
   code=abc123&
   redirect_uri=https://example-app.com/callback&
   client_id=12345&
   client_secret=secret
   ```

5. **Authorization Server Issues Access Token**:
   - The server validates the authorization code and responds with an access token (and optionally a refresh token):
     ```json
     {
       "access_token": "xyz789",
       "token_type": "Bearer",
       "expires_in": 3600,
       "refresh_token": "def456"
     }
     ```

6. **Client Accesses Protected Resources**:
   - The client uses the access token to make requests to the resource server:
     ```
     GET /protected-resource
     Host: resource-server.com
     Authorization: Bearer xyz789
     ```

7. **Resource Server Validates Token**:
   - The resource server checks the token's validity and serves the requested resource if valid.

---

### **Diagram: OAuth 2.0 Flow**
```
User → [Authorization Request] → Authorization Server
User ← [Login & Grant Permissions] ← Authorization Server
User → [Redirect with Authorization Code] → Client
Client → [Request Access Token] → Authorization Server
Client ← [Access Token] ← Authorization Server
Client → [Access Resource] → Resource Server
```

---

### **Grant Types in OAuth 2.0**

1. **Authorization Code Grant**:
   - Best for server-side apps.
   - Uses authorization code for obtaining the token.
   - Most secure as it hides tokens from the user.

2. **Implicit Grant**:
   - Best for client-side apps (e.g., Single Page Applications).
   - Issues access tokens directly to the browser.
   - Less secure due to token exposure.

3. **Resource Owner Password Credentials Grant**:
   - Suitable when the client is trusted by the resource owner (e.g., legacy apps).
   - The client sends the user's username and password to get an access token.

4. **Client Credentials Grant**:
   - Suitable for machine-to-machine communication.
   - The client uses its own credentials to access resources.

---

### **Advantages of OAuth 2.0**

- **Granular Access Control**:
  - Supports scopes to restrict the level of access.
- **Secure Delegation**:
  - Users don’t have to share credentials with third-party apps.
- **Token Expiry and Refresh**:
  - Tokens expire and can be renewed securely.
- **Scalability**:
  - Well-suited for microservices and distributed systems.

---

### **Example Use Case**
#### Accessing Google APIs:
1. A user logs into an application using Google.
2. The app requests permission to access Google Drive files.
3. The user grants permission.
4. Google issues an access token to the app.
5. The app uses the token to interact with Google Drive without knowing the user's credentials.

---

### **Conclusion**
OAuth 2.0 is a powerful and flexible framework for secure authorization. By separating roles and relying on tokens for access, it enables third-party applications to interact with resource servers without compromising user credentials. It is the backbone of modern API-driven systems, especially in microservices, cloud-based platforms, and distributed systems.
