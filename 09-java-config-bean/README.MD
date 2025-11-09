
# Java Config Bean - Mendaftarkan Bean Secara Manual

## Deskripsi
Project ini mendemonstrasikan cara mendaftarkan object/bean ke Spring Container menggunakan anotasi `@Bean` di class konfigurasi, tanpa menggunakan `@Component` atau component scanning.

## Konsep @Bean

### Kapan Menggunakan @Bean?
`@Bean` digunakan ketika:
- Class yang ingin didaftarkan berasal dari library pihak ketiga (tidak bisa dimodifikasi untuk menambah `@Component`)
- Ingin mengontrol proses pembuatan bean secara manual
- Perlu konfigurasi khusus saat membuat bean
- Ingin mendaftarkan class yang tidak bisa menggunakan `@Component`

### Cara Kerja

#### 1. Membuat Class Configuration
Class konfigurasi harus ditandai dengan `@Configuration`:

```java
@Configuration
public class SportConfig {
    // Bean definitions here
}
```


#### 2. Mendefinisikan Bean dengan @Bean
Method yang ditandai `@Bean` akan dipanggil Spring untuk membuat object:

```java
@Bean
public Coach swimCoach() {
    return new SwimCoach();
}
```


**Poin Penting:**
- Method name (`swimCoach`) menjadi **bean ID** secara default
- Return type (`Coach`) menentukan tipe bean
- Spring akan memanggil method ini untuk membuat dan mendaftarkan bean

#### 3. Inject Bean ke Component
Bean yang didaftarkan via `@Bean` dapat di-inject seperti bean biasa:

```java
@RestController
public class DemoController {
    private Coach myCoach;
    
    @Autowired
    public DemoController(@Qualifier("swimCoach") Coach theCoach) {
        myCoach = theCoach;
    }
}
```


## Struktur Project

```
src/main/java/com/bobby/demo/
├── common/
│   ├── Coach.java              (Interface)
│   ├── SwimCoach.java          (Implementation - TANPA @Component)
│   └── ...
├── config/
│   └── SportConfig.java        (Configuration Class dengan @Bean)
└── rest/
    └── DemoController.java     (REST Controller yang menggunakan bean)
```


## Perbandingan: @Component vs @Bean

### Menggunakan @Component
```java
@Component
public class SwimCoach implements Coach {
    // Otomatis terdeteksi via component scanning
}
```


### Menggunakan @Bean
```java
// Class biasa tanpa anotasi
public class SwimCoach implements Coach {
    // Tidak ada @Component
}

// Di class @Configuration
@Configuration
public class SportConfig {
    @Bean
    public Coach swimCoach() {
        return new SwimCoach();  // Manual instantiation
    }
}
```


## Keuntungan Menggunakan @Bean

1. **Fleksibilitas**: Bisa mendaftarkan class dari library eksternal
2. **Kontrol Penuh**: Mengontrol cara pembuatan object (constructor params, property setting, dll)
3. **Custom Bean ID**: Nama method menjadi bean ID
4. **Conditional Registration**: Bisa menggunakan logic untuk menentukan bean mana yang didaftarkan

## Custom Bean ID

Secara default, nama method adalah bean ID. Untuk menggunakan nama custom:

```java
@Bean("customBeanId")
public Coach swimCoach() {
    return new SwimCoach();
}
```


Kemudian inject dengan:
```java
@Qualifier("customBeanId")
```


## Cara Menjalankan
1. Jalankan `DemoApplication.java`
2. Akses endpoint REST controller
3. Bean `SwimCoach` akan otomatis ter-inject meskipun tidak menggunakan `@Component`

## Kesimpulan
- `@Bean` = cara manual mendaftarkan bean ke Spring Container
- Digunakan di class yang ditandai `@Configuration`
- Method dengan `@Bean` akan dipanggil Spring untuk membuat object
- Bean ID = nama method (bisa di-override)
- Berguna untuk class yang tidak bisa menggunakan `@Component`
```
README ini menjelaskan secara lengkap tentang:
1. Konsep dan tujuan penggunaan `@Bean`
2. Cara kerja pendaftaran bean manual
3. Perbandingan dengan `@Component`
4. Struktur project
5. Best practices dan keuntungan menggunakan `@Bean`
```
