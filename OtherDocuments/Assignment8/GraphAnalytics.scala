//Solutions for Assignment# 8 regarding Graph Analytics

//1. What are the packages in Spark that need to be imported to use graph analytics.
import org.apache.spark._
import org.apache.spark.graphx._
import org.apache.spark.rdd.RDD

//2. What are the basic elements in building a graph? a) Edge b) Graph c) Edge and Graph
Answer: Edge and Graph

//3. Using the below construct the vertexRdd and edgeRdd val vertexArray = Array( (1L, ("A", 18)), (2L, ("B", 17)), (3L, ("C", 45)), (4L, ("D", 32)), (5L, ("E", 45)), (6L, ("F", 40)) ) val edgeArray = Array( Edge(2L, 1L, 8), Edge(2L, 4L, 3), Edge(3L, 2L, 5), Edge(3L, 6L, 4), Edge(4L, 1L, 2), Edge(5L, 2L, 3), Edge(5L, 3L, 9), Edge(5L, 6L, 4) )

scala> val vertexArray=Array((1L,("A",18)),(2L,("B",17)),(3L,("C",45)),(4L,("D",32)),(5L,("E",45)),(6L,("F",40)))
vertexArray: Array[(Long, (String, Int))] = Array((1,(A,18)), (2,(B,17)), (3,(C,45)), (4,(D,32)), (5,(E,45)), (6,(F,40)))

scala> val edgeArray = Array(Edge(2L, 1L, 8),Edge(2L, 4L, 3),Edge(3L, 2L, 5),Edge(3L, 6L, 4),Edge(4L, 1L, 2),Edge(5L, 2L, 3),Edge(5L,3L, 9),Edge(5L, 6L, 4))
edgeArray: Array[org.apache.spark.graphx.Edge[Int]] = Array(Edge(2,1,8), Edge(2,4,3), Edge(3,2,5), Edge(3,6,4), Edge(4,1,2), Edge(5,2,3), Edge(5,3,9), Edge(5,6,4))
