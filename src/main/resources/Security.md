---
---

### **Difference Between Authentication and Authorization**

| **Aspect**             | **Authentication**                                                     | **Authorization**                                                   |
|-------------------------|------------------------------------------------------------------------|----------------------------------------------------------------------|
| **Definition**          | Verifying the identity of a user or entity.                          | Granting permission to access specific resources or perform actions. |
| **Purpose**             | Confirms "Who you are."                                               | Confirms "What you are allowed to do."                              |
| **Focus**               | Focuses on verifying credentials (e.g., username and password).       | Focuses on validating access rights for a user.                     |
| **Process**             | Happens before authorization.                                         | Happens after authentication.                                       |
| **HTTP Status Codes**   | `401 Unauthorized` if authentication fails.                          | `403 Forbidden` if authorization fails.                             |
| **Examples**            | - Login page to verify username and password.                        | - Accessing an admin-only dashboard after logging in.               |

---

### **HTTP Status Codes for Authentication and Authorization**

1. **Authentication Failure**:
   - **HTTP Status Code**: `401 Unauthorized` (despite the misleading name).
   - **Meaning**: The client must authenticate itself to get the requested response.
   - **Example**: 
     - When a user provides incorrect credentials (e.g., wrong username/password).
     - If a client omits a required `Authorization` header.

   ```http
   HTTP/1.1 401 Unauthorized
   WWW-Authenticate: Basic realm="Access to the secure site"
   Content-Type: application/json

   {
       "error": "Authentication required. Please provide valid credentials."
   }
   ```

2. **Authorization Failure**:
   - **HTTP Status Code**: `403 Forbidden`.
   - **Meaning**: The server understands the request but refuses to fulfill it due to insufficient permissions.
   - **Example**:
     - A user tries to access an admin-only resource without admin privileges.
     - A token is valid but lacks the required permissions for the requested resource.

   ```http
   HTTP/1.1 403 Forbidden
   Content-Type: application/json

   {
       "error": "You do not have permission to access this resource."
   }
   ```

---

### **Example: Authentication and Authorization Workflow**

1. **Scenario**:
   - A user tries to access a restricted resource `/admin-dashboard`.

2. **Step 1: Authentication**:
   - **Client Request**: Sends a request with no credentials or incorrect credentials.
   - **Server Response**: Returns `401 Unauthorized`.
     ```http
     HTTP/1.1 401 Unauthorized
     WWW-Authenticate: Bearer realm="Access requires authentication"
     ```

3. **Step 2: Authorization**:
   - **Client Request**: Sends valid credentials but lacks sufficient permissions.
   - **Server Response**: Returns `403 Forbidden`.
     ```http
     HTTP/1.1 403 Forbidden
     Content-Type: application/json

     {
         "error": "You are authenticated but not authorized to access this resource."
     }
     ```

4. **Step 3: Success**:
   - **Client Request**: Sends valid credentials with the required permissions.
   - **Server Response**: Returns `200 OK` with the resource.
     ```http
     HTTP/1.1 200 OK
     Content-Type: application/json

     {
         "message": "Welcome to the admin dashboard!"
     }
     ```

---

### **Key Points to Remember**
- **401 Unauthorized**: Indicates authentication has failed or is required.
- **403 Forbidden**: Indicates authentication succeeded, but the user does not have the necessary permissions.
- Properly handling these codes improves API security and usability by providing clear feedback to the client.
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
- API Proxies: Route, transform and secure your traffic through the API Gateway
- API Products: Control how developers consume your API 
- Shared Flow:
  - SF-Internet Access: Flow to include ACL policies for internet Access. It will have some policies which applies
    - IP Rules: Whitelist IPs for internet Access under match rule
    - Denied Access: If IPs is not listed then it will throw 403 Forbidden (Denied Access)
  - SF-central-token-validation: Validate token with uberorg token proxy. It will have some policies which applies.
    ```xml
              <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
              <SharedFlow name="default">
                  <Step>
                      <Name>Extract-Access-Token</Name>
                  </Step>
                  <Step>
                      <Name>SC-Validate-Token</Name>
                      <Condition>oauthtoken!=null</Condition>
                  </Step>
                  <Step>
                      <Name>RF-Invalid-Access-Token</Name>
                      <Condition>oauthtoken==null OR tokenValidateResponse.status.code!=200</Condition>
                  </Step>
                  <Step>
                      <Name>Extract-Token-Validation-Response-Values</Name>
                  </Step>
                  <Step>
                      <Name>Quota-Gekko</Name>
                      <Condition>quota_value!=null</Condition>
                  </Step>
                  <Step>
                      <Name>Spike-Arrest-Gekko</Name>
                  </Step>
                  </SharedFlow>

  - Extract: Access Token
    ```xml 
            <Source>request</Source>
            <Header name="Authorization">
            <Pattern ignoreCase="false">Bearer {oauthtoken}</Pattern>
            </Header>
  
  - Validate Token
    ```xml
          <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
            <ServiceCallout async="false" continueOnError="true" enabled="true" name="SC-Validate-Token">
                <DisplayName>SC: Validate Token</DisplayName>
                <Properties/>
                <Request clearPayload="true" variable="tokenValidateRequest">
                    <Set>
                        <Headers>
                            <Header name="X-HP-Organization">{organization.name}</Header>
                            <Header name="X-HP-Environment">{environment.name}</Header>
                            <Header name="Content-Type">application/json</Header>
                            <Header name="X-BU_apikey">TgO95XcuG5jGcTuOpaHyuDUVwrSBHLmd</Header>
                        </Headers>
                        <Payload contentType="application/json" variablePrefix="%" variableSuffix="#">{"accessToken":"%oauthtoken#","resourcePath":"%request.path#"}</Payload>
                        <Verb>POST</Verb>
                    </Set>
                </Request>
                <Response>tokenValidateResponse</Response>
                <HTTPTargetConnection>
                    <Properties/>
                    <LoadBalancer>
                        <Server name="coreOauth"/>
                    </LoadBalancer>
                    <Path>/hpid/oauth/v1/validate</Path>
                </HTTPTargetConnection>
            </ServiceCallout> 
    
  - spike arrest
    ```xml
        <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
    <SpikeArrest async="false" continueOnError="false" enabled="true" name="Spike-Arrest-Gekko">
        <DisplayName>Spike Arrest Gekko</DisplayName>
        <Rate>1000ps</Rate>
        <UseEffectiveCount>true</UseEffectiveCount>
    </SpikeArrest>
  
  - 
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

---

Authorization in microservices ensures that only authenticated users or services can access specific resources. There are multiple ways to handle authorization in microservices, commonly using **OAuth 2.0, JWT, API Gateway, Role-Based Access Control (RBAC), and Attribute-Based Access Control (ABAC)**. Below are the main strategies:

---

### 1. **Token-Based Authorization (OAuth 2.0 & JWT)**
A centralized **Identity Provider (IdP)** issues a token that services use to verify authorization.

#### **Steps:**
1. **User authentication:** A user logs in through an **Authorization Server** (e.g., Keycloak, Okta, Auth0).
2. **Token issuance:** A token (e.g., JWT) is issued upon successful authentication.
3. **Token propagation:** The token is sent in API requests (`Authorization: Bearer <token>`).
4. **Microservice validation:** Each microservice validates the token using a public key (for JWT) or by calling an authorization server.
5. **Access granted/denied:** Based on claims in the token (e.g., roles, permissions), access is granted or denied.

#### **Example:**
A user wants to access an order service:
   - The user logs in and receives a **JWT token**.
   - The frontend sends a request with the **JWT token** in the header.
   - The order service validates the token using the authentication server’s public key.
   - If valid, it checks the user’s roles/permissions before granting access.

---

### 2. **API Gateway-Based Authorization**
The **API Gateway** (e.g., Kong, Zuul, API Gateway in AWS) acts as an entry point, handling authorization before forwarding requests.

#### **Steps:**
1. **Token validation:** The API Gateway verifies the token.
2. **Request forwarding:** If valid, it forwards the request to the respective microservice.
3. **Fine-grained authorization:** The microservice can apply additional role-based or attribute-based checks.

#### **Advantages:**
- Reduces duplication of authentication logic across microservices.
- Centralized security enforcement.
- Rate-limiting, logging, and monitoring can be applied.

---

### 3. **Role-Based Access Control (RBAC)**
RBAC ensures access control based on predefined roles (e.g., **Admin, User, Manager**).

#### **How it Works?**
- Each user has roles.
- Each role has a set of permissions.
- Microservices enforce authorization based on the user’s role.

**Example:**
- **Admin** can create, update, and delete users.
- **User** can only view their own data.

JWT tokens often store roles in claims, like:
```json
{
  "sub": "user123",
  "roles": ["ROLE_USER"]
}
```
Microservices check the role before granting access.

---

### 4. **Attribute-Based Access Control (ABAC)**
ABAC extends RBAC by considering **attributes** like user location, department, time of access, etc.

#### **Example:**
- A **manager** can access financial reports **only from office hours**.
- A **doctor** can access patient records **only for assigned patients**.

Policies are defined using **Policy Enforcement Points (PEP)** and **Policy Decision Points (PDP)**.

---

### 5. **Service-to-Service Authorization (mTLS & OAuth 2.0)**
Microservices may need to communicate securely. Service-to-service authorization can be handled using:

1. **Mutual TLS (mTLS):** Services authenticate each other using certificates.
2. **OAuth 2.0 Client Credentials Flow:** One microservice authenticates with another using OAuth tokens.

**Example:**
- Order service calls the Payment service using an OAuth 2.0 client token.
- Payment service verifies the token before processing the request.

---

### **Best Practices for Authorization in Microservices**
✅ **Centralized Authentication** – Use an identity provider (e.g., Keycloak, Okta).  
✅ **Use JWT for Stateless Authorization** – Avoid session-based authentication.  
✅ **Limit API Exposure** – Use API Gateway for centralized security policies.  
✅ **Adopt RBAC or ABAC** – Define clear roles and permissions.  
✅ **Service-to-Service Security** – Use OAuth 2.0, mTLS, or service mesh (Istio).  
✅ **Least Privilege Access** – Grant only necessary permissions.  

---

Would you like code examples for implementing any of these approaches? 🚀


---

### 1. **JWT (JSON Web Token)**
- JWT is a **token format** used for securely transmitting information between parties as a JSON object.
- It consists of three parts: **Header, Payload, and Signature**.
- JWTs can be used as **access tokens** or **refresh tokens**.
- They are **self-contained**, meaning they can store user claims and do not require database lookups.

🔹 Example of a JWT:
```
eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjEyMywiZXhwIjoxNjcxMDA5NjAwfQ.5d41402abc4b2a76b9719d911017c592
```

### 2. **Access Token**
- An **access token** is a credential used to **authorize and authenticate** a user in an application.
- It is usually **short-lived** (e.g., 15-30 minutes).
- Sent in the `Authorization` header (e.g., `Bearer <token>`) for API requests.
- It can be a **JWT** or another format like opaque tokens.

🔹 Example:
```
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

### 3. **Refresh Token**
- A **refresh token** is used to obtain a new **access token** without requiring the user to log in again.
- It is **long-lived** (e.g., days or weeks).
- **Not sent with each request** (only used when the access token expires).
- Typically stored securely (e.g., HTTP-only cookies or encrypted storage).

🔹 Flow:
1. User logs in → receives an **access token** + **refresh token**.
2. Access token expires → client sends refresh token to the authentication server.
3. Server validates the refresh token → issues a **new access token**.

### **Comparison Table**

| Feature         | JWT              | Access Token         | Refresh Token       |
|---------------|----------------|--------------------|------------------|
| Purpose       | Format for tokens | Authenticates API requests | Renews access tokens |
| Lifetime     | Depends on usage | Short-lived (minutes) | Long-lived (days/weeks) |
| Storage      | Depends on token type | Usually in-memory/local storage | Secure storage (e.g., HTTP-only cookie) |
| Sent with requests? | Depends | Yes (in `Authorization` header) | No (only when renewing) |
| Used for Authentication? | Sometimes | Yes | No |
| Used for Authorization? | Yes | Yes | No |

### **Conclusion**
- **JWT** is just a format for tokens.
- **Access Token** is a credential for accessing APIs.
- **Refresh Token** helps renew access tokens without re-authentication.

---

JWT (JSON Web Token), access tokens, and refresh tokens are all used in authentication and authorization systems, but they serve different purposes and have distinct characteristics. Here's a breakdown of each:

### 1. **JWT (JSON Web Token)**:
- **Definition**: A JWT is a compact, URL-safe token format used to represent claims between two parties. It is typically used for securely transmitting information as a JSON object.
- **Structure**: A JWT consists of three parts: a header, a payload, and a signature. The header specifies the token type and the signing algorithm. The payload contains the claims (e.g., user ID, roles, expiration time). The signature ensures the token's integrity.
- **Usage**: JWTs can be used as access tokens, ID tokens, or other types of tokens. They are often used in stateless authentication mechanisms because they can carry all the necessary information within the token itself.

### 2. **Access Token**:
- **Definition**: An access token is a credential used to access protected resources on behalf of a user. It is typically short-lived and is used to authenticate API requests.
- **Usage**: When a user logs in, the authentication server issues an access token. The client (e.g., a web or mobile app) includes this token in the Authorization header of HTTP requests to access protected resources.
- **Lifetime**: Access tokens usually have a short expiration time (e.g., minutes or hours) to minimize the risk if the token is compromised.

### 3. **Refresh Token**:
- **Definition**: A refresh token is a credential used to obtain a new access token when the current access token expires. It is typically long-lived and stored securely on the client side.
- **Usage**: When an access token expires, the client can send the refresh token to the authentication server to obtain a new access token without requiring the user to log in again.
- **Lifetime**: Refresh tokens have a longer expiration time (e.g., days, weeks, or even months) compared to access tokens. They are often revoked or rotated for security reasons.

### Key Differences:
- **Purpose**:
    - **JWT**: A format for representing claims, which can be used as an access token, ID token, etc.
    - **Access Token**: Used to access protected resources.
    - **Refresh Token**: Used to obtain a new access token when the current one expires.

- **Lifetime**:
    - **JWT**: Can have varying lifetimes depending on its use case.
    - **Access Token**: Short-lived (minutes to hours).
    - **Refresh Token**: Long-lived (days to months).

- **Security**:
    - **JWT**: Can be signed and optionally encrypted to ensure integrity and confidentiality.
    - **Access Token**: Should be kept secure, but its short lifetime reduces the risk if compromised.
    - **Refresh Token**: Must be stored securely due to its long lifetime and the potential for misuse.

### Example Flow:
1. **User Logs In**: The authentication server validates the user's credentials and issues a JWT as an access token and a refresh token.
2. **Accessing Resources**: The client includes the access token in API requests to access protected resources.
3. **Token Expiry**: When the access token expires, the client uses the refresh token to request a new access token from the authentication server.
4. **New Access Token**: The authentication server validates the refresh token and issues a new access token (and optionally a new refresh token).

This flow ensures that the user remains authenticated without needing to log in frequently, while maintaining security by limiting the lifetime of access tokens.


---

If a **refresh token** expires, the client (e.g., a web or mobile app) can no longer use it to obtain a new **access token**. This means the user will need to re-authenticate (e.g., log in again) to get a new set of tokens (both a new access token and a new refresh token). Here's what happens in detail:

---

### **1. Refresh Token Expiration**
- Refresh tokens are typically long-lived but have an expiration time for security reasons.
- When the refresh token expires, it becomes invalid, and the authentication server will reject any requests to use it.

---

### **2. Client Attempts to Use an Expired Refresh Token**
- When the access token expires, the client will attempt to use the refresh token to get a new access token.
- If the refresh token has expired, the authentication server will respond with an error (e.g., `invalid_grant` or `token_expired`).

---

### **3. User Re-authentication**
- Since the refresh token is no longer valid, the client cannot automatically obtain a new access token.
- The user will need to log in again (e.g., provide their credentials or go through an authentication flow like OAuth or OpenID Connect).
- After successful re-authentication, the authentication server will issue a **new access token** and a **new refresh token**.

---

### **4. Security Implications**
- Refresh tokens expire to enhance security. If a refresh token were to be compromised, its limited lifetime reduces the window of opportunity for an attacker to misuse it.
- Expiration also ensures that users periodically re-authenticate, which is a good security practice.

---

### **5. Handling Refresh Token Expiry in Applications**
To provide a smooth user experience, applications should handle refresh token expiration gracefully:
- **Detect Expiry**: The application should recognize when the refresh token has expired (e.g., by checking the server's error response).
- **Prompt Re-authentication**: The application should prompt the user to log in again when the refresh token expires.
- **Store Tokens Securely**: Refresh tokens should be stored securely (e.g., in HTTP-only, secure cookies or encrypted storage) to prevent unauthorized access.

---

### **Example Scenario**
1. **Access Token Expires**: The client tries to access a protected resource, but the access token has expired.
2. **Refresh Token Request**: The client sends the refresh token to the authentication server to get a new access token.
3. **Refresh Token Expired**: The server responds with an error indicating the refresh token has expired.
4. **Re-authentication**: The client prompts the user to log in again.
5. **New Tokens Issued**: After successful login, the server issues a new access token and a new refresh token.

---

### **Best Practices**
- **Short-Lived Access Tokens**: Use short-lived access tokens (e.g., 15 minutes to 1 hour) to minimize risk.
- **Long-Lived Refresh Tokens**: Use longer-lived refresh tokens (e.g., days or weeks) but ensure they expire periodically.
- **Token Rotation**: Implement refresh token rotation, where a new refresh token is issued each time the refresh token is used. This helps detect and mitigate token theft.
- **Revocation**: Allow refresh tokens to be revoked (e.g., if the user logs out or if suspicious activity is detected).

By following these practices, you can balance security and user experience while managing token expiration effectively.

---

If an **access token** is compromised, it can pose a significant security risk because the attacker can use the token to access protected resources on behalf of the user. However, the impact of this compromise can be mitigated by following security best practices. Here's what happens and how to handle it:

---

### **1. Consequences of a Compromised Access Token**
- **Unauthorized Access**: An attacker can use the stolen access token to make requests to protected resources (e.g., APIs) as if they were the legitimate user.
- **Data Breach**: Sensitive data may be exposed if the attacker accesses resources they shouldn't.
- **Abuse of Permissions**: The attacker can perform actions allowed by the permissions associated with the token (e.g., modifying data, deleting resources).

---

### **2. Mitigation Strategies**
To minimize the damage caused by a compromised access token, the following strategies are commonly used:

#### **a. Short Expiration Time**
- Access tokens should have a short lifespan (e.g., 15 minutes to 1 hour). This limits the window of opportunity for an attacker to misuse the token.
- Even if the token is compromised, it will become invalid after a short period.

#### **b. Token Revocation**
- The authentication server can maintain a list of revoked tokens (e.g., a **token revocation list** or use a database to track invalidated tokens).
- When a token is compromised, it can be explicitly revoked, and the server will reject any requests using that token.

#### **c. Scope Limitation**
- Access tokens should be issued with the minimum necessary permissions (e.g., using OAuth2 scopes). This limits what an attacker can do even if they gain access to the token.

#### **d. Secure Storage and Transmission**
- Access tokens should be transmitted securely (e.g., over HTTPS) and stored securely on the client side (e.g., in memory or secure storage mechanisms like HTTP-only cookies).
- Avoid storing access tokens in insecure locations (e.g., local storage in web browsers).

#### **e. Monitoring and Detection**
- Monitor for unusual activity (e.g., unexpected API requests or access from unfamiliar locations).
- Implement mechanisms to detect and respond to suspicious behavior, such as rate limiting or blocking requests from suspicious IPs.

#### **f. Refresh Token Protection**
- If the access token is compromised, ensure the refresh token is not also compromised. Refresh tokens should be stored more securely (e.g., in encrypted storage or HTTP-only cookies).
- Implement refresh token rotation, where a new refresh token is issued each time it is used, and the old one is invalidated.

---

### **3. What to Do If an Access Token Is Compromised**
If you suspect or detect that an access token has been compromised:
1. **Revoke the Token**: Immediately revoke the compromised access token on the server side.
2. **Invalidate the Refresh Token**: If the refresh token is also compromised, revoke it to prevent the attacker from obtaining new access tokens.
3. **Force Re-authentication**: Require the user to log in again to obtain a new set of tokens.
4. **Investigate the Breach**: Determine how the token was compromised and take steps to prevent future incidents (e.g., improve token storage, enforce HTTPS, or implement additional security measures).

---

### **4. Example Scenario**
1. **Token Compromise**: An attacker steals an access token (e.g., through a man-in-the-middle attack or insecure storage).
2. **Unauthorized Access**: The attacker uses the token to access protected resources.
3. **Detection**: The system detects unusual activity (e.g., requests from an unfamiliar IP address).
4. **Revocation**: The compromised token is revoked on the server side.
5. **Re-authentication**: The user is prompted to log in again, and a new access token and refresh token are issued.
6. **Prevention**: Steps are taken to secure token storage and transmission to prevent future compromises.

---

### **5. Best Practices to Prevent Token Compromise**
- **Use HTTPS**: Always transmit tokens over secure channels (HTTPS).
- **Short-Lived Tokens**: Use short-lived access tokens to limit the damage if they are compromised.
- **Secure Storage**: Store tokens securely on the client side (e.g., HTTP-only cookies for web apps, encrypted storage for mobile apps).
- **Token Binding**: Bind tokens to specific client attributes (e.g., IP address or device fingerprint) to make them harder to misuse.
- **Regular Audits**: Regularly audit your authentication and authorization mechanisms for vulnerabilities.

By following these practices, you can reduce the risk of access token compromise and minimize the impact if a token is stolen.

Creating a **JWT (JSON Web Token)** involves generating a token that consists of three parts: a **header**, a **payload**, and a **signature**. The token is typically used for authentication and authorization purposes. Below is a step-by-step guide on how to create a JWT token, along with examples in different programming languages.

---

### **1. Structure of a JWT**
A JWT is a string with three parts separated by dots (`.`):
```
header.payload.signature
```

#### **a. Header**
- Contains metadata about the token, such as the signing algorithm (e.g., `HS256`, `RS256`) and the token type (`JWT`).
- Example:
  ```json
  {
    "alg": "HS256",
    "typ": "JWT"
  }
  ```

#### **b. Payload**
- Contains the claims (e.g., user ID, roles, expiration time) or any other data you want to include.
- Example:
  ```json
  {
    "sub": "1234567890",
    "name": "John Doe",
    "iat": 1516239022,
    "exp": 1516242622
  }
  ```

#### **c. Signature**
- Created by signing the encoded header and payload with a secret key (for HMAC) or a private key (for RSA).
- Ensures the token's integrity and authenticity.

---

### **2. Steps to Create a JWT**
1. **Encode the Header and Payload**:
    - Convert the header and payload to JSON strings.
    - Encode them using Base64Url encoding.

2. **Create the Signature**:
    - Combine the encoded header and payload with a `.` separator.
    - Sign the combined string using the specified algorithm (e.g., HMAC, RSA).

3. **Combine All Parts**:
    - Concatenate the encoded header, payload, and signature with `.` separators.



---

### **5. Example in Java**
Using the `java-jwt` library:
```xml
<dependency>
    <groupId>com.auth0</groupId>
    <artifactId>java-jwt</artifactId>
    <version>4.4.0</version>
</dependency>
```

```java
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.util.Date;

public class JWTExample {
    public static void main(String[] args) {
        // Secret key for signing the token
        String SECRET_KEY = "your-secret-key";

        // Payload (claims)
        String token = JWT.create()
            .withSubject("1234567890")  // Subject (user ID)
            .withClaim("name", "John Doe")  // Custom claim
            .withIssuedAt(new Date())  // Issued at (current time)
            .withExpiresAt(new Date(System.currentTimeMillis() + 30 * 60 * 1000))  // Expiration time (30 minutes)
            .sign(Algorithm.HMAC256(SECRET_KEY));  // Sign with HMAC SHA-256

        System.out.println("JWT Token: " + token);
    }
}
```


---

### **8. Key Points**
- **Secret Key**: Use a strong secret key for signing the token (for HMAC) or a private key (for RSA).
- **Expiration**: Always set an expiration time (`exp`) for the token to limit its validity.
- **Secure Storage**: Store the token securely on the client side (e.g., in HTTP-only cookies or secure storage).

By following these steps and examples, you can create JWT tokens in various programming languages.

---

The `OncePerRequestFilter` is a **base class for filters** in Spring Framework that ensures the filter is executed **only once per request**. It is part of the `org.springframework.web.filter` package and is commonly used in Spring applications to implement custom filters for HTTP requests.

---

### Key Features of `OncePerRequestFilter`
1. **Guarantees Single Execution**:
   - The filter ensures that its logic is executed only once per request, even if the request is forwarded or included in another request (e.g., via `RequestDispatcher`).

2. **Convenient Base Class**:
   - It provides a simple way to implement custom filters by overriding the `doFilterInternal` method, which contains the actual filtering logic.

3. **Integration with Spring's Filter Chain**:
   - It integrates seamlessly with Spring's filter chain and works well with other Spring features like dependency injection.

4. **Thread Safety**:
   - The filter is designed to be thread-safe, making it suitable for use in multi-threaded environments like web applications.

---

### How It Works
- The `OncePerRequestFilter` class extends `GenericFilterBean` and implements `Filter`.
- It overrides the `doFilter` method to ensure the filter logic is executed only once per request.
- Developers override the `doFilterInternal` method to implement custom filtering logic.

---

### Example Usage
A common use case for `OncePerRequestFilter` is implementing a **JWT authentication filter** that validates JWT tokens in incoming requests.

```java
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // Extract JWT token from the request (e.g., from the Authorization header)
        String token = extractToken(request);

        if (token != null && validateToken(token)) {
            // If the token is valid, set the authentication in the SecurityContext
            setAuthenticationInContext(token);
        }

        // Continue the filter chain
        filterChain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {
        // Logic to extract the token (e.g., from the Authorization header)
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7); // Remove "Bearer " prefix
        }
        return null;
    }

    private boolean validateToken(String token) {
        // Logic to validate the token (e.g., using a JWT library)
        return true; // Placeholder
    }

    private void setAuthenticationInContext(String token) {
        // Logic to set the authentication in the SecurityContext
    }
}
```

---

### Key Methods in `OncePerRequestFilter`
1. **`doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain)`**:
   - This is the method you override to implement custom filtering logic.
   - It is guaranteed to be executed only once per request.

2. **`shouldNotFilter(HttpServletRequest)`**:
   - You can override this method to specify conditions under which the filter should **not** be applied.
   - For example, you might skip filtering for certain endpoints.

```java
@Override
protected boolean shouldNotFilter(HttpServletRequest request) {
    // Skip filtering for public endpoints
    return request.getServletPath().startsWith("/public/");
}
```

---

### When to Use `OncePerRequestFilter`
- **Authentication Filters**: For validating tokens (e.g., JWT, OAuth2).
- **Logging Filters**: For logging request/response details.
- **Header Manipulation**: For adding or modifying HTTP headers.
- **Request/Response Wrapping**: For wrapping requests or responses (e.g., to read the request body multiple times).

---

### Comparison with `GenericFilterBean`
- `OncePerRequestFilter` extends `GenericFilterBean`, which provides additional features like dependency injection and configuration via Spring's `Environment`.
- The key difference is that `OncePerRequestFilter` ensures single execution per request, while `GenericFilterBean` does not.

---

### Summary
- `OncePerRequestFilter` is a convenient base class for implementing custom filters in Spring.
- It ensures the filter logic is executed only once per request.
- Commonly used for authentication, logging, and request/response manipulation.
- Override `doFilterInternal` to implement custom logic and `shouldNotFilter` to skip filtering for specific requests.


---

The `FilterRegistrationBean` is a **Spring Boot utility class** used to **register and configure custom filters** in a Spring Boot application. It provides a programmatic way to define how a filter should be registered with the servlet container, including its order, URL patterns, and initialization parameters.

---

### Key Uses of `FilterRegistrationBean`
1. **Register Custom Filters**:
   - It allows you to register custom filters (e.g., `OncePerRequestFilter`, `Filter`, or any custom implementation) with the servlet container.

2. **Control Filter Order**:
   - You can specify the order in which the filter is applied relative to other filters using the `setOrder` method.

3. **Specify URL Patterns**:
   - You can define which URLs the filter should apply to using the `addUrlPatterns` or `setUrlPatterns` methods.

4. **Add Initialization Parameters**:
   - You can pass initialization parameters to the filter using the `addInitParameter` method.

5. **Enable/Disable Filters**:
   - You can enable or disable a filter dynamically by setting the `enabled` property.

---

### How to Use `FilterRegistrationBean`
To use `FilterRegistrationBean`, you typically define it as a `@Bean` in a configuration class. Here’s an example:

```java
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<MyCustomFilter> myCustomFilterRegistration() {
        FilterRegistrationBean<MyCustomFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new MyCustomFilter()); // Register the custom filter
        registrationBean.addUrlPatterns("/secure/*"); // Apply to specific URL patterns
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE); // Set filter order
        registrationBean.addInitParameter("paramName", "paramValue"); // Add init parameters
        return registrationBean;
    }
}
```

---

### Example: Registering a Custom Filter
Suppose you have a custom filter `MyCustomFilter` that logs request details. You can register it using `FilterRegistrationBean` as follows:

```java
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class MyCustomFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization logic (optional)
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        System.out.println("Request URL: " + httpRequest.getRequestURL());
        chain.doFilter(request, response); // Continue the filter chain
    }

    @Override
    public void destroy() {
        // Cleanup logic (optional)
    }
}
```

Now, register this filter using `FilterRegistrationBean`:

```java
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<MyCustomFilter> myCustomFilterRegistration() {
        FilterRegistrationBean<MyCustomFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new MyCustomFilter());
        registrationBean.addUrlPatterns("/*"); // Apply to all URLs
        registrationBean.setOrder(1); // Set filter order
        return registrationBean;
    }
}
```

---

### Key Methods in `FilterRegistrationBean`
1. **`setFilter(Filter filter)`**:
   - Sets the custom filter to be registered.

2. **`addUrlPatterns(String... urlPatterns)`**:
   - Specifies the URL patterns to which the filter should apply.

3. **`setOrder(int order)`**:
   - Sets the order of the filter in the filter chain. Lower values have higher precedence.

4. **`addInitParameter(String name, String value)`**:
   - Adds initialization parameters to the filter.

5. **`setEnabled(boolean enabled)`**:
   - Enables or disables the filter.

6. **`setName(String name)`**:
   - Sets a name for the filter (useful for debugging and logging).

---

### Example: Registering a Spring Security Filter
If you’re using Spring Security and want to register a custom filter (e.g., a JWT filter), you can do so as follows:

```java
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityFilterConfig {

    @Bean
    public FilterRegistrationBean<JwtAuthenticationFilter> jwtFilterRegistration(JwtAuthenticationFilter jwtFilter) {
        FilterRegistrationBean<JwtAuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(jwtFilter);
        registrationBean.addUrlPatterns("/api/*"); // Apply to API endpoints
        registrationBean.setOrder(2); // Set filter order
        return registrationBean;
    }
}
```

---

### When to Use `FilterRegistrationBean`
- **Custom Filters**: When you need to register a custom filter that is not part of Spring Security or other auto-configured filters.
- **Fine-Grained Control**: When you need to control the order, URL patterns, or initialization parameters of a filter.
- **Dynamic Configuration**: When you want to enable or disable filters dynamically based on conditions.

---

### Summary
- `FilterRegistrationBean` is used to register and configure custom filters in a Spring Boot application.
- It provides control over filter order, URL patterns, initialization parameters, and more.
- Commonly used for custom logging, authentication, or request/response manipulation filters.
- Define it as a `@Bean` in a configuration class to register your filters.

---

Encrypting data at rest is a critical aspect of data security, ensuring that sensitive information remains protected even when stored on disk or in a database. Various encryption algorithms are used for this purpose, each with its own strengths and use cases. Below is a list of commonly used encryption algorithms for data at rest:

---

### **1. Symmetric Encryption Algorithms**
Symmetric encryption uses the same key for both encryption and decryption. It is fast and efficient, making it suitable for encrypting large amounts of data.

#### **A. Advanced Encryption Standard (AES)**
- **Key Sizes**: 128-bit, 192-bit, 256-bit.
- **Description**: AES is the most widely used symmetric encryption algorithm. It is secure, efficient, and approved by the U.S. government for protecting classified information.
- **Use Cases**: File encryption, database encryption, disk encryption (e.g., BitLocker, FileVault).

#### **B. Triple DES (3DES)**
- **Key Sizes**: 168-bit (effectively 112-bit due to vulnerabilities).
- **Description**: 3DES applies the DES algorithm three times to each data block, providing enhanced security over the original DES.
- **Use Cases**: Legacy systems, financial transactions (being phased out in favor of AES).

#### **C. Blowfish**
- **Key Sizes**: 32-bit to 448-bit.
- **Description**: Blowfish is a fast and flexible symmetric encryption algorithm, though it is less commonly used today.
- **Use Cases**: File encryption, legacy systems.

#### **D. Twofish**
- **Key Sizes**: 128-bit, 192-bit, 256-bit.
- **Description**: Twofish is a successor to Blowfish and is known for its security and performance.
- **Use Cases**: File encryption, disk encryption.

---

### **2. Asymmetric Encryption Algorithms**
Asymmetric encryption uses a pair of keys: a public key for encryption and a private key for decryption. It is slower than symmetric encryption but is useful for key exchange and digital signatures.

#### **A. RSA (Rivest-Shamir-Adleman)**
- **Key Sizes**: 1024-bit, 2048-bit, 4096-bit.
- **Description**: RSA is one of the most widely used asymmetric encryption algorithms. It is based on the difficulty of factoring large prime numbers.
- **Use Cases**: Encrypting small amounts of data (e.g., encryption keys), SSL/TLS certificates.

#### **B. Elliptic Curve Cryptography (ECC)**
- **Key Sizes**: 256-bit, 384-bit, 521-bit.
- **Description**: ECC provides equivalent security to RSA with smaller key sizes, making it more efficient.
- **Use Cases**: Mobile devices, SSL/TLS certificates, IoT devices.

---

### **3. Hashing Algorithms**
Hashing algorithms are used to ensure data integrity and are often combined with encryption for additional security.

#### **A. SHA-256 (Secure Hash Algorithm 256-bit)**
- **Description**: Part of the SHA-2 family, SHA-256 generates a fixed-size 256-bit hash value.
- **Use Cases**: Data integrity checks, password hashing, digital signatures.

#### **B. SHA-3 (Secure Hash Algorithm 3)**
- **Description**: The latest member of the SHA family, SHA-3 is based on the Keccak algorithm.
- **Use Cases**: Data integrity checks, cryptographic hashing.

#### **C. bcrypt**
- **Description**: A password-hashing function based on the Blowfish cipher, designed to be slow and resistant to brute-force attacks.
- **Use Cases**: Password storage.

#### **D. Argon2**
- **Description**: A modern password-hashing algorithm designed to be memory-hard and resistant to GPU-based attacks.
- **Use Cases**: Password storage.

---

### **4. Encryption Modes and Techniques**
Encryption algorithms are often used in conjunction with specific modes and techniques to enhance security.

#### **A. Block Cipher Modes**
- **Electronic Codebook (ECB)**: Simple but insecure; not recommended for encrypting large amounts of data.
- **Cipher Block Chaining (CBC)**: Adds randomness by XORing each block with the previous ciphertext block.
- **Galois/Counter Mode (GCM)**: Provides both encryption and authentication, commonly used with AES.

#### **B. Key Management**
- **Key Derivation Functions (KDFs)**: Used to derive encryption keys from passwords or other inputs (e.g., PBKDF2, HKDF).
- **Hardware Security Modules (HSMs)**: Secure hardware devices for managing encryption keys.

---

### **5. Common Use Cases for Data-at-Rest Encryption**
#### **A. Full Disk Encryption (FDE)**
- Encrypts the entire disk or storage device.
- Examples: BitLocker (Windows), FileVault (macOS), LUKS (Linux).

#### **B. File-Level Encryption**
- Encrypts individual files or directories.
- Examples: GPG, VeraCrypt.

#### **C. Database Encryption**
- Encrypts data stored in databases.
- Examples: Transparent Data Encryption (TDE) in SQL Server, Oracle, and PostgreSQL.

#### **D. Cloud Storage Encryption**
- Encrypts data stored in cloud services.
- Examples: AWS S3 Server-Side Encryption (SSE), Azure Storage Service Encryption.

---

### **6. Best Practices for Encrypting Data at Rest**
1. **Use Strong Algorithms**:
   - Prefer AES-256 for symmetric encryption and RSA-2048 or ECC for asymmetric encryption.

2. **Secure Key Management**:
   - Use HSMs or cloud-based key management services (e.g., AWS KMS, Azure Key Vault).

3. **Encrypt All Sensitive Data**:
   - Ensure that all sensitive data, including backups, is encrypted.

4. **Use Authenticated Encryption**:
   - Combine encryption with authentication (e.g., AES-GCM) to ensure data integrity.

5. **Regularly Rotate Keys**:
   - Periodically change encryption keys to reduce the risk of compromise.

6. **Monitor and Audit**:
   - Use logging and monitoring tools to detect unauthorized access or key usage.

---

### **Summary**
- **Symmetric Encryption**: AES, 3DES, Blowfish, Twofish.
- **Asymmetric Encryption**: RSA, ECC.
- **Hashing Algorithms**: SHA-256, SHA-3, bcrypt, Argon2.
- **Encryption Modes**: CBC, GCM.
- **Key Management**: KDFs, HSMs.

By using strong encryption algorithms and following best practices, you can ensure that your data at rest remains secure and protected from unauthorized access.
