package acme.testing.anonymous.shout;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

import acme.components.UtilComponent;
import acme.testing.AcmePlannerTest;

public class AnonymousShoutCreateTest extends AcmePlannerTest{

	
	/**
	 * La feature que prueba este test es la de crear un shout sin spam ni campos vacios
	 * <p>
	 * Para ello accedemos al formulario de creacion de shout.
	 * Cuando estamos en el formulario de crear shout, se introducen datos de un shout sin spam
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/create-shout-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createShoutPositive(final String author, final String info, final String text) {
		
		
		super.clickOnMenu("Anonymous", "Shout!");
		
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("info", info);
		super.fillInputBoxIn("text", text);
		
		final String date = UtilComponent.currentDateToString();
		super.clickOnSubmitButton("Shout!");
		
//		Comprobamos que no hay errores
		Assertions.assertFalse(super.exists(By.className("text-danger")));
		
		super.clickOnMenu("Anonymous", "List of shouts");
		
		super.checkColumnHasValue(0, 0, date);
		super.checkColumnHasValue(0, 1, author);
		super.checkColumnHasValue(0, 2, text);
		super.checkColumnHasValue(0, 3, info);
		
	}
	
	
	
	
	/**
	 * La feature que prueba este test es la de crear un shout con spam
	 * <p>
	 * Para ello accedemos al formulario de creacion de shout.
	 * Cuando estamos en el formulario de crear shout, se introducen datos de un shout con spam
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/create-shout-spam-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void createShoutSpamNegative(final String recordIndex, final String author, final String info, final String text) {
		
		super.clickOnMenu("Anonymous", "Shout!");
		
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("info", info);
		super.fillInputBoxIn("text", text);
		
		super.clickOnSubmitButton("Shout!");
		
		super.checkErrorsExist();

		
	}
	
	
	/**
	 * La feature que prueba este test es la de crear un shout con datos vacios o erroneos
	 * <p>
	 * Para ello accedemos al formulario de creacion de shout.
	 * Cuando estamos en el formulario de crear shout, se introducen datos de un shout con datos erroneos
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/create-shout-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void createShoutNegative(final String recordIndex, final String author, final String info, final String text) {
		
		super.clickOnMenu("Anonymous", "Shout!");
		
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("info", info);
		super.fillInputBoxIn("text", text);
		
		super.clickOnSubmitButton("Shout!");
		
		super.checkErrorsExist();

		
	}

	
	
	
	
}
