# EasyCentral Bot - Android uygulaması

Masaüstündeki `easycentral_bot.py` programına telefondan bağlanır: canlı listeler ve günlük,
başlat / durdur, link ekleme, düzeltme, silme, Price Splitter.

## APK'yı GitHub ile üretmek (bilgisayara hiçbir şey kurmadan)

1. https://github.com adresinde ücretsiz bir hesap açın ve giriş yapın.
2. Sağ üstteki **+** > **New repository**. Ad: `easycentral-android`. **Create repository**.
3. Açılan sayfada **uploading an existing file** bağlantısına tıklayın.
4. Bu klasörün **içindeki her şeyi** (`.github`, `app`, `build.gradle`, `settings.gradle`,
   `gradle.properties` ...) seçip sayfaya sürükleyin. Klasörün kendisini değil, içindekileri.
   Sonra **Commit changes**.
5. Üstteki **Actions** sekmesine girin. "Build APK" işi kendiliğinden başlar, 3-5 dakikada yeşil olur.
6. Deponun ana sayfasında sağdaki **Releases** bölümünden `EasyCentralBot.apk` dosyasını indirin.
   (Olmazsa: Actions > biten iş > en altta **Artifacts**.)
7. APK'yı telefona indirin / gönderin, dokunun, "bu kaynaktan yüklemeye izin ver" deyip kurun.

`.github` klasörü yüklenmediyse (Actions sekmesi boşsa): **Add file > Create new file**, dosya adına
`.github/workflows/build.yml` yazın, bu klasördeki aynı dosyanın içeriğini yapıştırın, **Commit changes**.

## Kullanım

1. Masaüstü programında **Telefon** sekmesi > "Telefonum bu programa bağlanabilsin" kutusunu işaretleyin.
   Windows Güvenlik Duvarı sorarsa **Erişime izin ver** deyin.
2. Aynı sekmede görünen adresi (ör. `192.168.1.25:8765`) ve 6 rakamlı şifreyi uygulamaya yazın.
3. Dışarıdan (mobil veri) bağlanmak için modemde TCP 8765 portunu bu bilgisayara yönlendirin
   (ya da programdaki UPnP düğmesini deneyin) ve uygulamadaki "Dışarıdaki adres" kutusuna
   programın gösterdiği dış adresi yazın.

## Güncelleme

Depodaki dosyayı değiştirip (ör. `app/src/main/assets/index.html`) Commit yaptığınızda GitHub yeni bir
APK üretir; eskisinin üzerine kurulur.
