package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class KarakterDekodolo {
    private static String beolvasKodoltSzot(String fajlnev) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(fajlnev))) {
            return br.readLine(); // Olvassa be az első sort, ami a kódolt szót tartalmazza
        }
    }

    private static void dekodolSzot(String kodoltSzo) {
        try {
            String dekodoltSzo = beolvasKodoltSzot("dekodol.txt");
            Karakter dekodoltKarakter = new Karakter(dekodoltSzo);

            if (dekodoltKarakter.Felismer(dekodoltKarakter)) {
                System.out.println("Dekódolt karakter mátrixa a(z) '?' karakterhez:");
                kiirMatrix(dekodoltKarakter.getMatrix());
            } else {
                System.out.println("Nincs ilyen karakter a bankban!");
            }
        } catch (IOException e) {
            System.err.println("Hiba a dekódolt szó beolvasásakor: " + e.getMessage());
        }
    }

    private static List<Karakter> karakterBank = new ArrayList<>();

    public static void main(String[] args) {
        try {
            beolvasKaraktereket("bank.txt");
        } catch (IOException e) {
            System.err.println("Hiba a karakterbank beolvasásakor: " + e.getMessage());
            System.exit(1);
        }

        System.out.println("5. feladat: A karakterbankban található karakterek száma: " + karakterBank.size());

        Scanner scanner = new Scanner(System.in);
        char bekertKarakter;
        do {
            System.out.print("6. feladat: Kérem, adjon meg egy angol ábécé nagybetűt: ");
            bekertKarakter = scanner.next().charAt(0);
        } while (!Character.isUpperCase(bekertKarakter));

        Karakter keresettKarakter = keresKaraktert(bekertKarakter);

        if (keresettKarakter != null) {
            System.out.println("7. feladat: A keresett karakter mátrixa:");
            kiirMatrix(keresettKarakter.getMatrix());
        } else {
            System.out.println("Nincs ilyen karakter a bankban!");
        }

        // 8. feladat
        try {
            String kodoltSzo = beolvasKodoltSzot("dekodol.txt");
            System.out.println("\n8. feladat: A dekodol.txt állományban található szó:");
            System.out.println(kodoltSzo);

            // 9. feladat
            dekodolSzot(kodoltSzo);
        } catch (IOException e) {
            System.err.println("Hiba a dekódolt szó beolvasásakor: " + e.getMessage());
        }
    }

    private static void beolvasKaraktereket(String fajlnev) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(fajlnev))) {
            String sor;
            while ((sor = br.readLine()) != null) {
                char karakter = sor.charAt(0);
                char[][] matrix = new char[4][7];

                // Ellenőrizd, hogy van-e még sor a fájlban
                for (int i = 0; i < 4 && (sor = br.readLine()) != null; i++) {
                    matrix[i] = sor.toCharArray();
                }

                karakterBank.add(new Karakter(karakter, matrix));
            }
        }
    }

    private static Karakter keresKaraktert(char karakter) {
        for (Karakter k : karakterBank) {
            if (k.getKarakter() == karakter) {
                return k;
            }
        }
        return null;
    }

    private static void kiirMatrix(char[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }
}
