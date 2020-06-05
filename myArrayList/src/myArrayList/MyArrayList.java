package myArrayList;
/**
 * Features of ArrayList.class
 * 
 * @author Ahrim
 *
 * @param <T>
 */

public class MyArrayList<T> {
	private Object[] arr;
	private int count;
	
	/**
	 * Appends the specified element to the end of this list
	 * 
	 * @param data
	 */
	public void add(T data) {
		Object[] temp = arr;
		arr = new Object[count + 1];
		for (int i = 0; i < count; i++) {
			arr[i] = temp[i];
		}
		arr[count] = data;
		count++;
		temp = null;
	}
	
	/**
	 * Inserts the specified element at the specified position in this list.
	 * Shifts the element currently at that position (if any) and
	 * any subsequent elements to the right.
	 * 
	 * @param index
	 * @param data
	 */
	public void add(int index, T data) {
		rangeCheckForAdd(index);
		Object[] temp = arr;
		arr = new Object[count + 1];
		for (int i = 0; i < index; i++) {
			arr[i] = temp[i];
		}
		arr[index] = data;
		for (int i = index; i < count; i++) {
			arr[i + 1] = temp[i];
		}
		count++;
		temp = null;
	}
	
	/**
	 * Removes the element at the specified position in this list.
	 * 
	 * @param index
	 */
	public void remove(int index) {
		Object[] temp = arr;
		arr = new Object[--count];
		for (int i = 0; i < index; i++) {
			arr[i] = temp[i];
		}
		for (int i = index; i < count; i++) {
			arr[i] = temp[i + 1];
		}
		temp = null;
	}
	
	/**
	 * 
	 * @return the number of elements in this list
	 */
	public int size() {
		return count;
	}
	
	/**
	 * 
	 * @return true if this list contains no elements
	 */
	public boolean isEmpty(){
		return count==0;
	}
	
	/**
     * 
	 * @param index
	 * @return the element at the specified position in this list
	 */
	public T get(int index) {
		rangeCheck(index);
		return (T)arr[index];
	}
	
	/**
	 * Replaces the element at the specified position in this list with
     * the specified element.
     * 
	 * @param index
	 * @param data
	 */
	public void set(int index, T data) {
		rangeCheck(index);
		arr[index] = data;
	}
	
	/**
	 * Removes all of the elements from the list.
	 * The list will be empty after this call returns.
	 */
	public void clear() {
		arr = null;
		count = 0;
	}
	
	/**
	 * Checks if the given index is in range 
	 * If not, throws and appropriate runtime exception.
	 * 
	 * @param index
	 */
    private void rangeCheck(int index) {
        if (index >= count)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }
    
    /**
     * A version of rangeCheck used by add and addAll.
     * @param index
     */
    private void rangeCheckForAdd(int index) {
        if (index > count || index < 0)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }
    
    /**
     * Constructs an IndexOutOfBoundsException detail message.
     * @param index
     * @return Error message
     */
    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+count;
    }

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String result = "[";
		for (int i = 0; i < count; i++) {
			result += arr[i];
			if (i != count - 1) {
				result += ", ";
			}
		}
		result += "]";
		return result;
	}
}
