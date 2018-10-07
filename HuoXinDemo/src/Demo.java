
public class Demo {

	/**
	 * 
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		ModifyOffset offset = ModifyOffset.getInstance(Demo.class.getResourceAsStream("axisoffset.dat"));
		PointDouble newdouble1 = offset.s2c(new PointDouble(116.29042787, 40.04337062));
		System.err.println(newdouble1);
	}

}
