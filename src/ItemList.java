import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ItemList<T extends Matchable<K>, K> implements Serializable {
	private static final long serialVersionUID = 1L;
	protected List<T> elements = new LinkedList<T>();

	public T search(K value) {
		for (T element : elements) {
			if (element.matches(value)) {
				return element;
			}
		}
		return null;
	}

	public boolean removeItem(K value) {
		T element = search(value);
		if (element == null) {
			return false;
		} else {
			return elements.remove(element);
		}
	}

	public boolean insertItem(T item) {
		elements.add(item);
		return true;
	}

	public Iterator<T> getItems() {
		return elements.iterator();
	}

	/*
	 * Supports serialization
	 * 
	 * @param output the stream to be written to
	 */
	private void writeObject(java.io.ObjectOutputStream output) {
		try {
			output.defaultWriteObject();
			output.writeObject(elements);
		} catch (IOException ioe) {
			System.out.println(ioe);
		}
	}

	/*
	 * Supports serialization
	 * 
	 * @param input the stream to be read from
	 */
	@SuppressWarnings("unchecked")
	private void readObject(java.io.ObjectInputStream input) {
		try {
			if (elements != null) {
				return;
			} else {
				input.defaultReadObject();
				if (elements == null) {
					elements = (List<T>) input.readObject();
				} else {
					input.readObject();
				}
			}
		} catch (IOException ioe) {
			System.out.println("in List<T> readObject \n" + ioe);
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
	}

	public String toString() {
		return elements.toString();
	}
}
