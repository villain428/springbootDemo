package com.ccccit.spring.boot.utils;

import java.util.UUID;

public class Test {

	public static void main(String[] args) {
		for(int i = 0; i < 1; i++) {
			System.out.println(UUID.randomUUID().toString().replaceAll("-", "").toUpperCase());
		}
		
		System.out.println(0x80000000);
	}
}
