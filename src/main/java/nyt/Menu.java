package nyt;

import java.util.Scanner;

public class Menu {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please Choose An Option ! ");
		System.out.println("1 : Insert Into Authors");
		System.out.println("2 : Insert Into Sections ");
		System.out.println("3 : Insert Into Articles ");
		System.out.println("4 : The Top 5 Sections With The Most Articles ");
		System.out.println("5 : Articles Were Written By Each Author ");
		System.out.println("6 : The Top 10 Articles With The Most Views");
		System.out.println("7 : Articles Were Published Each Month In The Year 2021 ");
		System.out.println("8 : Section Had The Most Articles Published On A Particular Day");

		int option = sc.nextInt();

		switch (option) {
		case 1:
			insertIntoAuthorsTable();
		}
	}
}
