package com.example;

import java.util.Arrays;

public class Karakter {
    private char karakter;
    private char[][] matrix;

    public Karakter(char karakter, char[][] matrix) {
        this.karakter = karakter;
        this.matrix = matrix;
    }

    public Karakter(String karakterSor) {
        this.karakter = karakterSor.charAt(0);
        this.matrix = new char[4][7];

        int index = 1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 7; j++) {
                if (index < karakterSor.length()) {
                    this.matrix[i][j] = karakterSor.charAt(index++);
                } else {
                    this.matrix[i][j] = ' ';
                }
            }
        }
    }

    public boolean Felismer(Karakter felismerendo) {
        return Arrays.deepEquals(matrix, felismerendo.matrix);
    }

    public char getKarakter() {
        return karakter;
    }

    public char[][] getMatrix() {
        return matrix;
    }
}
