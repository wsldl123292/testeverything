package starter;

/**
 * 说明: 遍历语法树转换
 * 作者: LDL
 * 日期: 2016/6/12.
 */
public class ShortToUnicodeString extends ArrayInitBaseListener {

    /**
     * Translate { to "
     */
    @Override
    public void enterInit(ArrayInitParser.InitContext ctx) {
        System.out.print('"');
    }

    /**
     * Translate } to "
     */
    @Override
    public void exitInit(ArrayInitParser.InitContext ctx) {
        System.out.print('"');
    }

    /**
     * Translate integers to 4-digit hexadecimal strings prefixed with \\u
     */
    @Override
    public void enterValue(ArrayInitParser.ValueContext ctx) {
        // Assumes no nested array initializers
        int value = Integer.valueOf(ctx.INT().getText());
        System.out.printf("\\u%04x", value);
    }
}
