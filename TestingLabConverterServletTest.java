package mockobjectsconverter;

import junit.framework.*;
import com.mockobjects.servlet.*;
import java.text.DecimalFormat;


 
public class TestingLabConverterServletTest extends TestCase {

	// Test: input is a string (boo!) instead of numbers
	public void test_bad_parameter1() throws Exception {
		TestingLabConverterServlet s = new TestingLabConverterServlet();
		MockHttpServletRequest request = 
				new MockHttpServletRequest();
		MockHttpServletResponse response = 
				new MockHttpServletResponse();

		request.setupAddParameter("farenheitTemperature", "boo!");
		response.setExpectedContentType("text/html");
		s.doGet(request,response);
		response.verify();
		assertEquals("Got a NumberFormatException on (boo!)",
						response.getOutputStreamContents());
	}

	// Test: input is a malformed number, e.g. 123.45.67
	public void test_bad_parameter2() throws Exception {
		TestingLabConverterServlet s = new TestingLabConverterServlet();
		MockHttpServletRequest request = 
				new MockHttpServletRequest();
		MockHttpServletResponse response = 
				new MockHttpServletResponse();

		request.setupAddParameter("farenheitTemperature", "123.45.67");
		response.setExpectedContentType("text/html");
		s.doGet(request,response);
		response.verify();
		assertEquals("Got a NumberFormatException on (123.45.67)",
						response.getOutputStreamContents());
	}

	// Test: input is in malformed notation, e.g. 9.73E2
	public void test_bad_parameter3() throws Exception {
		TestingLabConverterServlet s = new TestingLabConverterServlet();
		MockHttpServletRequest request = 
				new MockHttpServletRequest();
		MockHttpServletResponse response = 
				new MockHttpServletResponse();

		request.setupAddParameter("farenheitTemperature", "9.73E2");
		response.setExpectedContentType("text/html");
		s.doGet(request,response);
		response.verify();
		assertEquals("Got a NumberFormatException on (9.73E2)",
						response.getOutputStreamContents());
	}

	// Test: 0 to 212 should return two decimal digits
	public void test_ZeroToBoil() throws Exception {
		for (Integer i=0; i<=212; i+=5) {
			TestingLabConverterServlet s = new TestingLabConverterServlet();
			MockHttpServletRequest request = 
					new MockHttpServletRequest();
			MockHttpServletResponse response = 
					new MockHttpServletResponse();

			request.setupAddParameter("farenheitTemperature", i.toString());
			response.setExpectedContentType("text/html");
			s.doGet(request,response);
			response.verify();
			String austinTemperature = CityTemperatureServiceProvider.lookup("Austin");
	
			String expected = resultStr(i.doubleValue());
			String returned = response.getOutputStreamContents();
			assertEquals(expected, returned);
		}
	}

	public void test_BelowZero() throws Exception {
		Integer i=-100;
		TestingLabConverterServlet s = new TestingLabConverterServlet();
		MockHttpServletRequest request = 
				new MockHttpServletRequest();
		MockHttpServletResponse response = 
				new MockHttpServletResponse();

		request.setupAddParameter("farenheitTemperature", i.toString());
		response.setExpectedContentType("text/html");
		s.doGet(request,response);
		response.verify();
		String austinTemperature = CityTemperatureServiceProvider.lookup("Austin");

		String expected = resultStr(i.doubleValue());
		String returned = response.getOutputStreamContents();
		assertEquals(expected, returned);
	}

	public void test_AboveBoil() throws Exception {
		Integer i=300;
		TestingLabConverterServlet s = new TestingLabConverterServlet();
		MockHttpServletRequest request = 
				new MockHttpServletRequest();
		MockHttpServletResponse response = 
				new MockHttpServletResponse();

		request.setupAddParameter("farenheitTemperature", i.toString());
		response.setExpectedContentType("text/html");
		s.doGet(request,response);
		response.verify();
		String austinTemperature = CityTemperatureServiceProvider.lookup("Austin");

		String expected = resultStr(i.doubleValue());
		String returned = response.getOutputStreamContents();

		assertEquals(expected, returned);
	}


	public void test_ParamCase() throws Exception {
		Integer i=212;
		TestingLabConverterServlet s = new TestingLabConverterServlet();
		MockHttpServletRequest request = 
				new MockHttpServletRequest();
		MockHttpServletResponse response = 
				new MockHttpServletResponse();

		request.setupAddParameter("farenheittemperature", i.toString());
		response.setExpectedContentType("text/html");
		s.doGet(request,response);
		response.verify();
		String austinTemperature = CityTemperatureServiceProvider.lookup("Austin");

		String expected = resultStr(i.doubleValue());
		String returned = response.getOutputStreamContents();

		assertEquals(expected, returned);
	}

	
	// Return the string as if the servlet returns on the given parameter.
	private String resultStr(Double farTempDouble) {
		DecimalFormat df0 = new DecimalFormat("#");
		String farTemp = df0.format(farTempDouble);

		Double celTempDouble = 100.0*(farTempDouble - 32.0)/180.0;
		String celTemp = null;
		if (farTempDouble.compareTo(new Double(0))>=0 && farTempDouble.compareTo(new Double(212))<=0) {
			DecimalFormat df2 = new DecimalFormat("#.##");
			celTemp = df2.format(celTempDouble);
		} else {
			DecimalFormat df1 = new DecimalFormat("#.#");
			celTemp = df1.format(celTempDouble);
		}

		String result = "<html><head><title>Temperature Converter Result</title>"
				+ "</head><body><h2>" + farTemp + " Farenheit = " + celTemp + " Celsius "
				+ "</h2>\n";
		String austinTemperature = CityTemperatureServiceProvider.lookup("Austin");
		result += "<p><h3>The temperature in Austin is " + austinTemperature
							+ " degrees Farenheit</h3>\n</body></html>\n";

		return result;
	}
}
