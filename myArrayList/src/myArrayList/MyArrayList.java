package myArrayList;

public class MyArrayList<T> {
	private Object[] arr;
	private int count;

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

	public void remove() {
		Object[] temp = arr;
		arr = new Object[--count];
		for (int i = 0; i < count; i++) {
			arr[i] = temp[i];
		}
		temp = null;
	}

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

	int size() {
		return count;
	}

	T get(int index) {
		rangeCheck(index);
		return (T) arr[index];
	}

	void set(int index, T data) {
		rangeCheck(index);
		arr[index] = data;
	}

	void clear() {
		arr = null;
		count = 0;
	}
    private void rangeCheck(int index) {
        if (index >= count)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }
    private void rangeCheckForAdd(int index) {
        if (index > count || index < 0)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }
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
