/*
* Ad Soyad: Mehmet Boztepe
* Ogrenci No: 240541084
* Tarih: 05 Kasim 2025
* Aciklama: Detayli Maas Bordrosu Hesaplama Sistemi (Gorev 3)
*/

import java.util.Scanner;

public class MaasHesap {

    public static void main(String[] args) {
        
        // --- 1. SABITLERI TANIMLAMA (final) ---
        final double SGK_ORANI = 0.14;             // %14.0
        final double GELIR_VERGISI_ORANI = 0.15;   // %15.0
        final double DAMGA_VERGISI_ORANI = 0.00759; // %0.759 (Ornek cikti 0.8% dese de orani kullanacagiz)
        final double MESAI_CARPANI = 1.5;
        final int AYLIK_CALISMA_SAATI = 176; // Istatistik icin sabit
        final int STANDART_AYLIK_SAAT_HESABI = 160; // Mesai ucreti hesabi icin
        final int AYLIK_IS_GUNU = 22; // Istatistik icin sabit

        Scanner input = new Scanner(System.in);
        
        // --- 2. KULLANICIDAN BILGILERI ALMA ---
        System.out.println("=====================================");
        System.out.println("MAAS HESAPLAMA SISTEMI");
        System.out.println("=====================================");

        // next() kullanildi (nextLine() bug'ini onlemek icin, Ad Soyad tek kelime veya arada bosluk olmadan alinabilir)
        System.out.print("Calisanin Adini ve Soyadini girin (Ornek: AyseDemir): ");
        String calisanAdSoyad = input.next(); 
        // Eğer ayrı ayrı isim soyisim istenseydi next() ve ardından next() kullanılırdı.

        System.out.print("Aylik Brut Maas (TL, Ornek: 15000.00): ");
        double brutMaas = input.nextDouble();

        System.out.print("Haftalik Calisma Saati (saat, Ornek: 40): ");
        // Haftalik saat alindi ancak hesaplamada kullanilmadi, bilgi olarak saklandi.
        int haftalikSaat = input.nextInt(); 

        System.out.print("Toplam Mesai Saati Sayisi (saat, Ornek: 10): ");
        int mesaiSaati = input.nextInt();

        input.close();

        // --- 3. GELIRLERIN HESAPLANMASI ---
        
        // Saatlik Brut Ucret = Brut Maas / 160 (aylik ortalama standart calisma saati)
        double saatlikBrutUcret = brutMaas / STANDART_AYLIK_SAAT_HESABI; 
        
        // Mesai Ücreti = (Brüt Maaş / 160) × Mesai Saat × 1.5
        double mesaiUcreti = saatlikBrutUcret * mesaiSaati * MESAI_CARPANI;
        
        // Toplam Gelir = Brüt + Mesai
        double toplamGelir = brutMaas + mesaiUcreti;

        // --- 4. KESINTILERIN HESAPLANMASI ---
        
        // SGK Kesintisi: Toplam Gelir × 0.14
        double sgkKesintisi = toplamGelir * SGK_ORANI;
        
        // Gelir Vergisi: Toplam Gelir × 0.15
        double gelirVergisiKesintisi = toplamGelir * GELIR_VERGISI_ORANI;
        
        // Damga Vergisi: Toplam Gelir × 0.00759
        double damgaVergisiKesintisi = toplamGelir * DAMGA_VERGISI_ORANI;
        
        // Toplam Kesinti
        double toplamKesinti = sgkKesintisi + gelirVergisiKesintisi + damgaVergisiKesintisi;

        // --- 5. NET MAAŞ HESAPLAMASI ---
        
        // Net Maaş = Toplam Gelir - Toplam Kesinti
        double netMaas = toplamGelir - toplamKesinti;

        // --- 6. ISTATISTIKLERIN HESAPLANMASI ---
        
        // Kesinti Oranı (%): (Toplam Kesinti / Toplam Gelir) × 100
        double kesintiOraniYuzde = (toplamKesinti / toplamGelir) * 100;
        
        // Saatlik Net Kazanç: Net Maaş / 176 saat
        double saatlikNetKazanc = netMaas / AYLIK_CALISMA_SAATI;
        
        // Günlük Net Kazanç: Net Maaş / 22 gün
        double gunlukNetKazanc = netMaas / AYLIK_IS_GUNU;


        // ------------------------------------------------------------------
        // --- 7. BORDRO ÇIKTISI (Profesyonel ve Düzenli Format) ---
        // ------------------------------------------------------------------
        
        System.out.println("\n====================================");
        System.out.println("       MAAS BORDROSU");
        System.out.println("====================================");
        
        // %-30s: Sola hizali, 30 karakter genisliginde
        // %.2f: 2 ondalik basamakli (para birimi)
        // %.1f: 1 ondalik basamakli (yuzde)

        // Çalışan Bilgisi
        System.out.printf("%-30s: %s%n", "Calisan", calisanAdSoyad);
        System.out.println("------------------------------------");

        // GELIRLER
        System.out.println("GELIRLER:");
        System.out.printf("%-30s: %.2f TL%n", "Brut Maas", brutMaas);
        System.out.printf("%-30s: %.2f TL%n", "Mesai Ucreti (" + mesaiSaati + " saat)", mesaiUcreti);
        System.out.println("------------------------------------");
        System.out.printf("%-30s: %.2f TL%n", "TOPLAM GELIR", toplamGelir);

        // KESINTILER
        System.out.println("\nKESINTILER:");
        System.out.printf("%-30s: %.2f TL%n", "SGK Kesintisi (" + String.format("%.1f", SGK_ORANI * 100) + "%)", sgkKesintisi);
        System.out.printf("%-30s: %.2f TL%n", "Gelir Vergisi (" + String.format("%.1f", GELIR_VERGISI_ORANI * 100) + "%)", gelirVergisiKesintisi);
        // Damga Vergisi ornegi 0.8% dedigi icin orani yuzdeye cevirirken 1 ondalik formatla gosterildi
        System.out.printf("%-30s: %.2f TL%n", "Damga Vergisi (" + String.format("%.1f", DAMGA_VERGISI_ORANI * 100) + "%)", damgaVergisiKesintisi); 
        System.out.println("------------------------------------");
        System.out.printf("%-30s: %.2f TL%n", "TOPLAM KESINTI", toplamKesinti);

        // NET MAAS
        System.out.println("\n************************************");
        System.out.printf("%-30s: %.2f TL%n", "NET MAAS", netMaas);
        System.out.println("************************************");

        // ISTATISTIKLER
        System.out.println("\nISTATISTIKLER:");
        System.out.printf("%-30s: %.1f%%%n", "Toplam Kesinti Orani", kesintiOraniYuzde); // Yuzde degeri 1 ondalik
        System.out.printf("%-30s: %.2f TL%n", "Saatlik Net Kazanc (176h)", saatlikNetKazanc);
        System.out.printf("%-30s: %.2f TL%n", "Gunluk Net Kazanc (22g)", gunlukNetKazanc);
        System.out.println("====================================");
    }
}
