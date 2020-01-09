package com.company;

import javax.swing.*;

import java.awt.*;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.util.ArrayList;

import java.util.List;

import java.util.Scanner;

import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws Exception {

        Scanner scn = new Scanner(System.in);
        String cnf = scn.next();
        Boolean one;
        int counter;
        int counter2;
        int counterpbaste = 0;
        int counterpbaz = 0;
        int litral_mosbat = 0;
        String[] c = new String[0];
        ArrayList<Character> arrayList = new ArrayList<>();
        ArrayList<Character> arrayList1 = new ArrayList<>();
        String[] clause = cnf.split("and");
        for (int i = 0; i < clause.length; i++) {
            litral_mosbat = 0;
            for (int j = 0; j < clause[i].length(); j++) {
                if (Character.isUpperCase(clause[i].charAt(j))) {
                    if (clause[i].charAt(j-1) != '~') {
                        litral_mosbat++;
                    }
                }
            }
            if (litral_mosbat > 1){
                throw new Exception("It has mor than 1 positive literal");

            }

        }

        for (int i = 0; i < clause.length; i++) {

            if (clause[i].contains("and")) {

                throw new Exception("Its not in CNF form");
            }
        }

        for (int i = 0; i < cnf.length() - 1; i++) {

            if (cnf.charAt(i) == ')') {

                if (cnf.charAt(i + 1) != 'a') {

                    throw new Exception("Its not in CNF form");
                }
            }
        }
        for (int i = 0; i < cnf.length(); i++) {

            if (cnf.charAt(i) == '(') {

                counterpbaz++;

            } else if (cnf.charAt(i) == ')') {

                counterpbaste++;
            }
        }

        if (counterpbaz != counterpbaste) {

            throw new Exception("Its not a formula");
        }

        for (int i = 0; i < cnf.length(); i++) {

            if (Character.isUpperCase(cnf.charAt(i))) {

                if (cnf.charAt(i + 1) != 'o' && cnf.charAt(i + 1) != ')') {

                    throw new Exception("Syntax error");

                }
            }
        }

        for (int i = 0; i < clause.length; i++) {

            clause[i] = clause[i].substring(1, clause[i].length() - 1);
        }
//        System.out.println(Arrays.toString(clause));

        for (int i = 0; i < clause.length; i++) {

            one = true;

            for (int j = 0; j < clause[i].length(); j++) {

                if (clause[i].charAt(j) == '~') {

                    one = false;
                }
            }

            if (one) {

                for (int j = 0; j < clause[i].length(); j++) {

                    if (clause[i].charAt(j) != 'o' && clause[i].charAt(j) != 'r')

                        arrayList.add(clause[i].charAt(j));
                }
            }
        }

        while (true) {

            List<Character> ezafeArray =

                    arrayList.stream().distinct().collect(Collectors.toList());

            int ezafe = ezafeArray.size();
//            System.out.println( ezafe +" "+arrayList.size());

            for (int i = 0; i < clause.length; i++) {

                arrayList1.clear();

                counter = 0;

                counter2 = 0;

                for (int j = 0; j < clause[i].length(); j++) {

                    if (clause[i].charAt(j) == '~') {

                        arrayList1.add(clause[i].charAt(j + 1));
                    }
                }

                for (int j = 0; j < arrayList1.size(); j++) {

                    for (int k = 0; k < arrayList.size(); k++) {

                        if (arrayList1.get(j).equals(arrayList.get(k))) {

                            counter++;

                            break;

                        }

                    }
                }

                if (counter == arrayList1.size() && counter != 0) {

                    if (clause[i].charAt(0) != '~') {

                        arrayList.add(clause[i].charAt(0));

                        counter2++;

                    }

                    for (int j = 1; j < clause[i].length(); j++) {

                        if (clause[i].charAt(j) != 'o' && clause[i].charAt(j) != 'r'

                                && clause[i].charAt(j) != '~'

                                && clause[i].charAt(j - 1) != '~') {

                            counter2++;

                            arrayList.add(clause[i].charAt(j));

                        }

                    }

                    if (counter2 == 0) {
//
//          System.out.println("ERROR");

                        int error = i + 1;

                        throw new Exception("صدق پذیر نیست به دلیل بند  " + error);

                    }

                }


//
//  System.out.println(counter + "  " + counter2 + " " + arrayList1);
            }

            List<Character> ezafeArray2 =

                    arrayList.stream().distinct().collect(Collectors.toList());

            int ezafe2 = ezafeArray2.size();

            if (ezafe - ezafe2 == 0) {

                System.out.println("این فرمول صدق پذیر است");

                break;


            }

        }


        List<Character> array_B_tekrar = arrayList.stream().distinct().collect(Collectors.toList());

        System.out.println("آن اتم هایی که علامت دار شده اند " + array_B_tekrar);


    }
}
