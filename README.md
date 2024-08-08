Dưới đây là phiên bản đã chỉnh sửa của phần "Yêu cầu" và "Thiết lập" trong `README.md` để nó trông đẹp và dễ đọc hơn:

---

### Yêu cầu

Để bắt đầu, hãy đảm bảo rằng bạn đã cài đặt:

- **[JDK 8 trở lên](https://www.oracle.com/java/technologies/javase-jdk8-downloads.html)**
- **[Android Studio](https://developer.android.com/studio)**
- **[Gradle 7.x](https://gradle.org/releases/)**

### Thiết lập

1. **Clone kho lưu trữ:**

   Mở terminal và chạy lệnh sau để clone dự án:

   ```bash
   git clone https://github.com/phuonggcoder/DUANMAU.git
   ```

2. **Mở dự án trong Android Studio:**

   - Khởi động Android Studio.
   - Chọn `File -> Open` và điều hướng đến thư mục dự án vừa clone.

3. **Sync Gradle:**

   - Khi mở dự án, Android Studio sẽ tự động đề xuất việc đồng bộ Gradle. Nhấp vào “Sync Now” để tải xuống các phụ thuộc cần thiết.

### Build APK

Để build file APK, thực hiện lệnh sau trong terminal:

```bash
./gradlew assembleDebug
```

Sau khi build thành công, file APK sẽ được tạo trong thư mục `app/build/outputs/apk/debug/`.

### Chạy ứng dụng

Để cài đặt và chạy APK trên trình giả lập hoặc thiết bị thật, sử dụng lệnh sau:

```bash
adb install -r app/build/outputs/apk/debug/app-release.apk
```

---
