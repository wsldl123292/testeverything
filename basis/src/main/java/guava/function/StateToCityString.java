package guava.function;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import guava.common.State;

/**
 * 作者: LDL
 * 功能说明:
 * 创建日期: 2015/6/8 15:18
 */
public class StateToCityString implements Function<State,String> {
    @Override
    public String apply(State input) {
        return Joiner.on(",").join(input.getMainCities());
    }
}
