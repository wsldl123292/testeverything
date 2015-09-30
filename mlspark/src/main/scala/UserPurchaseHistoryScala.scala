import org.apache.spark.SparkContext

/**
 * 作者: LDL
 * 说明:
 * 时间: 2015/9/13 15:37
 */
object UserPurchaseHistoryScala {
    def main(args: Array[String]) {
        System.setProperty( "hadoop.home.dir", "F:\\data\\hadoop-common-2.2.0-bin-master" )
        val sc = new SparkContext("local[2]", "First Spark App")

        // we take the raw data in CSV format and convert it into a set of records of the form (user, product, price)
        val data = sc.textFile("F:\\github\\everythingtest\\mlspark\\src\\main\\resources\\UserPurchaseHistory.csv")
                .map(line => line.split(","))
                .map(purchaseRecord => (purchaseRecord(0), purchaseRecord(1), purchaseRecord(2)))

        // let's count the number of purchases
        val numPurchases = data.count()

        // let's count how many unique users made purchases
        val uniqueUsers = data.map { case (user, product, price) => user }.distinct().count()

        // let's sum up our total revenue
        val totalRevenue = data.map { case (user, product, price) => price.toDouble }.sum()

        // let's find our most popular product
        val productsByPopularity = data
                .map { case (user, product, price) => (product, 1) }
                .reduceByKey(_ + _)
                .collect()
                .sortBy(-_._2)
        val mostPopular = productsByPopularity(0)

        // finally, print everything out
        println("Total purchases: " + numPurchases)
        println("Unique users: " + uniqueUsers)
        println("Total revenue: " + totalRevenue)
        println("Most popular product: %s with %d purchases".format(mostPopular._1, mostPopular._2))

        sc.stop()
    }
}
