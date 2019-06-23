import java.lang.reflect.Field;

interface MyInterface{
	public void g();
		
}

class A implements MyInterface{

	@Override
	public void g() {
		System.out.println("A");
	}
	
}

class B implements MyInterface{

	@Override
	public void g() {
		System.out.println("B");
	}
	
}

class C<K>{
	private K m; // передать сюда Б.
	
	public C() {};
	
	public void method() {
		System.out.println(m);
		((MyInterface) m).g();
		
	}
}

class Factory{
	public static String getObj(String filename, String delimiter) {
		int position = 0;
		String objectName;
		String[] newFileName = new String[filename.length()];
		newFileName = filename.split(delimiter);
		objectName = newFileName[newFileName.length-1];
		return objectName;
	}
}


public class Main {

	public static void main(String[] args) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		
		String myFileName = "C:MyInterface:B";
		String delimiter = ":";
		
		C pc = new C();
		Class myClass = pc.getClass();
		Field f = myClass.getDeclaredField("m");
		f.setAccessible(true);
		
		
		
		String m = Factory.getObj(myFileName,delimiter);
		System.out.println(m);
		f.set(pc, m);
		pc.method();
		//создать обьект класса С
		//изменить обьект класса С на последний элемент файла
		//C:MyInterface:B это находиться в получаемом файле
		
	}

}
