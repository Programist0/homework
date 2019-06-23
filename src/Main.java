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

class C{
	private MyInterface m;
	
	public C() {};
	
	public void method() {
		m.g();
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
		String m = Factory.getObj(myFileName,delimiter);
		
		C pc = new C();
		Class myClass = pc.getClass();
		Field f = myClass.getDeclaredField("m");
		f.setAccessible(true);
		f.set(pc, m);
		
		pc.method();
		//ñîçäàòü îáüåêò êëàññà Ñ
		//èçìåíèòü îáüåêò êëàññà Ñ íà ïîñëåäíèé ýëåìåíò ôàéëà
		//C:MyInterface:B
		
	}

}
