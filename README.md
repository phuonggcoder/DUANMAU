Yêu cầu
Đảm bảo bạn đã cài đặt:

JDK 8 trở lên
Android Studio
Gradle 7.x
Thiết lập
Clone kho lưu trữ:

bash
Copy code
git clone https://github.com/phuonggcoder/DUANMAU.git
Mở dự án trong Android Studio:

Chọn File -> Open và điều hướng đến thư mục dự án.
Sync Gradle:

Cho phép Android Studio đồng bộ và tải xuống các phụ thuộc cần thiết.
Build APK
Để build file APK, sử dụng lệnh sau:

bash
Copy code
./gradlew assembleDebug
Lệnh này sẽ tạo file APK trong thư mục app/build/outputs/apk/debug/.

Chạy ứng dụng
Bạn có thể cài đặt và chạy APK trên trình giả lập hoặc thiết bị thật:

bash
Copy code
adb install -r app/build/outputs/apk/debug/app-release.apk
