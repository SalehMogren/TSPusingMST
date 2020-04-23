package Code;

public class Weight implements Comparable<Weight> {
        public int u,v;
        public double weight;

        public Weight(int u, int v, double weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }

        @Override
        public int compareTo(Weight other) {
            if (this.weight > other.weight)
                return 1;
            else if (this.weight < other.weight)
                return -1;
            return 0;
        }

		@Override
		public String toString() {
			return "Weight [=" + u + ", v=" + v + ", weight=" + weight + "]";
		}
    }
