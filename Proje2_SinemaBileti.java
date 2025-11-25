/**
Öğrenci No: 250541048
Ad-Soyad: Amine Ceren Yiğit
Ödev Adı: Sinema Bileti Fiyatlandırma
Açıklama: Belirli koşullara göre sinema biletinin hesaplanmasını sağlayan sistem
 */ 

package v1;
import java.util.Scanner;
public class SinemaBilet {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Gün numarası giriniz(1=pazartesi, 2=salı....7=pazar):");
        int gun=input.nextInt();
        System.out.print("Saati giriniz(8-23):");
        int saat=input.nextInt();
        System.out.print("Yaşınızı giriniz:");
        int yas=input.nextInt();
        System.out.print("Meslek seçiniz(1=Öğrenci, 2=Öğretmen, 3=Diğer):");
        int meslek=input.nextInt();
        System.out.print("Film türü seçiniz(1=2D, 2=3D, 3=IMAX, 4=4DX)");
        int filmturu=input.nextInt();
        double toplamFiyat=calculateFinalPrice(gun, saat, yas, meslek, filmturu);
        generateTicketInfo(gun, saat, yas, meslek, filmturu, toplamFiyat);
    }
    
    // Haftasonu mu değil mi kontrol edilmesi
    public static boolean isWeekend(int gun){
        switch (gun){
            case 6: case 7:
                return true;
            case 1: case 2: case 3: case 4: case 5:
                return false;
            default:
                System.out.println("Hatalı gün!!");
                return false;
        }
    }
    
    // Matine olup olmadığının kontrol edilmesi
    public static boolean isMatinee(int saat){
      return saat<12;
    }
    
    //Temel fiyat hesabının yapılması
    public static double calculateBasePrice(int gun,int saat){
        boolean haftasonu = isWeekend(gun);
        boolean matine = isMatinee(saat);

        if (!haftasonu && matine){
           return 45;
        }
        if (!haftasonu){
            return 65;
        }
        if (haftasonu && matine){
            return 55;
        }
        return 85;
    }
    
    //İndirimin hesaplanması
    public static double calculateDiscount(int yas, int meslek,int gun){
        double indirim = 0.0;

        // yaş için yapılan indirimler
        if (yas<12){
            return 0.25;
        }
        else if (yas>=65){
            return 0.30;
        }

        // Meslek için yapılan indirimler
        if (meslek==1 && gun<=5) {
            return 0.20;
        }
        else if(meslek==1 && gun>5){
            return 0.15;
        }
        if (meslek==2 && gun==3){
            return 0.35;
        }
        return indirim;

    }
    
    // Film türüne göre ekstra ücretlerin hesaplanması
    public static double getFormatExtra(int filmturu) {
        switch (filmturu) {
            case 1:
                return 0; //2D
            case 2:
                return 25; //3D
            case 3:
                return 35; //IMAX
            case 4:
                return 50; //4DX
        }
        return filmturu;
    }
    
    //Toplam Fiyatın hesaplanması
    public static double calculateFinalPrice(int gun, int saat, int yas, int meslek, int filmTuru){
        double temel_fiyat = calculateBasePrice(gun, saat);
        double indirim = calculateDiscount(yas, meslek, gun);
        double indirimli_fiyat = temel_fiyat - (temel_fiyat * indirim);
        double ekstra = getFormatExtra(filmTuru);
        double toplamFiyat = indirimli_fiyat + ekstra;
        return toplamFiyat;
    }
    public static void generateTicketInfo(int gun, int saat, int yas, int meslek, int filmTuru, double toplamFiyat) {
        System.out.println("\n--- Bilet Bilgisi ---");
        System.out.println("Gün: " + gun);
        System.out.println("Saat: " + saat);
        System.out.println("Yaş: " + yas);
        System.out.println("Meslek: " + (meslek==1?"Öğrenci":meslek==2?"Öğretmen":"Diğer"));
        System.out.println("Film Türü: " + (filmTuru==1?"2D":filmTuru==2?"3D":filmTuru==3?"IMAX":"4DX"));
        System.out.println("Toplam Fiyat: " + toplamFiyat + " TL");
    }
}
