package testdata;

import org.testng.annotations.DataProvider;

public class LoginData {

	private LoginData() {
		// prevent object creation
	}

	@DataProvider(name = "invalidLoginData")
	public static Object[][] invalidLoginData() {

		return new Object[][] { { "invalid_user", "wrong_password" }, { "standard_user", "wrong_password" },
				{ "invalid_user", "secret_sauce" }, { "", "" }, { "standard_user", "" } };
	}
}
