## AlgaComments

![Java](https://img.shields.io/badge/Java-25-007396?logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.7-6DB33F?logo=springboot&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-8.14.3-02303A?logo=gradle&logoColor=white)

A simple two-service application that demonstrates synchronous integration between a comments API (`comment-service`) and a text moderation API (`moderation-service`). The comments service calls the moderation service before persisting comments.

### Run locally

Prereqs:
- Java 25 (Gradle toolchains will provision automatically if available)
- Bash/zsh terminal

Defaults:
- moderation-service: `http://localhost:8081`
- comment-service: `http://localhost:8080` (configured to call the moderation service at `localhost:8081`)

Start the moderation service:

```bash
cd moderation-service
./gradlew bootRun
```

Start the comment service (in another terminal):

```bash
cd comment-service
./gradlew bootRun
```

Optional: build runnable JARs

```bash
cd moderation-service && ./gradlew build && cd ..
cd comment-service && ./gradlew build && cd ..
```

Test requests:
- Bruno collections in `bruno-requests/` or use any HTTP client
- Comment API base URL: `http://localhost:8080/api/comments`


