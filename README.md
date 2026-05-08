# 🛡️ Black Reflection (Core Library)

![Build Status](https://img.shields.io/github/actions/workflow/status/geterrorcode-me/reflection/build-reflection.yml?branch=main&style=for-the-badge)
![Platform](https://img.shields.io/badge/Platform-Android-green?style=for-the-badge)
![License](https://img.shields.io/badge/License-MIT-blue?style=for-the-badge)

**Black Reflection** adalah library pondasi (core) yang dirancang khusus untuk riset tingkat lanjut pada sistem operasi Android. Library ini berfungsi sebagai pembuka akses (bridge) untuk memanipulasi Hidden API dan internal framework yang biasanya dikunci oleh Google.

Ini merupakan komponen inti dari proyek (Virtual OS).

---

## 🚀 Fitur Utama

* **Advanced Hidden API Bypass**: Menembus batasan *Hidden API Policy* mulai dari Android 9 (Pie) hingga Android 14+ menggunakan teknik `setHiddenApiExemptions`.
* **Dynamic Reflection Helper**: Mempermudah pemanggilan field dan method tersembunyi tanpa boilerplate code yang membosankan.
* **AOT/DEX Friendly**: Kompatibel dengan optimasi Android modern seperti **d8/r8 synthetic lambdas**.
* **Ultra Lightweight**: Tidak memiliki dependensi eksternal, menjaga ukuran `reflection.jar` tetap kecil dan cepat.

---

## 🛠️ Arsitektur Proyek

Library ini bekerja dengan cara melakukan "unsealing" pada `dalvik.system.VMRuntime` untuk memberikan izin penuh kepada aplikasi host untuk mengakses kelas-kelas internal di `framework.jar`.



---

## 📦 Cara Penggunaan

### 1. Inisialisasi Bypass
Panggil kode ini di awal aplikasi (misal di `attachBaseContext` atau `onCreate`) agar sistem memberikan akses internal:

```java
import black.reflection.HiddenApiBypass;

public class MyVirtualApp extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        // Membuka gembok Hidden API
        HiddenApiBypass.bypass();
    }
}
