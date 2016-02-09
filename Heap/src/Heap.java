import java.util.Scanner;

public class Heap {
	int[] heap;
	int size;
	
	public Heap(int size) {
		heap = new int[size];
		this.size = 0;
	}
	
	public void insert(int input) {
		heap[size] = input;
		bubbleUp(size);
		size++;
	}
	
	//Removes an element from the heap
	//should not be used often
	public boolean remove(int target) {
		int i = findIndex(target);
		size--;
		heap[i] = heap[size];
		bubbleUp(i);
		return false;
	}
	
	public int popMin() {
		int min = heap[0];
		size--;
		heap[0] = heap[size];
		bubbleDown(0);
		return min;
	}
	
	//Gets the list of all elements in the heap
	//no particular order
	public String getList() {
		String result = "";
		for (int i = 0; i < size; i++) {
			result += Integer.toString(heap[i]);
			result += " ";
		}
		result += "\n";
		return result;
	}
	
	//Gets the index of a given value
	private int findIndex(int target) {
		for (int i = 0; i < size; i++) {
			if (heap[i] == target) return i;
		}
		return -1;
	}
	
	private void bubbleUp(int i) {
		int temp;
		while (i > 0) {
			int parent = (i - 1) / 2;
			if (heap[i] >= heap[parent]) break;
			temp = heap[i];
			heap[i] = heap[parent];
			heap[parent] = temp;
			i = parent;
		}
	}
	
	private void bubbleDown(int i) {
		int temp;
		while (true) {
			int child = 2 * i + 1;
			if (child >= size) break;
			if (child + 1 < size && heap[child + 1] < heap[child]) child++;
			if (heap[i] <= heap[child])	break;
			temp = heap[i];
			heap[i] = heap[child];
			heap[child] = temp;
			i = child;
		}
	}
	
	//Interactive loop to let the user use the functions of the heap
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Heap heap = new Heap(100);
		int input = 99;
		
		while (input != 0) {
			System.out.println("0-Exit\t1-Insert\t2-Get Min\t3-Remove\t4-Print List\n");
			input = Integer.parseInt(scanner.nextLine());
			switch(input) {
				case 0:
					scanner.close();
					return;
				case 1:
					System.out.println("Insert the value to be added\n");
					input = Integer.parseInt(scanner.nextLine());
					heap.insert(input);
					input = 1;
					break;
				case 2:
					input = heap.popMin();
					System.out.println("The minimum value is " + Integer.toString(input) + "\n");
					input = 2;
					break;
				case 3:
					System.out.println("Insert the value to be removed\n");
					input = Integer.parseInt(scanner.nextLine());
					heap.remove(input);
					input = 3;
					break;
				case 4:
					System.out.println(heap.getList());
					break;
			}
		}
	}
}