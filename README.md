# ArkanoidFX — Bài tập lớn Lập trình Hướng đối tượng (OOP)

Một bản Arkanoid viết bằng JavaFX, tập trung thể hiện các nguyên tắc OOP, tách lớp rõ ràng (Model–View–Controller), kèm hiệu ứng đồ họa/âm thanh, cấp độ, power‑up, địch (enemy) và leaderboard.

---

## 1) Tính năng nổi bật (đã có trong mã nguồn)

- Gameplay cơ bản
  - Paddle điều khiển bằng A/D; bóng nảy với tường, paddle, gạch; rơi khỏi màn hình sẽ mất mạng.
  - Quản lý mạng (lives), điểm (score), trạng thái game (PLAYING, PAUSED, GAME_OVER).
  - Nhiều cấp độ (Level 1…32 + ngẫu nhiên); layout gạch đa dạng, có gạch không phá được.
- Hệ thống gạch (Bricks)
  - ColoredBrick (RUBY, YLLW, BLUE, MGNT, LIME, WHIT, ORNG, CYAN).
  - StrongBrick (bền, cần nhiều hit), ExtraStrongBrick (rất bền), UnbreakableBrick (không phá).
- Power‑Up rơi khi phá gạch (xác suất ~15%) và có thời gian hiệu lực
  - ExpandPaddlePowerUp (mở rộng paddle)
  - FastBallPowerUp (tăng tốc bóng)
  - MultiBallPowerUp (nhân đôi bóng)
  - ExtraLifePowerUp (thêm mạng)
  - GunPowerUp (trang bị súng cho paddle; bắn bullet phá gạch/địch)
- Địch (Enemies) với nhiều kiểu hành vi
  - Reflector (va đập phản xạ bóng), Up/Down‑Sensitive (chỉ bị hạ khi bóng đi lên/xuống)
  - Cơ chế spawn qua “cửa” trên viền, có hoạt ảnh mở/đóng
- Hiệu ứng hình/âm
  - Sprite map cho paddle (thường/rộng/gun), bóng, gạch, enemy; shadow, blink khi gạch bị trúng, explosion khi enemy nổ
  - ParticleSystem tạo hiệu ứng hạt khi phá gạch
  - Âm thanh: va chạm, power‑up, nổ, level ready, game over…
- Giao diện & màn hình
  - Menu: SPACE để bắt đầu, L để xem Leaderboard, ESC để quay lại
  - In‑game: hiển thị LV, mạng (icon tim), điểm, gợi ý phím P (pause)
  - End Game: màn hình kết thúc (điều hướng lại menu/chơi tiếp)
- Leaderboard (tùy chọn)
  - Lưu file `leaderboard.txt` (định dạng: `name,score,level`), đọc/ghi, sắp xếp giảm dần theo điểm rồi level

---

## 2) Điều khiển

- Trong game: A (trái), D (phải), P (tạm dừng/tiếp tục)
- Ở Menu: SPACE (bắt đầu), L (Leaderboard), ESC (thoát Leaderboard về Menu)
- Bóng khởi đầu “dính” paddle; di chuyển paddle để thả bóng ra

---

## 3) Kiến trúc & Thiết kế OOP

- Nguyên tắc OOP áp dụng rõ ràng
  - Đóng gói: thuộc tính private/protected, cung cấp getter/setter hợp lý
  - Kế thừa: `MovableObject` kế thừa `GameObject`; `Ball`, `Paddle`, `Enemy` kế thừa `MovableObject`; `Brick` và các biến thể kế thừa cùng hệ thống; `PowerUp` là lớp trừu tượng với các lớp con cụ thể
  - Đa hình: các phương thức `update`, `move`, `render` (ở View) xử lý theo kiểu đối tượng
  - Trừu tượng: lớp cơ sở trừu tượng định nghĩa hành vi chung, ẩn chi tiết triển khai
- Mẫu thiết kế (Design Patterns)
  - Singleton: `GameEngine`, `AssetManager`, `SoundManager`, `MenuController`, `GameController`, `EndGameController`, `LeaderboardManager`
  - Factory: `BrickFactory` tạo gạch theo `BrickType`
- Phân lớp theo MVC
  - Model: logic game, đối tượng (Ball/Paddle/Brick/PowerUp/Enemy), va chạm, điểm/mạng, level, particle, âm thanh, leaderboard…
  - View: JavaFX `Canvas` vẽ nền, viền, UI, sprite, hiệu ứng, particle
  - Controller: nhận input phím, điều phối vòng lặp game (AnimationTimer), chuyển scene Menu ↔ Game ↔ EndGame

---

## 4) Cấu trúc dự án

- `src/main/java/org/OOPproject/ArkanoidFX/ArkanoidGame.java` — Entry point JavaFX, quản lý scene
- `model/`
  - `GameEngine` — Trái tim logic: cập nhật, va chạm, power‑up, enemy, tiến độ level, điểm, mạng, trạng thái
  - `GameObject`, `MovableObject` — lớp cơ sở; `Paddle`, `Ball`, `Bullet`, `Enemy`
  - `Bricks/` — `Brick` + biến thể (`ColoredBrick`, `StrongBrick`, `ExtraStrongBrick`, `UnbreakableBrick`), `BrickFactory`, `BrickType`
  - `PowerUps/` — `PowerUp` + `ExpandPaddlePowerUp`, `FastBallPowerUp`, `MultiBallPowerUp`, `ExtraLifePowerUp`, `GunPowerUp`, `PowerUpTypes`
  - `Level` — layout cấp độ sẵn có (1…32) + sinh ngẫu nhiên
  - `Sprite`, `Particle`, `ParticleSystem`, `Blink`, `Destroy`
  - `SoundManager`, `LeaderboardManager`
- `view/`
  - `GameView`, `MenuView`, `EndGameView` — vẽ game/menu/end bằng JavaFX Canvas; `AssetManager` tải/cấp phát ảnh/âm thanh
- `controller/`
  - `MenuController`, `GameController`, `EndGameController` — nhận input, vòng lặp AnimationTimer, chuyển scene
- `src/main/resources/assets/` — textures, sfx, font; cấu hình qua `AssetManager`
- `leaderboard.txt` — dữ liệu điểm cao
- `src/test/java/` — Unit tests JUnit cho core (GameEngine, GameObject, Paddle, Sprite, Particle, Asset/Sound)

---

## 5) Cách chạy (Windows, Maven Wrapper)

Yêu cầu:
- JDK 24 (POM thiết lập maven-compiler-plugin source/target = 24)
Chạy game (JavaFX):
```cmd
mvnw.cmd clean javafx:run
```

Chạy test:
```cmd
mvnw.cmd -q test
```

## 6) Cơ chế va chạm & vòng lặp

- Vòng lặp game: `AnimationTimer` trên JavaFX Application Thread; mỗi khung: đọc input → cập nhật `GameEngine.updateGame(delta)` → `GameView.render()`
- Va chạm:
  - Ball–Paddle: góc nảy phụ thuộc vị trí tiếp xúc + vận tốc paddle (tạo “độ xoáy”), đảm bảo bay lên; cooldown tránh double‑hit
  - Ball/Bullet–Brick: tính toán hướng nảy theo cạnh va chạm; tạo particle + blink; phá hủy gạch, cộng điểm, có thể spawn power‑up
  - Ball/Bullet–Enemy: theo loại enemy; hạ địch → explosion + điểm
  - Power‑Up–Paddle: kích hoạt hiệu ứng, lưu thời lượng, tự hủy khi hết hạn (tự đảo hiệu ứng)
- Quản lý mạng/level: rơi hết bóng → `loseLife()`; hết mạng → `gameOver()`; hết gạch phá được → `levelComplete()`

---

## 7) Leaderboard

- Lưu tại file gốc dự án: `leaderboard.txt` (định dạng: `playerName,score,level`)
- Đọc/ghi tự động, sắp xếp theo điểm giảm dần; vào Menu nhấn `L` để xem Top 10
- Reset leaderboard: xóa file `leaderboard.txt`

---

## 8) Kiểm thử (JUnit)

- Các test tiêu biểu: `GameEngineTest`, `GameObjectTest`, `MovableObjectTest`, `PaddleTest`, `SpriteTest`, `ParticleTest`, `AssetManagerIntegrationTest`, `SoundManagerIntegrationTest`
- Chạy toàn bộ test:
```cmd
mvnw.cmd -q test
```

---
