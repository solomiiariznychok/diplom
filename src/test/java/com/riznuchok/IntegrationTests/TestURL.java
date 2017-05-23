package com.riznuchok.IntegrationTests;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.apache.http.HttpStatus;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.runner.RunWith;

import static com.jayway.restassured.RestAssured.expect;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

/**
 * @author Solomiia Riznychok
 * @version 1.0
 * @since 2016-06-07
 * The TestURL class was created for testing URL.
 */

@RunWith(DataProviderRunner.class)
public class TestURL {

    /**
     * The WebServiceRule ruleWS rule generate base URI, base path, base port.
     * URI generated at the beginning of class.
     *
     * @see WebServiceRule
     */

    @ClassRule
    public static WebServiceRule ruleWS = new WebServiceRule();

    /**
     * The ErrorCollector errors rule allows execution of a test to continue after the first problem is found .
     */

    @Rule
    public ErrorCollector errors = new ErrorCollector();

    /**
     * The verifyBaseURL(String) was created for verify base URL
     * The verifyBaseURL(String) test use data provider "emptyIdString".
     *
     * @param testData represent " " (empty String) value
     *Steps to reproduce
     *using HTTP GET method and " " (empty String) as id values verify that status code is equals '200' (HttpStatus.SC_OK)
     */

    @Test
    @UseDataProvider("emptyIdString")
    public void verifyBaseURL(String testData) {
        //given().when().then().statusCode(200);
        expect().statusCode(200).when().then().get();
        //expect().statusCode(404).when().then().get(testData);
    }

    /**
     * The verifyAlphabeticalSymbolsForID(String) was created for verify correct id values for URL
     * The verifyAlphabeticalSymbolsForID(String) test use data provider "alphabeticalSymbols".
     *
     * @param testData represent alhabetical symbols ()
     *Steps to reproduce
     *using HTTP GET method and alhabetical symbols as id values verify that status code is equals '400' (HttpStatus.SC_NOT_FOUND)
     */

    @Test
    @UseDataProvider("alphabeticalSymbols")
    public void verifyAlphabeticalSymbolsForID(String testData) {
        expect().statusCode(HttpStatus.SC_NOT_FOUND).when().get("http://localhost:8090/api/formula" + "/" + testData);
    }

    /**
     * The verifyCyrillicSymbolsForID(String) was created for verify correct id values for URL
     * The verifyCyrillicSymbolsForID(String) test use data provider "cyrillicSymbols".
     *
     * @param testData represent cyrillic symbols from data provider
     *Steps to reproduce
     *using HTTP GET method and cyrillic symbols as id values verify that status code is equals '400' (HttpStatus.SC_NOT_FOUND)
     */

    // @Test
    @UseDataProvider("cyrillicSymbols")
    public void verifyCyrillicSymbolsForID(String testData) {
        expect().statusCode(HttpStatus.SC_NOT_FOUND).when().get(testData);
    }

    /**
     * The verifyINumericValuesForID(String) was created for verify correct id values for URL
     * The verifysCyrillicSymbolsForID(String) test use data provider "cyrillicSymbols".
     *
     * @param testData represent cyrillic symbols from data provider
     *Steps to reproduce
     *using HTTP GET method and cyrillic symbols as id values verify that status code is equals '400' (HttpStatus.SC_NOT_FOUND)
     */

    // @Test
   // @UseDataProvider("numberSymbols")
    public void verifyINumericValuesForID(String testData) {
        expect().statusCode(HttpStatus.SC_NOT_FOUND).when().then().get(testData);
    }

    /**
     * The verifySpecialCharactersForID(String) was created for verify correct id values for URL
     * The verifySpecialCharactersForID(String) test use data provider "specialCharacters".
     *
     * @param testData represent special characters from data provider.
     *Steps to reproduce
     *using HTTP GET method and special characters as id values verify that status code is equals '400' (HttpStatus.SC_NOT_FOUND)
     */

    //@Test
    @UseDataProvider("specialCharacters")
    public void verifySpecialCharactersForID(String testData) {
        expect().statusCode(HttpStatus.SC_NOT_FOUND).when().then().get(testData);
    }

    /**
     * The dataForURL() data provider storage test data, allows to run verifyValidID(String) test over and over again using different values.
     *
     * @return correct id values
     */


    /**
     * The emptyIdString() data provider storage test data, allows to run verifyBaseURL(String) test over and over again using different values.
     *
     * @return correct empty String
     */

    @DataProvider
    public static Object[][] emptyIdString() {
        return new Object[][]{
                {""}
        };
    }

    /**
     * The alphabeticalSymbols() data provider storage test data, allows to run verifyBaseURL(String) test over and over again using different values.
     *
     * @return alhabetical symbols,
     */

    @DataProvider
    public static Object[][] alphabeticalSymbols() {
        return new Object[][]{
                {"a"}, {"b"}, {"c"}, {"d"}, {"e"}, {"f"}, {"g"}, {"h"}, {"i"}, {"j"}, {"k"}, {"l"}, {"m"}, {"n"}, {"o"},
                {"p"}, {"q"}, {"r"}, {"s"}, {"t"}, {"u"}, {"v"}, {"w"}, {"x"}, {"y"}, {"z"}
        };
    }

    /**
     * The cyrillicSymbols() data provider storage test data, allows to run verifysCyrillicSymbolsForID(String) test over and over again using different values.
     *
     * @return cyrillic symbols
     */

    @DataProvider
    public static Object[][] cyrillicSymbols() {
        return new Object[][]{
                {"а"}, {"б"}, {"в"}, {"г"}, {"д"}, {"е"}, {"є"}, {"ж"}, {"з"}, {"и"}, {"і"}, {"ї"}, {"й"}, {"к"}, {"л"}, {"м"},
                {"н"}, {"о"}, {"п"}, {"р"}, {"с"}, {"т"}, {"у"}, {"ф"}, {"х"}, {"ц"}, {"ч"}, {"ш"}, {"щ"}, {"ь"}, {"ю"}, {"я"},
        };
    }

    /**
     * The numberSymbols() data provider storage test data, allows to run verifyINumericValuesForID(String) test over and over again using different values.
     *
     * @return number symbols
     */

    @DataProvider
    public static Object[][] numberSymbols() {
        return new Object[][]{
                {"0"}, {"1"}, {"2"}, {"3"}, {"4"}, {"5"}, {"6"}, {"7"}, {"8"}, {"9"}
        };
    }

    /**
     * The specialCharacters() data provider storage test data, allows to run verifySpecialCharactersForID(String) test over and over again using different values.
     *
     * @return special characters symbols
     */

    @DataProvider
    public static Object[][] specialCharacters() {
        return new Object[][]{
                {"!"}, {"."}, {"="}, {"-"}, {"*"}, {"+"}, {"("}, {")"}, {"{"}, {"}"}, {"@"}, {"$"},
                {"%"}, {"^"}, {":"}, {"&"}, {"|"}, {"<"}, {">"}, {";"}
        };
    }
}