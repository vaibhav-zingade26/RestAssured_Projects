package api.utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {

    @DataProvider(name="Data")
    public Object [][] getAllData() throws IOException {
        String path = System.getProperty("user.dir")+"//testData//UserData.xlsx";
        xlutilities x1 = new xlutilities(path);
        int rownum = x1.getRowCount("Sheet1");
        int colcount =x1.getCellCount("sheet1",1);

        String apiData [][] = new String[rownum][colcount];
        for(int i=1;i<=rownum;i++){
            for(int j=0;j<colcount;j++){
                apiData[i-1][j] = x1.getCellData("Sheet1",i,j);
            }
        }
        return apiData;
    }
    @DataProvider(name="UserNames")
    public Object[] getUserName() throws IOException {
        String path = System.getProperty("user.dir")+"//testData//UserData.xlsx";
        xlutilities x1 = new xlutilities(path);
        int rownum=x1.getRowCount("Sheet1");
        String apiData [] = new String[rownum];
        for(int i=1;i<=rownum;i++){
            apiData[i-1] = x1.getCellData("Sheet1",i,1);
        }

        return apiData;
    }
}
