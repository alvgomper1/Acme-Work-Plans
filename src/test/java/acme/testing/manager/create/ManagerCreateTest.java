
package acme.testing.manager.create;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

import acme.testing.AcmePlannerTest;

public class ManagerCreateTest extends AcmePlannerTest {

	@Override
	@BeforeAll
	public void beforeAll() {
		super.beforeAll();

		 
 
	}
	/**
	 * La feature que prueba este test es la de convertirse en manager
	 * <p>
	 * Lo primero que se hace es registrar a un usuario nuevo y iniciar sesion para tenerlo autenticado.
	 * Este metodo accede al menu desplegable de account, entra a "convertirse en manager" y confirma que
	 * quiere convertirse pulsando el boton de "registrar".
	 * Una vez convertido en manager, se comprueba que ya existe en el menu desplegable la seccion "Manager", además de
	 * que ya no existe el link de "convertirse en manager".
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/create/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void becomeManagerPositive(final String username, final String password,final String name, final String surname, final String email, final String phone) {


	 
		super.signUp(username, password, name, surname, email, phone);
		super.signIn(username, password);
		assert super.exists(By.linkText("Account"));

		super.clickAndGo(By.linkText("Account"));
		assert super.exists(By.linkText("Become a manager"));

		super.clickAndGo(By.linkText("Become a manager"));
		super.clickAndGo(By.xpath("//*[@id=\"form\"]/button[1]"));
		assert super.exists(By.linkText("Manager"));

		assert !super.exists(By.linkText("Become a manager"));
		this.signOut();
	}

	/**
	 * La feature que prueba este test es la de convertirse en manager, pero el caso negativo, que seria intentando convertir a
	 * un autenticado en manager cuando ya se habia convertido previamente
	 * <p>
	 * Lo primero es comprobar que no existe el link de "convertirse en manager". Despues intentamos acceder mediante url
	 * al formulario de "convertirse en manager", y comprobamos que el resultado es una pagina de error, ya que no está autorizado.
	 */

	@ParameterizedTest
	@CsvFileSource(resources = "/manager/create/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void becomeManagerNegative(final String username, final String password) {

		

		super.signIn(username, password);
		assert super.exists(By.linkText("Manager"));
		assert !super.exists(By.linkText("Become a manager"));
		super.driver.get("http://localhost:8090/Acme-Planner/authenticated/manager/create");
		this.checkPanicExists();
		this.signOut();

	}

 

}
