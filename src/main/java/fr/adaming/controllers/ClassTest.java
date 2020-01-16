package fr.adaming.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ClassTest {

	public static void main(String[] args) {
		Date du=new Date();
		
		System.out.println(du);
		
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		String date = simpleDateFormat.format(du);
		System.out.println(date);
	}

}
