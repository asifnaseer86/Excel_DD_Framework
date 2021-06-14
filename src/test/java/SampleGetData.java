import java.io.IOException;
import java.util.ArrayList;


public class SampleGetData {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		
		dataDriven d = new dataDriven();
		ArrayList<String> data = d.getData("Purchase", "Testcases", "TestData");
				
		data.stream().forEach(s->System.out.println(s));
				
		
	}

}
