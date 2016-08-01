package synchronization.condition;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2016-08-01 23:36
 */
public class FileMock {

    private String[] content;
    private int index;

    public FileMock(int size, int length) {
        content = new String[size];
        for (int i = 0; i < size; i++) {
            StringBuilder builder = new StringBuilder(length);
            for (int j = 0; j < length; j++) {
                int indice = (int) (Math.random() * 255);
                builder.append((char) indice);
            }
            content[i] = builder.toString();
        }
    }


    public boolean hasMoreLines() {
        return index < content.length;
    }

    public String getLines(){
        if(this.hasMoreLines()){
            System.out.println("Mock: "+(content.length-index));
            return content[index++];
        }
        return null;
    }

}
