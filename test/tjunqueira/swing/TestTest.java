package tjunqueira.swing;

import org.junit.Test;

public class TestTest {

	@Test
	public void testAdd() {
		int i = 10;
		TestAdd ta = new TestAdd(i);
		ta.add(i);
		
		System.out.println("value: " + ta.getValue());
		
		ta.addValue(ta);
		
		System.out.println("value: " + ta.getValue());
		
		ta.add();
		
		System.out.println("value: " + ta.getValue());
		
		int[] ii = {15};
		
		ta.add(ii);
		
		System.out.println("value: " + ii[0]);
	}

}
