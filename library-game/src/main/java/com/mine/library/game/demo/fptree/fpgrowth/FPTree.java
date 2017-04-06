package com.mine.library.game.demo.fptree.fpgrowth;//package com.cloverm.test.fptree.fpgrowth;
//
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.LinkedList;
//import java.util.List;
//
//import org.apache.log4j.Level;
//import org.apache.log4j.Logger;
//import org.apache.spark.SparkConf;
//import org.apache.spark.api.java.JavaPairRDD;
//import org.apache.spark.api.java.JavaRDD;
//import org.apache.spark.api.java.JavaSparkContext;
//import org.apache.spark.api.java.function.Function;
//import org.apache.spark.api.java.function.Function2;
//import org.apache.spark.api.java.function.PairFlatMapFunction;
//import org.apache.spark.api.java.function.PairFunction;
//
//import scala.Tuple2;
//public class FPTree {
//	public static int SUPPORT_DEGREE = 4;//the support of FPGrowth algorithm
//	public static String SEPARATOR = " ";//line separator
//	
//	public static void main(String[] args)
//	{
//		Logger.getLogger("org.apache.spark").setLevel(Level.OFF);
//		args = new String[]{"hdfs://master:9000/data/input/wordcounts.txt", "hdfs://master:9000/data/output"};
//		if(args.length != 2)
//		{
//			System.err.println("USage:<Datapath> <Output>");
//			System.exit(1);
//		}
//		
//		SparkConf sparkConf = new SparkConf().setAppName("frequent parttern growth").setMaster("local[4]");
//		JavaSparkContext ctx = new JavaSparkContext(sparkConf);
//		
//		//load the transactions data.
//		JavaRDD<String> lines = ctx.textFile(args[0], 1)
//		//remove the ID of transaction.
//		.map(new Function<String, String>()
//		{
//			private static final long serialVersionUID = -692074104680411557L;
//
//			public String call(String arg0) throws Exception
//			{
//				return arg0.substring(arg0.indexOf(" ") + 1).trim();
//			}
//		});
//		
//		JavaPairRDD<String, Integer> transactions = constructTransactions(lines);
//		//run FPGrowth algorithm
//		FPGrowth(transactions, null, ctx);
//		//close sparkContext
//		ctx.close();
//	}
//	
//	public static JavaPairRDD<String, Integer> constructTransactions(JavaRDD<String> lines)
//	{
//		JavaPairRDD<String, Integer> transactions = lines
//				//convert lines to <key,value>(or <line,1>) pairs.
//				.mapToPair(new PairFunction<String, String, Integer>()
//				{
//					private static final long serialVersionUID = 5915574436834522347L;
//
//					public Tuple2<String, Integer> call(String arg0) throws Exception
//					{
//						return new Tuple2<String, Integer>(arg0, 1);
//					}
//				})
//				//combine the same translations.
//				.reduceByKey(new Function2<Integer, Integer, Integer>()
//				{
//					private static final long serialVersionUID = -8075378913994858824L;
//
//					public Integer call(Integer arg0, Integer arg1) throws Exception
//					{
//						return arg0 + arg1;
//					}
//				});
//		return transactions;
//	}
//	/**
//	 * @param transactions 
//	 * @param postPattern  
//	 */
//	public static void FPGrowth(JavaPairRDD<String, Integer> transactions, final List<String> postPattern, JavaSparkContext ctx)
//	{
//		//construct headTable
//		JavaRDD<TNode> headTable = bulidHeadTable(transactions);
//		List<TNode> headlist = headTable.collect();
//		//construct FPTree
//		TNode tree = bulidFPTree(headlist, transactions);
//		//when the FPTree is empty, then exit the excursion
//		if(tree.getChildren() == null || tree.getChildren().size() == 0)
//		{
//			return;
//		}
//		//output the frequent itemSet
//		if(postPattern != null)
//		{
//			for(TNode head : headlist)
//			{
//				System.out.print(head.getCount() + " " + head.getItemName());
//				for(String item : postPattern)
//				{
//					System.out.print(" " + item);
//				}
//				System.out.println();
//			}
////			headTable.foreach(new VoidFunction<TNode>()
////			{
////				public void call(TNode head) throws Exception
////				{
////					System.out.println(head.getCount() + " " + head.getItemName());
////					for(String item : postPattern)
////					{
////						System.out.println(" " + item);
////					}
////				}
////			});
//		}
//		//traverse each item of headTable
//		for(TNode head : headlist)
//		{
//			List<String> newPostPattern = new ArrayList<String>();
//			newPostPattern.add(head.getItemName());
//			if(postPattern != null)
//			{
//				newPostPattern.addAll(postPattern);
//			}
//			//create new transactions
//			List<String> newTransactionsList = new ArrayList<String>();
//			TNode nextNode = head.getNext();
//			while(nextNode != null)
//			{
//				int count = head.getCount();
//				TNode parent = nextNode.getParent();
//				String tlines = "";
//				while(parent.getItemName() != null)
//				{
//					tlines += parent.getItemName() + " ";
//					parent = parent.getParent();
//				}
//				while((count--) > 0 && !tlines.equals(""))
//				{
//					newTransactionsList.add(tlines);
//				}
//				nextNode = nextNode.getNext();
//			}
//			JavaPairRDD<String, Integer> newTransactions = constructTransactions(ctx.parallelize(newTransactionsList));
//			FPGrowth(newTransactions, newPostPattern, ctx);
//		}
//	}
//	
//	/**
//	 * construct FPTree
//	 * @return the root of FPTree
//	 */
//	public static TNode bulidFPTree(List<TNode> headTable, JavaPairRDD<String, Integer> transactions)
//	{
//		//create the root node of FPTree
//		final TNode rootNode = new TNode();
//		
//		final List<TNode> headItems = headTable;
//		//convert to transactions which ordered by count DESC and items satisfy the minimum support_degree 
//		JavaPairRDD<LinkedList<String>, Integer> transactionsDesc = transactions.mapToPair(new PairFunction<Tuple2<String,Integer>, LinkedList<String>, Integer>()
//		{
//			private static final long serialVersionUID = 4787405828748201473L;
//
//			public Tuple2<LinkedList<String>, Integer> call(Tuple2<String, Integer> t)
//					throws Exception
//			{
//				LinkedList<String> descItems = new LinkedList<String>();
//				List<String> items = Arrays.asList(t._1.split(SEPARATOR));
//				for(TNode node : headItems)
//				{
//					String headName = node.getItemName();
//					if(items.contains(headName))
//					{
//						descItems.add(headName);
//					}
//				}
//				return new Tuple2<LinkedList<String>, Integer>(descItems, t._2);
//			}
//		})
//		.filter(new Function<Tuple2<LinkedList<String>,Integer>, Boolean>()
//		{
//			private static final long serialVersionUID = -8157084572151575538L;
//
//			public Boolean call(Tuple2<LinkedList<String>, Integer> v1) throws Exception
//			{
//				return v1._1.size() > 0;
//			}
//		});
//		List<Tuple2<LinkedList<String>, Integer>> lines = transactionsDesc.collect();
//		//add each transaction to FPTree
//		for(Tuple2<LinkedList<String>, Integer> t : lines)
//		{
//			LinkedList<String> itemsDesc = t._1;//items to be added to FPTree
//			int count = t._2;//how many times itemsDesc to be added to FPTree
//			//find out the root node which add List<String> as subtree
//			TNode subtreeRoot = rootNode;
//			if(subtreeRoot.getChildren().size() != 0)
//			{
//				TNode tempNode = subtreeRoot.findChildren(itemsDesc.peek());
//				while(!itemsDesc.isEmpty() && tempNode != null)
//				{
//					tempNode.countIncrement(count);
//					subtreeRoot = tempNode;
//					itemsDesc.poll();
//					tempNode = subtreeRoot.findChildren(itemsDesc.peek());
//				}
//			}
//			//add the left items of List<String> to FPTree
//			addSubTree(headItems, subtreeRoot, itemsDesc, count);
//		}
//		
////		transactionsDesc.foreach(new VoidFunction<Tuple2<LinkedList<String>,Integer>>()
////		{
////			private static final long serialVersionUID = 8054620367976985036L;
////
////			public void call(Tuple2<LinkedList<String>, Integer> t) throws Exception
////			{
////				LinkedList<String> itemsDesc = t._1;//items to be added to FPTree
////				int count = t._2;//how many times itemsDesc to be added to FPTree
////				//find out the root node which add List<String> as subtree
////				TNode subtreeRoot = rootNode;
////				if(subtreeRoot.getChildren().size() != 0)
////				{
////					TNode tempNode = subtreeRoot.findChildren(itemsDesc.peek());
////					while(!itemsDesc.isEmpty() && tempNode != null)
////					{
////						tempNode.countIncrement(count * 2);
////						subtreeRoot = tempNode;
////						itemsDesc.poll();
////						tempNode = subtreeRoot.findChildren(itemsDesc.peek());
////					}
////				}
////				//add the left items of List<String> to FPTree
////				addSubTree(headItems, subtreeRoot, itemsDesc, count);
////			}
////		});
//		return rootNode;
//	}
//	/**
//	 * 
//	 * @param headTable
//	 * @param subtreeRoot
//	 * @param itemsDesc
//	 * @param count
//	 */
//	public static void addSubTree(List<TNode> headItems, TNode subtreeRoot, LinkedList<String> itemsDesc, int count)
//	{
//		if(itemsDesc.size() > 0)
//		{
//			final TNode thisNode = new TNode(itemsDesc.pop(), count);//construct a new node
//			subtreeRoot.getChildren().add(thisNode);
//			thisNode.setParent(subtreeRoot);
//			//add thisNode to the relevant headTable node list
//			for(TNode t : headItems)
//			{
//				if(t.getItemName().equals(thisNode.getItemName()))
//				{
//					TNode lastNode = t;
//					while(lastNode.getNext() != null)
//					{
//						lastNode = lastNode.getNext();
//					}
//					lastNode.setNext(thisNode);
//				}
//			}
//			subtreeRoot = thisNode;//update thisNode as the current subtreeRoot
//			//add the left items in itemsDesc recursively
//			addSubTree(headItems, subtreeRoot, itemsDesc, count);
//		}
//	}
//	/**
//	 * construct the headTable of the format <count, itemName> descended.
//	 * @param transactions 
//	 * @return headTable
//	 */
//	public static JavaRDD<TNode> bulidHeadTable(JavaPairRDD<String, Integer> transactions)
//	{
//		JavaRDD<TNode> headtable = transactions.flatMapToPair(new PairFlatMapFunction<Tuple2<String,Integer>, String, Integer>()
//		{
//			private static final long serialVersionUID = -3654849959547730063L;
//
//			public Iterable<Tuple2<String, Integer>> call(Tuple2<String, Integer> arg0)
//					throws Exception
//			{
//				List<Tuple2<String, Integer>> t2list = new ArrayList<Tuple2<String, Integer>>(); 
//				String[] items = arg0._1.split(SEPARATOR);
//				int count = arg0._2;
//				for(String item : items)
//				{
//					t2list.add(new Tuple2<String, Integer>(item, count));
//				}
//				return t2list;
//			}
//		})
//		//combine the same items.
//		.reduceByKey(new Function2<Integer, Integer, Integer>()
//		{
//			private static final long serialVersionUID = 629605042999702574L;
//
//			public Integer call(Integer arg0, Integer arg1) throws Exception
//			{
//				return arg0 + arg1;
//			}
//		})
//		//convert <ietm,integer> to <integr,item> format.
//		.mapToPair(new PairFunction<Tuple2<String,Integer>, Integer, String>()
//		{
//			private static final long serialVersionUID = -7017909569876993192L;
//
//			public Tuple2<Integer, String> call(Tuple2<String, Integer> t)
//					throws Exception
//			{
//				return new Tuple2<Integer, String>(t._2, t._1);
//			}
//		})
//		//filter out items which satisfies the minimum support_degree.
//		.filter(new Function<Tuple2<Integer, String>, Boolean>()
//		{
//			private static final long serialVersionUID = -3643383589739281939L;
//
//			public Boolean call(Tuple2<Integer, String> v1) throws Exception
//			{
//				return v1._1 >= SUPPORT_DEGREE;
//			}
//		})
//		//sort items in descent.
//		.sortByKey(false)
//		//convert transactions to TNode.
//		.map(new Function<Tuple2<Integer,String>, TNode>()
//		{
//			private static final long serialVersionUID = 16629827688034851L;
//
//			public TNode call(Tuple2<Integer, String> v1) throws Exception
//			{
//				return new TNode(v1._2, v1._1);
//			}
//		});
//		return headtable;
//	}
//}
