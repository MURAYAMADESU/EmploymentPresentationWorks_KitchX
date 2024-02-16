package communal;

public class StringAlignment {

	public StringAlignment() {
		// コンストラクター
	}
	
	public static String AlignmentTitle(String data) {
		if(data.length() >= 44) {
		StringBuffer tmp = new StringBuffer();
		tmp.append(data.substring(0, 44));
		tmp.append("...");
		return tmp.toString();
		} else {
			return data;
		}
	}
	
	public static String AlignmentBigTitle(String data) {
		if(data.length() >= 60) {
		StringBuffer tmp = new StringBuffer();
		tmp.append(data.substring(0, 60));
		tmp.append("...");
		return tmp.toString();
		} else {
			return data;
		}
	}
	
	public static String AlignmentSmallTitle(String data) {
		if(data.length() >= 17) {
		StringBuffer tmp = new StringBuffer();
		tmp.append(data.substring(0, 17));
		tmp.append("...");
		return tmp.toString();
		} else {
			return data;
		}
	}
	
	public static String AlignmentMaterial(String data) {
		if(data.length() >= 65) {
		StringBuffer tmp = new StringBuffer();
		tmp.append(data.substring(0, 65));
		tmp.append("...");
		return tmp.toString();
		} else {
			return data;
		}
	}
	
	public static String AlignmentBigMaterial(String data) {
		if(data.length() >= 100) {
		StringBuffer tmp = new StringBuffer();
		tmp.append(data.substring(0, 100));
		tmp.append("...");
		return tmp.toString();
		} else {
			return data;
		}
	}
	
	public static String AlignmentIntroductoryEesay(String data) {
		if(data.length() >= 120) {
		StringBuffer tmp = new StringBuffer();
		tmp.append(data.substring(0, 120));
		tmp.append("...");
		return tmp.toString();
		} else {
			return data;
		}
	}
	
	public static String AlignmentBigIntroductoryEesay(String data) {
		if(data.length() >= 150) {
		StringBuffer tmp = new StringBuffer();
		tmp.append(data.substring(0, 150));
		tmp.append("...");
		return tmp.toString();
		} else {
			return data;
		}
	}

}
