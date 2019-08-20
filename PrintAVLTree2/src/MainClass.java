
public class MainClass {
	public static void main(String[] args) {
		String[] inputs = {
				"CBDAE",
				"DACBEFMLGHJK",
				"JABCDEFISN",
				"PWFKSALGJALSJV",
				"ABCDEFGHIJKLMNOPQR",
				"591043",
				"ABCDEFGHIJKLMNOPQRSTUVWXYZ"
				
		};
		for (int k=0; k<inputs.length;k++) {
			MyAVLTreeMap<String,Integer> mytree = new MyAVLTreeMap<String,Integer>();
		// this code populates your tree
		 	System.out.println("TREE #" + (k+1) +  " INPUTS: "+ inputs[k]);
		 	System.out.println("");

			for (int i =0 ; i< inputs[k].length(); i++) {
				mytree.put(inputs[k].substring(i,i+1), 1);
			}

		// System.out.println("Input of " + inputs[k]);
		 // this line of code call the printTree method you are to write
			mytree.printTreeRec(mytree.myPos, 0, mytree.daheight/2);
		 	mytree.printTree();
			System.out.println();
		  
		  
		}
		
	}
}



