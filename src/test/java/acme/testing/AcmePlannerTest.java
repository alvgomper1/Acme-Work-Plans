package acme.testing;

import org.hibernate.internal.util.StringHelper;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebElement;

public abstract class AcmePlannerTest extends AcmeTest {

	// Internal state ---------------------------------------------------------

	// Lifecycle management ---------------------------------------------------

	@Override
	@BeforeAll
  public void beforeAll(){
	  super.setHeadless(true);
		super.beforeAll();

		super.setBaseCamp("http", "localhost", "8090", "/Acme-Planner", "/master/welcome", "?language=en&debug=true");
		super.setAutoPausing(false);
		
		this.navigateHome();
		this.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "Populate DB (initial)");
		super.clickOnMenu("Administrator", "Populate DB (samples)");
		super.checkAlertExists(true);		
		this.signOut();
	}
	
	
	
	// Business methods -------------------------------------------------------
	protected Integer getEntityIdFromRow(final int recordIndex) {
		
		final WebElement row;
		final String entityId;
		
		row = super.getRowAsWebElement(recordIndex);
		entityId= row.getAttribute("data-item-id");
		return Integer.valueOf(entityId);
	}
	
	protected void checkEntityIdFromRowHasValue(final Integer recordIndex, final Integer id) {
		assert recordIndex >= 0;
		Integer entityId;	
		entityId= this.getEntityIdFromRow(recordIndex);
		assert entityId.equals(id): String.format("Entity id from the row has value %d, does not match the given value: %d ", entityId,id);
	}
	
	protected void signIn(final String username, final String password) {
		assert !StringHelper.isBlank(username);
		assert !StringHelper.isBlank(password);
		
		super.navigateHome();
		super.clickOnMenu("Sign in", null);
		super.fillInputBoxIn("username", username);
		super.fillInputBoxIn("password", password);
		super.fillInputBoxIn("remember", "true");
		super.clickOnSubmitButton("Sign in");
		super.checkSimplePath("/master/welcome");
		super.checkLinkExists("Account");
	}

	protected void signOut() {
		super.navigateHome();
		super.clickOnMenu("Sign out", null);
		super.checkSimplePath("/master/welcome");
	}

	protected void signUp(final String username, final String password, final String name, final String surname, final String email, final String phone) {
		assert !StringHelper.isBlank(username);
		assert !StringHelper.isBlank(password);
		assert !StringHelper.isBlank(name);
		assert !StringHelper.isBlank(surname);
		assert !StringHelper.isBlank(email);


		super.navigateHome();
		super.clickOnMenu("Sign up", null);	
		super.fillInputBoxIn("username", username);
		super.fillInputBoxIn("password", password);
		super.fillInputBoxIn("confirmation", password);
		super.fillInputBoxIn("identity.name", name);
		super.fillInputBoxIn("identity.surname", surname);
		super.fillInputBoxIn("identity.email", email);
		super.fillInputBoxIn("accept", "true");
		super.clickOnSubmitButton("Sign up");
		super.checkSimplePath("/master/welcome");
	}
	
	public void resetDataBase() {
		
		this.navigateHome();
		this.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Populate DB (samples)");
		super.checkAlertExists(true);		
		this.signOut();
	}
	
	public void clearDataBase() {
		this.navigateHome();
		this.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator", "Populate DB (initial)");
		super.checkAlertExists(true);		
		this.signOut();
	}

}
