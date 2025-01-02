package com.tka;

import org.testng.Assert;
import org.testng.annotations.Test;

public class FirstTestNG {

	@Test

	void TestAdd() {
		A a = new A();
		int add = a.Add(10, 20);
		Assert.assertEquals(add, 30);

	}

	@Test
	void testmult() {

		A a = new A();
		int mult = a.mult(10, 1);

		Assert.assertEquals(mult, 10);

	}

	@Test
	void text() throws Exception {

		AAA a = new AAA();
		String tetNGAutomation = a.tetNGAutomation();
		Assert.assertEquals(tetNGAutomation, "Welcome Back");

	}

}
