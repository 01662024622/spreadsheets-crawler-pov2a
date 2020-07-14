FROM docker.topicanative.edu.vn/java:18x

COPY ./target/spreadsheets-crawler-pov2a-0.0.1-SNAPSHOT.jar /app/dwh/
WORKDIR /app/dwh
ENV TZ=Asia/Ho_Chi_Minh
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

CMD java -jar -Dspring.profiles.active=release /app/dwh/spreadsheets-crawler-pov2a-0.0.1-SNAPSHOT.jar