# API 명세서

| 기능      | 메서드    | URL             | Request                                                                      | Response                                                            | 상태 코드                        |
|---------|--------|-----------------|------------------------------------------------------------------------------|---------------------------------------------------------------------|------------------------------|
| 유저 생성   | POST   | `/users/signup` | `{ "username" : "yubin", "email" : "yubin@example.com"}`                      | `{ "id" : 1, "username" : "yubin", "email" : "yubin@example.com" }` | 200 CREATED, 400 BAD_REQUEST |
| 유저 조회   | GET    | `/users/{id}`   | 없음                                                                           | `{ "id": 1, "name": "yubin", "email": "yubin@example.com" }`        | 200 OK, 404 NOT_FOUND        |
| 유저 업데이트 | PATCH  | `/users/{id}`   | 없음                                                              | `{ "total": 100, "users": [...] }`                                  | 200                          |
| 유저 삭제   | DELETE | `/users/{id}`   | `{ "name": "홍길동", "email": "hong@example.com", "password": "yourpassword" }` | `{ "id": 1, "name": "홍길동", "email": "hong@example.com" }`           | 201, 400                     |
| 사용자 삭제  | DELETE | `/users/{id}`   | 없음                                                                           | `{ "message": "삭제되었습니다." }`                                         | 200, 404                     |

