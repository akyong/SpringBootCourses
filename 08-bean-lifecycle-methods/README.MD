## Catatan Penggunaan

### 1. Anotasi @PostConstruct dan @PreDestroy

#### **Konsep Dasar**

* `@PostConstruct` dan `@PreDestroy` adalah **anotasi lifecycle** di Spring.
* Digunakan untuk mengeksekusi logika **setelah bean selesai dibuat** (`@PostConstruct`) dan **sebelum bean dihancurkan** (`@PreDestroy`).
* Biasanya digunakan untuk inisialisasi resource, koneksi, atau cleanup.

| Anotasi          | Waktu Eksekusi                                       | Fungsi Utama                                |
| ---------------- | ---------------------------------------------------- | ------------------------------------------- |
| `@PostConstruct` | Setelah konstruktor dan dependency injection selesai | Inisialisasi resource, konfigurasi tambahan |
| `@PreDestroy`    | Sebelum bean dihancurkan oleh container Spring       | Menutup koneksi, membersihkan resource      |

---

### 2. Cara Penggunaan

#### **a. Contoh Dasar**

```java
@Component
public class TennisCoach implements Coach {

    public TennisCoach() {
        System.out.println("TennisCoach: Constructor dipanggil");
    }

    @PostConstruct
    public void doStartupStuff() {
        System.out.println("TennisCoach: @PostConstruct -> Bean siap digunakan");
    }

    @PreDestroy
    public void doCleanupStuff() {
        System.out.println("TennisCoach: @PreDestroy -> Bean akan dihancurkan");
    }

    @Override
    public String getDailyWorkout() {
        return "Practice your backhand volley for 20 minutes!";
    }
}
```

**Urutan eksekusi:**

```
1. Constructor dipanggil
2. Dependency Injection selesai
3. @PostConstruct dijalankan
4. Bean siap digunakan
5. Saat aplikasi ditutup -> @PreDestroy dijalankan
```

---

#### **b. Contoh dengan @Scope("prototype")**

```java
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BaseballCoach implements Coach {

    @PostConstruct
    public void init() {
        System.out.println("BaseballCoach: @PostConstruct");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("BaseballCoach: @PreDestroy");
    }
}
```

**Catatan penting:**

* Untuk **bean prototype**, `@PreDestroy` **tidak akan dipanggil otomatis** oleh Spring.
* Karena Spring **tidak mengelola siklus hidup penuh** dari bean prototype — hanya membuatnya.
* Jika butuh cleanup, kamu harus panggil manual atau gunakan custom destroy method.

---

### 3. Alternatif dengan Bean Configuration

```java
@Configuration
public class AppConfig {

    @Bean(initMethod = "doStartupStuff", destroyMethod = "doCleanupStuff")
    public SwimCoach swimCoach() {
        return new SwimCoach();
    }
}
```

```java
public class SwimCoach implements Coach {

    public void doStartupStuff() {
        System.out.println("SwimCoach: initMethod dipanggil");
    }

    public void doCleanupStuff() {
        System.out.println("SwimCoach: destroyMethod dipanggil");
    }
}
```

✅ Kelebihan:

* Tidak tergantung anotasi JSR-250 (`@PostConstruct`, `@PreDestroy`)
* Cocok untuk integrasi dengan konfigurasi XML atau non-annotation setup

---

### 4. Kapan Menggunakan @PostConstruct & @PreDestroy?

#### **Gunakan `@PostConstruct` untuk:**

* Inisialisasi koneksi database
* Memuat konfigurasi awal (misal dari file atau API)
* Logging atau setup awal object complex

#### **Gunakan `@PreDestroy` untuk:**

* Menutup koneksi database / stream
* Membersihkan cache / temporary files
* Shutdown graceful resource seperti thread pool

---

### 5. Perbandingan: @PostConstruct vs Constructor

| Aspek                | Constructor        | @PostConstruct                               |
| -------------------- | ------------------ | -------------------------------------------- |
| Waktu eksekusi       | Saat bean dibuat   | Setelah semua dependency selesai di-inject   |
| Dependency injection | Belum lengkap      | Sudah lengkap                                |
| Tujuan               | Inisialisasi dasar | Inisialisasi yang bergantung pada dependency |
| Dipanggil otomatis   | Ya                 | Ya                                           |

**Kesimpulan:**
Gunakan `@PostConstruct` jika logika inisialisasi **membutuhkan dependency lain yang di-@Autowired**.

---

### 6. Best Practices

#### ✅ **DO:**

* Gunakan `@PostConstruct` untuk setup yang memerlukan dependency siap.
* Gunakan `@PreDestroy` untuk menutup resource penting.
* Selalu log aktivitas di kedua metode agar mudah debugging.

#### ❌ **DON’T:**

* Jangan gunakan `@PreDestroy` di bean prototype (tidak akan terpanggil).
* Hindari logika berat di `@PostConstruct` yang memperlambat startup.
* Jangan gunakan `@PostConstruct` untuk dependency injection tambahan (Spring sudah melakukannya).

---

### 7. Debugging & Testing

```java
@Component
public class GolfCoach implements Coach {

    @PostConstruct
    public void init() {
        System.out.println("GolfCoach initialized at: " + java.time.LocalDateTime.now());
    }

    @PreDestroy
    public void cleanup() {
        System.out.println("GolfCoach destroyed at: " + java.time.LocalDateTime.now());
    }
}
```

**Hasil ketika aplikasi dijalankan dan dihentikan:**

```
GolfCoach initialized at: 2025-11-09T21:18:10.235
GolfCoach destroyed at: 2025-11-09T21:18:15.142
```

