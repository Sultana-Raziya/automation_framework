package testdata;

import org.testng.annotations.DataProvider;

import utils.CsvDataReader;

public class LoginDataProvider {

    @DataProvider(name = "loginData")
    public static Object[][] loginData() {

        String path =
                System.getProperty("user.dir")
                + "/src/test/resources/testdata/logindata.csv";

        return CsvDataReader.readLoginData(path);
    }
}
