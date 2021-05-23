
package acme.testing.anonymous.shout;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import acme.testing.AcmePlannerTest;

public class AnonymousShoutListTest extends AcmePlannerTest {

	@Override
	@BeforeAll
	public void beforeAll() {
		super.beforeAll();


	}

	/**
	 * La feature que prueba este test es la de listar tareas siendo un usuario anonymous, es decir, sin autenticar
	 * <p>
	 * Este metodo accede al menu desplegable de anonymous y entra al listado
	 * shouts. Debe comrobar que el listado se muestra y no está vacio, para ello,
	 * se comprueba que en la tabla hay filas con la etiqueta
	 * 'td' y la clase "control.sorting_1" o que no existe la clase "dataTables_empty", ya que esto significa que
	 * no hay shouts disponibles en la lista.
	 * Tambien se hace la comprobacion de que la variable momento del shout cumple con la restriccion de mostrar
	 * solo las mas recientes, es decir, con antiguedad maxima de 1 mes
	 */

	@Test
	public void anonymousShoutListPositive2() {

		//Accedemos al listado de shouts de anonymous
		super.navigateHome();
		super.clickAndGo(By.linkText("Anonymous"));

		super.clickOnLink("List of shouts");

		//Se comprueba que se muestra el listado con o sin datos
		Assertions.assertTrue(super.exists(By.cssSelector(".control.sorting_1")) || super.exists(By.className("dataTables_empty")));

		//Si hay datos en el listado, recorremos las fechas
		if (super.exists(By.cssSelector(".control.sorting_1"))) {
			final List<WebElement> elements = super.driver.findElements(By.cssSelector(".control.sorting_1"));

			for (int i = 1; i <= elements.size(); i++) {
				final String fecha = super.driver.findElement(By.xpath("//*[@id=\"list\"]/tbody/tr[" + i + "]/td[2]")).getText();
				final String[] fechaLocalDate = fecha.split(" ");
				final String[] fechaLocalDateSplit = fechaLocalDate[0].split("/");
				final String anyo = fechaLocalDateSplit[0];
				final String mes = fechaLocalDateSplit[1];
				final String dia = fechaLocalDateSplit[2];

				//Comprobacion de la restriccion de la variable momento
				Assertions.assertTrue(LocalDate.of(Integer.valueOf(anyo), Integer.valueOf(mes), Integer.valueOf(dia)).isAfter(LocalDate.now().minusMonths(1)));
			}
		}

	}

	/**
	 * La feature que prueba este test es la de listar shouts como anonymous, pero el caso negativo, que seria intentando acceder al listado con un usuario logueado con
	 * el rol de Administrador
	 * <p>
	 * Una vez loguado como administrator, lo primero es comprobar que no existe la seccion de anonymous. Despues intentamos acceder mediante url
	 * al listado de shouts", y comprobamos que el resultado es una pagina de error, ya que no está autorizado.
	 */

	@Test
	public void anonymousShoutListNegative() {

		super.signIn("administrator", "administrator");
 		super.driver.get("http://localhost:8090/Acme-Planner/anonymous/shout/list");
 		this.checkPanicExists();
		this.signOut();

	}
	
	
	
	/**
	 * La feature que prueba este test es la de listar tareas siendo un usuario anonymous, es decir, sin autenticar
	 * <p>
	 * Este metodo accede al menu desplegable de anonymous y entra al listado
	 * shouts. Debe comrobar que el listado se muestra igual que nuestro archivo csv de entrada, en el que introducimos
	 *  los registros del sample data que se deben mostrar, es decir, los que tienen un maximo de
	 *  30 dias desde que se publicaron.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/list-recent-shouts-june.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void anonymousShoutListPositiveJune(final int recordIndex,final String moment,final String author,final String text) {		
		if (LocalDate.now().isBefore(LocalDate.of(2021, 6, 30))&& LocalDate.now().isAfter(LocalDate.of(2021, 5, 31))) {
		
		super.clickOnMenu("Anonymous", "List of shouts");
		
		super.checkColumnHasValue(recordIndex, 0, moment);
		super.checkColumnHasValue(recordIndex, 1, author);
		super.checkColumnHasValue(recordIndex, 2, text);
		}
	}
	
	/**
	 * La feature que prueba este test es la de listar tareas siendo un usuario anonymous, es decir, sin autenticar
	 * <p>
	 * Este metodo accede al menu desplegable de anonymous y entra al listado
	 * shouts. Debe comrobar que el listado se muestra igual que nuestro archivo csv de entrada, en el que introducimos
	 *  los registros del sample data que se deben mostrar, es decir, los que tienen un maximo de
	 *  30 dias desde que se publicaron.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/list-recent-shouts-july.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(30)
	public void anonymousShoutListPositiveJuly(final int recordIndex,final String moment,final String author,final String text) {		
	 
		if (LocalDate.now().isBefore(LocalDate.of(2021, 7, 31)) && LocalDate.now().isAfter(LocalDate.of(2021, 6, 30))) {
		super.clickOnMenu("Anonymous", "List of shouts");
		
		super.checkColumnHasValue(recordIndex, 0, moment);
		super.checkColumnHasValue(recordIndex, 1, author);
		super.checkColumnHasValue(recordIndex, 2, text);
		}
	}

	

}
