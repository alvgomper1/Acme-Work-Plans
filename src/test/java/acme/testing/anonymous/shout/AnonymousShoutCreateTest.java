package acme.testing.anonymous.shout;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

import acme.components.UtilComponent;
import acme.testing.AcmePlannerTest;

public class AnonymousShoutCreateTest extends AcmePlannerTest{

	
	
	// Lifecycle management ---------------------------------------------------
	
	
	@BeforeAll
	public void clearShouts() {
		super.clearDataBase();
	}
	
	/**
	 * El siguiente método simplemente es necesario para que funcionen los demás, no es un test como tal. 
	 * <p>
	 * Accedemos como administrador y creamos las palabras de spam para poder realizar los siguientes test
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/spam/populate-spam-words.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void populateSpamWords(final String word) {
		
		super.signIn("administrator", "administrator");

		super.clickOnMenu("Administrator", "Spam Module");	
		super.clickAndWait(By.xpath("//*[@id='form']/button[3]")); //List words button
		super.clickAndWait(By.xpath("//*[@id='form']/button")); //Create word button

		super.checkSimplePath("/administrator/word/create");
		super.fillInputBoxIn("value", word);
		super.clickOnSubmitButton("Create");
		
		super.signOut();
	}
	
	//Test cases --------------------------------------------------------------

		
	/**
	 * La feature que prueba este test es la de crear como anonimo un shout sin spam de manera correcta
	 * <p>
	 * Para ello accedemos al formulario de creacion de shout como anonimo y se introducen valores correctos
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/create-shout-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
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
		
		//Cuando se crea un nuevo shout, aparece siempre en la primera fila
		super.checkColumnHasValue(0, 0, date);
		super.checkColumnHasValue(0, 1, author);
		super.checkColumnHasValue(0, 2, text);
		super.checkColumnHasValue(0, 3, info);
		
	}
	
	
	
	
	
	/**
	 * La feature que prueba este test es la de intentar crear como anonimo un shout con spam de manera incorrecta
	 * <p>
	 * Para ello accedemos al formulario de creacion de shout como anonimo y se introducen valores de spam en el titulo, info y autor
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/create-shout-spam-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(30)
	public void createShoutSpamNegative(final String recordIndex, final String author, final String info, final String text) {
		
		super.clickOnMenu("Anonymous", "Shout!");
		
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("info", info);
		super.fillInputBoxIn("text", text);
		
		super.clickOnSubmitButton("Shout!");
		
		super.checkErrorsExist();

		
	}
	
	
	/**
	 * La feature que prueba este test es la de intentar crear como anonimo un shout de manera erronea
	 * <p>
	 * Para ello accedemos al formulario de creacion de shout como anonimo y se introducen datos vacios en info y autor
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/create-shout-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(40)
	public void createShoutNegative(final String recordIndex, final String author, final String info, final String text) {
		
		super.clickOnMenu("Anonymous", "Shout!");
		
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("info", info);
		super.fillInputBoxIn("text", text);
		
		super.clickOnSubmitButton("Shout!");
		
		super.checkErrorsExist();

		
	}

	
	
	
	
}
