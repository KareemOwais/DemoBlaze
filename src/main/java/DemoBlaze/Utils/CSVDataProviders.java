package DemoBlaze.Utils;

import org.testng.annotations.DataProvider;

import java.util.Iterator;

public class CSVDataProviders {

    @DataProvider(name = "DataProvTest1")
    public static Iterator<Object[]> getLoginData() {
        return CSVDataReader.readCsv("test1.csv");
    }

    @DataProvider(name = "DataProvTest2")
    public static Iterator<Object[]> getLoginData2() {
        return CSVDataReader.readCsv("test2.csv");
    }

    @DataProvider(name = "DataProvTest3")
    public static Iterator<Object[]> getLoginData3() {
        return CSVDataReader.readCsv("test3.csv");
    }

    @DataProvider(name = "DataProvTest4")
    public static Iterator<Object[]> getLoginData4() {
        return CSVDataReader.readCsv("test4.csv");
    }

    @DataProvider(name = "DataProvTest5")
    public static Iterator<Object[]> getLoginData5() {
        return CSVDataReader.readCsv("test5.csv");
    }
}
