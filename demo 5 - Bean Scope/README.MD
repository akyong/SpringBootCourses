## Catatan Penggunaan @Lazy dan application.properties

### 1. Anotasi @Lazy

#### **Konsep Dasar**
- `@Lazy` digunakan untuk menunda inisialisasi bean sampai bean tersebut benar-benar dibutuhkan
- Secara default, Spring membuat semua singleton beans saat aplikasi startup (eager initialization)
- Dengan `@Lazy`, bean baru dibuat saat pertama kali diminta (lazy initialization)

#### **Cara Penggunaan @Lazy**

##### **a. Pada Class Component**
```java
@Component
@Lazy
public class TrackCoach implements Coach {
    public TrackCoach() {
        System.out.println("TrackCoach: Constructor dipanggil");
    }
    
    @Override
    public String getDailyWorkout() {
        return "Run 5k";
    }
}
```


##### **b. Pada Constructor Injection**
```java
@RestController
public class DemoController {
    private Coach myCoach;
    
    // @Lazy diterapkan pada parameter
    @Autowired
    public DemoController(@Lazy Coach myCoach) {
        System.out.println("DemoController: Constructor dipanggil");
        this.myCoach = myCoach;
    }
}
```


##### **c. Pada Field Injection**
```java
@RestController
public class DemoController {
    
    @Autowired
    @Lazy
    private Coach myCoach;
}
```


##### **d. Pada Bean Configuration Method**
```java
@Configuration
public class AppConfig {
    
    @Bean
    @Lazy
    public Coach trackCoach() {
        return new TrackCoach();
    }
}
```


#### **Kapan Menggunakan @Lazy?**
- ✅ Bean yang jarang digunakan
- ✅ Bean dengan inisialisasi yang memakan waktu/resource
- ✅ Bean yang hanya dibutuhkan dalam kondisi tertentu
- ✅ Untuk mempercepat startup time aplikasi

---

### 2. Konfigurasi di application.properties

#### **Global Lazy Initialization**

```properties
# Mengaktifkan lazy initialization untuk SEMUA beans
spring.main.lazy-initialization=true
```


**Efek:**
- Semua beans di aplikasi akan menggunakan lazy initialization
- Startup aplikasi lebih cepat
- Beans baru dibuat saat pertama kali diakses

#### **Konfigurasi Lainnya yang Sering Digunakan**

```properties
# Server Configuration
server.port=8080
server.servlet.context-path=/api

# Logging Configuration
logging.level.root=INFO
logging.level.com.bobby.demo=DEBUG
logging.file.name=application.log

# Database Configuration (contoh)
spring.datasource.url=jdbc:mysql://localhost:3306/mydb
spring.datasource.username=root
spring.datasource.password=password

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```


---

### 3. Perbandingan: @Lazy vs spring.main.lazy-initialization

| Aspek | @Lazy (Annotation) | spring.main.lazy-initialization=true |
|-------|-------------------|--------------------------------------|
| **Scope** | Spesifik pada bean tertentu | Global untuk semua beans |
| **Kontrol** | Granular, per-bean | Aplikasi-wide |
| **Prioritas** | Lebih tinggi (override global setting) | Lebih rendah |
| **Use Case** | Bean-bean tertentu saja | Development/testing |

---

### 4. Contoh Kombinasi

```java
// Class dengan @Lazy
@Component
@Lazy
public class BaseballCoach implements Coach {
    public BaseballCoach() {
        System.out.println("BaseballCoach: Constructor");
    }
}

// Class tanpa @Lazy (akan mengikuti global setting)
@Component
public class CricketCoach implements Coach {
    public CricketCoach() {
        System.out.println("CricketCoach: Constructor");
    }
}
```


**application.properties:**
```properties
# Global lazy initialization diaktifkan
spring.main.lazy-initialization=true

# Setting lainnya
spring.application.name=demo
server.port=8080
```


**Hasil:**
- Kedua coach akan lazy-loaded karena global setting
- Jika global setting = false, hanya BaseballCoach yang lazy-loaded

---

### 5. Tips dan Best Practices

#### **✅ DO:**
- Gunakan `@Lazy` untuk beans yang memakan resource besar
- Kombinasikan dengan `@Qualifier` jika ada multiple implementations
- Dokumentasikan alasan penggunaan `@Lazy`

#### **❌ DON'T:**
- Jangan overuse lazy initialization - bisa menyembunyikan masalah dependency
- Hindari lazy initialization untuk beans critical yang harus siap di startup
- Jangan gunakan di production tanpa testing yang matang

#### **⚠️ Perhatian:**
- Error pada lazy beans baru terdeteksi saat bean diakses, bukan saat startup
- Performa hit pertama kali akan lebih lambat (karena bean dibuat saat itu)

---

### 6. Debugging & Testing

```java
@Component
@Lazy
public class TrackCoach implements Coach {
    
    public TrackCoach() {
        // Log untuk tracking kapan bean dibuat
        System.out.println("TrackCoach bean created at: " + 
            java.time.LocalDateTime.now());
    }
}
```

