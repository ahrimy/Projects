package myArrayList;
import java.util.ArrayList;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Integer> list = new ArrayList();
		MyArrayList<Integer> myList = new MyArrayList();
		
		list.add(1);
		list.add(2);
		list.add(1,20);
		System.out.println(list+" "+list.size());
		int num = list.get(0);
		System.out.println(num);
		list.set(0, 10);
		System.out.println(list);
		list.remove(0);
		System.out.println(list);
		list.clear();
		System.out.println(list);
		
		System.out.println("//--------------------------");
		myList.add(1);
		myList.add(2);
		myList.add(1,20);
		System.out.println(myList+" "+myList.size());
		num = myList.get(0);
		System.out.println(num);
		myList.set(0, 10);
		System.out.println(myList);
		myList.remove(0);
		System.out.println(myList);
		myList.clear();
		System.out.println(myList);

		
	}

}
