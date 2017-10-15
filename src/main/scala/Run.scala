import java.io.File
import java.net.{URL, URLClassLoader}
import java.util

object Run {
  def main(args: Array[String]): Unit = {
    //  val argss = Array("prueba", "1")
    try {
      val classPath = args(0).substring(0, args(0).lastIndexOf("/"))
      var className = args(0).substring(args(0).lastIndexOf("/")+1, args(0).length)
      val urls = Array[URL](new URL("file://"+classPath))
      val classLoader = new URLClassLoader(urls)
      System.out.println("Loading class: " + args(0))
      val cls = classLoader.loadClass(className).newInstance().asInstanceOf[calculoI]
      // val cls = Class.forName(classNamePath).newInstance().asInstanceOf[calculoI]
      val parameters = util.Arrays.copyOfRange(args, 1, args.length)
      //System.out.println("Passing parameters:" + util.Arrays.toString(parameters))
      cls.setParameters(parameters) //Pass all parameters without the first elements (Classpath)
      System.out.println("Running code...")
      cls.run
      //System.out.println(instance);
    } catch {
      case cnfe: ClassNotFoundException =>
        //Class not found return error...
        cnfe.printStackTrace()
      case e: Exception =>
        e.printStackTrace()
    }
  }
}
