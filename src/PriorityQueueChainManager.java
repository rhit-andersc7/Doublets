import java.util.PriorityQueue;

public class PriorityQueueChainManager extends ChainManager {

	private PriorityQueue<Entry> queue;
	private String end;

	public PriorityQueueChainManager(String end) {
		this.queue = new PriorityQueue<>();
		this.end = end;
	}

	@Override
	public void add(Chain chain) {
		this.queue.add(new Entry(chain));
		this.updateMax(this.queue.size());
	}

	@Override
	public Chain next() {
		if (this.queue.size() != 0) {
			this.incrementNumNexts();
			return this.queue.remove().chain;
		}
		return null;
	}

	@Override
	public boolean isEmpty() {
		return this.queue.size() == 0;
	}

	private class Entry implements Comparable {
		public Chain chain;
		int priority;

		public Entry(Chain chain) {
			this.chain = chain;
			priority = chain.length();
			String word = chain.getLast();
			for (int c = 0; c < word.length(); c++) {
				this.priority += (word.charAt(c) != end.charAt(c)) ? 1 : 0;
			}
		}

		@Override
		public int compareTo(Object o) {
			return (this.priority - ((Entry) o).priority);
		}
	}
}
