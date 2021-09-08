
package acme.testing.manager.task;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmeWorkPlansTest;

public class ManagerRemoveTaskTest extends AcmeWorkPlansTest {

	@Override
	@BeforeAll
	public void beforeAll() {

		super.beforeAll();

	}
	/**
	 * La feature que prueba este test es la de eliminar task como manager
	 * <p>
	 * Para ello, iniciamos sesion como manager, accedemos al listado y entramos a la primera task.
	 * Una vez dentro de los detalles de la task, pulsamos el boton de eliminar.
	 * Tras borrar la task, comprobamos que ya no existe si intentamos acceder a ella mediante url, por lo que nos salta el error
	 * esperado de que esa task no existe ya.
	 */
	@ParameterizedTest
	@Order(40)
	@CsvFileSource(resources = "/manager/task/list-task-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	public void deleteTaskPositive(final int recordIndex, final String title) {

		//Accedemos al listado de task del manager
		super.signIn("manager1", "manager1");
		super.clickOnMenu("Manager", "List tasks");
		this.setAutoPausing(true);
		//hacemos click en la 1a tarea del listado
		super.clickOnListingRecord(0);
		super.checkInputBoxHasValue("title", title);

		//Obtenemos la url de la tarea que se va a borrar para despues comprobar que no existe
		final String urlTaskBorrada = super.driver.getCurrentUrl();

		//Clickamos el boton de borrar tarea
		super.clickOnSubmitButton("Delete");

		//Comprobamos que la tarea no existe y se devuelve la vista de error
		this.driver.get(urlTaskBorrada);

		this.checkPanicExists();
		this.setAutoPausing(false);
		//Cerramos sesion
		super.signOut();
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
	@Order(30)
	public void deleteTaskAnotherManager() {
		this.resetDataBase();
		//Accedemos al listado de task del manager

		super.signIn("manager1", "manager1");
		super.clickOnMenu("Manager", "List tasks");
		this.setAutoPausing(true);
		//hacemos click en la 1 tarea del listado
		super.clickOnListingRecord(0);
		//Obtenemos la url de la tarea 
		final String urlTask = super.driver.getCurrentUrl();

		this.setAutoPausing(false);

		//Nos logueamos con otro manager
		super.signOut();
		super.signIn("manager2", "manager2");

		//Intentamos acceder a la task de otro manager para borrarla			
		this.driver.get(urlTask);

		//Comprobamos que no hay acceso, por lo que no podemos borrarla
		this.checkPanicExists();
		super.signOut();

	}

	/**
	 * La feature que prueba este test es la de borrar una task como manager, pero el caso negativo
	 * <p>
	 * Intentamos acceder sin loguearnos como manager a una task cualquiera del manager, y comprobamos que el resultado es una pagina de error como era de esperar
	 * , ya que no está autorizado.
	 */

	@Test
	@Order(20)
	public void deleteTaskNegative() {

		super.navigateHome();

		//Accedemos a una task cualquiera del manager
		super.driver.get("http://localhost:8090/Acme-Work-Plans/manager/task/show?id=34");

		//Comprobamos que no hay acceso, por lo que no podemos borrarla
		this.checkPanicExists();

	}
	
	//Features comprobadas en el siguiente test: Manager task Delete (Caso positivo)
	//Esta vez intentaremos que el Manager borre las task sin pasar por el listado ni por el método show, 
	//sino que el formulario de borrado se generará a partir de la petición GET al servicio de DELETE
	//("manager/delete/task?id=X") en lugar del de SHOW como en el anterior test. 
	//El CSV contiene identificadores de palabras que existen. Se espera que
	// al intentar eliminar una palabra aparezca un error
	//de panic.
	
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/deletePositive2.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void deletePositive2(final String taskId) {		
		
		super.signIn("manager1", "manager1");
		
		
		final String deleteWordSimplePath=String.format("/manager/task/delete?id=%s", taskId);
		final String urlDeleteWord= super.getBaseUrl() + deleteWordSimplePath;
		this.driver.get(urlDeleteWord);

	
		super.checkPanicExists();
	
		super.signOut();
		this.resetDataBase();
	}

}
