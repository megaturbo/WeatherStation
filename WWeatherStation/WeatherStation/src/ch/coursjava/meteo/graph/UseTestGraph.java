package ch.coursjava.meteo.graph;

import org.jfree.ui.RefineryUtilities;

public class UseTestGraph {
	 public static void main(final String[] args) {

	        final TestGraph testGraph = new TestGraph("Test graph");
	        testGraph.pack();
	        RefineryUtilities.centerFrameOnScreen(testGraph);
	        testGraph.setVisible(true);

	    }
}