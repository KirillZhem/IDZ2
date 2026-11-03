package org.example;

import java.util.ArrayList;

public class StringProcessor {

    public String repeat(String s, int n) {
        if (n < 0) {
            throw new IllegalArgumentException("N не может быть отрицательным");
        }
        String result = "";
        for (int i = 0; i < n; i++) {
            result = result + s;
        }
        return result;
    }

    public int countOccurrences(String text, String part) {
        if (part == null || part.isEmpty()) {
            throw new IllegalArgumentException("Вторая строка пустая или null");
        }
        int count = 0;
        int pos = text.indexOf(part);
        while (pos != -1) {
            count++;
            pos = text.indexOf(part, pos + part.length());
        }
        return count;
    }

    public String replaceDigits(String s) {
        String result = "";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '1') {
                result = result + "один";
            } else if (c == '2') {
                result = result + "два";
            } else if (c == '3') {
                result = result + "три";
            } else {
                result = result + c;
            }
        }
        return result;
    }

    public void removeEverySecond(StringBuilder sb) {
        for (int i = 1; i < sb.length(); i++) {
            sb.deleteCharAt(i);
        }
    }

    public String reverseWords(String s) {
        ArrayList<String> words = new ArrayList<>();
        StringBuilder word = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                if (!word.isEmpty()) {
                    words.add(word.toString());
                    word = new StringBuilder();
                }
            } else {
                word.append(c);
            }
        }
        if (!word.isEmpty()) {
            words.add(word.toString());
        }

        StringBuilder result = new StringBuilder();
        int next = words.size() - 1;
        int pos = 0;
        while (pos < s.length()) {
            if (s.charAt(pos) == ' ') {
                result.append(' ');
                pos++;
            } else {
                result.append(words.get(next));
                next--;
                while (pos < s.length() && s.charAt(pos) != ' ') {
                    pos++;
                }
            }
        }
        return result.toString();
    }
    public String replaceHex(String s) {
        StringBuilder result = new StringBuilder();
        int i = 0;
        while (i < s.length()) {
            if (i + 10 <= s.length() && s.charAt(i) == '0' && s.charAt(i + 1) == 'x'
                    && isHex(s.substring(i + 2, i + 10))) {
                String hex = s.substring(i + 2, i + 10);
                long number = Long.parseLong(hex, 16);
                result.append(number);
                i = i + 10;
            } else {
                result.append(s.charAt(i));
                i++;
            }
        }
        return result.toString();
    }

    private boolean isHex(String str) {
        for (int k = 0; k < str.length(); k++) {
            char c = str.charAt(k);
            boolean ok = (c >= '0' && c <= '9') || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F');
            if (!ok) {
                return false;
            }
        }
        return true;
    }
}