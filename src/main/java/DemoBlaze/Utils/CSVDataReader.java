//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package DemoBlaze.Utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CSVDataReader {
    public CSVDataReader() {
    }

    public static Iterator<Object[]> readCsv(String fileName) {
        List<Object[]> data = new ArrayList();

        try (
                InputStream is = CSVDataReader.class.getClassLoader().getResourceAsStream(fileName);
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
        ) {
            br.readLine();

            String line;
            while((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                data.add(fields);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data.iterator();
    }
}
