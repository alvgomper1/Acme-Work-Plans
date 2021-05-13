
package acme.testing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import acme.framework.testing.AbstractTest;

public class ManagerRemoveTaskTest extends AbstractTest {

	@Override
	@BeforeAll
	public void beforeAll() {
		super.beforeAll();

		super.setBaseCamp("http", "localhost", "8090", "/Acme-Planner", "/master/welcome", "?language=en&debug=true");
		super.setAutoPausing(false);

		this.signIn("administrator", "administrator");
		super.click(By.linkText("Administrator"));
		super.submit(By.linkText("Populate DB (initial)"));
		super.click(By.linkText("Administrator"));
		super.submit(By.linkText("Populate DB (samples)"));
		this.signOut();
	}
	/**
	 * La feature que prueba este test es la de eliminar task como manager
	 * <p>
	 * Para ello, iniciamos sesion como manager, accedemos al listado y entramos a la primera task.
	 * Una vez dentro de los detalles de la task, pulsamos el boton de eliminar.
	 * Tras borrar la task, comprobamos que ya no existe si intentamos acceder a ella mediante url, por lo que nos salta el error
	 * esperado de que esa task no existe ya.
	 */
	@Test
	public void deleteTaskPositive() {

		this.signIn("manager1", "manager1");

		//Accedemos al listado de task del manager
		super.driver.get("http://localhost:8090/Acme-Planner/manager/task/list");

		//hacemos click en la 1 tarea del listado
		super.click(By.xpath("//*[@id=\"list\"]/tbody/tr[1]/td[4]"));

		//Obtenemos la url de la tarea que se va a borrar para despues comprobar que no existe
		final String urlTaskBorrada = super.driver.getCurrentUrl();

		//Clickamos el boton de borrar tarea
		super.click(By.xpath("//*[@id=\"form\"]/button[2]"));

		//Comprobamos que la tarea no existe y se devuelve la vista de error
		super.driver.get(urlTaskBorrada);
		Assertions.assertEquals("Unexpected error", super.driver.findElement(By.xpath("/html/body/div[2]/div/h1")).getText());

		//Cerramos sesion
		this.signOut();
	}

	/**
	 * La feature que prueba este test es la de borrar una task como manager, pero esta vez
	 * intentando borrar la task de otro manager
	 * <p>
	 * Lo primero que se hace es loguearnos con un manager, acceder al listado de sus task y obtener la url de una cualquiera.
	 * Una vez tenemos la url de esa task, nos logueamos con otro manager diferente e intentamos accceder mediente url
	 * a la task del otro manager. Como era de esperar, nos salta la vista de error, ya que le manager no está autorizado a
	 * acceder a una task que no le pertenece, por lo que no se podría borrar.
	 */

	@Test
	public void deleteTaskAnotherManager() {

		this.signIn("manager1", "manager1");

		//Accedemos al listado de task del manager
		super.driver.get("http://localhost:8090/Acme-Planner/manager/task/list");

		//hacemos click en la 1 tarea del listado
		super.click(By.xpath("//*[@id=\"list\"]/tbody/tr[1]/td[4]"));

		//Obtenemos la url de la tarea 
		final String urlTask = super.driver.getCurrentUrl();

		//Nos logueamos con otro manager
		this.signOut();
		this.signIn("manager2", "manager2");

		//Intentamos acceder a la task de otro manager para borrarla			
		super.driver.get(urlTask);

		//Comprobamos que no hay acceso, por lo que no podemos borrarla
		Assertions.assertEquals("Unexpected error", super.driver.findElement(By.xpath("/html/body/div[2]/div/h1")).getText());
		this.signOut();

	}

	/**
	 * La feature que prueba este test es la de borrar una task como manager, pero el caso negativo
	 * <p>
	 * Intentamos acceder sin loguearnos como manager a una task cualquiera del manager, y comprobamos que el resultado es una pagina de error como era de esperar
	 * , ya que no está autorizado.
	 */

	@Test
	public void deleteTaskNegative() {

		this.navigateHome();
		//Accedemos a una task cualquiera del manager
		super.driver.get("http://localhost:8090/Acme-Planner/manager/task/show?id=34");

		//Comprobamos que no hay acceso, por lo que no podemos borrarla
		Assertions.assertEquals("Unexpected error", super.driver.findElement(By.xpath("/html/body/div[2]/div/h1")).getText());

	}

	protected void signIn(final String username, final String password) {
		super.navigateHome();
		super.click(By.linkText("Sign in"));
		super.fill(By.id("username"), username);
		super.fill(By.id("password"), password);
		super.click(By.id("remember$proxy"));
		super.submit(By.className("btn-primary"));
	}

	protected void signOut() {
		super.navigateHome();
		super.submit(By.linkText("Sign out"));
	}

	protected void signUp(final String username, final String password, final String name, final String surname, final String email) {
		super.navigateHome();
		super.click(By.linkText("Sign up"));
		super.fill(By.id("username"), username);
		super.fill(By.id("password"), password);
		super.fill(By.id("confirmation"), password);
		super.fill(By.id("identity.name"), name);
		super.fill(By.id("identity.surname"), surname);
		super.fill(By.id("identity.email"), email);
		super.click(By.id("accept$proxy"));
		super.submit(By.className("btn-primary"));
	}

}
