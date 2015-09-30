import org.python.core.PySystemState;
import org.python.util.PythonInterpreter;

/**
 * 作者: LDL
 * 功能说明:
 * 创建日期: 2015/8/20 14:34
 */
public class CallPythonTest {
    public static void main(String[] args) {
        PySystemState pySystemState = new PySystemState();
        pySystemState.path.add("D:\\develop\\Java\\jython2.7.0\\Lib");
        //pySystemState.path.add("F:\\tools\\spark-1.4.1\\python\\lib");
        PythonInterpreter interpreter = new PythonInterpreter(null,pySystemState);
        interpreter.exec("print 'hello'");
        /*interpreter.exec("from numpy import array");
        interpreter.exec("from math import sqrt");
        interpreter.set("a",0);
        interpreter.exec(interpreter.compile("from pyspark.mllib.clustering import KMeans, KMeansModel\n" +
                "from numpy import array\n" +
                "from math import sqrt\n" +
                "data = sc.textFile(\"data/mllib/kmeans_data.txt\")\n" +
                "parsedData = data.map(lambda line: array([float(x) for x in line.split(' ')]))\n" +
                "clusters = KMeans.train(parsedData, 2, maxIterations=10,\n" +
                "        runs=10, initializationMode=\"random\")\n" +
                "def error(point):\n" +
                "    center = clusters.centers[clusters.predict(point)]\n" +
                "    return sqrt(sum([x**2 for x in (point - center)]))\n" +
                "\n" +
                "WSSSE = parsedData.map(lambda point: error(point)).reduce(lambda x, y: x + y)\n" +
                "print(\"Within Set Sum of Squared Error = \" + str(WSSSE))\n" +
                "clusters.save(sc, \"myModelPath\")\n" +
                "sameModel = KMeansModel.load(sc, \"myModelPath\")"));*/
        //System.out.println(interpreter.get("c",Integer.class));

    }
}
