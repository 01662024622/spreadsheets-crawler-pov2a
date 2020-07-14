Mục đích
Lấy thông tin chi phí Marketing từ Google Sheets để lưu vào database tạm. 

Local Development
Code viết bằng Spring Boot, chạy trên Java 8. Được đóng gói trong Docker

Build và đóng gói bằng 2 câu lệnh chạy trên thư mục gốc của code:

./mvnw clean install -Djavax.xml.accessExternalSchema=all -DskipTests

docker-compose build

Trên máy local có thể khởi động bằng cách chạy trên IDE hoặc câu lệnh Maven: 

./mvnw spring-boot:run

Deployment
Được deploy lên Docker Swarm Cluster(mới chỉ có một Manager Node là "58.187.9.214")

Cài đặt môi trường deploy:

Cài đặt Docker trên 58.187.9.214
Add user đang thao tác(sinhhv) vào group docker để việc gọi các câu lệnh docker không cần gõ "sudo". Chú ý: logout ra rồi login lại mới có hiệu lực
Bật chế độ chạy Cluster(Swarm mode): docker swarm init --advertise-addr 58.187.9.214
Cài đặt tool quản trị(GUI) cho Cluster:
docker volume create portainer_data
docker service create --name portainer --publish 9000:9000 --constraint 'node.role == manager' --mount type=volume,src=portainer_data,dst=/data --mount type=bind,src=/var/run/docker.sock,dst=/var/run/docker.sock portainer/portainer -H unix:///var/run/docker.sock
Cài đặt "git" rồi lấy code về một thư mục trên Server. Chạy hai câu lệnh sau tại thư mục gốc của code để build phiên bản mới nhất từ code
./mvnw clean install -Djavax.xml.accessExternalSchema=all -DskipTests
docker-compose build
Trên server, gõ câu lệnh sau đây để deploy lên Swarm Cluster(chú ý, câu lệnh này chỉ cần chạy một lần khi khởi tạo môi trường): docker stack deploy --compose-file docker-compose.yml dwh
Sau đó, chúng ta có thể đăng nhập vào màn hình quản trị Swarm Cluster: http://58.187.9.214:9000, lần đầu tiên truy cập chúng ta sẽ có màn hình tạo mật khẩu cho tài khoản quản trị.

Khi có code mới hơn trên "git", ta muốn deploy phiên bản code mới đó thì làm các bước sau

Gõ câu lệnh "git" để lấy code mới nhất về
Chạy lại hai câu lệnh trong bước (5) ở trên
Vào  http://58.187.9.214:9000, chuyển đến mục "Services" → "dwh_spreadsheet-crawler-pov2a bấm "Update the service", bật "Pull latest image version", rồi bấm "Update"
Đợi khoảng 15-30s phiên bản mới sẽ được deploy lên Swarm Cluster
Khi cần thay đổi cấu hình trên môi trường deploy, thực hiện như sau
Vào  http://58.187.9.214:9000, chuyển đến mục "Services" → "dwh_apreadsheet-crawler-pov2a , cập nhật giá trị biến môi trường trong mục "Environment variables", rồi bấm "Apply changes"
Cấu hình mới sẽ có hiệu lực trong khoảng 15-30s