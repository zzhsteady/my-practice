package com.practice.nomal.uuid;

import org.junit.Test;

import java.util.UUID;

public class UUIDTest {

	@Test
	public void test(){
		System.out.println(UUID.randomUUID());
		System.out.println(UUID.fromString("2e2fc548-253b-42f7-a253-ad9b14c8b44f"));
	}
}
