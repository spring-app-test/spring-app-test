# 1단계: 빌드용 이미지
FROM eclipse-temurin:17-jdk AS build

# 작업 디렉토리 설정
WORKDIR /app

# Gradle wrapper 및 프로젝트 파일 복사
COPY . .

# Gradle 빌드 실행 (build/libs/*.jar 생성됨)
RUN chmod +x ./gradlew && ./gradlew build

# 2단계: 실제 실행 이미지 (최종 이미지)
FROM eclipse-temurin:17-jre

# 타임존 설정 (한국 시간)
ENV TZ=Asia/Seoul

# JAR 복사 (위 단계에서 생성된 JAR)
COPY --from=build /app/build/libs/app-0.0.1-SNAPSHOT.jar app.jar

# 포트 오픈 (Spring Boot 기본 포트)
EXPOSE 10000

# 실행 명령
ENTRYPOINT ["java", "-jar", "app.jar"]