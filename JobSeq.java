package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class NodeJob{
	public String value;
	public NodeJob prequel;
	public String preqVal;
	public NodeJob next;
	public boolean check;
	
	public NodeJob (String v) {
		value =v;
		check = false;
	}
}

public class JobSeq {
	
	public ArrayList<NodeJob> arr = new ArrayList<NodeJob>();
	static NodeJob root;
	NodeJob tail;
	
	
	public void seqOrder(String s) throws Exception {
		if(s!=null) {
			String[] s1 = s.split("=>");
			String s2 =  s1[0].replaceAll("\\s","");
			NodeJob nd = new NodeJob(s2);
			
			if(root==null) {
				root = nd;
				tail = nd;
			}
			else if(search(s2)==null) {
				tail.next = nd;
				tail = nd;
			}
			if(s1.length>1) {
				String s3 = s1[1].replaceAll("\\s","");
				if(s2.equals(s3) ) {
					throw new Exception("Self Dependence"); 
				}
				tail.preqVal = s3;
			}
		}
	}
	
	public void attachDependency() {
		if(root != null) {
			NodeJob n = root;
			while(n != null )
			{
				if(n.preqVal!=null) {
					NodeJob dependence = search(n.preqVal);	
					n.prequel = dependence;
				}
				n = n.next;
			}
			
		}
		
	}
	
	public NodeJob search(String val) {
		
		NodeJob n = root;
		
		while(n != null && !n.value.equals(val) ) {
			n = n.next;
		}
		
		if(n== null) {
			return null;
		}
		
		return n;
	}
	
	public void orderTrav(NodeJob n) throws Exception {
		
			while(n != null )
			{
					if(n.prequel!=null)
					{
						orderTrav(n.prequel);
					}
					
					if(!n.check) {
						System.out.print(n.value+" ");
						n.check = true;
					}
					else
					{
						return;
					}	
				n = n.next;
			}
	}
	
	public void traverse() {
		if(root != null) {
			NodeJob n = root;
			while(n != null )
			{
				System.out.print(n.value+" ");
				
				if(n.preqVal != null) {
					System.out.print("=>"+n.preqVal+" ");
				}
				n = n.next;
			}
		}
	}
	
	
	public static void main(String args[]) throws Exception {
		
		int n=0;
		Scanner scan = new Scanner(System.in);
		boolean badInput = true;
		JobSeq sq = new JobSeq();
		
		System.out.println("You have two options to input Job order sequence: \n1) Via Text file\n2) Via String Input");
	    do {
	        try {
	        	System.out.println("Please input selection option 1 or 2: ");
	    		n = scan.nextInt();
	    		if(n==1 || n==2)
	    			badInput = false;
	    		
	        	} catch (InputMismatchException e) 
	        	{
	        	badInput = true;
	        	}
	    } while (badInput);

	    /// Taking in File Path for input
		if(n==1) {
			badInput = true;
			 //"C:\\Users\\Komal\\Desktop\\Matlab\\jobSeq.txt"
			 Scanner sc;
			 do {
				 try 
				 {
					System.out.println("Please Enter the file Path: ");
					String fileName = scan.nextLine();
					File file =  new File(fileName); 
					 
					sc = new Scanner(file);
					while (sc.hasNextLine()) 
					{
						String str = sc.nextLine();
						/// adding Jobs to the sequence
						sq.seqOrder(str); 
						
					}
					badInput = false;
				 } 
				 catch (FileNotFoundException e) 
				 {
					 System.out.println("File not found, please input the correct file path");
					 badInput = true;
				 } 
			 }while(badInput);
		}
		/// Taking in user String input
		if(n==2) {
			badInput = true;
			
			do { 
				System.out.println("Please Enter the Job String in the following sequence: \n(a => b)\nTo exit press '0' ");
				String s1 = scan.next();
					
				System.out.println("");
					
				if(s1.equals("0"))
					badInput = false;
				else
					/// adding Jobs to the sequence
					sq.seqOrder(s1);
			 }while(badInput);
			
		}
		
		/// displaying Job sequence
		System.out.println("The Job Sequence is as below:");
		sq.traverse();
		
		/// Attaching dependencies for the job in the sequence 
		sq.attachDependency();
		System.out.println(" \n");
		System.out.println("A possible solution is as follows:");
			try{
			/// computing Job sequence path based on dependencies
			sq.orderTrav(root);				
			}
			catch(StackOverflowError e){
			   throw new Exception("Job cant have circular dependencies"); 
			} 
		}

}
