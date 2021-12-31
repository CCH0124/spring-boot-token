資料庫使用 Postgresql，實作以下 API

|Methods|	Urls|	Actions|
|---|---|---|
|POST|	/api/v1/auth/signup|	signup new account|
|POST|	/api/v1/auth/signin|	login an account|
|DELETE|	/api/v1/user/{account}	|access Admin's content|
|GET|	/api/v1/user/{account}	|access User's or Moderator's content|
|GET|	/api/v1/user/hello	| public content |

## JWT Authentication  Flow
1. signup 註冊
2. server 檢查一些邏輯是否
3. 回傳 200 HTTP Status

註冊完後，進行登入獲取 token
1. signin 登入
2. 建立 token
3. 回傳 token 給使用者

獲取後客戶端需在 HTTP Authorization 標頭中輸入 token 資訊。

## Architecture
- WebSecurityConfigurerAdapter
  - 提供 `HttpSecurity`  進行資源訪問請求設定，還有一些像是 cors、csrf 等配置
- UserDetailsService
  -  有一個 `loadUserByUsername` 方法並回傳 `UserDetails` 物件，之後可使用該對象進行驗證
-  UsernamePasswordAuthenticationToken
   - 攔截 login 請求，`AuthenticationManager` 會驗證登入的帳號
- OncePerRequestFilter 
  - 提供 `doFilterInternal` 方法，將會解析和驗證 JWT、透過 `UserDetailsS​​ervice` 加載用戶詳細訊息、使用 `UsernamePasswordAuthenticationToken` 檢查授權
- AuthenticationEntryPoint
  - 捕獲 authentication 錯誤
