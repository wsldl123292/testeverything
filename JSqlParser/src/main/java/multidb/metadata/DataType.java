package multidb.metadata;


public class DataType {
	public enum DataTypes {
		VARCHAR, INTEGER, BIGINT, FLOAT, DOUBLE, DATE, BLOB;
		public static DataTypes getType(String type) {
			return valueOf(type.toUpperCase());
		}
	}
}
