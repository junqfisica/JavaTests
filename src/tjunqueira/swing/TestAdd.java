package tjunqueira.swing;

public class TestAdd {
	
	private int value;
	
	public TestAdd(int value) {
		this.value = value;
	}
	
	public void add(int i){
		i++;
	}
	
	public void add(){
		value++;
	}
	
	public void add(int[] i) {
		i[0]++;
	}
	
	public void addValue(TestAdd obj) {
		obj.value++;
	}
	
	public int getValue() {
		return value;
	}
	
}
