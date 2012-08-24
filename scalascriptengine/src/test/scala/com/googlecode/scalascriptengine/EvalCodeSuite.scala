package com.googlecode.scalascriptengine

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers

/**
 * @author kostantinos.kougios
 *
 * 20 Aug 2012
 */
@RunWith(classOf[JUnitRunner])
class EvalCodeSuite extends FunSuite with ShouldMatchers {

	test("using functions") {
		// create a factory that evaluates code that takes 1 string param and returns an Int.
		// The string param is named s and the evaluated code is s.toInt.
		// In other words, this creates a function:
		// (s:String)=>s.toInt
		val ect = EvalCode[String => Int]("s" :: Nil, "s.toInt")

		// Now create a new instance of this function
		val x = ect.newInstance

		// evaluates f("17") = "17".toInt
		x("17") should be === 17
	}

	test("constructs src code correctly, 2 args") {
		// creates a factory for instantiating a function (Float, Double) => Double
		// as (i1:Float,i2:Double)=>i1+i2
		val ect = EvalCode[(Float, Double) => Double]("i1" :: "i2" :: Nil, "i1 + i2")

		// create a new instance of the function
		val x = ect.newInstance

		// and apply it
		x(12.5f, 2.5) should be === 15.0
	}

	test("return type string") {
		val ect = EvalCode[(Float, Double) => String]("i1" :: "i2" :: Nil, "(i1 + i2).toString")
		val x = ect.newInstance
		x(12.5f, 2.5) should be === "15.0"
	}
}
