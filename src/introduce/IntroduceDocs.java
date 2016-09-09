package introduce;
/**
 * @author Tamdroy
 * @deprecated my own summarize experience. 
 *
 *  */ 
public class IntroduceDocs {
	/*
	 * learn Github---> http://www.yangzhiping.com/tech/github.html
	 */
	
	/*
	 * GC
	 * 
	 * GC_CONCURRENT 当堆内存增长到一定程度时会触发。此时触发可以对堆中的没有用的对象及时进行回收，腾出空间供新的对象申请，避免进行不必要的增大堆内存的操作。
	 * GC_EXPLICIT 当程序中调用System.gc()方法触发。这个方法应避免出现在程序中调用。因为JVM有足够的能力来控制垃圾回收。
	 * GC_EXTERNAL_MALLOC 当Bitmap和NIO Direct ByteBuffer对象分配外部存储（机器内存，非Dalvik堆内存）触发。这个日志只有在2.3之前存在，从2.3系统开始，
	 * 垃圾回收进行了调整，前面的对象都会存储到Dalivik堆内存中。所以在2.3系统之后，你就再也不会看到这种信息了。
	 * GC_FOR_MALLOC 当堆内存已满，系统需要更多内存的时候触发。这条日志出现后意味着JVM要暂停你的程序进行垃圾回收操作。
	 * GC_HPROF_DUMP_HEAP 当创建一个内存分析文件HPROF时触发。
	 * */
	
	
	/*
	 * Dex文件方法数超过了最大值65536
	 * 
	 * 1.插件化；
	 * 2.分割Dex,多工程: 把所需要的.class文件或者是Jar文件和一些源码一起编译生成一个Jar文件，
	 *   然后使用Android SDK提供的dx工具把Jar文件转成Dex文件；
	 * 3. 使用Google的android-support-multidex.jar包。
	 */
	
	/*
	 * Dialog:
	 * 
	 * dialogFragment 在旋转屏幕的时候可以自动做数据保存。
	 * */
	
	/*
	 * Fragment:
	 * 
	 * 
	 * */
	
	/*
	 *RAM（random access memory）随机存取存储器,即内存。一般Java在内存分配时会涉及到以下区域：
	 *
	 *内存优化from-http://blog.csdn.net/a396901990/article/details/38904543
	 *寄存器（Registers）：速度最快的存储场所，因为寄存器位于处理器内部，我们在程序中无法控制
	 *栈（Stack）：存放基本类型的数据和对象的引用，但对象本身不存放在栈中，而是存放在堆中
	 *堆（Heap）：堆内存用来存放由new创建的对象和数组。在堆中分配的内存，由Java虚拟机的自动垃圾回收器（GC）来管理。
	 *静态域（static field）：  静态存储区域就是指在固定的位置存放应用程序运行时一直存在的数据，Java在内存中专门划
	 *分了一个静态存储区域来管理一些特殊的数据变量如静态的数据变量
	 *常量池（constant pool）：虚拟机必须为每个被装载的类型维护一个常量池。常量池就是该类型所用到常量的一个有序集
	 *和，包括直接常量（string,integer和floating point常量）和对其他类型，字段和方法的符号引用。
	 *非RAM存储：硬盘等永久存储空间
	 */
	
	 /*
	  * Adapter:
	  * 
	  * @Override  
	  * public View getView(int position, View convertView, ViewGroup parent) {  
	        ViewHolder vHolder = null;  
	    	if (convertView == null) {  
		        convertView = inflater.inflate(..., null);   
		        vHolder = new ViewHolder();  
		        vHolder.img= (ImageView) convertView.findViewById(...);  
		        vHolder.tv= (TextView) convertView.findViewById(...);         	
	        	convertView.setTag(vHolder);  
	    	} else {  	       
	        	vHolder = (ViewHolder) convertView.getTag();  
    		}  
	    vHolder.img.setImageBitmap(...);  
	    vHolder.tv.setText(...);  
	    return convertView;  
	* }  
	* */
}
